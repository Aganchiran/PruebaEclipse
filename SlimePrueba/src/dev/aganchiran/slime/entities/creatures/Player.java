package dev.aganchiran.slime.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.aganchiran.slime.Handler;
import dev.aganchiran.slime.gfx.Animation;
import dev.aganchiran.slime.gfx.Assets;
import dev.aganchiran.slime.media.Music;

public class Player extends Creature {

	
	
	//Animations.
	private Animation idle;
	private Animation right;
	private Animation left;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 10;
		bounds.y = 32;
		bounds.width = 44;
		bounds.height = 28;
		
		idle = new Animation(100 ,Assets.jerry_idle);
		right = new Animation(100,Assets.jerry_right);
		left = new Animation(100,Assets.jerry_left);
		
	}

	@Override
	public void tick() {
		
//		if(onAir) {
//			idle.setCurrentFrame(14);
//			right.setCurrentFrame(14);
//			left.setCurrentFrame(14);
//		} else {
			idle.tick();
			right.tick();
			left.tick();
//		}

		getInput();
		fall();
		move();
		
		handler.getGameCamera().centerOnEntity(this);
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
//		if(handler.getKeyManager().up) yMove = -speed;
//		if(handler.getKeyManager().down) yMove = +speed;
		if(handler.getKeyManager().right) xMove = +speed;
		if(handler.getKeyManager().left) xMove = -speed;
		
		if(handler.getKeyManager().up && !onAir) {
			onAir = true;
			airSpeed = jumpForce;
		}
		
	}
	
	private void fall() {
		
		yMove = -airSpeed;
		airSpeed -= weight;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
	
//		g.setColor(Color.RED);
//		g.fillRect((int) (x + bounds.x -handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y -handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(onAir) {
			return Assets.floatingSlime;
		}else if(xMove > 0) {
			return right.getCurrentFrame();
		}else if(xMove < 0){
			return left.getCurrentFrame();
		}
		return idle.getCurrentFrame();
	}
	
	public void setAnimationSpeed() {
		idle.setSpeed(Music.getRithm(handler.getWorld().getMusic()));
		right.setSpeed(Music.getRithm(handler.getWorld().getMusic()));
		left.setSpeed(Music.getRithm(handler.getWorld().getMusic()));
	}

}
