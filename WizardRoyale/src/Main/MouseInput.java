package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import GameElements.Consumable;
import GameElements.Player;
import GameElements.Projectile;
import Main.WizardRoyale.STATE;
import networking.frontend.NetworkDataObject;
import networking.frontend.NetworkMessenger;

/**
 * A class that handles input from the user's mouse to the game
 * 
 * @author Leofeng
 * @version 5/24/19
 *
 */

public class MouseInput extends MouseAdapter {

	private Handler handler;
	private Camera camera;
	NetworkMessenger nm;
	public static int recoilTimer = 0;

	/**
	 * Creates a new instance of MouseInput
	 * 
	 * @param h   the WizardRoyale's class' instance of the handler class, which
	 *            handles all the events that occur in the game
	 * @param cam the game's camera that will be focused on the player
	 * @param nm  the network messenger of the game
	 */

	public MouseInput(Handler h, Camera cam, NetworkMessenger nm, BufferedImage b) {
		handler = h;
		camera = cam;
		this.nm = nm;

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	/**
	 * Method that states what actions to take after the user has pressed the mouse
	 * 
	 * @param e an instance of MouseEvent that represents the user's mouse click
	 * @post the game's state may be changed after the user clicks on a button in
	 *       the main menu
	 */

	public void mousePressed(MouseEvent e) {

		int mouseX = (int) (e.getX() + camera.getX());
		int mouseY = (int) (e.getY() + camera.getY());

		if (WizardRoyale.State == STATE.MENU) {

			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 4
					&& mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94
					&& mouseY <= WizardRoyale.HEIGHT / 4 + WizardRoyale.HEIGHT / 9) {
				WizardRoyale.State = STATE.GAME;
			}

			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 2.4
					&& mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94
					&& mouseY <= WizardRoyale.HEIGHT / 2.4 + WizardRoyale.HEIGHT / 9) {
				WizardRoyale.State = STATE.INSTURCTIONS;
			}

//			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 1.8
//					&& mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94
//					&& mouseY <= WizardRoyale.HEIGHT / 1.8 + WizardRoyale.HEIGHT / 9) {
//			}

			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 1.71
					&& mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94
					&& mouseY <= WizardRoyale.HEIGHT / 1.71 + WizardRoyale.HEIGHT / 9) {
				System.exit(1);
			}

		} else if (WizardRoyale.State == STATE.GAME) {

			if (recoilTimer <= 0) {
				recoilTimer = 15;

				Player player = null;

				for (Player p : handler.getPlayers()) {

					if (p.getIp().equals(WizardRoyale.myIP.toString())) {
						player = p;
					}

				}

				Projectile p = null;

				if (player.getIsRight()) {

					if (mouseX < player.getX()) {
						player.setAnimationRight(false);
					}
					p = new Projectile(player.getX() + (int) (WizardRoyale.WIDTH / 20),
							player.getY() + (int) (WizardRoyale.HEIGHT / 60), ID.Projectile, mouseX, mouseY, handler,
							player.getProjectileType(), player.getIp());
				}

				else {

					if (mouseX > player.getX()) {
						player.setAnimationRight(true);
					}
					p = new Projectile(player.getX() - (int) (WizardRoyale.WIDTH / 60),
							player.getY() + (int) (WizardRoyale.HEIGHT / 60), ID.Projectile, mouseX, mouseY, handler,
							player.getProjectileType(), player.getIp());
				}

				player.setIsShoot(true);
				handler.addObject(p);
				nm.sendMessage(NetworkDataObject.MESSAGE, "MOUSE_SHOOT", p);
			}

		} else if (WizardRoyale.State == STATE.WINSCREEN) {

			if (e.getX() >= WizardRoyale.WIDTH / 3 && e.getY() >= WizardRoyale.HEIGHT / 3
					&& e.getX() <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94
					&& e.getY() <= WizardRoyale.HEIGHT / 3 + WizardRoyale.HEIGHT / 9) {
				
				nm.sendMessage(NetworkDataObject.MESSAGE, "RESET"); 
				WizardRoyale.newGame = true;
				
				handler.clear();
				handler.spawnCollectibles();
				
				for (int i = 0; i < handler.getGameObjects().size(); i++) {

					if (handler.getGameObjects().get(i).getID() == ID.Item) {
						Consumable consumable = (Consumable) handler.getGameObjects().get(i);
						this.nm.sendMessage(NetworkDataObject.MESSAGE, "CONSUMABLES", consumable.getX(),
								consumable.getY(), consumable.getSubID());
					}

				}
				
				WizardRoyale.numPlayers = 0;

				for (int i = 0; i < 2; i++) {
					
					
					if(WizardRoyale.connectedIPs.get(i).equals(WizardRoyale.serverIP)) {
						
						if (i == 0) {
							Player p = new Player((int) (WizardRoyale.WIDTH / 36), (int) (WizardRoyale.HEIGHT / 22.5),

							ID.Player, WizardRoyale.connectedIPs.get(i), handler, ID.RegularProjectile, nm);
							handler.addObject(p);
							nm.sendMessage(NetworkDataObject.MESSAGE, "NEW_PLAYER", (int) (WizardRoyale.WIDTH / 36), (int) (WizardRoyale.HEIGHT / 22.5), WizardRoyale.connectedIPs.get(i));
							

						} else {

						Player p = new Player((int) (WizardRoyale.WIDTH / 36), (int) (WizardRoyale.HEIGHT / 22.5),

								ID.Player, WizardRoyale.connectedIPs.get(i), handler, ID.RegularProjectile, nm);

						handler.addObject(p);
						nm.sendMessage(NetworkDataObject.MESSAGE, "NEW_PLAYER", (int) (WizardRoyale.WIDTH / 36), (int) (WizardRoyale.HEIGHT / 22.5), WizardRoyale.connectedIPs.get(i));



						}
					}
					else {
						
						if (i == 1) {

							Player p = new Player((int) (WizardRoyale.bgWidth* 30), (int) (WizardRoyale.bgHeight* 30),

									ID.Player, WizardRoyale.connectedIPs.get(i), handler, ID.RegularProjectile, nm);

							handler.addObject(p);
							nm.sendMessage(NetworkDataObject.MESSAGE, "NEW_PLAYER",(int) (WizardRoyale.bgWidth* 30), (int) (WizardRoyale.bgHeight* 30), WizardRoyale.connectedIPs.get(i));

						}
						else {
							Player p = new Player((int) (WizardRoyale.bgWidth* 30), (int) (WizardRoyale.bgHeight* 30),
									ID.Player, WizardRoyale.connectedIPs.get(i), handler, ID.RegularProjectile, nm);
									handler.addObject(p);
									nm.sendMessage(NetworkDataObject.MESSAGE, "NEW_PLAYER", (int) (WizardRoyale.bgWidth* 30), (int) (WizardRoyale.bgHeight* 30), WizardRoyale.connectedIPs.get(i));

						}
					}
					
					WizardRoyale.numPlayers++;
					
				}
				
				WizardRoyale.State = STATE.GAME;
			}


		}

			if (mouseX >= WizardRoyale.WIDTH / 3 && mouseY >= WizardRoyale.HEIGHT / 2
					&& mouseX <= WizardRoyale.WIDTH / 3 + WizardRoyale.WIDTH / 2.94
					&& mouseY <= WizardRoyale.HEIGHT / 2 + WizardRoyale.HEIGHT / 9) {
				System.exit(1);
			}

		}
	}


