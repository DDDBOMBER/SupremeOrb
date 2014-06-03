package com.dddbomber.proton.menu;

import java.awt.Graphics;

import com.dddbomber.proton.assets.Screen;

public abstract class ScreenMenu extends Menu{
	
	public ScreenMenu(){
		screen = new Screen(SCREENWIDTH, SCREENHEIGHT);
		screen.fill(0, 0, screen.width, screen.height, 0x2A1D24);
	}
	
	public Screen screen;
	
	public void render(Graphics g, int width, int height){
		if(SCREENWIDTH != width/2 || SCREENHEIGHT != height/2){
			screen = new Screen(SCREENWIDTH=width/2, SCREENHEIGHT=height/2);
		}

		g.drawImage(screen.getImage(), 0, 0, width, height, null);
	}
}
