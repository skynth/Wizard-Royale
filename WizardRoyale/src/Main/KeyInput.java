package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Thread.State;
import java.util.ArrayList;

import GameElements.Player;
import Main.WizardRoyale.STATE;


/**
 * A class that handles input from the user's keyboard to the game
 * 
 * @author Leofeng
 * @version 5/17/19
 */

public class KeyInput implements KeyListener {

	Handler handler;
	Player player;

	/**
	 * Creates a new instance of KeyInput
	 * 
	 * @param h the WizardRoyale's class' instance of the handler class, which
	 *          handles all the events that occur in the game
	 */

	public KeyInput(Handler h) {
		handler = h;
	}

	/**
	 * @pre user has clicked a key that holds a function in the game Method that
	 *      handles what to do after the user has pressed a certain key that has a
	 *      function in the game
	 * @param e e an instance of KeyEvent that represents a keystroke by the user
	 */

	public void keyPressed(KeyEvent e) {

		ArrayList<Player> players = handler.getPlayers();

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getIp().equals(WizardRoyale.myIP)) {
				player = players.get(i);
			}

		}

		int key = e.getKeyCode();
		if(key == KeyEvent.VK_P && WizardRoyale.State == STATE.INSTURCTIONS) {
			WizardRoyale.State = STATE.MENU;
		}
		if (WizardRoyale.numPlayers > 1) {
			if (key == KeyEvent.VK_W) {
				player.setUp(true);
			}

			if (key == KeyEvent.VK_A) {
				player.setLeft(true);
				player.setRight(false);
			}

			if (key == KeyEvent.VK_S) {
				player.setDown(true);
			}

			if (key == KeyEvent.VK_D) {
				player.setRight(true);
			}

		}

	}

	/**
	 * @pre user has released a key that holds a function in the game Method that
	 *      handles what to do after the user has released a certain key that has a
	 *      function in the game
	 * @param e e an instance of KeyEvent that represents a keystroke by the user
	 */

	public void keyReleased(KeyEvent e) {

		ArrayList<Player> players = handler.getPlayers();

		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getIp().equals(WizardRoyale.myIP)) {
				player = players.get(i);
			}

		}

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			player.setUp(false);
		}

		if (key == KeyEvent.VK_A) {
			player.setLeft(false);
		}

		if (key == KeyEvent.VK_S) {
			player.setDown(false);
		}

		if (key == KeyEvent.VK_D) {
			player.setRight(false);
		}
	}

	public void keyTyped(KeyEvent arg0) {

	}


}
