package blackjack;

/**
 * @author Ryan O'Shea
 */

import java.util.Random;

public class Game {

	Deck deck = new Deck();
	Player player = new User(false); 
	Player dealer = new User(true);
	String playerMove, dealerMove;
	public String winner = "";
	
	public void play() {
		deck.shuffle();
		
		//player.getBet();
		
		deal();
		/* Print out complete game status */
		dump();	
		
		while(!(player.stay && dealer.stay) && !player.bust && !dealer.bust && !dealer.hasBlackjack && !player.hasBlackjack) {
			if (player.stay == false) {
				player.decide();
			} 
			if (dealer.stay == false && !dealer.bust) {
//				if (player.handValue > 17) {
//					hit(dealer);
//				}
//				else {
					dealer.decide();
//				}
			}
			if (player.stay == false && !dealer.bust) {
				hit(player);
			} 
			if (dealer.stay == false && !player.bust) {
				hit(dealer);
			}
			if (dealer.hasBlackjack || (dealer.hasBlackjack && player.hasBlackjack)) {
				break;
			} 
			if (dealer.evaluate() > 21) {
				dealer.bust = true;
			}
			if (player.evaluate() > 21) {
				player.bust = true;
			} 
			if (player.hand.size() == 5 && player.evaluate() <= 21 && dealer.evaluate() <= 21) {
				dump();
				break;
			}
			dump();
		}
		
		endGame();
	}

	void deal() {
		Random rnd = new Random();
		
		int randomCard1 = rnd.nextInt(52);
				
		int randomCard2 = randomCard1; 
		while(randomCard2 == randomCard1) { // ensures that all random cards are different cards
			randomCard2 = rnd.nextInt(52);
		}
		
		int randomCard3 = randomCard1;
		while(randomCard3 == randomCard1 || randomCard3 == randomCard2) { // ensures that all random cards are different cards
			randomCard3 = rnd.nextInt(52);
		}
		
		int randomCard4 = randomCard1;
		while(randomCard4 == randomCard1 || randomCard4 == randomCard2 || randomCard4 == randomCard3) { // ensures that all random cards are different cards
			randomCard4 = rnd.nextInt(52);
		}		
		
		deck.cardStatus[randomCard1] = false;
		deck.cardStatus[randomCard2] = false;
		deck.cardStatus[randomCard1] = false;
		deck.cardStatus[randomCard2] = false;
		
		dealer.acceptHand(deck.deck[randomCard1], deck.deck[randomCard2]);
		player.acceptHand(deck.deck[randomCard3], deck.deck[randomCard4]);
	}

	/*private void askPlayersForMove() {
		if (player.stay = false) {
			player.decide();
		}
		if (dealer.stay = false) {
			dealer.decide();
		}
	}*/

	private void hit(Player givenPlayer) {
		Random rnd = new Random();
		int randomCard = rnd.nextInt(52);
		boolean goodCard = false;
		while(goodCard == false) {
			if (deck.cardStatus[randomCard] == false) {
				goodCard = false;
			}

			if (deck.cardStatus[randomCard] == true) {
				goodCard = true;
				givenPlayer.acceptHit(deck.deck[randomCard]);
			} else {
				randomCard = rnd.nextInt(52);
			}
		}
	}
	
	private void dump() {
		System.out.println("\nCurrent Game State:");
		
		System.out.println("\nDealer's hand:");
		for (int i = 0; i < dealer.hand.size(); i++) {
			if (i != 0) {
				System.out.print(dealer.hand.get(i).toString());
			} else if (i == 0) {
				System.out.print("Face down card");
			}
			if(!(i == (dealer.hand.size() - 1) )) {
				System.out.print(" + ");
			}
		}
		System.out.println();
		//System.out.println("(" + dealer.evaluate() + ")");
		
		System.out.println("\nYour hand:");
		for (int i = 0; i < player.hand.size(); i++) {
			System.out.print(player.hand.get(i).toString());
			if(!(i == (player.hand.size() - 1) )) {
				System.out.print(" + ");
			}
		}
		System.out.println();
		//System.out.println("(" + player.evaluate() + ")");
		
	}
	
	public void endGame() {
		System.out.println("\n\n");
		
		if(player.hand.size() == 5 && player.bust == false) {
			System.out.println("You have gotten 5 cards without busting. You Win!");
			winner = "player";
		} else if (player.hasBlackjack && dealer.hasBlackjack) {
			System.out.println("Both players have Blackjack! Dealer Wins!");
			winner = "dealer";
		} else if (dealer.hasBlackjack && !player.hasBlackjack) {
			System.out.println("Dealer has Blackjack. Dealer Wins!");
			winner = "dealer";
		} else if (player.bust) {
			System.out.println("You are over 21. Dealer Wins!");
			winner = "dealer";
		} else if (dealer.bust) {
			System.out.println("Dealer is over 21. You Win!");
			winner = "player";
		} else if (dealer.handValue == player.handValue) {
			System.out.println("Both players have the same score. Dealer Wins!");
			winner = "dealer";
		} else if (dealer.handValue > player.handValue) {
			System.out.println("Dealer has a higher score than you. Dealer Wins!");
			winner = "dealer";
		} else if (player.handValue > dealer.handValue) {
			System.out.println("You have a higher score than Dealer. You Win!");
			winner = "player";
		}
	}
	
}
