import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    // Instance variables
    private ArrayList<Card> hand;
    private int points;
    private String name;

    // Constructors
    public Player(ArrayList<Card> hand, String name) {
        this.hand = new ArrayList<Card>();
        this.hand = hand;
        this.points = 0;
        this.name = name;
    }

    public Player(String name) {
        this.hand = new ArrayList<Card>();
        this.points = 0;
        this.name = name;
    }

    // Getters and setters
    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    // Adding points and adding cards
    public void addPoints(int points) {
        this.points += points;
    }

    public void updatePoints(int index) {
        Scanner scanner = new Scanner(System.in);
            if (hand.get(index).getRank().equals("A")) {
                System.out.println("Do you want your ace to be 1 point or 11 points?");
                points += scanner.nextInt();
                scanner.nextLine();
            }
            else {
                points += hand.get(index).getValue();
            }
    }

    public void dealerUpdate(int index) {
        if (hand.get(index).getRank().equals("A") && points + 11 < 21) {
            points += 11;
        }
        else {
            points += hand.get(index).getValue();
        }
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    // toString
    public String toString() {
        return this.name + " has " + this.points + " points\n" + this.name + "'s cards: " + hand;
    }


}
