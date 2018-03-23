package dev.aganchiran.slime.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	private float speed;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		
	}
	
	public void tick() {
		timer += System.nanoTime() - lastTime;
		lastTime = System.nanoTime();
		
		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length)
				index = 0;
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	public void setCurrentFrame(int i) {
		index = i;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}
