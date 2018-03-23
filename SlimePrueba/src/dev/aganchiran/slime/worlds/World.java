package dev.aganchiran.slime.worlds;

import java.awt.Graphics;

import dev.aganchiran.slime.Handler;
import dev.aganchiran.slime.entities.EntityManager;
import dev.aganchiran.slime.entities.creatures.Player;
import dev.aganchiran.slime.entities.statics.Tree;
import dev.aganchiran.slime.gfx.Assets;
import dev.aganchiran.slime.tiles.Tile;
import dev.aganchiran.slime.tiles.TileManager;
import dev.aganchiran.slime.utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	private String music;
	
	private EntityManager entityManager;
	
	public World(Handler handler, String path, String music) {
		this.music = music;
		this.handler = handler;
		
		entityManager = new EntityManager(handler, new Player(handler, spawnX, spawnY));
		entityManager.addEntity(new Tree(handler,300,808));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick() {
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		
		g.drawImage(Assets.background, (int) (0 - handler.getGameCamera().getxOffset()), (int)(-10  - handler.getGameCamera().getyOffset()), 2880, 960, null);
		
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset()/Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth())/Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset()/Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight())/Tile.TILEHEIGHT + 1);
		
		
		for(int y = yStart ; y < yEnd ; y++) {
			for(int x = xStart ; x < xEnd ; x++) {
				getTile(x, y).render(g, (int)(x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), 
						(int) (y * Tile.TILEHEIGHT  - handler.getGameCamera().getyOffset()));
			}
		}
		
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) 
			return TileManager.grassTile;
		
		
		Tile t = TileManager.tiles[tiles[x][y]];
		if(t == null) 
			return TileManager.grassTile;
		
		return t;
	}
	
	private void loadWorld(String path) {
		
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		
		tiles = new int[width][height];
		for(int y = 0; y < height ; y++) {
			for(int x = 0; x < width ; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
