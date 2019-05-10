package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class MainMenuPanel {
	
	private Rectangle playButton;
	private Rectangle helpButton;
	private Rectangle quitButton;
	private Image[] wizardGif = new Image[24];
	public final static String FILE_SEP = System.getProperty("file.separator");
	private double step = 0;
	
	public MainMenuPanel() {
		playButton = new Rectangle(230, 200, 330, 100);
		helpButton = new Rectangle(230, 375, 330, 100);
		quitButton = new Rectangle(230, 550, 330, 100);
		for(int i = 0; i < 24; i++)
			wizardGif[i] = Toolkit.getDefaultToolkit().createImage("Resources" + FILE_SEP + "wizard" + MainMenuPanel.FILE_SEP +i+".gif");

	}

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font font0 = new Font("arial", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.white);
		g.drawString("Wizard Royale", 230, 100);
		g.drawImage(wizardGif[(int) step], 10, 550, 200, 200, null);
		step += 0.1;
		if(step >= 24)
			step = 0;
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
