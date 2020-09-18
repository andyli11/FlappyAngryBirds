package li_andy_cpt;

import java.awt.Graphics;
import java.awt.Rectangle;

public class greenPig extends GameObject {

    private final Handler handler;
    private final Game game;
    private final String filename = "greenPig.png"; //name of file

    public greenPig(int x, int y, ID id, Handler handler, Game game) {
        super(x, y, id);
        image = loader.LoadImage(filename);
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        /*this clamp method was created in the game class and blocks the green 
        pig from doing past the boundaries of the window
         */
        x = Game.clamp(x, 0, Game.WIDTH - image.getWidth() - 7);
        y = Game.clamp(y, 1, Game.HEIGHT - image.getHeight() - 32);
        collision();
    }

    /**
     * Description: this method does stuff
     *
     * pre condition: what must be true for the method to run
     *
     * post condition: what will be accomplished when the method is complete
     *
     * @param paramName - what the parameter is
     * @return a description of what is returned, if the method returns anything
     */
    private void collision() {
        //runs through all objects in the game
        for (int i = 0; i < handler.object.size(); i++) { // for loop loops through all the objects in the game
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.BasicEnemy || tempObject.getId()
                    == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy
                    || tempObject.getId() == ID.EnemyBoss) {

                //if the rectangle intersects with the enemy rectangle
                if (getBounds().intersects(tempObject.getBounds())) {
                    //collision code
                    HUD.HEALTH--;
                }
            }
        }
    }

    //this renders the player from the image
    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, image.getWidth(), image.getHeight(), game);
    }

    /*this determines the boundaries of the player which is useful for 
      detecting collisions*/
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, image.getWidth(), image.getHeight());
    }
}
