package GameElements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import Main.Handler;
import Main.ID;
import Main.MainMenuPanel;

/**
 * A class which respresents in an instance of a player.
 * 
 * @author Leofeng, Roee, Skyfreestylez
 *
 */
public class Player extends GameObject {
	
	private Handler handler;
	private Image sprite[] = new Image[24];
	private double step = 0;


	public Player(int x, int y, ID id, Handler h) {
		super(x, y, id);
		handler = h;
		for(int i = 0; i < 24; i++)
			sprite[i] = Toolkit.getDefaultToolkit().createImage("Resources" + MainMenuPanel.FILE_SEP + "wizard" + MainMenuPanel.FILE_SEP + i+".gif");

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
		g.drawImage(sprite[(int)step], xCoord, yCoord, 150, 150, null);
		step += 0.1;
		if(step >= 24)
			step = 0;
	}
	
	public void setUp(boolean check) {
		
	}
	
	public void collide(ArrayList<GameObject> objects)
	{
		
	}

}
