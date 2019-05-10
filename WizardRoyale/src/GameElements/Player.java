package GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import Main.Handler;
import Main.ID;
import Main.MainMenuPanel;

public class Player extends GameObject {
	
	private Handler handler;
	private Image sprite;

	public Player(int x, int y, ID id, Handler h) {
		super(x, y, id);
		handler = h;
		sprite = Toolkit.getDefaultToolkit().createImage("Resources" + MainMenuPanel.FILE_SEP + "wizard.gif");
		
	}
	
	public void tick() {
		xCoord += velX;
		yCoord += velY;
		
		if (handler.isUp()) {
			velY = -5;
		} else if (handler.isDown()) {
			velY = 5;
		} else if (!handler.isDown() && !handler.isUp()) {
			velY = 0;
		}
		
		if (handler.isLeft()) {
			velX = -5;
		} else if (handler.isRight()) {
			velX = 5;
		} else if (!handler.isLeft() && !handler.isRight()) {
			velX = 0;
		}
			
		//this.collide(pickables);
	}

	public void render(Graphics g) {
		g.drawImage(sprite, xCoord, yCoord, 100, 100, null);

	}
	
	public void setUp(boolean check) {
		
	}
	
	public void collide(ArrayList<GameObject> objects)
	{
		
	}

}
