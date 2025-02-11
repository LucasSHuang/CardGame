import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameViewer extends JFrame {
        // Instance variables and constants
        public final int WINDOW_WIDTH = 1500;
        public final int WINDOW_HEIGHT = 1000;
        public final int TITLE_BAR_HEIGHT = 23;
        private final Color BACKGROUND = new Color(52, 90,55);
        private Game game;

        // Constructor
        public GameViewer(Game game) {
            this.game = game;
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setTitle("Blackjack");
            this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            this.setVisible(true);
        }

        // Paint background
        public void paintBackground(Graphics g) {
            g.setColor(BACKGROUND);
            g.fillRect(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
        }

        // Prints instructions on front end
        public void printInstructions(Graphics g) {
            g.setColor(Color.WHITE);
            Font f1 = new Font("PLAIN", Font.BOLD, 24);
            g.setFont(f1);
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
            f1 = new Font("PLAIN", Font.BOLD, 48);
            g.setFont(f1);
            g.drawString("What is your name?", WINDOW_WIDTH / 3, WINDOW_HEIGHT - 300);
        }

        // Paint method
        public void paint(Graphics g)
        {
            paintBackground(g);
            if (game.getState() == 0) {
                printInstructions(g);
            }


        }
    }
