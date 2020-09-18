/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class allows the user to move from different game states
 * and controls the functionality of the mouse and clicking buttons on the
 * screen. It is responsible for updating the screens and rendering the
 * logos and buttons as well.
 */
package li_andy_cpt;

import li_andy_cpt.Game.STATE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Menu extends MouseAdapter {

    private final Game game;
    private final Handler handler;
    private final HUD hud;
    private final Random r = new Random();

    //array of colours
    private final Color[] menuButton = {Color.white, Color.white, Color.white, Color.white, Color.white,
        Color.white, Color.white, Color.white, Color.white};

    //array of integers for button width and button height
    private final int[] bx = {214, 214, 214, 214, 214, 214, 214, 214, 214};
    private final int[] bw = {200, 200, 200, 200, 200, 200, 200, 200, 200};
    private final BufferedImageLoader loader = new BufferedImageLoader();

    //array of filenames
    private final String[] filenames = {"logo.png", "angryBirds.png"};

    //array of buffered images
    private final BufferedImage[] images = new BufferedImage[filenames.length];

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        for (int i = 0; i < images.length; i++) {
            images[i] = loader.LoadImage(filenames[i]); //loading level
        }
        this.handler = handler;
        this.hud = hud;
    }

    //this method controls what happens when the mouse presses a button
    @Override
    public void mousePressed(MouseEvent e) {

        int mx = e.getX();
        int my = e.getY();

        //THE FOLLOWING BUTTONS ARE IN THE MENU STATE
        if (game.gameState == STATE.Menu) {
            handler.clearEnemies();

            //transfers to select state
            if (mouseOver(mx, my, 214, 150, 200, 64)) {
                game.gameState = STATE.Select;
                return;
            }

            //transfers to help state
            if (mouseOver(mx, my, 214, 250, 200, 64)) {
                game.gameState = STATE.Help;
            }

            //quit button
            if (mouseOver(mx, my, 214, 350, 200, 64)) {
                System.exit(1);
            }
        }

        //THE FOLLOWING BUTTONS ARE IN THE SELECT STATE
        if (game.gameState == STATE.Select) {

            //transfers to Angry Birds game mode
            if (mouseOver(mx, my, 214, 150, 200, 64)) {
                game.gameState = STATE.Game;
                handler.clearEnemies();
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new greenPig(Game.WIDTH / 2 - 24,
                        Game.HEIGHT / 2 - 24, ID.Player, handler, game));
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100),
                        r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy));
                game.diff = 0;
            }

            //transfers to Flappy Bird game mode
            if (mouseOver(mx, my, 214, 250, 200, 64)) {
                game.gameState = STATE.Game;
                handler.clearEnemies();
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Bird(130, 100, ID.Bird, handler, hud));
                game.diff = 1;
            }

            //back button
            if (mouseOver(mx, my, 214, 350, 200, 64)) {
                game.gameState = STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
                return;
            }
        }

        //BACK BUTTON FOR HELP SCREEN
        if (game.gameState == STATE.Help && mouseOver(mx, my, 214, 350,
                200, 64)) {
            game.gameState = STATE.Menu;
            return;
        }

        //THE FOLLOWING BUTTONS ARE IN THE END STATE
        if (game.gameState == STATE.End) {

            //transfers back to the game state
            if (mouseOver(mx, my, 214, 250, 200, 64)) {
                game.gameState = STATE.Game;
                handler.clearEnemies();
                hud.setLevel(1);
                hud.setScore(0);
                if (game.diff == 0) {
                    handler.clearEnemies();
                    handler.addObject(new greenPig(Game.WIDTH / 2 - 24, Game.HEIGHT
                            / 2 - 24, ID.Player, handler, game));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 100),
                            r.nextInt(Game.HEIGHT - 100), ID.BasicEnemy));
                } else if (game.diff == 1) {
                    handler.clearEnemies();
                    handler.addObject(new Bird(130, 100, ID.Bird, handler, hud));
                }

                //transfers back to the menu state
            } else if (mouseOver(mx, my, 214, 350, 200, 64)) {
                game.gameState = STATE.Menu;
                hud.setLevel(1);
                hud.setScore(0);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    //controls the colour of the button when the mouse hovers over a button
    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //THE FOLLOWING BUTTON ARE IN THE MENU STATE
        if (game.gameState == STATE.Menu) {

            //play button
            if (mouseOver(mx, my, 214, 150, 200, 64)) {
                menuButton[0] = Color.blue;
            } else {
                menuButton[0] = Color.white;
            }

            //help button
            if (mouseOver(mx, my, 214, 250, 200, 64)) {
                menuButton[1] = Color.green;
            } else {
                menuButton[1] = Color.white;
            }

            //quit button
            if (mouseOver(mx, my, 214, 350, 200, 64)) {
                menuButton[2] = Color.pink;
            } else {
                menuButton[2] = Color.white;
            }
        }

        //THE FOLLOWING BUTTONS ARE IN THE SELECT STATE
        if (game.gameState == STATE.Select) {

            //Angry Birds game mode
            if (mouseOver(mx, my, 214, 150, 200, 64)) {
                menuButton[3] = Color.blue;
            } else {
                menuButton[3] = Color.white;
            }

            //Flappy Bird game mode
            if (mouseOver(mx, my, 214, 250, 200, 64)) {
                menuButton[4] = Color.green;
            } else {
                menuButton[4] = Color.white;
            }

            //back button
            if (mouseOver(mx, my, 214, 350, 200, 64)) {
                menuButton[5] = Color.pink;
            } else {
                menuButton[5] = Color.white;
            }
        }

        //BACK BUTTON FOR HELP STATE
        if (game.gameState == STATE.Help) {
            if (mouseOver(mx, my, 214, 350, 200, 64)) {
                menuButton[6] = Color.blue;
            } else {
                menuButton[6] = Color.white;
            }
        }

        //THE FOLLOWING BUTTONS ARE FOR THE END STATE
        if (game.gameState == STATE.End) {

            //play again button
            if (mouseOver(mx, my, 214, 250, 200, 64)) {
                menuButton[7] = Color.green;
            } else {
                menuButton[7] = Color.white;
            }

            //back to menu button
            if (mouseOver(mx, my, 214, 350, 200, 64)) {
                menuButton[8] = Color.pink;
            } else {
                menuButton[8] = Color.white;
            }
        }
    }

    /*this method return a boolean value of whether or not the mouse is 
    hovering over a specific area*/
    
    private boolean mouseOver(int mx, int my, int x, int y, int width,
            int height) {
        return mx > x && mx < x + width && my > y && my < y + height;
    }
    
    //updates the size of the buttons
    public void tick() {
        for (int i = 0; i < 7; i++) {
            if (menuButton[i] == Color.white) {
                bx[i] += (234 - bx[i]) * 0.05; //default 214
                bw[i] += (200 - bw[i]) * 0.05; //default 200
            } else {
                bx[i] += (114 - bx[i]) * 0.05; //default 214
                bw[i] += (400 - bw[i]) * 0.05; //default 200
            }
        }
    }

    //renders the buttons onto the screen
    public void render(Graphics g) {
        Font fnt2 = new Font("arial", 1, 30);
        Font fnt3 = new Font("arial", 1, 40);
        if (null != game.gameState) {
            switch (game.gameState) {
                case Menu:
                    //////////////TITLE OF GAME///////////////////
                    g.drawImage(images[1], (Game.WIDTH - images[1].getWidth()) / 2 - 10, 60, images[1].getWidth(), images[1].getHeight(), game);
                    g.drawImage(images[0], (Game.WIDTH - images[0].getWidth()) / 2, 5, images[0].getWidth(), images[0].getHeight(), game);
                    //////////////PLAY BUTTON/////////////////////
                    g.setFont(fnt2);
                    g.setColor(menuButton[0]);
                    g.drawRect(bx[0], 150, bw[0], 64);
                    g.drawString("PLAY", 275, 195);
                    g.setColor(Color.white);
                    //////////////HELP BUTTON////////////////////
                    g.setColor(menuButton[1]);
                    g.drawRect(bx[1], 250, bw[1], 64);
                    g.drawString("HELP", 275, 295);
                    g.setColor(Color.white);
                    ///////////////QUIT BUTTON///////////////////
                    g.setColor(menuButton[2]);
                    g.drawRect(bx[2], 350, bw[2], 64);
                    g.drawString("QUIT", 275, 395);
                    g.setColor(Color.white);
                    break;
                case Help:
                    //////////////BACK BUTTON FROM HELP//////////
                    g.setFont(fnt2);
                    g.setColor(menuButton[6]);
                    g.drawRect(bx[6], 350, bw[6], 64);
                    g.drawString("Back", 270, 390);
                    g.setColor(Color.white);
                    break;
                case End:
                    /////////////INFORMATION FOR SCORE////////////
                    g.setFont(fnt3);
                    if (game.diff == 0) {
                        g.setColor(Color.cyan);
                        g.drawString("" + hud.getLevel(), 495, 195);
                        g.setColor(Color.magenta);
                        g.drawString("" + game.getHighScore(game.diff), 495, 235);
                    } else if (game.diff == 1) {
                        g.setColor(Color.cyan);
                        g.drawString("" + hud.getScore(), 495, 195);
                        g.setColor(Color.magenta);
                        g.drawString("" + game.getHighScore(game.diff), 495, 235);
                    }   ////////////TRY AGAIN BUTTON//////////////////
                    g.setFont(fnt2);
                    g.setColor(menuButton[7]);
                    g.drawRect(bx[7], 250, bw[7], 64);
                    g.drawString("Try Again", 245, 290);
                    g.setColor(Color.white);
                    ////////////MAIN MENU BUTTON//////////////////
                    g.setColor(menuButton[8]);
                    g.drawRect(bx[8], 350, bw[8], 64);
                    g.drawString("Main Menu", 245, 390);
                    g.setColor(Color.white);
                    break;
                case Select:
                    ///////////BUTTON FOR SPECIAL EDITION////////
                    g.setFont(fnt2);
                    g.setColor(menuButton[3]);
                    g.drawRect(bx[3], 150, bw[3], 64);
                    g.drawString("Angry Bird", 235, 195);
                    g.setColor(Color.white);
                    ///////////BUTTON FOR REGULAR////////////////
                    g.setColor(menuButton[4]);
                    g.drawRect(bx[4], 250, bw[4], 64);
                    g.drawString("Flappy Bird", 235, 295);
                    g.setColor(Color.white);
                    //////////BUTTON TO GO BACK//////////////////
                    g.setColor(menuButton[5]);
                    g.drawRect(bx[5], 350, bw[5], 64);
                    g.drawString("Back", 277, 395);
                    g.setColor(Color.white);
                    break;
                default:
                    break;
            }
        }
    }
}
