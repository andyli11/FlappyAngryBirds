/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class stores the information about the trail in
 * the Angry Birds game mode which is a visual counterpart to the projectile
 * that is released from the enemy boss (black bird). When this trail is
 * instantiated, the image is loaded and begins moving. The trail has a fading
 * effect that makes the bullets appear as if they were traveling at very 
 * fast speeds with a lot of force.
 */
package li_andy_cpt;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {

    private float alpha = 1;
    private final float life;
    private final Handler handler;
    private final Color color;
    private final int width;
    private final int height;

    public Trail(float x, float y, ID id, Color color, int width, int height,
            float life, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    @Override
    public void tick() {

        /*subtracting from the alpha makes the trail slowly fade out. When the
        trail has finished fading out, it is removed. If life has a large 
        value, the trail is smaller and lasts shorter. If life has a small 
        value, trail is larger and lasts longer
        */
        
        if (alpha > life) {
            alpha -= life - 0.00001f;
        } else {
            handler.removeObject(this);
        }
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER; //
        return (AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);

        g2d.setComposite(makeTransparent(1));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
