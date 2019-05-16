package Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Queue;

import GameElements.Consumable;
import GameElements.GameObject;
import GameElements.Player;
import GameElements.Projectile;
import GameElements.Tile;
import networking.backend.SchoolServer;
import networking.frontend.NetworkDataObject;
import networking.frontend.NetworkListener;
import networking.frontend.NetworkMessenger;


/**
 * A class that represents our game. This class controls what is happening in the game and also contains the different screens such as
 * the instructions, game, and main menu
 * 
 * @author Leofeng, skyfreestylez
 * @version 5/9/19
 * 
 */
public class WizardRoyale extends Canvas implements Runnable, NetworkListener {
	
	private static final long serialVersionUID = 1L;
	
	private static final String messageTypeInit = "CREATE_CURSOR";
	private static final String messageTypeMove = "MOUSE_MOVE";
	private static final String messageTypeShoot = "MOUSE_SHOOT";
	private static final String messageTypePress = "MOUSE_PRESS";
	private static final String messageTypeColor = "COLOR_SWITCH";
	
	public static InetAddress myIP;
	
	/**
	 * field that represents the width of the window in which our game is contained in
	 */
	
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/**
	 * field that represents the height of the window in which our game is contained in
	 */
	
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private NetworkMessenger nm;
	private MainMenuPanel menu = new MainMenuPanel();
	private InstructionsPanel instructions = new InstructionsPanel();
	
	private Thread thread; //thread - sub process - created to handle the game, we want to do multiple things simultaneously
	private boolean running = false;
	
	private Handler handler;
	private Camera gameCamera;
	
	/**
	 * enum for the different states of our game, such as the state in which the game is in the menu
	 * @author Leofeng
	 *
	 */
	
	public static enum STATE { 
		MENU, 
		GAME,
		INSTURCTIONS;
	}
	
	/**
	 * a variable that represents the state of our game
	 */
	public static STATE State = STATE.MENU;
	
	private Image image = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "Background.jpg");
	private Image floorImage = Toolkit.getDefaultToolkit().getImage("Resources" + MainMenuPanel.FILE_SEP + "Floor.png");
	
	private BufferedImage backgroundImage = null;
	
	
	/**
	 * Constructor that creates a new instance of our game. The constructor also initializes a handler that handles events in the game and
	 * adds starting objects to the game, as well as listeners to detect mouse and keyboard input
	 */
	
	public WizardRoyale() {
		
		try {
			myIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		new Window(WIDTH, HEIGHT, "Wizard Royale", this);
		handler = new Handler();
		gameCamera = new Camera(0, 0, handler);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		backgroundImage = loader.loadImage("Resources" + MainMenuPanel.FILE_SEP + "WizardBackground.png");
	
		handler.addObject(new Consumable((int)(WizardRoyale.WIDTH / 4.8), (int)(WizardRoyale.HEIGHT / 3), ID.Item, handler,ID.MedKit));
		this.addMouseListener(new MouseInput(handler, gameCamera));
		this.addKeyListener(new KeyInput(handler));
		loadLevel(backgroundImage);
		start();
	}
	
	private void start() {
		running = true;
		thread = new Thread(this);
		thread.start(); //begins running the game
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
	 * Method that is called after the thread for the game has been started. This method continually calls the tick and render method as long
	 * as the game is running, which updates all the objects in the game as well as the game's graphics. 
	 * @post the boolean variable that represents whether the game is running or not will be set to false
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
		     
		     while(delta >= 1) {
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
		
		if (State == STATE.GAME) {
			
			for (Player p : handler.getPlayers()) {
				
				if (p.getIp().equals(myIP.toString())) {
					gameCamera.tick(p);
				}
				
			}
			
			handler.tick(); 
			
		} else if (State == STATE.MENU) {
			
		}
		processNetworkMessages();
		

	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(3); 
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); //retrieves an instance of graphics2D from the bufferStrat, lets us draw stuff
		Graphics2D g2d = (Graphics2D) g;
		
		if (State == STATE.GAME) {
			
			super.paint(g);
			
			g2d.translate(-gameCamera.getX(), -gameCamera.getY());
			
			for (int i = 0; i < 128 * 32; i+=71) {
				
				for (int j = 0; j < 128 * 32; j+=71) {
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
		}
		
		g.dispose();
		bs.show(); //makes the buffer we just drew the current buffer for the JFrame, and displays everything we drew
	}
	
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
		
		handler.addObject(new Player((int)(WizardRoyale.WIDTH / 36), (int)(WizardRoyale.HEIGHT / 22.5), ID.Player, myIP.toString(), handler));
		
	}

	public void receiveUpdate(String hostname, Object[] message) {
		
	}

	public void connectedToServer(NetworkMessenger nm) {
		this.nm = nm;
	}

	public void networkMessageReceived(NetworkDataObject ndo) {

		
		
	}
	
	public void processNetworkMessages() {
		
		if (nm == null)
			return;
		
		Queue<NetworkDataObject> queue = nm.getQueuedMessages();
		
		while (!queue.isEmpty()) {
			NetworkDataObject ndo = queue.poll();
			String host = ndo.getSourceIP();
			
			if (ndo.message[0].equals(messageTypeInit)) {
			
				for (Player p : handler.getPlayers()) {
				
					if (p.getIp().equals(host)) {
						return;
					}
				
			}
				
			Player player = new Player((Integer)ndo.message[1], (Integer)ndo.message[2], (ID)ndo.message[3], (String) ndo.message[4], (Handler) ndo.message[5]);
			handler.addObject(player);

		} 
			
			else if (ndo.messageType.equals(NetworkDataObject.CLIENT_LIST)) {
				Player player = null;
				nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeInit, 200, 200, ID.Player, ndo.getSourceIP(), handler);
				for (Player p : handler.getPlayers()) {
					if (p.getIp().equals(host)) {
						player = p;
					}
				}
				//Not sure if this is in the right place
				if(player != null)
					nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeMove, player.getX(), player.getY());
				
				//needs to send over projectiles
				


				if(MouseInput.isProjectileMade())
						nm.sendMessage(NetworkDataObject.MESSAGE, messageTypeShoot,handler.getProjectiles().get(handler.getProjectiles().size() - 1));

				
			}
				
			else if(ndo.message[0].equals(messageTypeMove)) {
				for (Player p : handler.getPlayers()) {
					
					if (p.getIp().equals(host)) {
						p.setX((int)ndo.message[1]);
						p.setY((int)ndo.message[2]);
						

					}
				}
			}
			else if(ndo.message[0].equals(messageTypeShoot)) {
					Projectile p = (Projectile)ndo.message[1];
					handler.addObject(p);
				}
				
			}
			
		}
		
	}
	

