package Main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Main.WizardRoyale.STATE;

public class WizardRoyale extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final int SCALE = 2;
	
	private MainMenuPanel menu = new MainMenuPanel();
	private InstructionsPanel instructions = new InstructionsPanel();
	
	private Thread thread; //thread - sub process - created to handle the game, we want to do multiple things simultaneously
	private boolean running = false;
	
	public static enum STATE { //enumerations for the different states of the game
		MENU, 
		GAME,
		INSTURCTIONS
	}
	public static STATE State = STATE.MENU;
	
	Image image = Toolkit.getDefaultToolkit().getImage("Resources/Background.jpg");
	
	public WizardRoyale() {
		new Window(WIDTH, HEIGHT, "Wizard Royale", this);
		this.addMouseListener(new MouseInput());
		start();
	}
	

	public void start() {
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
		    	tick(); //
		        delta--;
		     }
		     
		     render(); //render : called thousand of times per second 
		     frames++;

		     if (System.currentTimeMillis() - timer > 1000) {
		        timer += 1000;
		        frames = 0;
		        //updates = 0;
		     }
		 }
		 stop();
	}
	
	public void tick() {
		
		if (State == STATE.GAME) {
										//run through tick methods if we are in the proper state
		} else if (State == STATE.MENU) {
			
		}
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			this.createBufferStrategy(5); 
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); //retrieves an instance of graphics2D from the bufferStrat, lets us draw stuff
		
		if (State == STATE.GAME) {
			super.paint(g);
			g.setColor(Color.red);
			g.fillRect(50, 50, 100, 100);
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

	public void PaintComponent(Graphics g) {

	}
	
}
