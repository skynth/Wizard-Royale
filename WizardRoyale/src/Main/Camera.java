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
				
		if (handler.isMoving()) {
		
			if (handler.isDown()) {
				xCoord += ((object.getX() - xCoord) - (int)(WizardRoyale.WIDTH / 0.847) / (int)(WizardRoyale.WIDTH / 720)) * (WizardRoyale.WIDTH / 28800f);
				yCoord += ((object.getY() - yCoord) - (int)(WizardRoyale.HEIGHT / 1.5) / (int)(int)(WizardRoyale.HEIGHT / 450)) * (WizardRoyale.HEIGHT / 18000f);
			}
		
			if (handler.isUp()) {
				xCoord += ((object.getX() - xCoord) - (int)(WizardRoyale.WIDTH / 0.847) / (int)(WizardRoyale.WIDTH / 720)) * (WizardRoyale.WIDTH / 28800f);
				yCoord += ((object.getY() - yCoord) - (int)(WizardRoyale.HEIGHT / 0.9) / (int)(int)(WizardRoyale.HEIGHT / 450)) * (WizardRoyale.HEIGHT / 18000f);
			}
		
			if (handler.isLeft()) {
				xCoord += ((object.getX() - xCoord) - (int)(WizardRoyale.WIDTH / 0.738) / (int)(WizardRoyale.WIDTH / 720)) * (WizardRoyale.WIDTH / 28800f);
				yCoord += ((object.getY() - yCoord) - (int)(WizardRoyale.HEIGHT / 1.125) / (int)(WizardRoyale.HEIGHT / 450)) * (WizardRoyale.HEIGHT / 18000f);
			}
		
			if (handler.isRight()) {
				xCoord += ((object.getX() - xCoord) - (int)(WizardRoyale.WIDTH / 0.929) / (int)(WizardRoyale.WIDTH / 720)) * (WizardRoyale.WIDTH / 28800f);
				yCoord += ((object.getY() - yCoord) - (int)(WizardRoyale.HEIGHT / 1.125) / (int)(WizardRoyale.HEIGHT / 450)) * (WizardRoyale.HEIGHT / 18000f);
			}
			
		} else {
			xCoord += ((object.getX() - xCoord) - (int)(WizardRoyale.WIDTH / 0.847) / (int)(WizardRoyale.WIDTH / 720)) * (WizardRoyale.WIDTH / 28800f);
			yCoord += ((object.getY() - yCoord) - (int)(WizardRoyale.HEIGHT / 1.125) / (int)(WizardRoyale.HEIGHT / 450)) * (WizardRoyale.HEIGHT / 18000f);
		}
	}
	
	public float getX() {
		return xCoord;
	}
	
	public float getY() {
		return yCoord;
	}

}
