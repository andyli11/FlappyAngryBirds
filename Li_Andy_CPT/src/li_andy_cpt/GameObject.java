/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this is the parent class of all the game objects in the
 * program. Since it is an abstract class, all the subclasses must inherit
 * the abstract methods. In addition, there are all the getter and setters
 * that are necessary for all the game objects that extend this class.
 */
package li_andy_cpt;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject {

    protected float x, y; //the x and y locations 
    protected ID id; //the identifcation 
    protected float velX, velY; //the velocity in the x and y axis
    protected boolean falling = true; //determines if the bird is falling
    protected boolean jumping = false; //determines if the bird is jumping
    protected final BufferedImageLoader loader = new BufferedImageLoader();
    protected BufferedImage image;

    //constructor: objects must have an x and y location and an identification
    public GameObject(float x, float y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    //updates the game object 
    public abstract void tick();

    //renders the game object to the screen 
    public abstract void render(Graphics g);

    //gets the boundaries of the game object which is useful for collisions
    public abstract Rectangle getBounds();

    //the following are accessor and mutator methods 
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
