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
        System.out.println("The goal is to get as close to 21 points as possible");
        System.out.println("Number cards are worth their face value and face cards are worth 10 points");
        System.out.println("An ace can be 1 or 11 points");
        System.out.println("After you are dealt two cards, you can choose to hit (take another card) or to stop");
        System.out.println("You win if you get more points than the dealer");
    }

    public void dealHand() {
        for (int i = 0; i < 2; i++) {
            player.addCard(deck.deal());
            player.updatePoints(i);
            dealer.addCard(deck.deal());
            dealer.dealerUpdate(i);
        }
    }

    public void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(dealer);
        while (player.getPoints() < 21) {
            System.out.println(player);
            String response;
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
            if (response.equals("hit")) {
                player.addCard(deck.deal());
                player.updatePoints(player.getHand().size() - 1);
                if (player.getPoints() >= 21) {
                    break;
                }
            }
            else if (response.equals("stay")) {
                break;
            }

        }
    }

    public boolean checkBust(Player player) {
        if (player.getPoints() > 21) {
            return true;
        }
        return false;
    }


    public void playGame() {
        dealHand();
        playerTurn();
        if (checkBust(player)) {
            System.out.println(player);
            System.out.println("You busted! Better luck next time!");
            return;
        }


    }

    public static void main(String[] args) {
        printInstructions();
        Game blackjack = new Game();
        blackjack.playGame();
    }
}