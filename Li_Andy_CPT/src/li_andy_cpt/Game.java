/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class is responsible for everything that happens in this
 * program: from changing the backgrounds, implementing the audio, creating the
 * window, and managing the game states, this is the bulk of the program.
 * 
 */
package li_andy_cpt;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Game extends Canvas implements Runnable {

    /*DIMENSIONS OF THE WINDOW
    width and height of the window follows a 4:3 ratio*/
    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 4 * 3;

    //DECLARATIONS
    private Thread thread;                   //declare a Thread object
    private final Handler handler;           //declare Handler object 
    private final HUD hud;        //declare HUD - heads up display - object
    private final Spawn spawner;             //declare Spawn object
    private final Menu menu;                 //declare a Menu object
    private final FileManager managerAngry;  //declare a FileManager object
    private final FileManager managerFlappy; //declare a FileManager object

    //FILE PATHS
    //music
    private final String filepath = "Music\\\\BackgroundMusic.wav";
    //game backgrounds
    private final String[] background_Names = {"dryVillage.jpg", 
                                               "volcanoVillage.jpg", 
                                               "volcanoVillageBottom.jpg"};
    //game screens
    private final String[] screen_Names = {"helpScreen.png", 
                                           "selectScreen.png", 
                                           "pauseScreen.png", 
                                           "endScreen.png"};
    
    //2D array of file path names
    private final String[][] filenames = {background_Names, screen_Names};
    
    //2D array of buffered images
    private final BufferedImage[][] images = 
            new BufferedImage[filenames[0].length][filenames[1].length];

    //LOCAL VARIABLES
    private boolean running = false;     //determines if the game is running
    public static boolean paused = false; //determines if the game is paused
    public int diff = 0;  //determines the gamemode: 0 is normal , 1 is hard
    private int highScoreAngry = 0, highScoreFlappy = 0;       //high scores

    //these are the different game states
    public enum STATE {
        Menu,
        Select,
        Help,
        Game,
        End
    }

    //game begins in the menu state
    public STATE gameState = STATE.Menu;

    //constructor for the Game
    public Game() {
        playMusic(filepath);
        BufferedImageLoader loader = new BufferedImageLoader();
        
        //for loop loads all the images into a 2d array
        for (int i = 0; i < filenames.length; i++) {
            for (int j = 0; j < filenames[i].length; j++) {
                images[i][j] = loader.LoadImage(filenames[i][j]);
            }
        }

        handler = new Handler(); //instantiate Hanlder object

        hud = new HUD(this);     //instantiate HUD - heads up display - object

        menu = new Menu(this, handler, hud);   //instantiate Menu object

        //adds keyboard and mouse functionaility
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        this.addMouseMotionListener(menu);

        //instantiate Window object
        Window window = new Window(WIDTH, HEIGHT, "Angry Flappy Birds", this);

        //instantiate Spawner object
        spawner = new Spawn(handler, hud, this);

        //instantiate filemanager for both game modes
        managerAngry = new FileManager("AngryBirds.txt");
        managerFlappy = new FileManager("FlappyBirds.txt");
    }

    //starts the game and ensures threads are set before running is true
    public synchronized void start() {
        thread = new Thread(this);
        //threads are starting
        thread.start();
        running = true;
    }

    //ends the game and running is fase
    public synchronized void stop() {
        try {
            //thread stops
            thread.join();
            running = false;
        } catch (InterruptedException e) {
        }
    }

    /**
     * Description: this method updates and renders the game at a constant 60
     * ticks per seconds. This means that each second, there will be 60 
     * updates. As a result, the movement of characters and the images will be 
     * smooth and have little to no delay.
     *
     * pre condition: none
     *
     * post condition: game will update and render and print the frames per
     * second and ticks per second
     */
    @Override
    public void run() {
        
        //causes program to begin right away without having to click screen
        this.requestFocus(); 
        long lastTime = System.nanoTime();

        //this is the number of updates per second
        double amountOfTicks = 60.0;

        //calculate the number of nanoseconds per tick
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            //updates and renders 60 times per second 
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            if (running) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //shows the frames per second and the number of updates
                System.out.println("FPS: " + frames + " , Ticks: " + updates);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    /**
     * Description: this method updates all the parts that make up the game
     * which includes the HUD - heads up display, the spawner, the handler, and
     * the game states.
     *
     * pre condition: none
     *
     * post condition: all objects will be updated
     */
    private void tick() {
        if (gameState == STATE.Game) {
            if (!paused) {
                hud.tick();
                spawner.tick();
                handler.tick();

                /*the following condition statements will add the score of the
                player to the text file 
                 */
                if (HUD.HEALTH <= 0) {
                    
                    /*for Angry Bird game mode, scores are saved in the 
                    angry birds specific file*/
                    if (this.diff == 0) {
                        managerAngry.writeToFile(hud.getLevel(), true);
                        highScoreAngry = managerAngry.getHighScore();
                        
                    /*for Angry Bird game mode, scores are saved in the 
                    angry birds specific file*/
                    } else if (this.diff == 1) {
                        managerFlappy.writeToFile(hud.getScore(), true);
                        highScoreFlappy = managerFlappy.getHighScore();
                    }
                    
                    //resets health and clear frame after game is over
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.object.clear();
                }
            }
        } else if (gameState == STATE.Menu || gameState == STATE.End
                || gameState == STATE.Select) {
            menu.tick();
            handler.tick();
        }
    }

    /**
     * Description: this method renders all the objects in the game
     *
     * pre condition: all images must be loaded 
     *
     * post condition: all the objects will be rendered
     */
    private void render() {
        
        //canvas method that was extracted 
        BufferStrategy bs = this.getBufferStrategy(); 
        if (bs == null) {
            
            //creates 3 buffers within game 
            this.createBufferStrategy(3); 
            return;
        }

        Graphics g = bs.getDrawGraphics(); //get graphics for buffering
        
        ///////////////ALL THE IMAGES IN THE GAME ARE HERE////////////////

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        if (gameState == STATE.Game) {
            
            //FOR THE ANGRY BIRDS GAME MODE
            if (this.diff == 0) {
                
                //draws the image of the background
                g.drawImage(images[0][0], 0, 0, images[0][0].getWidth(), 
                        images[0][0].getHeight(), this);
                
                hud.render(g);
                handler.render(g);
                
                //FOR THE FLAPPY BIRD GAME MODE
            } else if (this.diff == 1) {
                
                //draws the image of the background
                g.drawImage(images[0][1], 0, 0, images[0][1].getWidth(), 
                        images[0][1].getHeight(), this);
                
                handler.render(g);
                hud.render(g);
                
                //draw image of the bottom portion of the background 
                g.drawImage(images[0][2], 0, Game.HEIGHT - 
                        images[0][2].getHeight(), images[0][2].getWidth(), 
                        images[0][2].getHeight(), this);
            }
            
        } else if (gameState == STATE.Menu || gameState == STATE.Help
                || gameState == STATE.End || gameState == STATE.Select) {
            if (null != gameState) {
                
                //switch statement for the game states
                switch (gameState) {
                    case Help:
                        //draws the background for the help screen
                        g.drawImage(images[1][0], 0, 0, images[1][0].getWidth()
                                , images[1][0].getHeight(), this);
                        break;
                        
                    case Select:
                        //draws the background for the select screen
                        g.drawImage(images[1][1], 0, 0, images[1][1].getWidth()
                                , images[1][1].getHeight(), this);
                        break;
                        
                    case End:
                        //draws the background for the end screen
                        g.drawImage(images[1][3], 0, 0, images[1][3].getWidth()
                                , images[1][3].getHeight(), this);
                        break;
                    default:
                        break;
                }
            }
            menu.render(g);
        }
        
        //FOR THE PAUSE SCREEN
        if (paused) {
            //draws the background for the pause screen
            g.drawImage(images[1][2], 0, 0, images[1][2].getWidth(), 
                    images[1][2].getHeight(), this);
        }
        ///////////////////////////////////////////////////////////////////
        g.dispose();
        bs.show();
    }

    //keeps the value of the variable var within the range of the min and max
    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        }
        return var;
    }

    //keeps the value of the variable var within the range of the min and max
    public static int clamp(int var, int min, int max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        }
        return var;
    }

    /**
     * Description: this method plays background music
     *
     * pre condition: the file path must be valid
     *
     * post condition: there will be a background song
     *
     * @param filepath - the file name
     */
    public void playMusic(String filepath) {
        InputStream music;
        try {
            music = new FileInputStream(new File(filepath));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public int getHighScore(int gamemode) {
        if (gamemode == 0) {
            return highScoreAngry;
        }
        return highScoreFlappy;
    }

    public static void main(String[] args) {
        new Game();
    }
}
