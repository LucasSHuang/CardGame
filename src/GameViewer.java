import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame {

        // Instance variables and constants
        private final int WINDOW_WIDTH = 1500;
        private final int WINDOW_HEIGHT = 1000;
        private final int TITLE_BAR_HEIGHT = 23;
        public static final int CARD_HEIGHT = 180;
        public static final int CARD_WIDTH = 116;
        public static final int MIDDLE = 650;
        public static final int SCORE_X = 1200;
        private final Color BACKGROUND = new Color(52, 90,55);
        private final Font MAIN = new Font("PLAIN", Font.BOLD, 24);
        private final Font LARGE = new Font("PLAIN", Font.BOLD, 48);
        private Game game;
        private Image back;

        // Constructor
        public GameViewer(Game game) {
            this.game = game;
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setTitle("Blackjack");
            this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            this.setVisible(true);
            back = new ImageIcon("Resources/back.png").getImage();
        }

        // Paint background
        public void paintBackground(Graphics g) {
            g.setColor(BACKGROUND);
            g.fillRect(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
        }

        // Prints instructions on front end
        public void printInstructions(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(MAIN);
            ArrayList <String> instructions = new ArrayList<String>();
            instructions.add("Welcome to Blackjack!");
            instructions.add("In Blackjack, you play against the dealer");
            instructions.add("The goal is to get as close to 21 points as possible");
            instructions.add("Number cards are worth their face value and face cards are worth 10 points");
            instructions.add("An ace can be 1 or 11 points");
            instructions.add("After you are dealt two cards, you can choose to hit (take another card) or to stay");
            instructions.add("You win if you get more points than the dealer");
            for (int i = 0; i < instructions.size(); i++) {
                g.drawString(instructions.get(i), WINDOW_WIDTH / 5,  WINDOW_HEIGHT / 10 + 50 * (i + 1) + TITLE_BAR_HEIGHT);
            }
            g.setFont(LARGE);
            g.drawString("What is your name?", WINDOW_WIDTH / 3, WINDOW_HEIGHT - 300);
        }

        // Gets the dealer's and players points
        public String findPoints(Player player) {
            return player.getName() + ": " + Integer.toString(player.getPoints());
        }

        // Prints the points of both the dealer and player
        public void printPoints(Graphics g) {
            String playerScore = findPoints(game.getPlayer());
            g.drawString(playerScore, SCORE_X, TITLE_BAR_HEIGHT + 50);
            String dealerScore = findPoints(game.getDealer());
            g.drawString(dealerScore, SCORE_X, TITLE_BAR_HEIGHT + 100);
        }

        // Prints the player's cards
        public void printPlayer(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(MAIN);
            int handSize = game.getPlayer().getHand().size();
            // Creates a variable that holds the leftmost card's x coord so everything is centered
            int startingX = (WINDOW_WIDTH - (handSize * CARD_WIDTH)) / 2;
            if (game.getState() == 1) {
                // Checks for an ace
                if (game.getPlayer().getLastCard().getRank().equals("A") && !game.getPlayer().isChosenAceVal()) {
                    g.drawString("Do you want your ace to be 1 or 11 points?", MIDDLE - 100, WINDOW_HEIGHT / 2);
                }
                else {
                    g.drawString("Do you want to hit or stand?", MIDDLE - 100, WINDOW_HEIGHT / 2);

                }
            }
            // Prints out player's cards
            for (int i = 0; i < handSize; i++) {
                game.getPlayer().getHand().get(i).draw(g, startingX + (CARD_WIDTH * i), WINDOW_HEIGHT - CARD_HEIGHT * 2);
            }
        }

        // Prints out the dealers cards
        public void printDealer(Graphics g) {
            // If its player's turn only shows one card and other is backwards
            if (game.getState() == 1) {
                // Check if hand is empty then draw first card face up
                // Draw second card face down
                g.drawImage(back, MIDDLE + CARD_WIDTH, 50 + TITLE_BAR_HEIGHT, CARD_WIDTH, CARD_HEIGHT, this);
                game.getDealer().getHand().get(0).draw(g, MIDDLE, 50 + TITLE_BAR_HEIGHT);
            }
            else {
                g.setColor(Color.WHITE);
                g.setFont(MAIN);
                int handSize = game.getDealer().getHand().size();
                int startingX = (WINDOW_WIDTH - (handSize * CARD_WIDTH)) / 2;
                // If dealer's turn just print out all cards
                g.drawString("Press space and enter for the dealer to hit", MIDDLE - 100, WINDOW_HEIGHT / 2);
                for (int i = 0; i < handSize; i++) {
                    game.getDealer().getHand().get(i).draw(g, startingX + (CARD_WIDTH * i), 50 + TITLE_BAR_HEIGHT);
                }
            }
        }

        // Ending scene
        public void printEnd(Graphics g) {
            g.setColor(Color.WHITE);
            g.setFont(LARGE);
            // If player busted print out certain message
            if (game.isPlayerBusted()) {
                g.drawString("You busted! Better luck next time!", WINDOW_WIDTH / 4, WINDOW_HEIGHT / 2);
            }
            // If player has won game print out winning message
            else if (game.isGameWon()) {
                g.drawString("Congratulations, " + game.getPlayer().getName() + ", the dealer busted and you won!", 50, WINDOW_HEIGHT / 2);
            }
            // Else just print the message that the dealer beat the player
            else {
                g.drawString("Sorry, the dealer beat you. Better luck next time!", WINDOW_WIDTH / 9, WINDOW_HEIGHT / 2);
            }
        }

        // Paint method
        public void paint(Graphics g)
        {
            // Always clears everything with a repaint of the background
            paintBackground(g);
            // Starts with printing instructions
            if (game.getState() == 0) {
                printInstructions(g);
            }
            // Same order for player and dealer turn but state is just different
            else if (game.getState() == 1 || game.getState() == 2) {
                printDealer(g);
                printPlayer(g);
                printPoints(g);
            }
            // Prints the end of the game
            else {
                printEnd(g);
            }
        }
    }
