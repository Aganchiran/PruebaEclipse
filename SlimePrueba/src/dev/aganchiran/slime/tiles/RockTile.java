package dev.aganchiran.slime.tiles;

import dev.aganchiran.slime.gfx.Assets;

public class RockTile extends Tile{

	public RockTile(int id) {
		super(Assets.stones, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
