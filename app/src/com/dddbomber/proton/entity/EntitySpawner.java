package com.dddbomber.proton.entity;

import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.level.Level;

public class EntitySpawner extends Entity{

	public int targetSize, sizeIncreaseDelay;
	public Entity entityToSpawn;
	
	public EntitySpawner(Entity entityToSpawn){
		this.entityToSpawn = entityToSpawn;
		this.x = entityToSpawn.x;
		this.y = entityToSpawn.y;
		this.colors = entityToSpawn.colors;
		this.targetSize = entityToSpawn.size;
	}
	
	public void tick(Level level, InputHandler input) {
		sizeIncreaseDelay++;
		if(sizeIncreaseDelay > 12){
			sizeIncreaseDelay = 0;
			size++;
			if(size > targetSize){
				level.entities.add(entityToSpawn);
				removed = true;
				level.slowDown = 12;
			}
			for(int i = 0; i < 3; i++){
				level.entities.add(new Particle(colors, x, y, random.nextInt(360), 4));
			}
		}
	}

	public void render(Level level, Screen screen, int xScroll, int yScroll) {
		screen.renderFaint((int)x-xScroll, (int)y-yScroll, size*8, colors.light);
		screen.renderLight((int)x-xScroll, (int)y-yScroll, size, colors.col);
		screen.renderLight((int)x-xScroll, (int)y-yScroll, size, (size == targetSize ? colors.col : colors.light));
	}

}
