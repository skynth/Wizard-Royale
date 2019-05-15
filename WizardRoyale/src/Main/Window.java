package Main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * The JFrame of our game, stores the canvas in the game class that contains and controls the graphics for our game
 * @author Leofeng 
 * @version 5/6/19
 */

public class Window {
	
	/**
	 * Constructs a new window that has a specified width, height, and title, and contains an instance of our game. Also sets the options
	 * for the window
	 * 
	 * @param width the width of the window
	 * @param height the height of the window
	 * @param title the title of our game 
	 * @param wizardRoyale the instance of our game that will be added to this window
	 */
	
	public Window(int width, int height, String title, WizardRoyale wizardRoyale) {
		
		JFrame window = new JFrame("Wizard Royale");
		window.add(wizardRoyale);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
	}
	

	public static void main (String[] args) {
		
		NetworkManagementPanel nmp = new NetworkManagementPanel();
		
		new WizardRoyale(nmp.getMessageServer());
		
	}
	
}
