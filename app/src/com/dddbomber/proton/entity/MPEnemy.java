package com.dddbomber.proton.entity;

import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.cpu.NameGenerator;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.level.Level;

public class MPEnemy extends Enemy {

	public boolean walking, left;
	public int anim, shootDelay;
	
	public int col, light;
	
	public MPEnemy(int x, int y, PredefinedColors colors, String name){
		super(x, y);
		this.colors = colors;
		this.name = name;
		col = colors.col;
		light = colors.light;
	}
	
	@Override
	public void tick(Level level, InputHandler input) {
		
		if(ySpeed > 0.5)ySpeed -= 0.5;
		else if(ySpeed < -0.5)ySpeed += 0.5;
		else ySpeed = 0;
		
		if(xSpeed > 0.5)xSpeed -= 0.5;
		else if(xSpeed < -0.5)xSpeed += 0.5;
		else xSpeed = 0;
		
		x += xSpeed;
		y += ySpeed;
		
		if(xSpeed < -4)xSpeed = -4;
		if(xSpeed > 4)xSpeed = 4;
		if(ySpeed < -4)ySpeed = -4;
		if(ySpeed > 4)ySpeed = 4;
		
		if(shootDelay > 0)shootDelay--;
		if(shootDelay == 0){
			shootDelay = 20;
			level.entities.add(new Bullet(colors, x+2, y+2, Math.toDegrees(Math.atan2(level.player.y-y+level.player.ySpeed*5, level.player.x-x+level.player.xSpeed*5))+90, size, this));
			double rot = Math.toRadians(Math.toDegrees(Math.atan2(level.player.y-y+level.player.ySpeed*5, level.player.x-x+level.player.xSpeed*5))+90);
			x -= Math.sin(rot)*1;
			y += Math.cos(rot)*1;
		}
		
		xSize = ySize = size;
		if(hurtTime > 0)hurtTime--;

		
		if(removed){
			level.slowDown = 36;
			for(int i = 0; i < 75; i++){
				Particle p = new Particle(colors, x, y, random.nextInt(360), 8);
				p.tick(level, input);
				level.entities.add(p);
			}
		}
	}

	public void render(Level level, Screen screen, int xScroll, int yScroll) {
		if(hurtTime > 0){
			screen.renderLight((int)x-xScroll, (int)y-yScroll, size+shootDelay/2, hurtColor.light);
		}
		screen.renderFaint((int)x-xScroll, (int)y-yScroll, size*4, col);
		screen.renderLight((int)x-xScroll, (int)y-yScroll, size+shootDelay/2, col);
		screen.renderLight((int)x-xScroll, (int)y-yScroll, size+shootDelay/2, light);
	}

}
