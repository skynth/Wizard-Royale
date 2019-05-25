package GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Serializable;

import Main.Handler;
import Main.ID;
import Main.MainMenuPanel;
import Main.WizardRoyale;
import networking.frontend.NetworkMessenger;
/**
 * A class that represents an instance of a projectile. The projectile shoots as soon as it is made.
 * 
 * @author skyfreestylez
 *@version 5/17/19
 */
public class Projectile extends GameObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private double angle;
	private Handler handler;
	private int damage;
	private boolean isOutOfRange;
	private boolean isRight;
	private boolean isUp;
	private String host;
	NetworkMessenger nm;
	
	private transient Image fireball = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "fireball.png");
	/**
	 * Creates an instance of a projectile in which shoots as soon as it's made.
	 * 
	 * 
	 * @param x the x coordinate of the projectile 
	 * @param y the y coordinate of the projectile
	 * @param id the id of the projectile
	 * @param mouseX the x coordinate of the mouse when the projectile is made/shot
	 * @param mouseY the y coordinate of the mouse when the projectile is made/shot
	 * @param h the handler passed in
	 */
	public Projectile(int x, int y, ID id, int mouseX, int mouseY, Handler h, ID subID, String host) {
		super(x, y, id);
		handler = h;
		this.subID = subID;
		this.host = host;
		
		damage = 10;
		
		angle = Math.atan((double)(mouseY - y) / (mouseX - x)); //Not sure if this works yet
		handler = h;
		isOutOfRange = false;
		
		if(mouseX > x)
			isRight = true;
		else {
			isRight = false;
		}
		
		if(mouseY< y) {
			isUp = true;;
		}
		else {
			isUp = false;
		}
	
	}
	
	/**
	 * Continually draws the projectile
	 * @param g the instance of the graphics class that will handle drawing everything in the WizardRoyale class
	 */

	public void render(Graphics g) {
		//g.drawImage(fireball, x, y, null);

		g.setColor(Color.red);
		g.fillOval(x, y, (int)(WizardRoyale.WIDTH / 90), (int)(WizardRoyale.HEIGHT / 56.25));
	}
	
	/**
	 * Continually updates the projetile by moving its coordinates and seeing if it should be removed
	 */

	public void tick() {
		if(isRight && isUp) {

			x += Math.cos(angle) * 17;
			y += Math.sin(angle) * 17;

		}
		else if(isRight && !isUp){

			x += Math.cos(angle) * 17;
			y += Math.sin(angle) * 17;

		}
		else if(isUp && !isRight) {

			x -= Math.cos(angle) * 17;
			y -= Math.sin(angle) * 17;

		}
		else if(!isUp && !isRight){

			x -= Math.cos(angle) * 17;
			y -= Math.sin(angle) * 17;

		}
		
		if(handler.getGameObjects() != null) {
			for (int i = 0; i < handler.getGameObjects().size(); i++) {
				
				if (handler.getGameObjects().get(i).getID() == ID.Wall) {
					
					if (this.getBounds().intersects(handler.getGameObjects().get(i).getBounds())) {
						if(subID == ID.LargeFireProjectile )
						{
							handler.removeObject(handler.getGameObjects().get(i));
						}
						handler.getGameObjects().remove(this);
					}
					
				}
			}
			
		}

		if(isOutOfRange)
			handler.getGameObjects().remove(this);
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, (int)(WizardRoyale.WIDTH / 90), (int)(WizardRoyale.HEIGHT / 56.25));
	}
	
	/*
	 * @return the IP of the computer this projectile came from
	 */
	
	public String getHost() {
		return host;
	}
	
	/*
	 * returns the damage that this projectile will deal
	 */
	
	public int getDamage() {
		return damage;
	}
	
	/*
	 * Sets the handler of the projectile to a new one
	 */
	
	public void setHandler(Handler h) {
		handler = h;
	}

	

}
