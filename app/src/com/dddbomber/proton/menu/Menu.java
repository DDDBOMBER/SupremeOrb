package com.dddbomber.proton.menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.dddbomber.proton.input.InputHandler;

public abstract class Menu {
	
	public static int SCREENWIDTH = 480, SCREENHEIGHT = 320;
	
	public static Menu menu = new GameModeMenu();
	
	public abstract void tick(InputHandler input);
	
	public abstract void render(Graphics g, int width, int height);

	public abstract void keyTyped(KeyEvent event);
	
}
