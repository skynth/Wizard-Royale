package Main;

import GameElements.GameObject;

public class Camera {
	
	private float x;
	private float y;
	
	private Handler handler;
	
	public Camera(float x, float y, Handler h) {
		handler = h;
		this.x = x;
		this.y = y;
	}
	
	/*public void tick(GameObject object) {
				
		if (handler.isMoving()) {
		
			if (handler.isDown()) {
				x += ((object.getX() - x) - (int)(WizardRoyale.WIDTH / 0.847) / (int)(WizardRoyale.WIDTH / 720)) * (WizardRoyale.WIDTH / 28800f);
				y += ((object.getY() - y) - (int)(WizardRoyale.HEIGHT / 1.5) / (int)(int)(WizardRoyale.HEIGHT / 450)) * (WizardRoyale.HEIGHT / 18000f);
			}
		
			if (handler.isUp()) {
				x += ((object.getX() - x) - (int)(WizardRoyale.WIDTH / 0.847) / (int)(WizardRoyale.WIDTH / 720)) * (WizardRoyale.WIDTH / 28800f);
				y += ((object.getY() - y) - (int)(WizardRoyale.HEIGHT / 0.9) / (int)(int)(WizardRoyale.HEIGHT / 450)) * (WizardRoyale.HEIGHT / 18000f);
			}
		
			if (handler.isLeft()) {
				x += ((object.getX() - x) - (int)(WizardRoyale.WIDTH / 0.738) / (int)(WizardRoyale.WIDTH / 720)) * (WizardRoyale.WIDTH / 28800f);
				y += ((object.getY() - y) - (int)(WizardRoyale.HEIGHT / 1.125) / (int)(WizardRoyale.HEIGHT / 450)) * (WizardRoyale.HEIGHT / 18000f);
			}
		
			if (handler.isRight()) {
				x += ((object.getX() - x) - (int)(WizardRoyale.WIDTH / 0.929) / (int)(WizardRoyale.WIDTH / 720)) * (WizardRoyale.WIDTH / 28800f);
				y += ((object.getY() - y) - (int)(WizardRoyale.HEIGHT / 1.125) / (int)(WizardRoyale.HEIGHT / 450)) * (WizardRoyale.HEIGHT / 18000f);
			}
			
		} else {
			x += ((object.getX() - x) - (int)(WizardRoyale.WIDTH / 0.847) / (int)(WizardRoyale.WIDTH / 720)) * (WizardRoyale.WIDTH / 28800f);
			y += ((object.getY() - y) - (int)(WizardRoyale.HEIGHT / 1.125) / (int)(WizardRoyale.HEIGHT / 450)) * (WizardRoyale.HEIGHT / 18000f);
		}
	}*/
	
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
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

}
