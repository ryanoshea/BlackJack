package blackjack;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Ryan O'Shea
 */
public class Deck {
		
	Card[] deck = new Card[52];
	boolean cardStatus[] = new boolean[52]; //boolean value that keeps track of whether each card is in the deck or not (true = in; false = out)
	
	Deck() {
		int i = 0;
		for(int suit = 0; suit < 4; suit++) {
			for(int num = 0; num < 13; num++) {
				deck[i] = new Card(suit, num);
				i++;
			}
		}
		
		for(int k = 0; k < 52; k++) {
			cardStatus[k] = true;
		}
	}

	void shuffle() {
		Collections.shuffle(Arrays.asList(deck));
	}
}
