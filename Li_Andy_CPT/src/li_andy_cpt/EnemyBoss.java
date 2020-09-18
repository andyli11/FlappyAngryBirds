/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class stores the information about the enemy boss in
 * the Angry Birds game mode which is the black bird. When this object is
 * instantiated, the image is loaded and slowly descends from the top of the
 * screen. Once this bird is fully visible, it begins to move side to side,
 * while exploding dangerous black bits. This bird is the ultimate boss and is
 * reached at a high level. Not only is the black bird the biggest bird, but
 * it is also the only bird that is capable of releasing dangerous projectiles
 * at the player.
 */
package li_andy_cpt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject {

    private final Handler handler;
    private final Random r = new Random();
    private int timer = 65;
    private int timer2 = 70;
    private final String filename = "blackBird.png";

    public EnemyBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        image = loader.LoadImage(filename);
        this.handler = handler;
        velX = 0;
        velY = 2;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        /*the bird which starts above the window, begins to descend. After
        this timer is finished, it stops descending
        */
        if (timer <= 0) {
            velY = 0;
            timer2--;
        } else {
            timer--;
        }

        /*after the bird finishes descending, it moves side to side along the
        x-axis and its velocity changes everytime the bird hits the side of
        the window to make its motion unpredictable and fluctuant
        */
        if (timer2 <= 0) {
            if (velX == 0) {
                velX = 2;
            } else if (velX > 0) {
                velX += 0.005f;
            } else if (velX < 0) {
                velX -= 0.005;
            }
            
            //limits the maximum velocity from a range of -10 to 10
            velX = Game.clamp(velX, -10, 10);

            //randomly spawns a ton of projectiles that can damage the player
            int spawn = r.nextInt(4);
            if (spawn == 0) {
                handler.addObject(new EnemyBossBullet((int) x + 48,
                        (int) y + 48, ID.BasicEnemy, handler));
            }
        }

        /*bird changes direction when it collides with the window's boundaries
        Since the bird is only moving in the x-axis, the y boundaries are not
        considered.
        */
        if (x <= 0 || x >= Game.WIDTH - 96) {
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
