package dev.aganchiran.slime.tiles;

public class TileManager {
	public static Tile[] tiles = new Tile[256];
	public static Tile emptyTile = new EmptyTile(0);
	public static Tile grassTile = new GrassTile(1);
	public static Tile rockTile = new RockTile(2);
}
