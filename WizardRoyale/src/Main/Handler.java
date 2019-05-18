package Main;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import GameElements.GameObject;
import GameElements.Player;
import GameElements.Projectile;
import networking.frontend.NetworkDataObject;


/**
 * This class is the class that handles all changes made to all game objects while users are playing the game. It uses an array list of 
 * GameObjects and shifts through them, while checking for conditions to make changes. This class also calls the tick and render methods of 
 * the GameObjects to make sure they are constantly updated
 * 
 * @author Leofeng, Roee
 * @version 5/17/19
 * 
 */

public class Handler implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private transient ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	/**
	 * Shifts through every GameObject currently in the game, and calls the tick method of every object in order to constantly update their status
	 * in case any changes have been made to them
	 */
	
	public void tick() {
		
		for (int i = 0; i < gameObjects.size(); i++) {
			
			GameObject tempObject = gameObjects.get(i);
			tempObject.tick();
			
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
	
	public ArrayList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	/**
	 * returns an arraylist of all the players currently in the game
	 * @return an arraylist of all the players currently in the game
	 */
	
	public ArrayList<Player> getPlayers() {
		
		ArrayList<Player> players = new ArrayList<Player>();
		
		for (int i = 0; i < gameObjects.size(); i++) {
			
			if (gameObjects.get(i).getID() == ID.Player) {
				players.add((Player)gameObjects.get(i));
			}
			
		}
		
		return players;
		
	}
	
	/**
	 * returns an arraylist of all the projectiles currently in the game
	 * @return an arraylist of all the projectiles currently in the game
	 */
	
	public ArrayList<Projectile> getProjectiles() {
		
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
		
		for (int i = 0; i < gameObjects.size(); i++) {
			
			if (gameObjects.get(i).getID() == ID.Projectile) {
				projectiles.add((Projectile)gameObjects.get(i));
			}
			
		}
		
		return projectiles;

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
	


}
