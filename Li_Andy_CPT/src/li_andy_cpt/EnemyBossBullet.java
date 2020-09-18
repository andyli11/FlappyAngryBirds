/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class stores the information about the projectile that is 
 * fired from the enemy boss (black bird) in the Angry Birds game mode. When 
 * this object is instantiated, the image is loaded and begins moving 
 * downwards at a random velocity to make the bullets unpredictable.
 */
package li_andy_cpt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject {

    private final Handler handler;
    private final int DIMENSIONS = 12;
    private final Random r = new Random();

    public EnemyBossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = r.nextInt(5 - (-5)) + (-5);
        velY = 5;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        //removes the object when it leaves the window
        if (y >= Game.HEIGHT) {
            handler.removeObject(this);
        }

        //creates the trail effect on the object when it moves
        handler.addObject(new Trail(x, y, ID.Trail, Color.black, DIMENSIONS, 
                DIMENSIONS,0.11f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int) x, (int) y, DIMENSIONS, DIMENSIONS);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, DIMENSIONS, DIMENSIONS);
    }
}
