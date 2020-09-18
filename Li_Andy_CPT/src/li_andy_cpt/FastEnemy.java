/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class stores the information about the fast enemy in
 * the Angry Birds game mode which is the yellow bird. When this fast enemy is
 * instantiated, the image is loaded and begins moving at high velocities.
 * What this bird lacks in size, it makes up for it through its speed.
 */
package li_andy_cpt;

import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject {

    private final String filename = "yellowBird.png";

    public FastEnemy(int x, int y, ID id) {
        super(x, y, id);
        image = loader.LoadImage(filename);
        velX = 2;
        velY = 8;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        /*the following conditional statements will reverse the directon of 
        the bird's velocity when it hits the boundaries of the window
         */
        
        if (y <= 0 || y >= Game.HEIGHT - image.getHeight() - 32) {
            velY *= -1;
        }
        if (x <= -2 || x >= Game.WIDTH - 36) {
            velX *= -1;
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
