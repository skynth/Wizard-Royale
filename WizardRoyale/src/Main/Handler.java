package Main;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import GameElements.Consumables;
import GameElements.GameObject;

public class Handler {
	
	LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	ArrayList<Consumables> pickables  = new ArrayList<Consumables>();
	//ArrayList<Obstacles> obstacles  = new ArrayList<Obstacles>();
	
	private boolean isUp = false, isDown, isLeft, isRight;
	
	public void tick() {
		
		for (int i = 0; i < gameObjects.size(); i++) {
			
			GameObject tempObject = gameObjects.get(i);
			tempObject.tick();;
			
		}
		
	}
	
	public void render(Graphics g) {
		
		for (int i = 0; i < gameObjects.size(); i++) {
			
			GameObject tempObject = gameObjects.get(i);
			tempObject.render(g);
			
		}
		
	}
	
	public LinkedList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public void addObject(GameObject object) {
		gameObjects.add(object);
	}
	
	public void removeObject(GameObject object) {
		gameObjects.remove(object);
	}
	
	public void setUp(boolean check) {
		isUp = check;
	}
	
	public void setDown(boolean check) {
		isDown = check;
	}
	
	public void setLeft(boolean check) {
		isLeft = check;
	}
	
	public void setRight(boolean check) {
		isRight = check;
	}

	public boolean isUp() {
		return isUp;
	}

	public boolean isDown() {
		return isDown;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public boolean isRight() {
		return isRight;
	}

}
