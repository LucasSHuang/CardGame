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
        private final Color BACKGROUND = new Color(52, 90,55);
        private final Font MAIN = new Font("PLAIN", Font.BOLD, 24);
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
            Font f1 = new Font("PLAIN", Font.BOLD, 48);
            g.setFont(f1);
            g.drawString("What is your name?", WINDOW_WIDTH / 3, WINDOW_HEIGHT - 300);
        }

        public void printPlayer(Graphics g) {
            g.setColor(Color.WHITE);
            int handSize = game.getPlayer().getHand().size();
            for (int i = 0; i < handSize; i++) {
                g.setFont(MAIN);
                if (game.getPlayer().getLastCard().getRank().equals("A")) {
                    g.drawString("Do you want your ace to be 1 or 11 points?", 300, WINDOW_HEIGHT / 2);
                }
                game.getPlayer().getHand().get(i).draw(g, ((MIDDLE * 2) / handSize) + (CARD_WIDTH * i), WINDOW_HEIGHT - CARD_HEIGHT * 2);
            }
        }

        public void printDealer(Graphics g, int state) {
            if (state == 1) {
                printPlayer(g);
                g.drawImage(back, MIDDLE, 50 + TITLE_BAR_HEIGHT, CARD_WIDTH, CARD_HEIGHT, this);
                if (!game.getDealer().getHand().isEmpty()) {
                    game.getDealer().getHand().get(0).draw(g, MIDDLE + CARD_WIDTH, 50 + TITLE_BAR_HEIGHT);
                }
            }
        }
        // Paint method
        public void paint(Graphics g)
        {
            paintBackground(g);
            if (game.getState() == 0) {
                printInstructions(g);
            }
            else if (game.getState() == 1) {
                printPlayer(g);
                g.drawImage(back, MIDDLE, 50 + TITLE_BAR_HEIGHT, CARD_WIDTH, CARD_HEIGHT, this);
                if (!game.getDealer().getHand().isEmpty()) {
                    game.getDealer().getHand().get(0).draw(g, MIDDLE + CARD_WIDTH, 50 + TITLE_BAR_HEIGHT);

                }
            }


        }
    }
