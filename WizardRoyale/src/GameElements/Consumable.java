package GameElements;

import java.awt.Color;
import java.awt.Graphics;

import Main.Handler;
import Main.ID;

public class Consumable extends GameObject{
	
	private boolean isPickedUp;
	private  String type;
	private Handler handler;
	
	
	/**
	 * 
	 * 
	 * @param x The x coordinate of the consumable on the map
	 * @param y The y coordinate of the consumable on the map
	 * @param id The id of the consumable
	 * @param type The type or "Name" of the consumable
	 * @param h the handler passed in
	 */
	public Consumable(int x, int y, ID id, String type, Handler h) {
		super(x, y, id);
		isPickedUp = false;
		this.type = type;
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

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(xCoord, yCoord, 150, 30);
	}

	@Override
	public void tick() {
		if(isPickedUp) {
			handler.getGameObjects().remove(this);
		}
	}
	/**
	 * 
	 * @return the type of "name" of the consumable
	 */
	public String getType() {
		return type;
	}


	
	
}
