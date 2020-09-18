/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class stores the information about the bottom pipe in
 * the Flappy Bird game mode which is 1 of the obstacles. When this object is
 * instantiated, the image is loaded and begins moving left at a constant 
 * velocity (-x).
 */
package li_andy_cpt;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BottomPipe extends GameObject {
    private final String filename = "pipeBottom.png";

    public BottomPipe(int x, int y, ID id) {
        super(x, y, id);
        image = loader.LoadImage(filename);
        velX = -4;
    }

    @Override
    public void tick() {
        x += velX;
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
