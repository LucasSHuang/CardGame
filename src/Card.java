import java.awt.*;

public class Card {

    // Instance Variables
    private String rank;
    private String suit;
    private int value;
    private Image card;
    private GameViewer window;

    // Constructor
    public Card(String rank, String suit, int value, Image card, GameViewer window) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    // Getters and setters
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // toString method
    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(card, x, y, window.CARD_WIDTH, window.CARD_HEIGHT, window);
    }
}
