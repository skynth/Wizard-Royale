package GameElements;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import Main.ID;

/**
 * This class represents every object in our game, for example a player or an item. It provides methods and fields that all objects in
 * our game share.
 * @author Leofeng, Roee, Sky
 * @version 5/7/19
 */

public abstract class GameObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * field that represents the X coordinate of an object in the game 
	 */
	protected int x = 0;
	
	/**
	 * field that represents the Y coordinate of an object in the game 
	 */
	protected int y = 0;
	
	/**
	 * field that represents what specific type of object the object is, e.g armor or health consumable
	 */
	
	protected ID id;
	
	/**
	 * field that represents what specific type of object the object is, e.g armor or health consumable
	 */
	
	protected ID subID;
	
	/**
	 * field that represents the X direction velocity of an object in the game
	 */

	protected float velX = 0;
	
	/**
	 * field that represents the Y direction velocity of an object in the game
	 */
	
	protected float velY = 0;
	
	/**
	 * field that represents the collision rectangle of an object in the game
	 */
	
	protected Rectangle collisionRect;
	
	
	/**
	 * Constructs an object in the game with (x,y) coordinates and an ID to know what type of object it is
	 * 
	 * @param x the X coordinate of the object
	 * @param y the Y coordinate of the object
	 * @param id the specific type of object 
	 * 
	 */

	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
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
		return x;
	}
	
	public float getVelY() {
		return velY;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public void setVelX(float velx) {
		velX = velx;
	}
	
	public void setVelY(float vely) {
		velY = vely;
	}
	
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Returns the Y coordinate of the game object
	 * @return the y coordinate of the game object
	 */
	
	public int getY() {
		return y;
	}
	
	/**
	 * Returns the ID of the game object
	 * @return the ID the game object
	 */
	
	public ID getID() {
		return id;
	}
	
	/**
	 * Returns the subID of the game object
	 * @return the subID the game object
	 */
	public ID getSubID()
	{
		return subID;
	}
	
	/**
	 * Returns the collision rectangle of the game object
	 * @return the rectangle of the game object
	 */
	public Rectangle getBounds()
	{
		return collisionRect;
	}

	
	
}
