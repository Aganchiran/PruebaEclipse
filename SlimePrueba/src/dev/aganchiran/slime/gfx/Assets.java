package dev.aganchiran.slime.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	private static final int cellWidth = 31, cellHeight = 31;
	
	public static BufferedImage floatingSlime, background, grass, stones, tree, empty;
	public static BufferedImage[] jerry_idle, jerry_right, jerry_left;
	
	public static void init() {
		background = ImageLoader.loadImage("/textures/forest.png");
		
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet.png"));
		
		
		jerry_idle = new BufferedImage[26];
		initializeFrames(jerry_idle, sheet, 0, 0);
		
		jerry_right = new BufferedImage[26];
		initializeFrames(jerry_right, sheet, 0, 3 * cellHeight);
		
		jerry_left = new BufferedImage[26];
		initializeFrames(jerry_left, sheet, 0, 5 * cellHeight);
		
		
		floatingSlime = sheet.crop(0, 2*cellHeight, cellWidth, cellHeight);
		grass = sheet.crop(18 * cellWidth, 15*cellHeight, cellWidth, cellHeight);
		stones = sheet.crop(17 * cellWidth, 15*cellHeight, cellWidth, cellHeight);
		tree = sheet.crop(17 * cellWidth, 12*cellHeight, 2*cellWidth, cellHeight);
		empty = sheet.crop(19 * cellWidth, 15*cellHeight, cellWidth, cellHeight);
	}
	
	private static void initializeFrames(BufferedImage[] frames, SpriteSheet sheet, int startXPos, int startYPos) {
		
		
		for(int i = 0 ; i < frames.length ; i++) {
			
			frames[i] = sheet.crop(startXPos + (i % (sheet.getWidth()/cellWidth)) * cellWidth, 
					startYPos + (i / (sheet.getWidth()/cellWidth)) * cellHeight, cellWidth, cellHeight);
			
		}
		
		
	}
	
}
