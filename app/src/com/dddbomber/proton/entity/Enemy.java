package com.dddbomber.proton.entity;

import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.cpu.NameGenerator;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.level.Level;

public class Enemy extends NamedEntity {

	public boolean walking, left;
	public int anim, shootDelay;
	
	public int col, light;
	
	public Enemy(int x, int y){
		this.x = x;
		this.y = y;
		size = 10;
		colors = PredefinedColors.getRandomCol();
		name = "CPU "+NameGenerator.generateName();
		col = colors.col;
		light = colors.light;
	}
	
	public int dir;
	
	@Override
	public void tick(Level level, InputHandler input) {
		
		if(random.nextInt(50) == 0)dir = random.nextInt(4);
		
		if(dir == 0)ySpeed-=0.5;
		else if(dir == 1)ySpeed+=0.5;
		else if(ySpeed > 0.5)ySpeed -= 0.5;
		else if(ySpeed < -0.5)ySpeed += 0.5;
		else ySpeed = 0;
		
		if(dir == 2)xSpeed-=0.5;
		else if(dir == 3)xSpeed+=0.5;
		else if(xSpeed > 0.5)xSpeed -= 0.5;
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
		if(x < size){
			x = size;
			xSpeed = -xSpeed;
			dir = 3;
		}
		if(y < size){
			y = size;
			ySpeed = -ySpeed;
			dir = 1;
		}
		if(x > screen.width-xSize){
			x = screen.width-xSize;
			xSpeed = -xSpeed;
			dir = 2;
		}
		if(y > screen.height-ySize){
			y = screen.height-ySize;
			ySpeed = -ySpeed;
			dir = 0;
		}
	}

}
