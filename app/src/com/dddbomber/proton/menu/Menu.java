package com.dddbomber.proton.menu;

import java.awt.Graphics;

import com.dddbomber.proton.input.InputHandler;

public abstract class Menu {
	
	public static int SCREENWIDTH = 480, SCREENHEIGHT = 320;
	
	public static Menu menu = new DropInEloMenu(1748);
	
	public abstract void tick(InputHandler input);
	
	public abstract void render(Graphics g, int width, int height);
	
}
