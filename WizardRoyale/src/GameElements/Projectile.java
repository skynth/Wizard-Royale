package GameElements;

import java.awt.Color;
import java.awt.Graphics;

import Main.Handler;
import Main.ID;

public class Projectile extends GameObject{
	
	private String type;
	private double angle;
	private Handler handler;
	private boolean isOutOfRange;


	
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
	public String getType() {
		return type;
	}
	
	

	

}
