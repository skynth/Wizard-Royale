package Main;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * The panel that shows up when a player defeats the other one
 * @author Leofeng
 * @version : 5/24/19
 * 
 */

public class WinScreenPanel {
	
	private Rectangle playButton;
	private Rectangle quitButton;
	int playerNumber;
	
	/**
	 * Constructs a new instance of the win screen panel by initializing its fields
	 */
	
	public WinScreenPanel(int playerNumber) {
		playButton = new Rectangle(WizardRoyale.WIDTH / 3, (int)(WizardRoyale.HEIGHT / 3), (int)(WizardRoyale.WIDTH / 2.94), WizardRoyale.HEIGHT / 9);
		quitButton = new Rectangle(WizardRoyale.WIDTH / 3, (int)(WizardRoyale.HEIGHT / 2), (int)(WizardRoyale.WIDTH / 2.94), WizardRoyale.HEIGHT / 9);
		this.playerNumber = playerNumber;
	}
	
	/**
	 * Draws everything on the win screen panel
	 * @param g the instance of the graphics class that will handle drawing everything in the WizardRoyale class
	 * @post the graphics of the main menu will be drawn onto g
	 */

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
	
		Font font1 = new Font("SANS_SERIF", Font.BOLD, 60);
		g.setColor(Color.BLACK);
	
		FontMetrics metrics = g.getFontMetrics(font1);
		g.setFont(font1);
		
	    int x = (WizardRoyale.WIDTH - metrics.stringWidth("Player" + playerNumber + "has won")) / 2;
	    int y = 250;

	    g.drawString("Player" + " " + playerNumber + " " + "has won", x, y);
		
		g2d.fillRect(WizardRoyale.WIDTH / 3, (int)(WizardRoyale.HEIGHT / 3), (int)(WizardRoyale.WIDTH / 2.94), WizardRoyale.HEIGHT / 9);
		g2d.fillRect(WizardRoyale.WIDTH / 3, (int)(WizardRoyale.HEIGHT / 2), (int)(WizardRoyale.WIDTH / 2.94), WizardRoyale.HEIGHT / 9);
		
		g.setColor(Color.CYAN);

		metrics = g.getFontMetrics(font1);
	    x = playButton.x + (playButton.width - metrics.stringWidth("Play Again")) / 2;
	    y = playButton.y + ((playButton.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font1);
	    g.drawString("Play Again", x, y);
	    
	    x = quitButton.x + (quitButton.width - metrics.stringWidth("Quit")) / 2;
	    y = quitButton.y + ((quitButton.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font1);
	    g.drawString("Quit", x, y);
		
		

		
	}

}