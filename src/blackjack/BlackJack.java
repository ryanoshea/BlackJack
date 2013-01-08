package blackjack;

/**
 * @author Ryan O'Shea
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class BlackJack {

	public static void main(String[] args) {
		boolean gameOver = false;
		double playerMoney = 1000;
		
		while(gameOver == false) {
			
			Game game = new Game();
			if(playerMoney > 0) {
				DecimalFormat formatter = new DecimalFormat("#.00");
				System.out.println("You have $" + formatter.format(playerMoney) + " left.");
			} else if (playerMoney <= 0) {
				System.out.println("You are out of money. Go home to your wife, and pray she doesn't divorce you.");
				break;
			}
			game.player.getBet(playerMoney);
			game.play();
			if (game.winner.equals("player")) {
				playerMoney += game.player.bet;
			} else if (game.winner.equals("dealer")) {
				playerMoney -= game.player.bet;
			}
			
			if(playerMoney > 0) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				DecimalFormat formatter = new DecimalFormat("#.00");
				boolean inputValid = false;

				while(inputValid == false) {
					try {
						System.out.println("You have $" + formatter.format(playerMoney) + " left. Do you want to keep playing? (yes or no)");
						String input = reader.readLine();
						if ( !(input.toLowerCase().equals("yes") || input.toLowerCase().equals("no")) ) {
							throw new IOException();
						} else if (input.toLowerCase().equals("yes")) {
							gameOver = false;
							inputValid = true;
						} else if (input.toLowerCase().equals("no")) {
							gameOver = true;
							inputValid = true;
						}
					} catch (IOException ex) {
						//Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
						System.out.println("Please try again. Type \"yes\" or \"no\".");
					}

				}
			} else if (playerMoney <= 0) {
				System.out.println("You are out of money. Go home to your wife, and pray she doesn't divorce you.");
				break;
			}
		}
	}
}
