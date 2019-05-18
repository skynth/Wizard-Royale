package Main;

import GameElements.GameObject;

/**
 * Camera that moves to center the player when the player moves
 * 
 * @author Leofeng
 * @version 5/17/19
 */

public class Camera {
	
	private float x;
	private float y;
	
	private Handler handler;
	
	/**
	 * Creates a new camera with a set xy coordinate 
	 * 
	 * @param x x coordinate of the camera
	 * @param y y coordinate of the camera
	 * @param h the handler of the game
	 */
	
	public Camera(float x, float y, Handler h) {
		handler = h;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * moves the view of the camera so that it centers on an object
	 * 
	 * @param object the object the camera will be centering on 
	 */
	
	public void tick(GameObject object) {
		this.x += ((object.getX() - this.x) - WizardRoyale.WIDTH / 2) * 0.05f;
		this.y += ((object.getY() - this.y) - WizardRoyale.HEIGHT / 2) * 0.05f;

		if (this.x < 0) {
			this.x = 0;
		}
		if (this.y < 0) {
			this.y = 0;
		}
		if (this.x > 32 * 128 - WizardRoyale.WIDTH + 32) {
			this.x = 32 * 128 - WizardRoyale.WIDTH + 32;
		}
		if (this.y > 32 * 128 - WizardRoyale.HEIGHT + 32) {
			this.y = 32 * 128 - WizardRoyale.HEIGHT + 32;
		}

	}
	
	/**
	 * @return x the x coordinate of the camera
	 */
	
	public float getX() {
		return x;
	}
	
	/**
	 * @return y the y coordinate of the camera
	 */
	
	public float getY() {
		return y;
	}

}
