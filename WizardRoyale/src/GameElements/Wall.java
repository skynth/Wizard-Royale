package GameElements;

import java.awt.Color;
import java.awt.Graphics;

import Main.ID;

public class Wall extends GameObject {
	
	public Wall(int x, int y) {
		super(x , y, ID.Wall);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(xCoord, yCoord, 32, 32);
	}

	@Override
	public void tick() {
		
	}

}
