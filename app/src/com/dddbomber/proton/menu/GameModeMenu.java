package com.dddbomber.proton.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.dddbomber.proton.assets.Bitmap;
import com.dddbomber.proton.entity.Colors;
import com.dddbomber.proton.input.InputHandler;

public class GameModeMenu extends ScreenMenu {

	static Random random = new Random();
	
	public Colors selected;                                  
	public Colors hovered;
	
	public String ip = "";
	
	public int ticks = 0;
	
	public void tick(InputHandler input) {
		ticks++;
		if(input.keyboard.keys[KeyEvent.VK_ENTER]){
			if(ip.equals("")){
				Menu.menu = new ColorSelectMenu();
			}else{
				// connect to ip
			}
		}
	}
	
	public void render(Graphics g, int width, int height) {
		screen.fill(0, 0, screen.width, screen.height, 0x2E2128, 20);
		
		String msg = (ip.equals("") ? "Press Enter To Play Bots" : "Press Enter To Try And Connect");
		screen.draw(msg, screen.width/2-msg.length()*6, 32, 0xffffff, 2);
		
		screen.fill(screen.width/2-200, 128, 400, 24, 0, 25);
		screen.draw(ip+(ticks%40>20 ? "_" : ""),  screen.width/2-196, 132, 0xffffff, 2);
		
		super.render(g, width, height);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		if(event.getKeyChar() == KeyEvent.VK_BACK_SPACE){
			if(ip.length() > 0)ip = ip.substring(0, ip.length()-1);
		}else if(Bitmap.chars.indexOf(event.getKeyChar()) >= 0){
			ip += event.getKeyChar();
		}
	}

}
