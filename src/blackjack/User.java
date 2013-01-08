package blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 * @author Ryan O'Shea
 */
public class User extends Player  {
	
	User(boolean isDealer) {
		this.isDealer = isDealer;
		if (isDealer) {
			name = "Dealer";
		} else if (!isDealer) {
			name = "Player";
		}
	}
	
	@Override
	public void decide() {
		handValue = evaluate();
		if (isDealer == true) {
			dealerDecide(evaluate());
		} else {
			playerDecide(evaluate());
		}
	}

	private void dealerDecide(int value) {
		String move = "";
		
		if (value >= 17) {
			stay = true;
			System.out.println("\nDealer has decided to stay.");
		} else {
			System.out.println("\nDealer has decided to hit.");
		}
	}

	private void playerDecide(int value) {
		
		System.out.println("Your hand is worth " + value);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		boolean inputValid = false;
        while(inputValid == false) {
			try {
				System.out.println("Do you want to hit or stay?");
				String input = reader.readLine();
				if ( !(input.toLowerCase().equals("hit") || input.toLowerCase().equals("stay")) ) {
					throw new IOException();
				} else if (input.toLowerCase().equals("hit")) {
					stay = false;
					inputValid = true;
				} else if (input.toLowerCase().equals("stay")) {
					stay = true;
					inputValid = true;
				}
			} catch (IOException ex) {
				//Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
				System.out.println("Please try again. Type \"hit\" or \"stay\".");
			}
		}
	}

	
	
}