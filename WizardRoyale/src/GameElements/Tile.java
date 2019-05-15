package GameElements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import Main.ID;
import Main.MainMenuPanel;
import Main.WizardRoyale;

public class Tile extends GameObject {
	
	private Image wallImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "wall.png");
	private Image wallImageRight = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "wallRight.png");
	private Image wallImageLeft = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "wallleft.png");
	private Image wallImageUp = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "wall1.png");
	private ID subID;
	
	public Tile(int x, int y, ID id, ID subID) {
		super(x , y, id);
		this.subID = subID;
	}

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
