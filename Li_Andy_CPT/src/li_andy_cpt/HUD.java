/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class stores the information about the HUD - heads up
 * display. In the Angry Birds game mode, the HUD is responsible for 
 * displaying a counter until the next level and the current level. In the 
 * Flappy Bird game mode, the HUD is responsible for displaying the score.
 */
package li_andy_cpt;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

    public static int HEALTH = 100;
    public static int score = 0;
    private int greenValue = 255;
    private int level = 1;
    private final Game game;
    private final int SCORE_WIDTH = 60;

    public HUD(Game game) {
        this.game = game;
    }

    public void tick() {
        if (game.diff == 0) {
            //creates the health bar and cool effect
            HEALTH = Game.clamp(HEALTH, 0, 100);
            greenValue = Game.clamp(greenValue, 0, 255);
            greenValue = HEALTH * 2;
            if (score >= 0) {
                score--;
            } else {
                score = 250;
            }
        }
    }

    public void render(Graphics g) {
        Font fnt = new Font("arial", 1, 30);
        Font fnt2 = new Font("arial", 1, 20);
        Font fnt3 = new Font("arial", 1, 17);
        if (game.diff == 0) {
            g.setColor(Color.gray);
            g.fillRect(10, 15, 200, 42);
            g.setColor(new Color(75, greenValue, 0));
            g.fillRect(10, 15, (int) HEALTH * 2, 42);
            g.setColor(Color.white);
            g.drawRect(10, 15, 200, 42); //creates the border
            g.setFont(fnt);
            g.drawString(HEALTH + "%", 20, 47); //prints the health
            g.setFont(fnt3);
            g.drawString("Next Level: " + score, 10, 75);
            g.setFont(fnt2);
            g.drawString("Level: " + level, 10, 100);
        } else if (game.diff == 1) {
            g.setFont(fnt);
            g.setColor(Color.red);
            g.fillRect((Game.WIDTH - SCORE_WIDTH) / 2, 0, SCORE_WIDTH, 
                    SCORE_WIDTH);
            g.drawRect((Game.WIDTH - SCORE_WIDTH) / 2, 0, SCORE_WIDTH, 
                    SCORE_WIDTH);
            g.setColor(Color.black);
            g.drawString("" + score, (Game.WIDTH - SCORE_WIDTH) / 2 + 22, 40);
        }
    }
    public void setScore(int score) {
        HUD.score = score;
    }
    public int getScore() {
        return HUD.score;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getLevel() {
        return this.level;
    }
}
