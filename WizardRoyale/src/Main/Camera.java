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
				xCoord += ((object.getX() - xCoord) - 675 / 2) * 0.05f;
				yCoord += ((object.getY() - yCoord) - 350 / 2) * 0.05f;
			}
		
			if (handler.isUp()) {
				xCoord += ((object.getX() - xCoord) - 675 / 2) * 0.05f;
				yCoord += ((object.getY() - yCoord) - 700 / 2) * 0.05f;
			}
		
			if (handler.isLeft()) {
				xCoord += ((object.getX() - xCoord) - 850 / 2) * 0.05f;
				yCoord += ((object.getY() - yCoord) - 500 / 2) * 0.05f;
			}
		
			if (handler.isRight()) {
				xCoord += ((object.getX() - xCoord) - 500 / 2) * 0.05f;
				yCoord += ((object.getY() - yCoord) - 500 / 2) * 0.05f;
			}
			
		} else {
			xCoord += ((object.getX() - xCoord) - 675 / 2) * 0.05f;
			yCoord += ((object.getY() - yCoord) - 500 / 2) * 0.05f;
		}
	}
	
	public float getX() {
		return xCoord;
	}
	
	public float getY() {
		return yCoord;
	}

}
