package GameElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;
import Main.ID;
/**
 * A class that represents an instance of a projectile. The projectile shoots as soon as it is made.
 * 
 * @author skyfreestylez
 *@version 5/10/19
 */
public class Projectile extends GameObject{
	
	private double angle;
	private Handler handler;
	private boolean isOutOfRange;
	private boolean isRight;
	private boolean isUp;

	/**
	 * Creates an instance of a projectile in which shoots as soon as it's made.
	 * 
	 * 
	 * @param x the x coordinate of the projectile 
	 * @param y the y coordinate of the projectile
	 * @param id the id of the projectile
	 * @param mouseX the x coordinate of the mouse when the projectile is made/shot
	 * @param mouseY the y corrdinate of the mouse when the projectile is made/shot
	 * @param h the handler passed in
	 */
	public Projectile(int x, int y, ID id, int mouseX, int mouseY, Handler h) {
		super(x, y, id);

		angle = Math.atan((double)(mouseY - y) / (mouseX - x)); //Not sure if this works yet
		handler = h;
		isOutOfRange = false;
		
		if(mouseX > xCoord)
			isRight = true;
		else {
			isRight = false;
		}
		
		if(mouseY< yCoord) {
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
		g.setColor(Color.red);
		g.fillRect(xCoord, yCoord, 50, 10);
	}
	
	/**
	 * Continually updates the projetile by moving its coordinates and seeing if it should be removed
	 */

	public void tick() {
		if(isRight && isUp) {
			xCoord += Math.cos(angle) * 15;
			yCoord += Math.sin(angle) * 15;

		}
		else if(isRight && !isUp){
			xCoord += Math.cos(angle) * 15;
			yCoord += Math.sin(angle) * 15;
		}
		else if(isUp && !isRight) {
			xCoord -= Math.cos(angle) * 15;
			yCoord -= Math.sin(angle) * 15;
		}
		else if(!isUp && !isRight){
			xCoord -= Math.cos(angle) * 15;
			yCoord -= Math.sin(angle) * 15;
		}

		if(isOutOfRange)
			handler.getGameObjects().remove(this);
		
	}

	public Rectangle getBounds() {
		return null;
	}

	

}
