/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class controls the user's keyboard inputs and assigns
 * functionality to keyboard buttons. It uses 2 methods, "key pressed", which
 * is what happens when the key is pressed and "key released" which is what
 * happens when the key is released.
 */
package li_andy_cpt;

import li_andy_cpt.Game.STATE;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Game game;
    private final Handler handler;
    private boolean uP = false;
    private boolean dP = false;
    private boolean rP = false;
    private boolean lP = false;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    /**
     * Description: this method controls what happens when the key is pressed.
     * It does this by finding the player object within the handler and assigns
     * a integer key code value to a button on a keyboard which provides the
     * game with different functions.
     *
     * pre condition: a key must be pressed
     *
     * post condition: keyboard functions will work when key is pressed
     *
     * @param e - a key event that was inherited from KeyAdapter
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); //takes the key code 

        //loops through all the objects in the game
        for (int i = 0; i < handler.object.size(); i++) { //loops through the array list 
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Bird) { 
                //key events for player 1
                if (game.diff == 0) {
                    if (key == KeyEvent.VK_UP) {
                        uP = true;
                        tempObject.setVelY(-5);
                    }
                    if (key == KeyEvent.VK_DOWN) {
                        dP = true;
                        tempObject.setVelY(5);
                    }
                    if (key == KeyEvent.VK_RIGHT) {
                        rP = true;
                        tempObject.setVelX(5);
                    }
                    if (key == KeyEvent.VK_LEFT) {
                        lP = true;
                        tempObject.setVelX(-5);
                    }
                } else if (game.diff == 1) {
                    if (key == KeyEvent.VK_SPACE && !tempObject.isJumping()) {
                        tempObject.setJumping(true);
                        tempObject.setVelY(-6);
                    }
                }
            }
            tempObject.setJumping(false);
        }
        if (key == KeyEvent.VK_P) {
            if (game.gameState == STATE.Game) {
                Game.paused = !Game.paused;
            }
        }
        if (key == KeyEvent.VK_ESCAPE) {
            if (game.gameState == STATE.Game) {
                game.gameState = STATE.Menu;
            }
            handler.clearAll();
            HUD.HEALTH = 100;
            HUD.score = 0;
        }
    }

    /**
     * Description: this method controls what happens when the key is released.
     * It does this by finding the player object within the handler and assigns
     * a integer key code value to a button on a keyboard which provides the
     * game with different functions.
     *
     * pre condition: a key must be released
     *
     * post condition: keyboard functions will work when key is released
     *
     * @param e - a key event that was inherited from KeyAdapter
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode(); //takes the key code 

        for (int i = 0; i < handler.object.size(); i++) { //loops through the array list 
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Bird) { //if the object is a player
                //key events for player 1
                if (game.diff == 0) {
                    if (key == KeyEvent.VK_UP) {
                        uP = false;
                        if (dP) {
                            tempObject.setVelY(5);
                        } else {
                            tempObject.setVelY(0);
                        }
                    }
                    if (key == KeyEvent.VK_DOWN) {
                        dP = false;
                        if (uP) {
                            tempObject.setVelY(-5);
                        } else {
                            tempObject.setVelY(0);
                        }
                    }
                    if (key == KeyEvent.VK_RIGHT) {
                        rP = false;
                        if (lP) {
                            tempObject.setVelX(-5);
                        } else {
                            tempObject.setVelX(0);
                        }
                    }
                    if (key == KeyEvent.VK_LEFT) {
                        lP = false;
                        if (rP) {
                            tempObject.setVelX(5);
                        } else {
                            tempObject.setVelX(0);
                        }
                    }
                }
            }
        }
    }
}
