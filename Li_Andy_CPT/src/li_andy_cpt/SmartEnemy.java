/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class stores the information about the smart enemy in
 * the Angry Birds game mode which is the blue bird. When this smart enemy is
 * instantiated, the image is loaded and begins moving. The smart enemy is
 * highly intelligent since it tracks the location of the player and 
 * determines the shortest path towards it. Don't worry, this smart enemy 
 * has 2 weaknesses: it is very small and slow.
 */
package li_andy_cpt;

import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {

    private final Handler handler;
    private GameObject player;
    private final String filename = "blueBird.png";

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        image = loader.LoadImage(filename);
        this.handler = handler;

        /*this for loop in the constructor will get the information about
        the player becuase the smart enemy's goal is to follow the player*/
        
        for (int i = 0; i < this.handler.object.size(); i++) {

            //determines if the object is a player
            if (handler.object.get(i).getId() == ID.Player) {
                player = this.handler.object.get(i);
            }
        }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        /*determines difference in length in the x and y axis. Then, this uses
        that information to calculate the shortest possible route to the 
        player. This is achieved through the Pythagorean theorem. */
        
        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.hypot(x - player.getX(),
                y - player.getY());
        
        velX = -1 / distance * diffX;
        velY = -1 / distance * diffY;
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
