/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class stores the information about the top pipe in
 * the Flappy Bird game mode which is 1 of the obstacles. When this object is
 * instantiated, the image is loaded at the top of the screen and begins 
 * moving left at a constant velocity (-x).
 */
package li_andy_cpt;

import java.awt.Graphics;
import java.awt.Rectangle;

public class TopPipe extends GameObject {
    
    private final String filename = "pipeTop.png";

    public TopPipe(int x, int y, ID id) {
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
