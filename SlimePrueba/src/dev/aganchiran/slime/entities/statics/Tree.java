package dev.aganchiran.slime.entities.statics;

import java.awt.Graphics;

import dev.aganchiran.slime.Handler;
import dev.aganchiran.slime.gfx.Assets;
import dev.aganchiran.slime.tiles.Tile;

public class Tree extends StaticEntity{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, 2 * Tile.TILEWIDTH, Tile.TILEHEIGHT);
		
		bounds.x = 18;
		bounds.y = 8;
		bounds.width = width - 28;
		bounds.height = height -8;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree,(int) (x - handler.getGameCamera().getxOffset()), (int) (y  - handler.getGameCamera().getyOffset()), width, height, null);
	}

}
