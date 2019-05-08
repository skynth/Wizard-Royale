package GameElements;

import java.awt.Graphics;

import Main.ID;

public abstract class GameObject {
	
	protected int xCoord = 0;
	protected int yCoord = 0;
	protected ID id;
	
	protected float velX = 0;
	protected float velY = 0;

	public GameObject(int x, int y, ID id) {
		xCoord = x;
		yCoord = y;
		this.id = id;
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public int getX() {
		return xCoord;
	}
	
	public int getY() {
		return yCoord;
	}
	
	public ID getID() {
		return id;
	}

}
