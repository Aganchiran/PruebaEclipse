package dev.aganchiran.slime.entities.statics;

import dev.aganchiran.slime.Handler;
import dev.aganchiran.slime.entities.Entity;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}
}
