package Main;

import java.awt.image.BufferedImage;

/**
 * @author Leofeng
 * @version 5/17/19
 * 
 * takes in the path to an image and creates a buffered image using that path
 * 
 */

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	private BufferedImage image;
	
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
