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

public class MainMenuPanel {
	
	private Rectangle playButton;
	private Rectangle helpButton;
	private Rectangle quitButton;
	private Rectangle serverButton;
	private Image[] wizardGif = new Image[24];
	/**
	 * Constant String variable that acts as a file separator 
	 */
	public final static String FILE_SEP = System.getProperty("file.separator");
	private double step = 0;
	public final int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public final int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	
	/**
	 * Constructs a new instance of the main menu panel by initializing the menu's fields
	 */
	
	public MainMenuPanel() {
		playButton = new Rectangle(WIDTH / 3, (int)(HEIGHT / 4.5), (int)(WIDTH / 2.94), HEIGHT / 9);
		System.out.println(WIDTH);
		System.out.println(HEIGHT);
		helpButton = new Rectangle(WIDTH / 3, (int)(HEIGHT / 2.57), (int)(WIDTH / 2.94), HEIGHT / 9);
		serverButton = new Rectangle(WIDTH / 3, (int)(HEIGHT / 1.8), (int)(WIDTH / 2.94),  HEIGHT / 9);
		quitButton = new Rectangle(WIDTH / 3, (int)(HEIGHT / 1.38), (int)(WIDTH / 2.94), HEIGHT / 9);
		for(int i = 0; i < 24; i++)
			wizardGif[i] = Toolkit.getDefaultToolkit().createImage("Resources" + FILE_SEP + "wizard" + MainMenuPanel.FILE_SEP +i+".gif");

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

	    g.drawString("Wizard Royale", x, y);
		g.drawImage(wizardGif[(int) step], WIDTH / 48, (int)(HEIGHT / 1.7), (int)(WIDTH / 5.76), (int)(HEIGHT / 3.6), null);
		step += 0.1;
		if(step >= 24)
			step = 0;
		
		
		Font font1 = new Font("SANS_SERIF", Font.BOLD, 60);
		g.setColor(Color.BLACK);
		
		g2d.draw(helpButton);
		g2d.draw(playButton);
		g2d.draw(quitButton);
		g2d.draw(serverButton);
		
		g.setColor(Color.CYAN);

		metrics = g.getFontMetrics(font1);
	    x = playButton.x + (playButton.width - metrics.stringWidth("Play")) / 2;
	    y = playButton.y + ((playButton.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font1);
	    g.drawString("Play", x, y);
	    
	    x = helpButton.x + (helpButton.width - metrics.stringWidth("Help")) / 2;
	    y = helpButton.y + ((helpButton.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font1);
	    g.drawString("Help", x, y);
	    
	    x = serverButton.x + (serverButton.width - metrics.stringWidth("Servers")) / 2;
	    y = serverButton.y + ((serverButton.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font1);
	    g.drawString("Servers", x, y);
		
	    x = quitButton.x + (quitButton.width - metrics.stringWidth("Quit")) / 2;
	    y = quitButton.y + ((quitButton.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font1);
	    g.drawString("Quit", x, y);
		
		

		
	}

}
