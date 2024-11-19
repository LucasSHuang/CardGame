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
        cardsLeft = cards.size();
        shuffle();
    }

    // Methods

    // Checks to see if number of cards left in deck is 0
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    // Deals a random card in the deck
    public Card deal() {
        // Checks to see if deck is empty and then decrements cardsLeft and returns card at index
        if (isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    // Shuffles deck
    public void shuffle() {
        for (int i = cardsLeft - 1; i >= 0; i--) {
            int r = (int)(Math.random() * i + 1);
            cards.add(r, cards.remove(i));
            cards.add(i ,cards.remove(r + 1));
        }
    }
}
