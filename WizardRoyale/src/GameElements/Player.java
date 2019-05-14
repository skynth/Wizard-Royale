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
import Main.WizardRoyale;

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
	private int speed = 7;
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
		
		this.collide(handler.getGameObjects());
		this.movement();
		
		if (xCoord < 15) {
			xCoord = 15;
		}
		
		if (xCoord > WizardRoyale.WIDTH) {
			xCoord = WizardRoyale.WIDTH;
		}
		
		if (yCoord < 20) {
			yCoord = 20;
		}
		
		if (yCoord > WizardRoyale.HEIGHT - WizardRoyale.HEIGHT / 17.09) {
			yCoord = (int) (WizardRoyale.HEIGHT - WizardRoyale.HEIGHT / 17.09);
		}
		
			
	}

	public void render(Graphics g) {
		System.out.println(WizardRoyale.WIDTH);
		System.out.println(WizardRoyale.HEIGHT);

		if(isShoot) {
			if(isRight)
				g.drawImage(spriteShoot[(int)shootStep], xCoord - (int)(WizardRoyale.WIDTH / 28.8), yCoord, (int)(WizardRoyale.WIDTH / 7.2), (int)(WizardRoyale.HEIGHT / 9), null);
			else if(!isRight)
				g.drawImage(spriteShootLeft[(int)shootStep], xCoord - (int)(WizardRoyale.WIDTH / 28.8), yCoord, (int)(WizardRoyale.WIDTH / 7.2), (int)(WizardRoyale.HEIGHT / 9), null);

			shootStep += 0.2;
		}
		else if(isRight)
			g.drawImage(spriteRight[(int)step], xCoord, yCoord, (int)(WizardRoyale.WIDTH / 14.4), (int)(WizardRoyale.HEIGHT / 9), null);
		else if(!isRight)
			g.drawImage(spriteLeft[(int)step], xCoord, yCoord, (int)(WizardRoyale.WIDTH / 14.4), (int)(WizardRoyale.HEIGHT / 9), null);

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
						this.xCoord += this.velX * -1;
						this.yCoord += this.velY * -1;
					
					}
				}
			}
	}
	
	private void movement() {
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
	}
	
	public Rectangle getBounds() {
		return new Rectangle(xCoord, yCoord, (int)(WizardRoyale.WIDTH / 14.4), (int)(WizardRoyale.HEIGHT / 9));
	}
	
}
