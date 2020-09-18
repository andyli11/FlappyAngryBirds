/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class stores the information about the basic enemy in
 * the Angry Birds game mode which is the red bird. When this basic enemy is
 * instantiated, the image is loaded and begins moving. This bird is the basic
 * bird since all of its abilities are average; its speed and size are normal.
 */
package li_andy_cpt;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

    private final String filename = "redBird.png";

    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        image = loader.LoadImage(filename); 
        velX = 4;
        velY = 4;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        /*the following conditional statements will reverse the directon of 
        the bird's velocity when it hits the boundaries of the window*/
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
