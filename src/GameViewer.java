import javax.swing.*;
import java.awt.*;

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

        // Paint method
        public void paint(Graphics g)
        {
            paintBackground(g);
        }
    }
