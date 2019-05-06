package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MainMenuPanel {
	
	private Rectangle playButton = new Rectangle(230, 175, 330, 100);
	private Rectangle helpButton = new Rectangle(230, 350, 330, 100);
	private Rectangle quitButton = new Rectangle(230, 525, 330, 100);

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("arial", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.black);
		g.drawString("Wizard Royale", 230, 100);
		
		
		Font font1 = new Font("arial", Font.BOLD, 50);
		g.setFont(font1);
		
		g.drawString("Play", playButton.x + 115, playButton.y + 65);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 115, helpButton.y + 65);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x + 115, quitButton.y + 65);
		g2d.draw(quitButton);

		
	}

}
