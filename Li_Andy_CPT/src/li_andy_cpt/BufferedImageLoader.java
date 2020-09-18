/**
 * Name: Andy Li
 * Date: January 14, 2019
 * Project Name: Angry Flappy Birds
 * Description: this class is responsible for loading the images that are used
 * throughout this program using buffered image.
 */
package li_andy_cpt;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader {

    private BufferedImage image;

    public BufferedImageLoader() {
    }

    /**
     * Description: this method loads a buffered image by reading the path of
     * the image and saving it as an image. If the image is not found, the 
     * program will throw an error.
     *
     * pre condition: path name must be valid and found within the java class
     *
     * post condition: a buffered image will be loaded
     *
     * @param path - this is the path name of the image
     *
     * @return - returns a buffered image
     */
    public BufferedImage LoadImage(String path) {
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
        }
        return image;
    }
}
