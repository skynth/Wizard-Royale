package Main;
import java.awt.*;
import java.util.LinkedList;

import GameElements.GameObject;


/**
 * This class is the class that handles all changes made to all game objects while users are playing the game. It uses an array list of 
 * GameObjects and shifts through them, while checking for conditions to make changes. This class also calls the tick and render methods of 
 * the GameObjects to make sure they are constantly updated
 * 
 * @author Leofeng, Roee
 * @version 5/7/19
 * 
 */

public class Handler {
	
	LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	
	private boolean isUp = false, isDown, isLeft, isRight;
	
	/**
	 * Shifts through every GameObject currently in the game, and calls the tick method of every object in order to constantly update their status
	 * in case any changes have been made to them
	 */
	
	public void tick() {
		
		for (int i = 0; i < gameObjects.size(); i++) {
			
			GameObject tempObject = gameObjects.get(i);
			tempObject.tick();
			
		}
		
		for (int i = 0; i < gameObjects.size(); i++) {
			
			if (gameObjects.get(i).getID() == ID.Item) {
			
				if (this.getPlayer().getX() - 10 < gameObjects.get(i).getX() && this.getPlayer().getX() + 10 > gameObjects.get(i).getX() && 
					this.getPlayer().getY() - 10 <  gameObjects.get(i).getY() && this.getPlayer().getY() + 10 > gameObjects.get(i).getY()) {
					gameObjects.remove(gameObjects.get(i));
				}
					
			}
			
		}
		
	}
	
	/**
	 * Shifts through every GameObject currently in the game, and calls the render method in order to constantly draw the object in case
	 * changes have been made to it
	 * @param g the instance of the graphics class that will handle drawing everything in the WizardRoyale class
	 */
	
	public void render(Graphics g) {
		
		for (int i = 0; i < gameObjects.size(); i++) {
			
			GameObject tempObject = gameObjects.get(i);
			tempObject.render(g);
			
		}
		
	}
	
	/**
	 * returns the array list containing all the GameObjects currently in the game
	 * @return the array list containing all the GameObjects currently in the game
	 */
	
	public LinkedList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public GameObject getPlayer() {
		
		for (int i = 0; i < gameObjects.size(); i++) {
			
			if (gameObjects.get(i).getID() == ID.Player) {
				return gameObjects.get(i);
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * adds a new game object to the array list of game objects
	 * @param object the new game object to be added to the list
	 * @post a new object will be ended to the end of the gameObjects arraylist
	 */
	
	public void addObject(GameObject object) {
		gameObjects.add(object);
	}
	
	/**
	 * removes a game object from the list of game objects
	 * @param object the game object to be removed from the list
	 * @post a game object will be removed from the list
	 */
	
	public void removeObject(GameObject object) {
		gameObjects.remove(object);
	}
	
	/**
	 * Sets the isUp variable to check
	 * @param check the boolean variable representing whether the player has been moved up
	 * @post : the isUp variable has been set to check
	 */
	
	public void setUp(boolean check) {
		isUp = check;
	}
	
	/**
	 * Sets the isDown variable to check
	 * @param check the boolean variable representing whether the player has been moved down
	 * @post : the isDown variable has been set to check
	 */
	
	public void setDown(boolean check) {
		isDown = check;
	}
	
	/**
	 * Sets the isLeft variable to check
	 * @param check the boolean variable representing whether the player has been moved left
	 * @post : the isLeft variable has been set to check
	 */
	
	public void setLeft(boolean check) {
		isLeft = check;
	}
	
	/**
	 * Sets the isRight variable to check
	 * @param check the boolean variable representing whether the player has been moved right
	 * @post : the isRight variable has been set to check
	 */
	
	public void setRight(boolean check) {
		isRight = check;
	}
	
	/**
	 * returns the isUp variable, which represents whether the 'W' key is currently being pressed
	 * @return a boolean variable representing whether the 'W' key is currently being pressed
	 */

	public boolean isUp() {
		return isUp;
	}
	
	/**
	 * returns the isDown variable, which represents whether the 'S' key is currently being pressed
	 * @return a boolean variable representing whether the 'S' key is currently being pressed
	 */

	public boolean isDown() {
		return isDown;
	}
	
	/**
	 * returns the isLeft variable, which represents whether the 'A' key is currently being pressed
	 * @return a boolean variable representing whether the 'A' key is currently being pressed
	 */

	public boolean isLeft() {
		return isLeft;
	}
	
	/**
	 * returns the isRight variable, which represents whether the 'D' key is currently being pressed
	 * @return a boolean variable representing whether the 'D' key is currently being pressed
	 */

	public boolean isRight() {
		return isRight;
	}

}
