package GameElements;

import java.awt.Color;
import java.awt.Graphics;

import Main.ID;

public class PickableProjectile extends GameObject implements Consumables {
	
	private boolean isPickedUp;
	private  String type;
	
	
	
	public PickableProjectile(int x, int y, ID id, String type) {
		super(x, y, id);
		isPickedUp = false;
		this.type = type;
		
	}

	@Override
	public void setIsPickedUp(boolean b) {
		isPickedUp = b;
	}
	public boolean getIsPickedUp() {
		return isPickedUp;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(xCoord, yCoord, 150, 30);
	}

	@Override
	public void tick() {

	}

	@Override
	public String getType() {
		return type;
	}


	
	
}
