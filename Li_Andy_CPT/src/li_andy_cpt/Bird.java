/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class stores the information about the bird in
 * the Flappy Bird game mode. When this bird player is instantiated, the image
 * is loaded and begins moving downwards since it is affected by gravity. The
 * user is required to press a button to allow the bird to jump by small
 * increments of space in order to avoid hitting the ground or the pipe
 * obstacles.
 */
package li_andy_cpt;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bird extends GameObject {

    private final Handler handler;
    private final HUD hud;
    private final float gravity = 0.4f;
    private final float MAX_SPEED = 8;
    private final String filename = "bird.png";

    public Bird(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        image = loader.LoadImage(filename);
        this.handler = handler;
        this.hud = hud;
        hud.setScore(0);
    }

    @Override
    public void tick() {
        y += velY;

        //controls the bird's gravity which causes to speed up as it falls
        if (falling || jumping) {
            velY += gravity;
            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }

        //blocks the bird from going above the top of the window
        if (y <= 0) {
            this.y = 0;
        }

        collision();
        gainPoint();
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            //game end when the player hits the ground
            if (this.y >= Game.HEIGHT - image.getHeight() - 10) {
                HUD.HEALTH = 0;

                //game ends when the player hits the pipe
            } else if (tempObject.getId() == ID.TopPipe
                    || tempObject.getId() == ID.BottomPipe) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH = 0;
                }
            }
        }
    }

    public void gainPoint() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.TopPipe) {

                /*a point is awarded when the player's x location and the 
                pipe's x location are the same*/
                if (this.x - tempObject.x < 4 && this.x - tempObject.x >= 0) {
                    hud.setScore(hud.getScore() + 1);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, image.getWidth(),
                image.getHeight(), null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, image.getWidth(),
                image.getHeight());
    }
}
