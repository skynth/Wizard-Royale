package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Main.WizardRoyale.STATE;

public class MouseInput implements MouseListener {
	
	Handler handler;
	
	public MouseInput(Handler h) {
		handler = h; 
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}


	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		
		if (WizardRoyale.State == STATE.MENU) {
			
			if (mouseX >= 230 && mouseY >= 175 && mouseX <= 560 && mouseY <= 275) {
				WizardRoyale.State = STATE.GAME;
			}
			
			if (mouseX >= 230 && mouseY >= 350 && mouseX <= 560 && mouseY <= 450) {
				WizardRoyale.State = STATE.INSTURCTIONS;
			}
			
			if (mouseX >= 230 && mouseY >= 525 && mouseX <= 560 && mouseY <= 625) {
				System.exit(1);
			}
			
		} else if (WizardRoyale.State == STATE.GAME) {
			//code to handle if user clicks in the game
		}
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
