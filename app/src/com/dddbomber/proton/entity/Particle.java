package com.dddbomber.proton.entity;

import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.level.Level;

public class Particle extends Entity {
	public int col, light;
	
	public boolean moving;
	
	public Particle(Colors colors, double x, double y, double rotation, int size, boolean moving){
		this.x = x;
		this.y = y;
		xSize = 16;
		ySize = 16;
		this.xSpeed = Math.sin(Math.toRadians(rotation))*Math.random()*3;
		this.ySpeed = Math.cos(Math.toRadians(rotation))*Math.random()*3;
		this.colors = colors;
		col = colors.col;
		light = colors.light;
		this.size = size+random.nextInt(5)-2;
		if(!moving)this.size = size;
		xSize = ySize = size;
		this.moving = moving;
	}
	
	public Particle(Colors colors, double x, double y, int rotation, int size) {
		this(colors, x, y, rotation, size, true);
	}

	public Colors colors;
	public int sizeDelay;
	
	@Override
	public void tick(Level level, InputHandler input){
		if(moving){
			x += xSpeed;
			y -= ySpeed;
		}
		sizeDelay++;
		if(sizeDelay > 5){
			sizeDelay = 0;
			size--;
			xSize = ySize = size;
			if(size <= 0)removed = true;
		}
	}
	
	public void render(Level level, Screen screen, int xScroll, int yScroll) {
		screen.renderFaint((int)x-xScroll, (int)y-yScroll, size*4, col);
		screen.renderGlow((int)x-xScroll, (int)y-yScroll, size+1, (!moving ? light : col));
		screen.renderLight((int)x-xScroll, (int)y-yScroll, size/2+1, light);
	}

}
