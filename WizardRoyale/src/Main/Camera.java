package Main;

import GameElements.GameObject;

public class Camera {
	
	private float xCoord;
	private float yCoord;
	
	private Handler handler;
	
	public Camera(float x, float y, Handler h) {
		handler = h;
		xCoord = x;
		yCoord = y;
	}
	
	public void tick(GameObject object) {
		this.xCoord += ((object.getX() - this.xCoord) - WizardRoyale.WIDTH / 2) * 0.05f;
		this.yCoord += ((object.getY() - this.yCoord) - WizardRoyale.HEIGHT / 2) * 0.05f;

		if (this.xCoord < 0) {
			this.xCoord = 0;
		}
		if (this.yCoord < 0) {
			this.yCoord = 0;
		}
		if (this.xCoord > WizardRoyale.WIDTH + 48) {
			this.xCoord = WizardRoyale.WIDTH + 48;
		}
		if (this.yCoord > WizardRoyale.HEIGHT + 52) {
			this.yCoord = WizardRoyale.HEIGHT + 52;
		}

	}
	
	public float getX() {
		return xCoord;
	}
	
	public float getY() {
		return yCoord;
	}

}
