/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class is responsible for creating the window that the
 * game will run using JFrame.
 */
package li_andy_cpt;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas {

    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height)); //size of window
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close button
        frame.setResizable(false); //does not allow the user to resize window
        frame.setLocationRelativeTo(null); //opens game in center of the screen
        frame.add(game);
        frame.setVisible(true); //visible to the user
        game.start(); //starts the thread for the game
    }
}
