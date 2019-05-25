package GameElements;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Serializable;

import Main.ID;
import Main.MainMenuPanel;
import Main.WizardRoyale;

/**
 * @author Leofeng
 * @version 5/17/19
 *
 * A class that represents a terrain object in our map. 
 *
 */

public class Tile extends GameObject implements Serializable {
	
	private static final long serialVersionUID = 3L;
	private Image wallImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "wall.png");
	private Image wallImageRight = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "wallRight.png");
	private Image wallImageLeft = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "wallleft.png");
	private Image wallImageUp = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "wall1.png");

	
	/**
	 * Creates a new block of terrain, with xy coordinates and a subID to tell what terrain it is
	 * @param x the x coordinate of the block
	 * @param y the y coordinate of the block
	 * @param id what type of game object the terrain is
	 * @param subID what type of terrain the tile is
	 */
	
	public Tile(int x, int y, ID id, ID subID) {
		super(x , y, id);
		this.subID = subID;
	}
	
	/**
	 * Continually draws the walls 
	 */

	public void render(Graphics g) {
		if (subID == ID.Wall) {
			g.drawImage(wallImage, x, y, (int)(WizardRoyale.WIDTH / 45), (int)(WizardRoyale.HEIGHT / 28.125), null);
		}
		
		if (subID == ID.WallRight) {
			g.drawImage(wallImageRight, x, y, (int)(WizardRoyale.WIDTH / 45), (int)(WizardRoyale.HEIGHT / 28.125), null);
		}
		
		if (subID == ID.WallLeft) {
			g.drawImage(wallImageLeft, x, y, (int)(WizardRoyale.WIDTH / 45), (int)(WizardRoyale.HEIGHT / 28.125), null);
		}
		
		if (subID == ID.WallUp) {
			g.drawImage(wallImageUp, x, y, (int)(WizardRoyale.WIDTH / 45), (int)(WizardRoyale.HEIGHT / 28.125), null);
		}
		
	}


	public void tick() {
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, (int)(WizardRoyale.WIDTH / 45), (int)(WizardRoyale.HEIGHT / 28.125));
	}
	
	

}
