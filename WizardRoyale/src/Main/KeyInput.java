package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import GameElements.Player;
import networking.frontend.NetworkDataObject;
import networking.frontend.NetworkMessenger;

/**
 * A class that handles input from the user's keyboard to the game
 * 
 * @author Leofeng
 * @version 5/17/19
 */

public class KeyInput implements KeyListener {

	Handler handler;
	Player player;
	NetworkMessenger nm;

	/**
	 * Creates a new instance of KeyInput
	 * 
	 * @param h the WizardRoyale's class' instance of the handler class, which
	 *          handles all the events that occur in the game
	 */

	public KeyInput(Handler h, NetworkMessenger nm) {
		handler = h;
		this.nm = nm;
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
		if (WizardRoyale.numPlayers > 1) {
			if (key == KeyEvent.VK_W) {
				player.setUp(true);
				nm.sendMessage(NetworkDataObject.MESSAGE, "MOVE", player.getX(), player.getY());
			}

			if (key == KeyEvent.VK_A) {
				player.setLeft(true);
				player.setRight(false);
				nm.sendMessage(NetworkDataObject.MESSAGE, "MOVE", player.getX(), player.getY());
			}

			if (key == KeyEvent.VK_S) {
				player.setDown(true);
				nm.sendMessage(NetworkDataObject.MESSAGE, "MOVE", player.getX(), player.getY());
			}

			if (key == KeyEvent.VK_D) {
				player.setRight(true);
				nm.sendMessage(NetworkDataObject.MESSAGE, "MOVE", player.getX(), player.getY());
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
