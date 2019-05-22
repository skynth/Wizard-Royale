package GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import Main.Handler;
import Main.ID;
import Main.MainMenuPanel;
import Main.WizardRoyale;
import Main.WizardRoyale.STATE;

/**
 * A class which represents in an instance of a player.
 * 
 * @author Leofeng, Roee, Skyfreestylez
 *@version 5/17/19
 */
public class Player extends GameObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	private Handler handler;
	private boolean isUp = false, isDown, isLeft, isRight;
	private transient Image spriteRight[] = new Image[24];
	private transient Image spriteLeft[] = new Image[24];
	private transient Image spriteShoot[] = new Image[15];
	private transient Image spriteShootLeft[] = new Image[15];
	private double step, shootStep;
	private int health;
	private int speed = 8;
	private boolean isAnimationRight;
	private boolean isShoot;
	private ID projectileType;
	private String ip;

	/**
	 * Creates a new instance of a player (Wizard)
	 * @param x the X coordinate the wizard spawns at
	 * @param y the Y coordinate the wizard spawns at
	 * @param id the Id of the wizard
	 * @param h the handler passed in
	 */
	public Player(int x, int y, ID id, String ip, Handler h, ID projectileType) {
		super(x, y, id);
		handler = h;
		this.projectileType = projectileType;
		for(int i = 0; i < 24; i++) {
			spriteRight[i] = Toolkit.getDefaultToolkit().createImage("Resources" + MainMenuPanel.FILE_SEP + "wizard" + MainMenuPanel.FILE_SEP + i+".gif");
			spriteLeft[i] = Toolkit.getDefaultToolkit().createImage("Resources" + MainMenuPanel.FILE_SEP + "Wizard Left" + MainMenuPanel.FILE_SEP + i+"L.gif");
			if(i < 15) {
				spriteShoot[i] = Toolkit.getDefaultToolkit().createImage("Resources" + MainMenuPanel.FILE_SEP + "wizard shoot" + MainMenuPanel.FILE_SEP + "shoot" + (i+1)+".gif");
				spriteShootLeft[i] = Toolkit.getDefaultToolkit().createImage("Resources" + MainMenuPanel.FILE_SEP + "wizard shoot left" + MainMenuPanel.FILE_SEP + i+".gif");

			}
		}
		health = 100;
		isAnimationRight = true;
		step = 0;
		shootStep = 0;
		this.ip = ip;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(health <= 0)
		{
			handler.getGameObjects().remove(this);
			//if (handler.getPlayers().size()  1) {
				WizardRoyale.State = STATE.WINSCREEN;
			//}
		}
		
		this.collide(handler.getGameObjects());
		this.movement();
		
		if (x < 15) {
			x = 15;
		}
		
		if (x > 31 * 128) {
			x = 31 * 128;
		}
		
		if (y < 20) {
			y = 20;
		}
		
		if (y > 31 * 128) {
			y = 31 * 128;
		}

			
	}

	public void render(Graphics g) {

		if(isShoot) {
			if(isAnimationRight)
				g.drawImage(spriteShoot[(int)shootStep], x - (int) (WizardRoyale.WIDTH / 48), y, (int)(WizardRoyale.WIDTH / 12), (int)(WizardRoyale.HEIGHT / 15), null);
			else if(!isAnimationRight)
				g.drawImage(spriteShootLeft[(int)shootStep], x - (int) (WizardRoyale.WIDTH / 48), y, (int)(WizardRoyale.WIDTH / 12), (int)(WizardRoyale.HEIGHT / 15), null);

			shootStep += 1;
		}
		else if(isAnimationRight)
			g.drawImage(spriteRight[(int)step], x, y, (int)(WizardRoyale.WIDTH / 24), (int)(WizardRoyale.HEIGHT / 15), null);
		else if(!isAnimationRight)
			g.drawImage(spriteLeft[(int)step], x, y, (int)(WizardRoyale.WIDTH / 24), (int)(WizardRoyale.HEIGHT / 15), null);

		step += 0.;
		if(step >= 24)
			step = 0;
		if(shootStep >= 15) {
			isShoot = false;
			shootStep = 0;
		}
		
		g.setColor(Color.green);
		g.fillRect(this.x, this.y + WizardRoyale.HEIGHT / 15, (WizardRoyale.WIDTH / 24) * health/100, 10);

	}
	
	public boolean getIsShoot() {
		return isShoot;
	}
	public void setIsShoot(boolean s) {
		isShoot = s;
	}
	public boolean getIsRight() {
		return isAnimationRight;
	}
	
	/**
	 * Checks and performs actions based on collisions.
	 * @param objects the arraylist of all game objects in the game
	 */
	public void collide(ArrayList<GameObject> objects)
	{
		if(objects != null) {
			for (int i = 0; i < objects.size(); i++) 
			{		
				if (objects.get(i).getID() == ID.Item) 
				{
					if (getBounds().intersects(objects.get(i).getBounds())) 
					{
						if(objects.get(i).getSubID() == ID.MedKit)
						{	
								if(health>=50) 
								{
									health = 100;
								}
								else 
								{
									health +=50;
								}
						}
						else if(objects.get(i).getSubID() == ID.LargeConsumable) 
						{
							health -=100;
							projectileType = ID.LargeFireProjectile;
					    }

						

						objects.remove(objects.get(i));
					}

							
				}
				
				else if (objects.get(i).getID() == ID.Wall) {
					
					if (getBounds().intersects(objects.get(i).getBounds())) {
						this.x += this.velX * -1;
						this.y += this.velY * -1;
					
					}
				}
				else if(objects.get(i).getID() == ID.Projectile) {
					Projectile p = (Projectile) objects.get(i);
					
					if (getBounds().intersects(objects.get(i).getBounds()) && !(p.getHost().equals(this.ip))) {
				
						health -= 10;
						
						objects.remove(objects.get(i));

					}
				}
			
				
				
			}
	}
	}
	
	/*private void movement() {
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
	}*/

	
	private void movement() {
		if (this.isUp()) {
			this.velY = -speed;
		} else if (!this.isDown()) {
			this.velY = 0;
		}
		if (this.isDown()) {
			this.velY = speed;
		} else if (!this.isUp()) {
			this.velY = 0;
		}
		if (this.isLeft()) {
			this.velX = -speed;
			this.isAnimationRight = false;
		} else if (!this.isRight()) {
			this.velX = 0;
			
		}
		if (this.isRight()) {
			this.velX = speed;
			this.isAnimationRight = true;
		} else if (!this.isLeft()) {
			this.velX = 0;
		}
	}
	

	public int getHealth() {
		return health;
	}
	
	/**
	 * Sets the isUp variable to check
	 * @param check the boolean variable representing whether the player has been moved up
	 * @post : the isUp variable has been set to check
	 */
	
	public void setUp(boolean check) {
		isUp = check;
	}
	
	/**
	 * Sets the isDown variable to check
	 * @param check the boolean variable representing whether the player has been moved down
	 * @post : the isDown variable has been set to check
	 */
	
	public void setDown(boolean check) {
		isDown = check;
	}
	
	/**
	 * Sets the isLeft variable to check
	 * @param check the boolean variable representing whether the player has been moved left
	 * @post : the isLeft variable has been set to check
	 */
	
	public void setLeft(boolean check) {
		isLeft = check;
	}
	
	/**
	 * Sets the isRight variable to check
	 * @param check the boolean variable representing whether the player has been moved right
	 * @post : the isRight variable has been set to check
	 */
	
	public void setRight(boolean check) {
		isRight = check;
	}
	
	/**
	 * returns the isUp variable, which represents whether the 'W' key is currently being pressed
	 * @return a boolean variable representing whether the 'W' key is currently being pressed
	 */

	public boolean isUp() {
		return isUp;
	}
	
	/**
	 * returns the isDown variable, which represents whether the 'S' key is currently being pressed
	 * @return a boolean variable representing whether the 'S' key is currently being pressed
	 */

	public boolean isDown() {
		return isDown;
	}
	
	/**
	 * returns the isLeft variable, which represents whether the 'A' key is currently being pressed
	 * @return a boolean variable representing whether the 'A' key is currently being pressed
	 */

	public boolean isLeft() {
		return isLeft;
	}
	
	/**
	 * returns the isRight variable, which represents whether the 'D' key is currently being pressed
	 * @return a boolean variable representing whether the 'D' key is currently being pressed
	 */

	public boolean isRight() {
		return isRight;
	}
	
	public boolean isMoving() {
		return isRight || isLeft || isUp || isDown;
				
	}
	
	public void setAnimationRight(boolean check) {
		isAnimationRight = check;
	}
	
	public String getIp() {
		return ip;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, (int)(WizardRoyale.WIDTH / 24), (int)(WizardRoyale.HEIGHT / 15));
	}
	
	public ID getProjectileType()
	{
		return projectileType;
	}
	
	public void setProjectileType(ID type)
	{
		projectileType = type;
	}
	
}
