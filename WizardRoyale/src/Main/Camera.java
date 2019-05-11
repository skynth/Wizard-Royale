package Main;

import GameElements.GameObject;

public class Camera {
	
	private float xCoord;
	private float yCoord;
	
	public Camera(float x, float y) {
		xCoord = x;
		yCoord = y;
	}
	
	public void tick(GameObject object) {
		xCoord += ((object.getX() - xCoord) - 1000 / 2) * 0.05f;
		yCoord += ((object.getY() - yCoord) - 563 / 2) * 0.05f;
	}
	
	public float getX() {
		return xCoord;
	}
	
	public float getY() {
		return yCoord;
	}

}
