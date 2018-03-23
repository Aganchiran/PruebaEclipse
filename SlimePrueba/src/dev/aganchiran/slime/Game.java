package dev.aganchiran.slime;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import dev.aganchiran.slime.display.Display;
import dev.aganchiran.slime.gfx.Assets;
import dev.aganchiran.slime.gfx.GameCamera;
import dev.aganchiran.slime.input.KeyManager;
import dev.aganchiran.slime.media.Music;
import dev.aganchiran.slime.states.GameState;
import dev.aganchiran.slime.states.GameStateManager;
import dev.aganchiran.slime.states.MenuState;
import dev.aganchiran.slime.states.State;

public class Game implements Runnable{
	private Display display;
	public String title;
	private int width,height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	
	//private boolean refresh;
	
	//States.
	private State gameState;
	private State menuState;
	
	//Input.
	private KeyManager keyManager;
	
	//Camera.
	private GameCamera gameCamera;
	
	//Handeler.
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}
	
	public void run() {

		init();
		
		Timer render = new Timer();
		
		render.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				tick();
				render();
				
			}}, 0, 15);
		
		Timer musicTimer = new Timer();
		
		musicTimer.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				
				Music.play(handler.getWorld().getMusic());
				
			}}, 0,Music.getMiliseconds(handler.getWorld().getMusic()));
		
		
		
		
		while(running)
		/*refresh = true;
		while(running) {
			System.out.println(refresh);
			if(refresh) {
				
				tick();
				render();
				refresh = false;
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						
						refresh = true;
						
					}}, 17);
				
				
				
			}
			
		}*/
		stop();
		
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void init() {
		display = new Display(title,width,height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		GameStateManager.setState(gameState);
		

	}
	
	
	
	private void tick() {
		keyManager.tick();
		
		if(GameStateManager.getState() != null) {
			GameStateManager.getState().tick();
		}
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);//Clear all the screen.
		
		
		if(GameStateManager.getState() != null) {
			GameStateManager.getState().render(g);
		}
		
		
		bs.show();
		g.dispose();
	}
	
	public synchronized void start() {
		
		if(!running) {
			
			running = true;
			thread = new Thread(this);
			thread.start();
			
		}
	}
	
	public synchronized void stop() {
		if(running) {
			
			running = false;
			try {
				thread.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
