// Blackjack by Lucas Huang
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    // Instance variables
    private Deck deck;
    private Player player;
    private Player dealer;
    private GameViewer window;

    // Constructor
    public Game() {
        // Creates front end
        this.window = new GameViewer(this);
        // Gets user's name
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name? ");
        String name = scanner.nextLine();
        // Creates the deck of cards
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        deck = new Deck(ranks, suits, values);
        deck.shuffle();
        // Creates the player and dealer
        player = new Player(name);
        dealer = new Player("Dealer");
        window.repaint();
    }

    // Prints instructions
    public static void printInstructions() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("In Blackjack, you play against the dealer");
        System.out.println("The goal is to get as close to 21 points as possible");
        System.out.println("Number cards are worth their face value and face cards are worth 10 points");
        System.out.println("An ace can be 1 or 11 points");
        System.out.println("After you are dealt two cards, you can choose to hit (take another card) or to stay");
        System.out.println("You win if you get more points than the dealer");
    }

    // Deals the hands of both the player and dealer
    public void dealHand() {
        // Deals cards to the players
        for (int i = 0; i < 2; i++) {
            player.addCard(deck.deal());
            player.updatePoints();
        }
        // Deals card to the dealer
        dealer.addCard(deck.deal());
        dealer.dealerUpdate();
    }

    // Does the player's turn
    public void playerTurn() {
        // Initializes scanner
        Scanner scanner = new Scanner(System.in);
        // Prints out dealer hand for user
        System.out.println(dealer);
        // Makes it so that while player has not busted they have option to hit or stay
        while (player.getPoints() < 21) {
            // Prints out player hand
            System.out.println(player);
            String response;
            // Makes it so that the user has to put in a valid response
            while (true) {
                System.out.println("Do you want to hit or stay?");
                response = scanner.nextLine();
                if (response.equals("hit") || response.equals("stay")) {
                    break;
                }
                else {
                    System.out.println("Please type a valid response");
                }
            }
            // Adds a card to player hand if they chose to hit and if hand went over 21 ends turn
            if (response.equals("hit")) {
                player.addCard(deck.deal());
                player.updatePoints();
                if (player.getPoints() >= 21) {
                    break;
                }
            }
            // If player chose to stay ends turn
            else {
                break;
            }

        }
    }

    // Checks if anyone went over 21
    public boolean checkBust(Player player) {
        if (player.getPoints() > 21) {
            return true;
        }
        return false;
    }

    // Does the dealers turn
    public void dealerTurn() {
        // Shows final hand of player
        System.out.println(player);
        System.out.println("Dealer's turn");
        // Makes it so that if the dealer has fewer points than the player they always hit
        // Once the dealer has more points or ties the player the turn ends
        while (dealer.getPoints() < player.getPoints()) {
            System.out.println(dealer);
            // Updates dealer hand
            System.out.println("Dealer hits");
            dealer.addCard(deck.deal());
            dealer.dealerUpdate();
        }
        System.out.println(dealer);
    }

    // Plays the game
    public void playGame() {
        dealHand();
        playerTurn();
        // Checks if the player has busted and if so ends the game
        if (checkBust(player)) {
            System.out.println(player);
            System.out.println("You busted! Better luck next time!");
            return;
        }
        dealerTurn();
        // Checks if the dealer busted and plays a message for both outcomes
        if (checkBust(dealer)) {
            System.out.println("Congratulations, " + player.getName() + ", the dealer busted and you won!");
        }
        else {
            System.out.println("Sorry, the dealer beat you. Better luck next time!");
        }
    }

    public static void main(String[] args) {
        printInstructions();
        Game blackjack = new Game();
        blackjack.playGame();
    }
}