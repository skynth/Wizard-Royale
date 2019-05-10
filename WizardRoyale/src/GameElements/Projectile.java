package GameElements;

import java.awt.Graphics;

import Main.Handler;
import Main.ID;

public class Projectile extends GameObject{
	
	private String type;
	private int mouseX, mouseY;
	private Handler handler;


	
	public Projectile(String t, int x, int y, ID id, int mouseX, int mouseY, Handler h) {
		super(x, y, id);
		type = t;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		handler = h;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
		
	}
	public String getType() {
		return type;
	}
	
	

	

}
