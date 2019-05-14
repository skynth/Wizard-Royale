package GameElements;

import java.awt.Color;
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
	
		if (handler.isUp()) {
			velY = -7.5f;
			
			if (handler.isLeft()) {
				isRight = false;
				velY = -(float) Math.sqrt(Math.pow(7.5, 2) / 2);
				velX = -(float) Math.sqrt(Math.pow(7.5, 2) / 2);	
			}
			else if (handler.isRight()) {
				isRight = true;
				velY = -(float) Math.sqrt(Math.pow(7.5, 2) / 2);
				velX = (float) Math.sqrt(Math.pow(7.5, 2) / 2);	
			}
			
		} else if (handler.isDown()) {
			velY = 7.5f;
			
			if (handler.isLeft()) {
				isRight = false;
				velY = (float) Math.sqrt(Math.pow(7.5, 2) / 2);
				velX = -(float) Math.sqrt(Math.pow(7.5, 2) / 2);	
			}
			else if (handler.isRight()) {
				isRight = true;
				velY = (float) Math.sqrt(Math.pow(7.5, 2) / 2);
				velX = (float) Math.sqrt(Math.pow(7.5, 2) / 2);	
			}
			
		} else if (!handler.isDown() && !handler.isUp()) {
			velY = 0;
		}
		
		if (handler.isLeft()) {
			velX = -7.5f;
			isRight = false;
			
			if (handler.isDown()) {
				velX = -(float) Math.sqrt(Math.pow(7.5, 2) / 2);
				velY = (float) Math.sqrt(Math.pow(7.5, 2) / 2);
			}
			else if (handler.isUp()) {
				velX = -(float) Math.sqrt(Math.pow(7.5, 2) / 2);
				velY = -(float) Math.sqrt(Math.pow(7.5, 2) / 2);
			}
			
		} else if (handler.isRight()) {
			velX = 7.5f;
			isRight = true;
			
			if (handler.isDown()) {
				velX = (float) Math.sqrt(Math.pow(7.5, 2) / 2);
				velY = (float) Math.sqrt(Math.pow(7.5, 2) / 2);
			}
			else if (handler.isUp()) {
				velX = (float) Math.sqrt(Math.pow(7.5, 2) / 2);
				velY = -(float) Math.sqrt(Math.pow(7.5, 2) / 2);
			}
			
		} else if (!handler.isLeft() && !handler.isRight()) {
			velX = 0;
		}
			
		//this.collide(handler.getGameObjects());
			
	}

	public void render(Graphics g) {
		if(isShoot) {
			if(isRight)
				g.drawImage(spriteShoot[(int)shootStep], xCoord - 50, yCoord, 200, 100, null);
			else if(!isRight)
				g.drawImage(spriteShootLeft[(int)shootStep], xCoord - 50, yCoord, 200, 100, null);

			shootStep += 0.2;
		}
		else if(isRight)
			g.drawImage(spriteRight[(int)step], xCoord, yCoord, 100, 100, null);
		else if(!isRight)
			g.drawImage(spriteLeft[(int)step], xCoord, yCoord, 100, 100, null);

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
					if (getBounds().intersects(objects.get(i).getBounds())) 
					{
						objects.remove(objects.get(i));
					}
							
				}
				
				if (objects.get(i).getID() == ID.Wall) {
					
					if (getBounds().intersects(objects.get(i).getBounds())) {
						xCoord += velX * - 1;
						yCoord += velY * - 1;
					}
					
				}
			}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xCoord, yCoord, 100, 100);
	}
	
}
