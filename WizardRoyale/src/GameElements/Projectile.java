package GameElements;

import java.awt.Color;
import java.awt.Graphics;

import Main.Handler;
import Main.ID;
/**
 * A class that represents an instance of a projectile. The projectile shoots as soon as it is made.
 * 
 * @author skyfreestylez
 *@version 5/10/19
 */
public class Projectile extends GameObject{
	
	private String type;
	private double angle;
	private Handler handler;
	private boolean isOutOfRange;


	/**
	 * Creates an instance of a projectile in which shoots as soon as it's made.
	 * 
	 * @param t The type or "name" of the projectile being made
	 * @param x the x coordinate of the projectile 
	 * @param y the y coordinate of the projectile
	 * @param id the id of the projectile
	 * @param mouseX the x coordinate of the mouse when the projectile is made/shot
	 * @param mouseY the y corrdinate of the mouse when the projectile is made/shot
	 * @param h the handler passed in
	 */
	public Projectile(String t, int x, int y, ID id, int mouseX, int mouseY, Handler h) {
		super(x, y, id);
		type = t;
		angle = Math.atan((y - mouseY) / (mouseX - x)); //Not sure if this works yet
		handler = h;
		isOutOfRange = false;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillRect(xCoord, yCoord, 50, 10);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		xCoord += Math.cos(angle) * 5;
		yCoord += Math.sin(angle) * 5;
		if(isOutOfRange)
			handler.getGameObjects().remove(this);
		
	}
	/**
	 * 
	 * @return the type of the projectile
	 */
	public String getType() {
		return type;
	}
	
	

	

}
