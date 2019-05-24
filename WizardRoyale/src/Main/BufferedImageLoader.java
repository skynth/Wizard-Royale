

package Main;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Leofeng
 * @version 5/17/19
 * 
 * takes in the path to an image and creates a buffered image using that path
 * 
 */

public class BufferedImageLoader {
	
	private BufferedImage image;
	
	/**
	 * @param path the path of the file to be converted to a BufferImage
	 * @return The BufferedImage of the file
	 */
	
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
