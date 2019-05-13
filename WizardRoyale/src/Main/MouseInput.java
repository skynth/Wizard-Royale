package Main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GameElements.Projectile;
import Main.WizardRoyale.STATE;

/**
 * A class that handles input from the user's mouse to the game
 * @author Leofeng 
 * @version 5/10/19
 *
 */

public class MouseInput implements MouseListener {
	
	private Handler handler; //will be used later

	/**
	 * Creates a new instance of MouseInput
	 * @param h the WizardRoyale's class' instance of the handler class, which handles all the events that occur in the game
	 */
	
	public MouseInput(Handler h) {
		handler = h; 
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
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (WizardRoyale.State == STATE.MENU) {
			
			if (mouseX >= 704 && mouseY >= 250 && mouseX <= 1194 && mouseY <= 350) {
				WizardRoyale.State = STATE.GAME;
			}
			
			if (mouseX >= 704 && mouseY >= 425 && mouseX <= 1194 && mouseY <= 525) {
				WizardRoyale.State = STATE.INSTURCTIONS;
			}
			
			if (mouseX >= 704 && mouseY >= 600 && mouseX <= 1194 && mouseY <= 700) {
				NetworkManagementPanel serverPanel= new NetworkManagementPanel();
			}
			
			if (mouseX >= 704 && mouseY >= 775 && mouseX <= 1194 && mouseY <= 875) {
				System.exit(1);
			}
			
		} else if (WizardRoyale.State == STATE.GAME) {
			Projectile p = null;
			if(handler.getPlayer().getIsRight())
				 p = new Projectile(handler.getPlayer().getX() +120, handler.getPlayer().getY() + 20, ID.Projectile, e.getX(), e.getY(), handler);
			else {
				 p = new Projectile(handler.getPlayer().getX() - 20, handler.getPlayer().getY() + 20, ID.Projectile, e.getX(), e.getY(), handler);

			}
			handler.getPlayer().setIsShoot(true);
			handler.addObject(p);
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
