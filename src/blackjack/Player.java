/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author ryan
 */
public abstract class Player {
	public boolean bust;
	public ArrayList<Card> hand = new ArrayList();
	public int handValue;
	public boolean hasBlackjack;
	public boolean isDealer;
	public String name;
	public boolean stay = false;
	public double bet;

	public Player() {
	}

	public void acceptHand(Card card1, Card card2) {
		hand.add(card1);
		hand.add(card2);
	}

	void acceptHit(Card card) {
		hand.add(card);
	}

	public abstract void decide();

	int evaluate() {
		int numAces = 0;
		
		int handValue = 0;
		
		
		for (int i = 0; i < hand.size(); i++) {
			
			switch(hand.get(i).num) {
				
				case 0: handValue += 2;
					break;
				case 1: handValue += 3;
					break;
				case 2: handValue += 4;
					break;
				case 3: handValue += 5;
					break;
				case 4: handValue += 6;
					break;
				case 5: handValue += 7;
					break;
				case 6: handValue += 8;
					break;
				case 7: handValue += 9;
					break;
				case 8: handValue += 10;
					break;
				case 9: handValue += 10;	//jack
					break;
				case 10: handValue += 10;	//queen
					break;
				case 11: handValue += 10;	//king
					break;
				case 12: handValue += 11;	//ace
					numAces++;
					break;
				
			}
		}
		
		/*for (int k = 0; k < hand.size(); k++) {
			if (hand.get(k).num == 12) {
				numAces++;
			}
		}*/
		
		
		if (numAces != 0) { //Decides whether to use Aces as 11s or 1s
			
			int aceIdx = numAces;
			while (handValue > 21) {
				if (aceIdx == 0) {
					break;
				}
				handValue -= 10;
				aceIdx--;
			}
			
		}

		
		if (handValue > 21) {
			bust = true;
		} else if (	handValue == 21 
					&& hand.size() == 2
					&& hand.get(0).num != 8
					&& hand.get(1).num != 8) {
			hasBlackjack = true;
		}
		return handValue;
	}

	void getBet(double playerMoney) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		boolean inputValid = false;
		while(inputValid == false) {
			try {
				System.out.println("How much do you want to bet?");
				String input = reader.readLine();
				
				if (Double.parseDouble(input) <= playerMoney) {
					bet = Double.parseDouble(input);
					inputValid = true;
				} else if (Double.parseDouble(input) > playerMoney) {
					throw new NumberFormatException();
				}
				
			} catch (NumberFormatException ex) {
				//Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println("Please try again. Type a number not greater than your current balance.");
			} catch (IOException ex) {
				System.out.println("Please try again. Type a number.");
			}

		}
	}

	
}
