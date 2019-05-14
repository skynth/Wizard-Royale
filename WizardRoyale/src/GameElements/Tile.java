package GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import Main.ID;
import Main.MainMenuPanel;

public class Tile extends GameObject {
	
	private Image wallImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "wall.png");
	private Image floorImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "Floor.png");
	
	public Tile(int x, int y, ID id) {
		super(x , y, id);
	}

	public void render(Graphics g) {
		if (id == ID.Wall) {
			g.drawImage(wallImage, xCoord, yCoord, 32, 32, null);
		}
	}


	public void tick() {
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xCoord, yCoord, 32, 32);
	}

}
