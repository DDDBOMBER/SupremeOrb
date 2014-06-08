package com.dddbomber.proton.entity;

import java.awt.Rectangle;
import java.util.Random;

import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.level.Level;

public abstract class Entity {
	public double x, y;
	public int xSize, ySize;
	
	public double xSpeed, ySpeed;
	
	public int size;
	public boolean removed;
	
	public int hurtTime;
	public PredefinedColors hurtColor;
	public PredefinedColors colors;
	
	protected Random random = new Random();
	
	public abstract void tick(Level level, InputHandler input);

	public abstract void render(Level level, Screen screen, int xScroll, int yScroll);
	
	public double fallSpeed;
	public int health = 32;
	
	public boolean intersects(Entity e){
		Rectangle tr = new Rectangle((int) x - xSize, (int) y - ySize, xSize*2, ySize*2);
		Rectangle er = new Rectangle((int) e.x - e.xSize, (int) e.y - e.ySize, e.xSize*2, e.ySize*2);
		return tr.intersects(er);
	}
}
