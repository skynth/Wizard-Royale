package Main;

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
			
			if (mouseX >= 230 && mouseY >= 175 && mouseX <= 560 && mouseY <= 275) {
				WizardRoyale.State = STATE.GAME;
			}
			
			if (mouseX >= 230 && mouseY >= 350 && mouseX <= 560 && mouseY <= 450) {
				WizardRoyale.State = STATE.INSTURCTIONS;
			}
			
			if (mouseX >= 230 && mouseY >= 525 && mouseX <= 560 && mouseY <= 625) {
				System.exit(1);
			}
			
		} else if (WizardRoyale.State == STATE.GAME) {
			Projectile p = new Projectile(handler.getPlayer().getX(), handler.getPlayer().getY(), ID.Projectile, e.getX(), e.getY(), handler);
			
			handler.addObject(p);
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
