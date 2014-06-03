package com.dddbomber.proton.entity;

import java.awt.event.KeyEvent;

import com.dddbomber.proton.Game;
import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.level.Level;

public class Player extends NamedEntity {

	public boolean walking, left;
	public int anim, shootDelay;
	
	public int col, light;
	
	public Player(Colors color, int x, int y){
		colors = color;
		size = 10;
		this.x = x;
		this.y = y;
		name = Game.account.username;
	}
	
	@Override
	public void tick(Level level, InputHandler input) {
		col = colors.col;
		light = colors.light;
		
		if(input.keyboard.keys[KeyEvent.VK_W])ySpeed-=0.5;
		else if(input.keyboard.keys[KeyEvent.VK_S])ySpeed+=0.5;
		else if(ySpeed > 0.5)ySpeed -= 0.5;
		else if(ySpeed < -0.5)ySpeed += 0.5;
		else ySpeed = 0;
		
		if(input.keyboard.keys[KeyEvent.VK_A])xSpeed-=0.5;
		else if(input.keyboard.keys[KeyEvent.VK_D])xSpeed+=0.5;
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
		if(shootDelay == 0  && input.mouse.left){
			shootDelay = 20;
			level.entities.add(new Bullet(colors, x+2, y+2, Math.toDegrees(Math.atan2(input.mouse.y-y, input.mouse.x-x))+90, size, this));
			double rot = Math.toRadians(Math.toDegrees(Math.atan2(input.mouse.y-y, input.mouse.x-x))+90);
			x -= Math.sin(rot)*1;
			y += Math.cos(rot)*1;
			level.shotsFired++;
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
			screen.renderLight((int)x-xScroll, (int)y-yScroll, size+shootDelay/2, hurtColor.col);
			screen.renderLight((int)x-xScroll, (int)y-yScroll, size+shootDelay/2, hurtColor.light);
		}
		screen.renderFaint((int)x-xScroll, (int)y-yScroll, size*4, col);
		screen.renderLight((int)x-xScroll, (int)y-yScroll, size+shootDelay/2, col);
		screen.renderLight((int)x-xScroll, (int)y-yScroll, size+shootDelay/2, light);
		if(x < size){
			x = size;
			xSpeed = -xSpeed;
		}
		if(y < size){
			y = size;
			ySpeed = -ySpeed;
		}
		if(x > screen.width-xSize){
			x = screen.width-xSize;
			xSpeed = -xSpeed;
		}
		if(y > screen.height-ySize){
			y = screen.height-ySize;
			ySpeed = -ySpeed;
		}
	}

}
