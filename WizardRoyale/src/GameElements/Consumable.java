package GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Serializable;

import Main.Handler;
import Main.ID;
import Main.MainMenuPanel;
import Main.WizardRoyale;

/**
 * A class representing all items in which are picked up on the map.
 * 
 * @author skyfreestylez
 * @version 5/24/19
 *
 */
public class Consumable extends GameObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private boolean isPickedUp;
	private Handler handler;
	private transient Image medKitImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "medicalKit.png");
	private transient Image fireImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "fireConsumable.png");
	private transient Image armorImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "cloak3.png");
	/**
	 * Constructs an instance of Consumable, given its type
	 * 
	 * @param x The x coordinate of the consumable on the map
	 * @param y The y coordinate of the consumable on the map
	 * @param id The id of the consumable
	 * @param type The type or "Name" of the consumable
	 * @param h the handler passed in
	 */

	public Consumable(int x, int y, ID id, Handler h, ID subID) {
		super(x, y, id);
		isPickedUp = false;
		handler = h;
		this.subID = subID;
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
		if(subID == ID.MedKit)
		{
			g.drawImage(medKitImage, x, y, (int)(WizardRoyale.WIDTH / 30), (int)(WizardRoyale.HEIGHT / 25), null);
		}
		else if(subID == ID.LargeConsumable)
		{
			g.drawImage(fireImage, x, y, (int)(WizardRoyale.WIDTH / 45), (int)(WizardRoyale.HEIGHT / 28.125), null);
		} else if (subID == ID.Armor) {
			g.drawImage(armorImage, x, y, (int)(WizardRoyale.WIDTH / 45), (int)(WizardRoyale.HEIGHT / 28.125), null);
		}
	}

	/**
	 * Continually updates the consumable object by checking if any changes need to be made to it
	 */
	public void tick() {
		if(isPickedUp) {
			handler.getGameObjects().remove(this);
		}
	}
	
	/**
	 * Returns the boundaries of the consumable, represented by a Rectangle
	 * 
	 * @return A rectangle representing the boundaries of the consumable
	 * 
	 */
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, (int)(WizardRoyale.WIDTH / 25), (int)(WizardRoyale.HEIGHT / 30));
	}

	

	
	
}
