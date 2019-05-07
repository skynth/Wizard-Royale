package Main;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {
	
	public Window(int width, int height, String title, WizardRoyale wizardRoyale) {
		
		JFrame window = new JFrame("Wizard Royale");
		window.add(wizardRoyale);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setPreferredSize(new Dimension(width, height));
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
	}

	public static void main (String[] args) {
		
		new WizardRoyale();
		System.out.println("dasdadsadsadsad");
	}
	
}
