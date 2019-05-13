package GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Main.ID;

public class Wall extends GameObject {
	
	public Wall(int x, int y) {
		super(x , y, ID.Wall);
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(xCoord, yCoord, 32, 32);
	}


	public void tick() {
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xCoord, yCoord, 32, 32);
	}

}
