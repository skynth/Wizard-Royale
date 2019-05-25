package Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Queue;

import GameElements.Consumable;
import GameElements.Player;
import GameElements.Projectile;
import GameElements.Tile;
import networking.frontend.NetworkDataObject;
import networking.frontend.NetworkListener;
import networking.frontend.NetworkMessenger;

/**
 * A class that represents our game. This class controls what is happening in
 * the game and also contains the different screens such as the instructions,
 * game, and main menu
 * 
 * @author Leofeng, skyfreestylez
 * @version 5/17/19
 * 
 */
public class WizardRoyale extends Canvas implements Runnable, NetworkListener {

	private static final long serialVersionUID = 1L;

	private static final String messageTypeMove = "MOUSE_MOVE";
	private static final String messageTypeShoot = "MOUSE_SHOOT";
	private static final String messageTypePlayerList = "PLAYER_LIST";
	private static final String messageTypeRestart = "RESTART";
	public static String myIP;
	public static int numPlayers;
	public static boolean hasMoveToStart;
	public static String serverIP = "";
	public static ArrayList<String> connectedIPs;


	/**
	 * field that represents the width of the window in which our game is contained
	 * in
	 */

	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

	/**
	 * field that represents the height of the window in which our game is contained
	 * in
	 */

	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	private NetworkMessenger nm;
	private MainMenuPanel menu = new MainMenuPanel();
	private InstructionsPanel instructions = new InstructionsPanel();
	private WinScreenPanel winscreen;
	private static int numOfPlayers = 1;

	private Thread thread; // thread - sub process - created to handle the game, we want to do multiple
							// things simultaneously
	private boolean running = false;

	private Handler handler;
	private Camera gameCamera;

	/**
	 * enum for the different states of our game, such as the state in which the
	 * game is in the menu
	 * 
	 * @author Leofeng
	 *
	 */

	public static enum STATE {
		MENU, GAME, INSTURCTIONS, WINSCREEN, NEWGAME;
	}

	/**
	 * a variable that represents the state of our game
	 */
	public static STATE State = STATE.MENU;

