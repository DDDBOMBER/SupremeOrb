package com.dddbomber.proton.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.dddbomber.proton.assets.Asset;
import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.entity.Colors;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.level.Level;
import com.dddbomber.proton.rank.Rank;

public class ColorSelectMenu extends ScreenMenu {

	static Random random = new Random();
	
	public Colors selected;
	public Colors hovered;
	
	public void tick(InputHandler input) {
		int x = 48;
		hovered = null;
		for(Colors c : Colors.cols()){
			
			Rectangle cr = new Rectangle(x-24, 104, 48, 48);
			Point p = new Point(input.mouse.x, input.mouse.y);
			if(cr.contains(p))hovered = c;
			x += 48;
		}
		if(input.mouse.left)selected = hovered;
		if(selected != null && input.keyboard.keys[KeyEvent.VK_SPACE]){
			Menu.menu = new GameMenu(selected);
		}
	}
	
	public void render(Graphics g, int width, int height) {
		screen.fill(0, 0, screen.width, screen.height, 0x2E2128, 20);
		int x = 48;
		for(Colors c : Colors.cols()){
			screen.fill(x-24, 104, 1, 48, c.col, 10);
			screen.fill(x-24, 104, 48, 1, c.col, 10);
			screen.fill(x-24, 104+47, 48, 1, c.col, 10);
			screen.fill(x+23, 104, 1, 48, c.col, 10);
			screen.renderFaint(x, 128, 32+(int)(Math.random()*5), c.col);
			screen.renderLight(x, 128, 8+(int)(Math.random()*5), c.col);
			screen.renderLight(x, 128, 8+(int)(Math.random()*5), c.light);
			
			if(c == selected || c == hovered){

				screen.fill(x-24, 104, 48, 48, c.light, 25);
			}
			
			x+=48;
		}
		String msg = "SELECT A COLOR";
		screen.draw(msg, screen.width/2-msg.length()*6, 32, 0xffffff, 2);
		
		if(selected != null){
			msg = "PRESS SPACE TO START";
			screen.draw(msg, screen.width/2-msg.length()*6, 160, 0xffffff, 2);
		}
		super.render(g, width, height);
	}

}
