/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class is responsible for organizing all of the game
 * objects into an array list and hosts a variety of methods that allow
 * the game to change or use the array list.
 */
package li_andy_cpt;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {

    //array list of game objects which stores all the objects in the game
    ArrayList<GameObject> object = new ArrayList<>();

    //constructor for Handler object
    public Handler() {
    }

    public void tick() {
        //for loop runs through all the objects in the game
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        //for loop runs through all the objects in the game
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    /**
     * Description: this method deletes all the objects in the array list that
     * are not players which include the green pig and Flappy Bird. Thus, all
     * enemies are removed. This method is useful in level 10 of the Angry 
     * Birds game where all the enemies are removed and then arrives the grand 
     * boss, Mr.BOMB!
     *
     * pre condition: the array list cannot be empty
     *
     * post condition: all the objects in the array list except for the players
     * (Flappy Bird or the Green Pig) will be removed from the array list
     */
    public void clearEnemies() {
        //for loop runs through all the objects in the game
        for (int i = 0; i < this.object.size(); i++) {
            GameObject tempObject = this.object.get(i);

            /*this if statement is to see if the temporary object is not a 
            player which include the green pig and the Flappy Bird*/
            if (tempObject.getId() != ID.Player
                    && tempObject.getId() != ID.Bird) {
                this.removeObject(tempObject);
                i--;
            }
        }
    }

    /**
     * Description: this method will clear all the objects in the array list
     *
     * pre condition: array list cannot be empty
     *
     * post condition: the array list is empty
     */
    public void clearAll() {
        for (int i = 0; i < this.object.size(); i++) {
            this.removeObject(this.object.get(i));
        }
    }

    //this instance method allows the user to add an object to the array list
    public void addObject(GameObject object) { //add object into list
        this.object.add(object);
    }

    /*this instance method allows the user to remove an object from the array 
    list*/
    public void removeObject(GameObject object) { //removes object from list
        this.object.remove(object);
    }

}
