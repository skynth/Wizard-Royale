package GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;
import Main.ID;

/**
 * A class representing all items in which are picked up on the map.
 * 
 * @author skyfreestylez
 * @version 5/10/19
 *
 */
public class Consumable extends GameObject{
	
	private boolean isPickedUp;
	private Handler handler;
	
	/**
	 * Constructs an instance of Consumable, given its type
	 * 
	 * @param x The x coordinate of the consumable on the map
	 * @param y The y coordinate of the consumable on the map
	 * @param id The id of the consumable
	 * @param type The type or "Name" of the consumable
	 * @param h the handler passed in
	 */

	public Consumable(int x, int y, ID id, Handler h) {
		super(x, y, id);
		isPickedUp = false;
		handler = h;
	}
	/**
	 * 
	 * @param b sets whether the consumable has been picked up or not to this value
	 */
	public void setIsPickedUp(boolean b) {
		isPickedUp = b;
	}
	/**
	 * 
	 * @return True if the consumable has been picked up, false if not.
	 */
	public boolean getIsPickedUp() {
		return isPickedUp;
	}

	/**
	 * Continually draws the consumable object onto the game canvas
	 * @param g the instance of the graphics class that will handle drawing everything in the WizardRoyale class
	 */
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(xCoord, yCoord, 150, 30);
	}

	/**
	 * Continually updates the consumable object by checking if any changes need to be made to it
	 */
	public void tick() {
		if(isPickedUp) {
			handler.getGameObjects().remove(this);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xCoord, yCoord, 150, 150);
	}

	

	
	
}
