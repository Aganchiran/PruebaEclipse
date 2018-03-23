package dev.aganchiran.slime.states;

import java.awt.Graphics;

import dev.aganchiran.slime.Handler;
import dev.aganchiran.slime.worlds.World;

public class GameState extends State{

	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt","tem.wav");
		handler.setWorld(world);
		world.getEntityManager().getPlayer().setAnimationSpeed();
		
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		
		world.render(g);
		
	}
	
}
