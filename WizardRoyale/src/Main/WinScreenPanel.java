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
 * Class that represents the panel for the main menu of our game, seen when the game is first opened. Contains play, help, and quit buttons
 * that will change the state of the game
 * 
 * @author Leofeng, skyfreestylez
 * @version : 5/9/19
 * 
 */

public class WinScreenPanel {
	
	private Rectangle playButton;
	private Rectangle quitButton;

	
	/**
	 * Constructs a new instance of the main menu panel by initializing the menu's fields
	 */
	
	public WinScreenPanel() {
		playButton = new Rectangle(WizardRoyale.WIDTH / 3, (int)(WizardRoyale.HEIGHT / 4.5), (int)(WizardRoyale.WIDTH / 2.94), WizardRoyale.HEIGHT / 9);
		quitButton = new Rectangle(WizardRoyale.WIDTH / 3, (int)(WizardRoyale.HEIGHT / 1.38), (int)(WizardRoyale.WIDTH / 2.94), WizardRoyale.HEIGHT / 9);

	}
	
	/**
	 * Draws all the images on the main menu screen
	 * @param g the instance of the graphics class that will handle drawing everything in the WizardRoyale class
	 * @post the graphics of the main menu will be drawn onto g
	 */

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("arial", Font.BOLD, 75);
		g.setFont(font0);
		g.setColor(Color.white);
		
	    FontMetrics metrics = g.getFontMetrics(font0);
	    int x = (WizardRoyale.WIDTH - metrics.stringWidth("Wizard Royale")) / 2;
	    int y = 110;
	    g.setFont(font0);
		
		Font font1 = new Font("SANS_SERIF", Font.BOLD, 60);
		g.setColor(Color.BLACK);
		
		g2d.draw(playButton);
		g2d.draw(quitButton);
		
		g.setColor(Color.CYAN);

		metrics = g.getFontMetrics(font1);
	    x = playButton.x + (playButton.width - metrics.stringWidth("Play")) / 2;
	    y = playButton.y + ((playButton.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font1);
	    g.drawString("Play", x, y);
	    
	    x = quitButton.x + (quitButton.width - metrics.stringWidth("Quit")) / 2;
	    y = quitButton.y + ((quitButton.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font1);
	    g.drawString("Quit", x, y);
		
		

		
	}

}