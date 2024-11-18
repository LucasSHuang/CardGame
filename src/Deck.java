import java.util.ArrayList;

public class Deck {

    // Instance Variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck(String[] rank, String[] suit, int[] value) {
        cards = new ArrayList<Card>();
        // Adds all cards into ArrayList
        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < rank.length; j++) {
                cards.add(new Card(rank[j], suit[i], value[j]));
            }
        }
    }
}
