import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Deck {

    // Instance Variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    public Deck(String[] rank, String[] suit, int[] value, GameViewer window) {
        cards = new ArrayList<Card>();
        // Adds all cards into ArrayList
        for (int i = 0; i < rank.length; i++) {
            for (int j = 0; j < suit.length; j++) {
                Image card = new ImageIcon("Resources/" + Integer.toString((i + 1) * (j + 1)) + ".png").getImage();
                cards.add(new Card(rank[i], suit[j], value[i], card, window));
            }
        }
        cardsLeft = cards.size();
    }

    // Methods

    // Checks to see if number of cards left in deck is 0
    public boolean isEmpty() {
        return cardsLeft == 0;
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
            // Gets random index
            int r = (int)(Math.random() * (i + 1));
            // Creates temporary card placeholder for original card
            Card temp = cards.get(i);
            // Swaps the index of the two cards
            cards.set(i, cards.get(r));
            cards.add(r ,temp);
        }
    }
}
