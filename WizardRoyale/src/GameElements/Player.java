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
	private int speed = 8;
	private boolean isRight;
	private boolean isShoot;
	private String ip;

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
		x += velX;
		y += velY;
		
		if(health <= 0)
		{
			handler.getGameObjects().remove(this);
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
			if(isRight)
				g.drawImage(spriteShoot[(int)shootStep], x - (int) (WizardRoyale.WIDTH / 48), y, (int)(WizardRoyale.WIDTH / 12), (int)(WizardRoyale.HEIGHT / 15), null);
			else if(!isRight)
				g.drawImage(spriteShootLeft[(int)shootStep], x - (int) (WizardRoyale.WIDTH / 48), y, (int)(WizardRoyale.WIDTH / 12), (int)(WizardRoyale.HEIGHT / 15), null);

			shootStep += 1;
		}
		else if(isRight)
			g.drawImage(spriteRight[(int)step], x, y, (int)(WizardRoyale.WIDTH / 24), (int)(WizardRoyale.HEIGHT / 15), null);
		else if(!isRight)
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
	
	public void setUp(boolean check) {
		
	}
	public boolean getIsShoot() {
		return isShoot;
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
	public void collide(ArrayList<GameObject> objects)
	{
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
//						else if(objects.get(i).getSubID() == ID.invincibility) {
//							
//						}

						

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
					if (getBounds().intersects(objects.get(i).getBounds())) {
				
						health -= 10;
						objects.remove(objects.get(i));
						System.out.println(health);
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
		if (handler.isUp()) {
			this.velY = -speed;
		} else if (!handler.isDown()) {
			this.velY = 0;
		}
		if (handler.isDown()) {
			this.velY = speed;
		} else if (!handler.isUp()) {
			this.velY = 0;
		}
		if (handler.isLeft()) {
			this.velX = -speed;
			isRight = false;
		} else if (!handler.isRight()) {
			this.velX = 0;
			
		}
		if (handler.isRight()) {
			this.velX = speed;
			isRight = true;
		} else if (!handler.isLeft()) {
			this.velX = 0;
		}
	}
	public int getHealth() {
		return health;
	}
	
	public void setRight(boolean check) {
		isRight = check;
	}
	
	public String getIp() {
		return ip;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, (int)(WizardRoyale.WIDTH / 24), (int)(WizardRoyale.HEIGHT / 15));
	}
	
}
