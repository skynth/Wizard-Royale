package GameElements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.LinkedList;

import Main.Handler;
import Main.ID;
import Main.MainMenuPanel;

/**
 * A class which represents in an instance of a player.
 * 
 * @author Leofeng, Roee, Skyfreestylez
 *@version 5/10/19
 */
public class Player extends GameObject {
	
	private Handler handler;
	private Image spriteRight[] = new Image[24];
	private Image spriteLeft[] = new Image[24];
	private Image spriteShoot[] = new Image[15];
	private Image spriteShootLeft[] = new Image[15];
	private double step, shootStep;
	private int health;
	private boolean isRight;
	private boolean isShoot;

	/**
	 * Creates a new instance of a player (Wizard)
	 * @param x the X coordinate the wizard spawns at
	 * @param y the Y coordinate the wizard spawns at
	 * @param id the Id of the wizard
	 * @param h the handler passed in
	 */
	public Player(int x, int y, ID id, Handler h) {
		super(x, y, id);
		handler = h;
		collisionRect = new Rectangle(x,y,150,150);
		for(int i = 0; i < 24; i++) {
			spriteRight[i] = Toolkit.getDefaultToolkit().createImage("Resources" + MainMenuPanel.FILE_SEP + "wizard" + MainMenuPanel.FILE_SEP + i+".gif");
			spriteLeft[i] = Toolkit.getDefaultToolkit().createImage("Resources" + MainMenuPanel.FILE_SEP + "Wizard Left" + MainMenuPanel.FILE_SEP + i+"L.gif");
			if(i < 15) {
				spriteShoot[i] = Toolkit.getDefaultToolkit().createImage("Resources" + MainMenuPanel.FILE_SEP + "wizard shoot" + MainMenuPanel.FILE_SEP + "shoot" + (i+1)+".gif");
				spriteShootLeft[i] = Toolkit.getDefaultToolkit().createImage("Resources" + MainMenuPanel.FILE_SEP + "wizard shoot left" + MainMenuPanel.FILE_SEP + i+".gif");

			}
		}
		health = 100;
		isRight = true;
		step = 0;
		shootStep = 0;
	}
	
	public void tick() {
		xCoord += velX;
		yCoord += velY;
		collisionRect.x = xCoord;
		collisionRect.y = yCoord;
		
		if (handler.isUp()) {
			velY = -5;
		} else if (handler.isDown()) {
			velY = 5;
		} else if (!handler.isDown() && !handler.isUp()) {
			velY = 0;
		}
		
		if (handler.isLeft()) {
			velX = -5;
			isRight = false;
		} else if (handler.isRight()) {
			velX = 5;
			isRight = true;
		} else if (!handler.isLeft() && !handler.isRight()) {
			velX = 0;
		}
			
		this.collide(handler.getGameObjects());
			
	}

	public void render(Graphics g) {
		if(isShoot) {
			if(isRight)
				g.drawImage(spriteShoot[(int)shootStep], xCoord - 75, yCoord, 300, 150, null);
			else if(!isRight)
				g.drawImage(spriteShootLeft[(int)shootStep], xCoord - 75, yCoord, 300, 150, null);

			shootStep += 0.2;
		}
		else if(isRight)
			g.drawImage(spriteRight[(int)step], xCoord, yCoord, 150, 150, null);
		else if(!isRight)
			g.drawImage(spriteLeft[(int)step], xCoord, yCoord, 150, 150, null);

		step += 0.1;
		if(step >= 24)
			step = 0;
		if(shootStep >= 15) {
			isShoot = false;
			shootStep = 0;
		}

	}
	
	public void setUp(boolean check) {
		
	}
	public void setIsShoot(boolean s) {
		isShoot = s;
	}
	public boolean getIsRight() {
		return isRight;
	}
	
	/**
	 * Checks and performs actions based on collisions.
	 * @param objects
	 */
	public void collide(LinkedList<GameObject> objects)
	{
		//collision with pickables
			for (int i = 0; i < objects.size(); i++) 
			{		
				if (objects.get(i).getID() == ID.Item) 
				{
					if (collisionRect.intersects(objects.get(i).getRect())) 
					{
						objects.remove(objects.get(i));
					}
							
				}
			}
	}
	
}
