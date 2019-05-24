package Main;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * The panel that will hold the graphics for the instructions to the game. Will appear when the user clicks the "help" button in the main menu panel
 * @author Leofeng 
 * @version 5/24/19
 *
 */

public class InstructionsPanel {
	
	Image background = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "Instructions_background.jpg");	
	
	private Image medKitImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "medicalKit.png");
	private Image fireImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "fireConsumable.png");
	private Image armorImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "cloak3.png");
	
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
	    
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setStroke(new BasicStroke(10));
	    g2d.drawLine(WizardRoyale.WIDTH / 2, 150, WizardRoyale.WIDTH / 2, WizardRoyale.HEIGHT - 100);
	    
	    
	    g.setColor(Color.green);
	  	g.drawString("Press WASD To Move", WizardRoyale.WIDTH / 25, WizardRoyale.HEIGHT / 5);
	  	g.drawString("Click the Mouse to Shoot", WizardRoyale.WIDTH / 25, WizardRoyale.HEIGHT / 3);
	  	
	  	font0 = new Font("arial", Font.BOLD, (int)(WizardRoyale.WIDTH / 34));
	  	g.setFont(font0);
	  	g.drawString("Walk over Items to pick them up", WizardRoyale.WIDTH / 25, (int) (WizardRoyale.HEIGHT / 2.15));
	  	
	  	g.drawImage(medKitImage, WizardRoyale.WIDTH / 2 + WizardRoyale.WIDTH / 23, (int) (WizardRoyale.HEIGHT / 5.2), (int)(WizardRoyale.WIDTH / 30), (int)(WizardRoyale.HEIGHT / 25), null);
	  	g.drawString("Restores 30 HP", WizardRoyale.WIDTH / 2 + (int) (WizardRoyale.WIDTH / 12.5), (int) (WizardRoyale.HEIGHT / 4.5));
	  	
	  	g.drawImage(armorImage, WizardRoyale.WIDTH / 2 + WizardRoyale.WIDTH / 23, (int) (WizardRoyale.HEIGHT / 3.8), (int)(WizardRoyale.WIDTH / 30), (int)(WizardRoyale.HEIGHT / 25), null);
	  	g.drawString("Gives 30 Armor", WizardRoyale.WIDTH / 2 + (int) (WizardRoyale.WIDTH / 12.5), (int) (WizardRoyale.HEIGHT / 3.3));
	  	
	  	g.drawImage(fireImage, WizardRoyale.WIDTH / 2 + WizardRoyale.WIDTH / 23, (int) (WizardRoyale.HEIGHT / 2.9), (int)(WizardRoyale.WIDTH / 30), (int)(WizardRoyale.HEIGHT / 25), null);
	  	g.drawString("", WizardRoyale.WIDTH / 2 + (int) (WizardRoyale.WIDTH / 12.5), (int) (WizardRoyale.HEIGHT / 4.2));
	    
		
	}

}

