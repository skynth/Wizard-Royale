package Main;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * The panel that will hold the graphics for the instructions to the game. Will appear when the user clicks the "help" button in the main menu panel
 * @author Leofeng 
 * @version 5/6/19
 *
 */

public class InstructionsPanel {
	
	Image background = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "Instructions_background.jpg");	
	/**
	 * Method that draws the graphics for the instructions to the game 
	 * @param g the instance of the graphics class that will handle drawing everything in the WizardRoyale class
	 * @post the graphics of the instructions panel will be drawn onto g
	 */

	public void render(Graphics g) {
		
		g.drawImage(background, 0, 0, null);
		Font font0 = new Font("arial", Font.BOLD, (int)(WizardRoyale.WIDTH / 28.8));
		g.setFont(font0);
		
		Rectangle boundingRect = new Rectangle(WizardRoyale.WIDTH / 3, (int)(WizardRoyale.HEIGHT / 4.5), (int)(WizardRoyale.WIDTH / 2.94), WizardRoyale.HEIGHT / 9);
		
		FontMetrics metrics = g.getFontMetrics(font0);
	    int x = (WizardRoyale.WIDTH - metrics.stringWidth("INSTRUCTIONS")) / 2;
	    int y = 110;
	    g.setFont(font0);
	    
	    g.setColor(Color.white);
	    g.drawString("INSTRUCTIONS", x, y);
	    
		
	}

}

