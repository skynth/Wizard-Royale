package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainMenuPanel {
	
	private Rectangle playButton;
	private Rectangle helpButton;
	private Rectangle quitButton;
	private BufferedImage wizardGif;
	
	public MainMenuPanel() {
		playButton = new Rectangle(230, 200, 330, 100);
		helpButton = new Rectangle(230, 375, 330, 100);
		quitButton = new Rectangle(230, 550, 330, 100);
		try {
			wizardGif = ImageIO.read(new File("Resources/wizard.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("arial", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.white);
		g.drawString("Wizard Royale", 230, 100);
		g.drawImage(wizardGif, 20, 600, 150, 150, null);
		Font font1 = new Font("SANS_SERIF", Font.BOLD, 50);
		g.setColor(Color.CYAN);
		g.setFont(font1);
		
		g.setColor(Color.BLACK);
		
		g2d.draw(helpButton);
		g2d.draw(playButton);
		g2d.draw(quitButton);
		
		g.setColor(Color.CYAN);
		
		g.drawString("Play", playButton.x + 115, playButton.y + 65);
		g.drawString("Help", helpButton.x + 115, helpButton.y + 65);
		g.drawString("Quit", quitButton.x + 115, quitButton.y + 65);
		

		
	}

}
