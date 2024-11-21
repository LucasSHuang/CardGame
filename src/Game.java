import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    // Instance variables
    private Deck deck;
    private Player player;
    private Player dealer;


    public Game() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name? ");
        String name = scanner.nextLine();
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        deck = new Deck(ranks, suits, values);
        deck.shuffle();
        player = new Player(name);
        dealer = new Player("Dealer");
    }

    public static void printInstructions() {
        System.out.println("Welcome to Blackjack!");
        System.out.println("In Blackjack, you play against the dealer");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void dealHand() {
        for (int i = 0; i < 2; i++) {
            player.addCard(deck.deal());
            player.updatePoints(i);
            dealer.addCard(deck.deal());
            dealer.updatePoints(i);
        }
    }

    public void playerTurn() {
        System.out.println(player);
        System.out.println(dealer);
    }



    public void playGame() {
        dealHand();
        playerTurn();

    }

    public static void main(String[] args) {
        printInstructions();
        Game blackjack = new Game();
        blackjack.playGame();
    }
}