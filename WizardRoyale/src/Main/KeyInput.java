package Main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class that handles input from the user's keyboard to the game
 * @author Leofeng
 * @version 5/7/19
 */

public class KeyInput implements KeyListener {
	
	Handler handler;
	
	/**
	 * Creates a new instance of KeyInput
	 * @param h the WizardRoyale's class' instance of the handler class, which handles all the events that occur in the game
	 */
	
	public KeyInput(Handler h) {
		handler = h;
	}
	
	/**
	 * @pre user has clicked a key that holds a function in the game
	 * Method that handles what to do after the user has pressed a certain key that has a function in the game
	 * @param e e an instance of KeyEvent that represents a keystroke by the user
	 */

	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.getGameObjects().size(); i++) {
			
			if (handler.getGameObjects().get(i).getID() == ID.Player) {
				
				if (key == KeyEvent.VK_W) {
					handler.setUp(true);
				}
				
				if (key == KeyEvent.VK_A) {
					handler.setLeft(true);
				}
				
				if (key == KeyEvent.VK_S) {
					handler.setDown(true);
				}
				
				if (key == KeyEvent.VK_D) {
					handler.setRight(true);
				}
			}
		}
		
	}
	
	/**
	 * @pre user has released a key that holds a function in the game
	 * Method that handles what to do after the user has released a certain key that has a function in the game
	 * @param e e an instance of KeyEvent that represents a keystroke by the user
	 */

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.getGameObjects().size(); i++) {
			
			if (handler.getGameObjects().get(i).getID() == ID.Player) {
				
				if (key == KeyEvent.VK_W) {
					handler.setUp(false);
				}
				
				if (key == KeyEvent.VK_A) {
					handler.setLeft(false);
				}
				
				if (key == KeyEvent.VK_S) {
					handler.setDown(false);
				}
				
				if (key == KeyEvent.VK_D) {
					handler.setRight(false);
				}
			}
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		
	}

	
	
}
