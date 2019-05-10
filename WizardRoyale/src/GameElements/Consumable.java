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
		collisionRect = new Rectangle(x,y,150,30);
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

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(xCoord, yCoord, 150, 30);
	}

	@Override
	public void tick() {
		collisionRect.x = xCoord;
		collisionRect.y = yCoord;
		if(isPickedUp) {
			handler.getGameObjects().remove(this);
		}
	}

	

	
	
}
