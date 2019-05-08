package GameElements;

import java.awt.Graphics;

import Main.ID;

public class Projectile extends GameObject{
	
	private String type;
	private int mouseX, mouseY;

	
	public Projectile(String t, int x, int y, ID id, int mouseX, int mouseY) {
		super(x, y, id);
		type = t;
		this.mouseX = mouseX;
		this.mouseY = mouseY;
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
