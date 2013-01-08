package blackjack;

/**
 * @author Ryan O'Shea
 */
public class Card {
	
	public int num;
	public int suit;
	
	Card(int suit, int num) {
		this.num = num;
		this.suit = suit;	//0 = heart
							//1 = diamond
							//2 = clubs
							//3 = spades
	}
	
	public String toString() {
		String cardString = "", 
				suitString = "", 
				numString = "";
		
		switch(suit) {
			case 0: 
				suitString = "hearts";
				break;
			case 1: 
				suitString = "diamonds";
				break;
			case 2: 
				suitString = "clubs";
				break;
			case 3: 
				suitString = "spades";
				break;
		}
		
		switch(num) {
			case 0:
				numString = "2";
				break;
			case 1:
				numString = "3";
				break;
			case 2:
				numString = "4";
				break;
			case 3:
				numString = "5";
				break;
			case 4:
				numString = "6";
				break;
			case 5:
				numString = "7";
				break;
			case 6:
				numString = "8";
				break;
			case 7:
				numString = "9";
				break;
			case 8:
				numString = "10";
				break;
			case 9:
				numString = "Jack";
				break;
			case 10:
				numString = "Queen";
				break;
			case 11:
				numString = "King";
				break;
			case 12:
				numString = "Ace";
				break;
		}
		
		cardString = numString + " of " + suitString;
		
		return cardString;
	}
	
}
