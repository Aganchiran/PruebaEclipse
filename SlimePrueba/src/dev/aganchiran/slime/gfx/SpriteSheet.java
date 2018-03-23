package dev.aganchiran.slime.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;
	private int width;
	private int height;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
		width = sheet.getWidth();
		height = sheet.getHeight();
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
