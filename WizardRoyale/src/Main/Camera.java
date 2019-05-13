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
				xCoord += ((object.getX() - xCoord) - 1700 / 2) * 0.05f;
				yCoord += ((object.getY() - yCoord) - 600 / 2) * 0.05f;
			}
		
			if (handler.isUp()) {
				xCoord += ((object.getX() - xCoord) - 1700 / 2) * 0.05f;
				yCoord += ((object.getY() - yCoord) - 1000 / 2) * 0.05f;
			}
		
			if (handler.isLeft()) {
				xCoord += ((object.getX() - xCoord) - 1950 / 2) * 0.05f;
				yCoord += ((object.getY() - yCoord) - 800 / 2) * 0.05f;
			}
		
			if (handler.isRight()) {
				xCoord += ((object.getX() - xCoord) - 1550 / 2) * 0.05f;
				yCoord += ((object.getY() - yCoord) - 800 / 2) * 0.05f;
			}
			
		} else {
			xCoord += ((object.getX() - xCoord) - 1700 / 2) * 0.05f;
			yCoord += ((object.getY() - yCoord) - 800 / 2) * 0.05f;
		}
	}
	
	public float getX() {
		return xCoord;
	}
	
	public float getY() {
		return yCoord;
	}

}
