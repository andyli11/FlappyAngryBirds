/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this enumeration is a library that holds all the objects
 * within the game which allows the program to differentiates between a
 * player and enemy. This is useful in many cases. For example, in level 10 of 
 * the Angry Birds game mode, in order to check for collisions with the 
 * moving projectiles, I check if the player comes in contact with the 
 * projectile itself and not with its trail which is only a visual component. 
 */
package li_andy_cpt;

public enum ID {
    Player(),
    Bird(),
    BasicEnemy(),
    FastEnemy(),
    SmartEnemy(),
    TopPipe(),
    BottomPipe(),
    SafeGap(),
    EnemyBoss(),
    Trail(),
    RedBird(),
    YellowBird(),
    BlackBird(),
}
