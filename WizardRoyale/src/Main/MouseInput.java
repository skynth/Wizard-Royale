package Main;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GameElements.Projectile;
import Main.WizardRoyale.STATE;
import networking.frontend.NetworkManagementPanel;

/**
 * A class that handles input from the user's mouse to the game
 * @author Leofeng 
 * @version 5/10/19
 *
 */

public class MouseInput extends MouseAdapter {
	
	private Handler handler; 
	private Camera camera;

	/**
	 * Creates a new instance of MouseInput
	 * @param h the WizardRoyale's class' instance of the handler class, which handles all the events that occur in the game
	 */
	
	public MouseInput(Handler h, Camera cam) {
		handler = h; 
		camera = cam;
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}


	public void mouseExited(MouseEvent e) {
		
	}
	
	/**
	 * Method that states what actions to take after the user has pressed the mouse
	 * @param e an instance of MouseEvent that represents the user's mouse click
	 * @post the game's state may be changed after the user clicks on a button in the main menu
	 */

	public void mousePressed(MouseEvent e) {
		
		int mouseX = (int) (e.getX() + camera.getX());
		int mouseY = (int) (e.getY() + camera.getY());
		
		if (WizardRoyale.State == STATE.MENU) {
			
			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 4.5 && mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94 && mouseY <= WizardRoyale.HEIGHT / 4.5 + WizardRoyale.HEIGHT / 9) {
				WizardRoyale.State = STATE.GAME;
			}
			
			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 2.57 && mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94 && mouseY <= WizardRoyale.HEIGHT / 2.57 + WizardRoyale.HEIGHT / 9) {
				WizardRoyale.State = STATE.INSTURCTIONS;
			}

			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 1.8 && mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94 && mouseY <= WizardRoyale.HEIGHT / 1.8 + WizardRoyale.HEIGHT / 9) {
				NetworkManagementPanel serverPanel= new NetworkManagementPanel();
			}
			
			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 1.38 && mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94 && mouseY <= WizardRoyale.HEIGHT / 1.38 + WizardRoyale.HEIGHT / 9) {
				System.exit(1);
			}	
		} else if (WizardRoyale.State == STATE.GAME) {
			Projectile p = null;
			
			if (handler.getPlayer().getIsRight()) {
				
				if (mouseX < handler.getPlayer().getX()) {
					handler.getPlayer().setRight(false);
				}
				
				p = new Projectile(handler.getPlayer().getX() + (int)(WizardRoyale.WIDTH / 21), handler.getPlayer().getY() + (int)(WizardRoyale.HEIGHT / 60), ID.Projectile, mouseX, mouseY, handler);
				
			}
			else {
				
				if (mouseX > handler.getPlayer().getX()) {
					handler.getPlayer().setRight(true);
				}
				
				p = new Projectile(handler.getPlayer().getX() - (int)(WizardRoyale.WIDTH / 60), handler.getPlayer().getY() + (int)(WizardRoyale.HEIGHT / 60), ID.Projectile, mouseX, mouseY, handler);
			
			}
			
			handler.getPlayer().setIsShoot(true);
			handler.addObject(p);
		}
		}
		
	

	public void mouseReleased(MouseEvent e) {
		
	}
}


