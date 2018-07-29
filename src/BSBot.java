import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BSBot {
	//players is number of players
	//nextCard is the next card to be played
	public static final String[] KEY = {"", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	public static int players;
	public static String nextCard;
	public static List<String> hand = new ArrayList<String>();
	
	public static void main(String[] args) {
		/*System.out.println();
		Scanner scanner = new Scanner(System.in);
		
		//Set the hand
		while (true) {
			String s = scanner.nextLine();
			if (s.equals("-1")) {
				break;
			}
			hand.add(s);
		}
		players = Integer.parseInt(scanner.nextLine());
		nextCard = scanner.nextLine();
		
		sort();
		System.out.println("---------------");
		for (String s : hand) {
			System.out.print(s + " ");
		}
		System.out.println();
		scanner.close();*/
	}
	
	//Gives the index in the KEY for a given card (s)
	public static int getInd(String s) {
		for (int i = 0 ; i < KEY.length; i++) {
			if (KEY[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}
	
	//Goes through the hand and sorts such that the cards go in the order
	//They will be played, rather than in traditional ascending order.
	public static void sort() {
		List<String> tempHand = new ArrayList<String>();
		for (int i = hand.size() - 1; i >= 0; i--) {
			if (hand.get(i).charAt(0) == '(') {
				hand.remove(i);
			}
		}
		int ind = getInd(nextCard); //start ind is set to the next card
		while (hand.size() > 0) {

			String toFind = KEY[ind];
			boolean none = true;
			for (int i = hand.size() - 1; i >= 0; i--) {
				if (hand.get(i).equals(toFind)) {
					tempHand.add(toFind);
					hand.remove(i);
					none = false;
				}
			}
			if (none) {
				tempHand.add("(" + toFind + ")");
			}
			ind = (ind + players) % 13;
			if (ind == 0) {
				ind = 13;
			}
		}
		hand = tempHand;
		
	}
}
	
		
		