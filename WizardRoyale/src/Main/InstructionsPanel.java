package Main;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;

/**
 * The panel that will hold the graphics for the instructions to the game. Will appear when the user clicks the "help" button in the main menu panel
 * @author Leofeng 
 * @version 5/6/19
 *
 */

public class InstructionsPanel {
	
	/**
	 * Method that draws the graphics for the instructions to the game 
	 * @param g the instance of the graphics class that will handle drawing everything in the WizardRoyale class
	 * @post the graphics of the instructions panel will be drawn onto g
	 */

	public void render(Graphics g) {
		
		Font font0 = new Font("arial", Font.BOLD, (int)(WizardRoyale.WIDTH / 28.8));
		g.setFont(font0);
		g.setColor(Color.black);
		g.drawString("INSTRUCTIONS", (int)(WizardRoyale.WIDTH / 3), (int)(WizardRoyale.HEIGHT / 10));
		
	}

}

