package com.dddbomber.proton.entity;

import java.awt.Rectangle;
import com.dddbomber.proton.Game;
import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.level.Level;

public class Bullet extends Entity {
	public int col, light;
	
	public Entity owner;
	
	public Bullet(PredefinedColors colors, double x, double y, double rotation, int size, Entity owner){
		this.x = x;
		this.y = y;
		xSize = 16;
		ySize = 16;
		this.xSpeed = Math.sin(Math.toRadians(rotation))*5.0;
		this.ySpeed = Math.cos(Math.toRadians(rotation))*5.0;
		this.colors = colors;
		col = colors.col;
		light = colors.light;
		this.size = size+3;
		xSize = ySize = size;
		this.owner = owner;
	}
	
	public PredefinedColors colors;
	public boolean hit = false;
	
	@Override
	public void tick(Level level, InputHandler input){
		if(hit){
			
		}else{
			x += xSpeed;
			y -= ySpeed;

			for(int i = 0; i < level.entities.size(); i++){
				Entity e = level.entities.get(i);
				if(e instanceof Particle)continue;
				if(!(e instanceof Bullet) && e != owner && (!(owner instanceof Enemy) || (!(e instanceof Enemy)))){
					if(this.intersects(e)){
						if(e instanceof Enemy){
							level.shotsHit++;
						}
						e.health--;
						e.xSpeed += xSpeed;
						e.ySpeed += -ySpeed;
						hit = true;
						Game.hit.play(7.5);
						e.hurtTime = 12;
						level.slowDown = 18;
						e.hurtColor = colors;
						if(e.health <= 0){
							e.removed = true;
							hit = false;
							for(int ip = 0; ip < 5; ip++){
								level.entities.add(new Particle(colors, x, y, random.nextInt(360), 6));
							}
						}
					}
				}
			}
		}
		
		if(removed){
			for(int i = 0; i < 25; i++){
				level.entities.add(new Particle(colors, x, y, random.nextInt(360), 6));
			}
		}
	}

	public void render(Level level, Screen screen, int xScroll, int yScroll) {
		screen.renderFaint((int)x-xScroll, (int)y-yScroll, size*3, col);
		screen.renderGlow((int)x-xScroll, (int)y-yScroll, size, col);
		screen.renderLight((int)x-xScroll, (int)y-yScroll, size/2, light);
		
		Rectangle tr = new Rectangle((int) x - xSize, (int) y - ySize, xSize*2, ySize*2);
		Rectangle screenRect = new Rectangle(0, 0, screen.width, screen.height);
		if(!screenRect.contains(tr))removed = true;

		if(hit){
			screen.fill(0, 0, screen.width, screen.height, colors.col, 50);
			removed = true;
		}
	}

}
