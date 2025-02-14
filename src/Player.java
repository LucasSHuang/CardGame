import java.util.ArrayList;
import java.util.InputMismatchException;
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

    // Updates player points
    public void updatePoints() {
        Scanner scanner = new Scanner(System.in);
        // Asks the player what point amount they want their ace to be if they get one
        if (hand.get(hand.size() - 1).getRank().equals("A")) {
            // Checks to see if the input was valid
            boolean validInput = false;
            while (!validInput) {
                // Uses try catch loop for a non integer and if loop for wrong integer value
                try {
                    System.out.println("Do you want your ace to be 1 point or 11 points?");
                    int value = scanner.nextInt();
                    if (value == 1 || value == 11) {
                        points += value;
                        scanner.nextLine();
                        validInput = true;
                    }
                    else {
                        System.out.println("Type 1 or 11");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please type an integer");
                    scanner.nextLine();
                }
            }
        }
        else {
            // Adds the amount of points the card value holds
            points += hand.get(hand.size() - 1).getValue();
        }
    }

    // Updates dealer points
    public void dealerUpdate() {
        // Adds the best point value of ace for the dealer
        if (hand.get(hand.size() - 1).getRank().equals("A") && points + 11 <= 21) {
            points += 11;
        }
        else {
            // Add the amount of points the card value holds
            points += hand.get(hand.size() - 1).getValue();
        }
    }

    // Adds a card to the hand
    public void addCard(Card card) {
        hand.add(card);
    }

    public Card getLastCard() {
        return hand.get(hand.size() - 1);
    }

    // toString
    public String toString() {
        return this.name + " has " + this.points + " points\n" + this.name + "'s cards: " + hand;
    }


}
