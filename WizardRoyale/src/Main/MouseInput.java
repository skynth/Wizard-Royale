package Main;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GameElements.GameObject;
import GameElements.Player;
import GameElements.Projectile;
import Main.WizardRoyale.STATE;
import networking.frontend.NetworkManagementPanel;

/**
 * A class that handles input from the user's mouse to the game
 * @author Leofeng 
 * @version 5/10/19
 *
 */

public class MouseInput extends MouseAdapter {
	
	private Handler handler; 
	private Camera camera;
	private static boolean isProjectileMade = false;

	/**
	 * Creates a new instance of MouseInput
	 * @param h the WizardRoyale's class' instance of the handler class, which handles all the events that occur in the game
	 */
	
	public MouseInput(Handler h, Camera cam) {
		handler = h; 
		camera = cam;
		isProjectileMade = false;
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}


	public void mouseExited(MouseEvent e) {
		
	}
	
	/**
	 * Method that states what actions to take after the user has pressed the mouse
	 * @param e an instance of MouseEvent that represents the user's mouse click
	 * @post the game's state may be changed after the user clicks on a button in the main menu
	 */

	public void mousePressed(MouseEvent e) {
		
		int mouseX = (int) (e.getX() + camera.getX());
		int mouseY = (int) (e.getY() + camera.getY());
		
		if (WizardRoyale.State == STATE.MENU) {
			
			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 4.5 && mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94 && mouseY <= WizardRoyale.HEIGHT / 4.5 + WizardRoyale.HEIGHT / 9) {
				WizardRoyale.State = STATE.GAME;
			}
			
			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 2.57 && mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94 && mouseY <= WizardRoyale.HEIGHT / 2.57 + WizardRoyale.HEIGHT / 9) {
				WizardRoyale.State = STATE.INSTURCTIONS;
			}

			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 1.8 && mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94 && mouseY <= WizardRoyale.HEIGHT / 1.8 + WizardRoyale.HEIGHT / 9) {
			}
			
			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 1.38 && mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94 && mouseY <= WizardRoyale.HEIGHT / 1.38 + WizardRoyale.HEIGHT / 9) {
				System.exit(1);
			}	
		} else if (WizardRoyale.State == STATE.GAME) {
			
			Player player = null;
			
			for (Player p : handler.getPlayers()) {
				
				if (p.getIp().equals(WizardRoyale.myIP.toString())) {
					player = p;
				}
				
			}
			
			Projectile p = null;
			
			if (player.getIsRight()) {
				
				if (mouseX < player.getX()) {
					player.setRight(false);
				}
				
				p = new Projectile(player.getX() + (int)(WizardRoyale.WIDTH / 21), player.getY() + (int)(WizardRoyale.HEIGHT / 60), ID.Projectile, mouseX, mouseY, handler,player.getProjectileType());
				
			}
			else {
				
				if (mouseX > player.getX()) {
					player.setRight(true);
				}
				
				p = new Projectile(player.getX() - (int)(WizardRoyale.WIDTH / 60), player.getY() + (int)(WizardRoyale.HEIGHT / 60), ID.Projectile, mouseX, mouseY, handler,player.getProjectileType());
				
			}
			isProjectileMade = true;
			player.setIsShoot(true);
			handler.addObject(p);
		}
		}
	
	public static boolean isProjectileMade() {
		
		return isProjectileMade;
	}
		
	

	public void mouseReleased(MouseEvent e) {
		
	}

}


