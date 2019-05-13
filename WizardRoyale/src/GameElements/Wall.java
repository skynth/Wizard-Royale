package GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import Main.ID;
import Main.MainMenuPanel;

public class Wall extends GameObject {
	
	private Image wallImage  = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "wall.png");
	
	public Wall(int x, int y) {
		super(x , y, ID.Wall);
	}

	public void render(Graphics g) {
		g.drawImage(wallImage, xCoord, yCoord, 32, 32, null);
	}


	public void tick() {
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xCoord, yCoord, 32, 32);
	}

}
