/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class is responsible for spawning all the enemies into 
 * the game. In the Angry Birds mode, different types of bird enemies are
 * spawned at different levels. In the Flappy Bird game, the pipes are
 * continuously spawning at even increments apart. Overall, this class
 * spawns the enemies into the game and can be edited to spawn all types of
 * enemies in no particular order for both game types.
 */
package li_andy_cpt;

import java.util.Random;

public class Spawn {

    private final Handler handler;
    private final HUD hud;
    private int delay = 60; //the delay between the spawning of pipes
    private final Random r = new Random();
    private final Game game;
    private final int GAP_SIZE = 110;//the space between the gap in the pipes

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    //adding objects into the game
    public void tick() {
        
        //for Angry Birds
        if (game.diff == 0) {
              if(hud.getScore()==0){
                hud.setLevel(hud.getLevel() + 1);

                //every new level means adding a new enemy
                switch (hud.getLevel()) {
                    case 2:
                        handler.addObject(new FastEnemy(r.nextInt(
                                Game.WIDTH - 100), 
                                r.nextInt(Game.HEIGHT - 100), ID.FastEnemy));
                        break;
                    case 3:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH 
                                - 100), r.nextInt(Game.HEIGHT - 100), 
                                ID.SmartEnemy, handler));
                        break;
                    case 4:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH
                                - 100), r.nextInt(Game.HEIGHT - 100), 
                                ID.BasicEnemy));
                        break;
                    case 5:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH 
                                - 100), r.nextInt(Game.HEIGHT - 100), 
                                ID.FastEnemy));
                        break;
                    case 6:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH 
                                - 100), r.nextInt(Game.HEIGHT - 100), 
                                ID.SmartEnemy, handler));
                        break;
                    case 7:
                        handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH 
                                - 100), r.nextInt(Game.HEIGHT - 100), 
                                ID.BasicEnemy));
                        break;
                    case 8:
                        handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH 
                                - 100), r.nextInt(Game.HEIGHT - 100), 
                                ID.FastEnemy));
                        break;
                    case 9:
                        handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH 
                                - 100), r.nextInt(Game.HEIGHT - 100), 
                                ID.SmartEnemy, handler));
                        break;
                    case 10:
                        handler.clearEnemies();
                        handler.addObject(new EnemyBoss(
                                Game.WIDTH / 2 - 48, -132,
                                ID.EnemyBoss, handler));
                        break;
                    default:
                        break;
                }
            }

            //for Flappy Bird
        } else if (game.diff == 1) {

            //pipes are spawned when the delay is at 0
            if (delay > 0) {
                delay--;
            } else {
                int ran = r.nextInt(260 - 50) + 50;
                handler.addObject(new TopPipe(Game.WIDTH, -ran, 
                        ID.TopPipe));
                handler.addObject(new BottomPipe(Game.WIDTH, -ran + 303 
                        + GAP_SIZE, ID.BottomPipe));
                delay = 60;
            }
        } 
    }
}
