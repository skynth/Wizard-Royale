package GameElements;

import java.awt.Graphics;

import Main.ID;

/**
 * This class represents every object in our game, for example a player or an item. It provides methods and fields that all objects in
 * our game share.
 * @author Leofeng, Roee
 * @version 5/7/19
 */

public abstract class GameObject {
	
	/**
	 * field that represents the X coordinate of an object in the game 
	 */
	protected int xCoord = 0;
	
	/**
	 * field that represents the Y coordinate of an object in the game 
	 */
	protected int yCoord = 0;
	
	/* 
	 * field that represents what specific type of object the object is, e.g armor or health consumable
	 */
	
	protected ID id;
	
	/**
	 * field that represents the X direction velocity of an object in the game
	 */

	protected float velX = 0;
	
	/**
	 * field that represents the Y direction velocity of an object in the game
	 */
	
	protected float velY = 0;
	
	
	/**
	 * Constructs an object in the game with (x,y) coordinates and an ID to know what type of object it is
	 * 
	 * @param x the X coordinate of the object
	 * @param y the Y coordinate of the object
	 * @param id the specific type of object 
	 * 
	 */

	public GameObject(int x, int y, ID id) {
		xCoord = x;
		yCoord = y;
		this.id = id;
	}
	
	/**
	 * 
	 * Abstract shell for the render method, which will handle drawing the GameObject
	 * @param g the instance of the graphics class that will handle drawing everything in the WizardRoyale class
	 * 
	 */
	
	public abstract void render(Graphics g);
	
	/**
	 * Abstract shell for the tick method, which will handle changes made to the objects in the game (e.g the player moves)
	 */
	
	public abstract void tick();
	
	/**
	 * Returns the X coordinate of the game object
	 * @return the x coordinate of the game object
	 */
	
	public int getX() {
		return xCoord;
	}
	
	/**
	 * Returns the Y coordinate of the game object
	 * @return the y coordinate of the game object
	 */
	
	public int getY() {
		return yCoord;
	}
	
	/**
	 * Returns the ID of the game object
	 * @return the ID the game object
	 */
	
	public ID getID() {
		return id;
	}

}