	private Image image = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "Background.jpg");
	private Image floorImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "Floor.png");

	private BufferedImage backgroundImage = null;
	public static int bgWidth, bgHeight;

	/**
	 * Constructor that creates a new instance of our game. The constructor also
	 * initializes a handler that handles events in the game and adds starting
	 * objects to the game, as well as listeners to detect mouse and keyboard input
	 */

	public WizardRoyale() {
		

		try {
			myIP = InetAddress.getLocalHost().toString();
			myIP = myIP.substring(myIP.indexOf('/') + 1);
			System.out.print(myIP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		connectedIPs = new ArrayList<String>();
		connectedIPs.add(myIP);

		hasMoveToStart = false;
		new Window(WIDTH, HEIGHT, "Wizard Royale", this);
		handler = new Handler();
		gameCamera = new Camera(0, 0, handler);

		BufferedImageLoader loader = new BufferedImageLoader();
		backgroundImage = loader.loadImage("Resources" + MainMenuPanel.FILE_SEP + "WizardBackground.png");
		bgWidth = backgroundImage.getWidth();
		bgHeight = backgroundImage.getHeight();

		start();
	}

	private void start() {
		running = true;
		thread = new Thread(this);
		thread.start(); // begins running the game
	}

	private void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that is called after the thread for the game has been started. This
	 * method continually calls the tick and render method as long as the game is
	 * running, which updates all the objects in the game as well as the game's
	 * graphics.
	 * 
	 * @post the boolean variable that represents whether the game is running or not
	 *       will be set to false
	 * 
	 */

	public void run() {

		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {

		MouseInput.recoilTimer--;
		if (State == STATE.GAME) {

			for (Player p : handler.getPlayers()) {

				if (p.getIp().equals(myIP.toString())) {
					gameCamera.tick(p);
				}

			}

			handler.tick();

		} 
		processNetworkMessages();

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics(); // retrieves an instance of graphics2D from the bufferStrat, lets us draw
											// stuff
		Graphics2D g2d = (Graphics2D) g;

		if (State == STATE.GAME) {

			super.paint(g);

			g2d.translate(-gameCamera.getX(), -gameCamera.getY());

			for (int i = 0; i < 128 * 32; i += 71) {

				for (int j = 0; j < 128 * 32; j += 71) {
					g.drawImage(floorImage, i, j, null);
				}

			}

			handler.render(g);
			g2d.translate(gameCamera.getX(), gameCamera.getY());

		} else if (State == STATE.MENU) {
			g.drawImage(image, 0, 0, WIDTH, HEIGHT, this);
			menu.render(g);
		} else if (State == STATE.INSTURCTIONS) {
			super.paint(g);
			instructions.render(g);
		} else if (State == STATE.WINSCREEN) {
			winscreen = new WinScreenPanel(handler.getPlayers().size());
			winscreen.render(g);
		}
		g.dispose();
		bs.show(); // makes the buffer we just drew the current buffer for the JFrame, and displays
					// everything we drew
	}

	/**
	 * loads the map for our game
	 * 
	 * @param image the image of the map of the game
	 */

	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int i = 0; i < w; i++) {

			for (int j = 0; j < h; j++) {

				int pixel = image.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255) {
					handler.addObject(new Tile(i * 32, j * 31, ID.Wall, ID.Wall));
				}

				if (red == 127) {
					handler.addObject(new Tile(i * 32, j * 31, ID.Wall, ID.WallLeft));
				}

				if (red == 255 && green == 106) {
					handler.addObject(new Tile(i * 32, j * 31, ID.Wall, ID.WallRight));
				}

				if (red == 127 && green == 51) {
					handler.addObject(new Tile(i * 32, j * 31, ID.Wall, ID.WallUp));
				}

			}
		}

		handler.addObject(new Player((int) (WizardRoyale.WIDTH / 36), (int) (WizardRoyale.HEIGHT / 22.5), ID.Player,
				myIP, handler, ID.RegularProjectile, nm));
		numPlayers++;
		System.out.println("tick Players" + numPlayers);

	}

	public void receiveUpdate(String hostname, Object[] message) {

	}

	public void connectedToServer(NetworkMessenger nm) {
		this.nm = nm;
		loadLevel(backgroundImage);
		this.addMouseListener(new MouseInput(handler, gameCamera, nm, backgroundImage));
		this.addKeyListener(new KeyInput(handler));
		handler.spawnCollectibles(backgroundImage);
	}

	public void networkMessageReceived(NetworkDataObject ndo) {

	}

	public static int getNumOfPlayers() {
		return numOfPlayers;
	}

	/**
	 * Method that is repeatedly called in tick, and takes in messages from the
	 * network messenger, then uses those messages to update the game on other
	 * users' games.
	 */

	public void processNetworkMessages() {
		
		if (nm == null)
			return;

		Queue<NetworkDataObject> queue = nm.getQueuedMessages();

		while (!queue.isEmpty()) {

			NetworkDataObject ndo = queue.poll();
			String host = ndo.getSourceIP();
			serverIP = ndo.serverHost.toString().substring(1);

			if (ndo.messageType.equals(NetworkDataObject.CLIENT_LIST)) {
				
				for (Player p : handler.getPlayers()) {

					if (p.getIp().equals(host)) {
						return;
					}

				}
				
				if (connectedIPs.indexOf(host) == -1) {
				
					connectedIPs.add(host);
				
				}
				
				Player player = new Player((int) (WizardRoyale.WIDTH / 36), (int) (WizardRoyale.HEIGHT / 22.5),
						ID.Player, host, handler, ID.RegularProjectile, nm);
				handler.addObject(player);
				numPlayers++;
				for (int i = 0; i < handler.getGameObjects().size(); i++) {

					if (handler.getGameObjects().get(i).getID() == ID.Item) {
						Consumable consumable = (Consumable) handler.getGameObjects().get(i);
						this.nm.sendMessage(NetworkDataObject.MESSAGE, "CONSUMABLES", consumable.getX(),
								consumable.getY(), consumable.getSubID());
					}

				}

			}

			if (ndo.messageType.equals(NetworkDataObject.HANDSHAKE)) {

				hasMoveToStart = true;
				System.out.println("Handshake");
				for (Player p : handler.getPlayers()) {

					if (p.getIp().equals(host)) {
						return;
					}
					
					if (connectedIPs.indexOf(host) == -1) {
						
						connectedIPs.add(host);
					
					}

					numPlayers++;
					for (int i = 0; i < handler.getGameObjects().size(); i++) {

						if (handler.getGameObjects().get(i).getID() == ID.Item) {
							Consumable consumable = (Consumable) handler.getGameObjects().get(i);
							this.nm.sendMessage(NetworkDataObject.MESSAGE, "CONSUMABLES", consumable.getX(),
									consumable.getY(), consumable.getSubID());
						}

					}
				}

				Player player = new Player((int) (WizardRoyale.WIDTH / 36), (int) (WizardRoyale.HEIGHT / 22.5),
						ID.Player, host, handler, ID.RegularProjectile, nm);
				handler.addObject(player);
				// Player myPlayer = null;
//				for (Player p : handler.getPlayers()) {	
//					if (p.getIp().equals(myIP)) {
//							myPlayer = p;
//					}
//				}
//				if(myPlayer != null) {
//					System.out.println("not null, " + ndo.serverHost.toString() + ", " + myPlayer.getIp());
//					String serverHost = ndo.serverHost.toString().substring(1);
//					if(myPlayer.getIp().equals(serverHost.toString())) {
//						isReadyToMove = true;
//						System.out.println(handler.getPlayers().size());
//						nm.sendMessage(NetworkDataObject.MESSAGE, messageTypePlayerList, handler.getPlayers());
//
			}
			
			if (ndo.message[0].equals("NEW PLAYER") && ndo.getSourceIP() != myIP) {
				handler.addObject(new Player((int)ndo.message[1], (int)ndo.message[2], ID.Player, (String) ndo.message[3], handler, ID.RegularProjectile, nm));
			}
			
			if (ndo.message[0].equals("RESET")) {
				handler.clear();
				WizardRoyale.State = STATE.GAME;
			}

			if (ndo.message[0].equals("CONSUMABLES")) {
				System.out.println("Got consumable from other guy");
				handler.addObject(new Consumable((int) ndo.message[1], (int) ndo.message[2], ID.Item, handler,
						(ID) ndo.message[3]));
			}

			if (ndo.message[0].equals("MOUSE_SHOOT")) {
				System.out.println("Got mouse shoot");
				Projectile projectile = (Projectile) ndo.message[1];
				projectile.setHandler(handler);
				handler.addObject(projectile);

			}

			else if (ndo.message[0].equals("MOVE")) {
				System.out.println("GOT MOVE");

				for (int i = 0; i < handler.getPlayers().size(); i++) {

					if (handler.getPlayers().get(i).getIp().equals(host)) {

						/*
						 * System.out.println("success");
						 * handler.getPlayers().get(i).setRight((boolean)ndo.message[1]);
						 * 
						 * if ((boolean)ndo.message[1] == true) {
						 * handler.getPlayers().get(i).setAnimationRight(true); }
						 * 
						 * handler.getPlayers().get(i).setLeft((boolean)ndo.message[2]);
						 * 
						 * if ((boolean)ndo.message[1] == true) {
						 * handler.getPlayers().get(i).setAnimationRight(false); }
						 * 
						 * handler.getPlayers().get(i).setUp((boolean)ndo.message[3]);
						 * handler.getPlayers().get(i).setDown((boolean)ndo.message[4]);
						 */

						if ((int) ndo.message[1] > handler.getPlayers().get(i).getX()) {
							handler.getPlayers().get(i).setAnimationRight(true);
						} else if ((int) ndo.message[1] < handler.getPlayers().get(i).getX()) {
							handler.getPlayers().get(i).setAnimationRight(false);

						}
						handler.getPlayers().get(i).setX((int) ndo.message[1]);
						handler.getPlayers().get(i).setY((int) ndo.message[2]);

					}

				}
			} else if (ndo.message[0].equals("WINSCREEN")) {

				WizardRoyale.State = STATE.WINSCREEN;

			}

//			if(ndo.message[0].equals(messageTypePlayerList)) {
//				System.out.println("got player list");
//				ArrayList<Player> players = (ArrayList<Player>) ndo.message[1];
//				for(int i = 0; i < players.size(); i++) {
//					if(players.get(i).getIp().equals(myIP)) {
//						numPlayers = i + 1;
//					}
//				}
//				isReadyToMove = true;
//
//			}
			Player myPlayer = null;
			for (Player p : handler.getPlayers()) {
				if (p.getIp().equals(myIP)) {
					myPlayer = p;
				}
			}
			//System.out.println("server " + ndo.serverHost.toString() + ", " + myIP);
			if (serverIP.equals(myIP)) {
				hasMoveToStart = true;
			}
			if (hasMoveToStart == false && numPlayers > 0) {

				System.out.println("Players " + numPlayers);
				if (numPlayers == 1) {
					myPlayer.setX((int) (WizardRoyale.WIDTH / 36));
					myPlayer.setY((int) (WizardRoyale.HEIGHT / 22.5));
				} else if (numPlayers == 2) {
					myPlayer.setX((int) (backgroundImage.getWidth() * 30));
					myPlayer.setY((int) (backgroundImage.getHeight() * 30));
				} else if (numPlayers == 3) {
					myPlayer.setX((int) (backgroundImage.getWidth() * 30));
					myPlayer.setY((int) (WizardRoyale.HEIGHT / 22.5));
				} else if (numPlayers == 4) {
					myPlayer.setX((int) (WizardRoyale.WIDTH / 36));
					myPlayer.setY((int) (backgroundImage.getHeight() * 30));
				}
				hasMoveToStart = true;
			}
			// nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeMove,
			// myPlayer.isRight(), myPlayer.isLeft(), myPlayer.isUp(), myPlayer.isDown());
			if (myPlayer != null) {
				nm.sendMessage(NetworkDataObject.MESSAGE, "MOVE", myPlayer.getX(), myPlayer.getY());
				System.out.println("SENT MOVE");
			}

		}

	}

}
