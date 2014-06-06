package com.dddbomber.proton.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.dddbomber.proton.Game;
import com.dddbomber.proton.account.ServerCommunication;
import com.dddbomber.proton.assets.Bitmap;
import com.dddbomber.proton.entity.Colors;
import com.dddbomber.proton.input.InputHandler;

public class LoginMenu extends ScreenMenu {

	static Random random = new Random();
	
	public boolean selected = true;
	
	public String username = "", password = "";
	
	public int ticks = 0;
	
	public void tick(InputHandler input) {
		ticks++;
		if(input.keyboard.keys[KeyEvent.VK_ENTER]){
			if(!username.equals("")){
				if(selected)selected = false;
			}
			if(!password.equals("")){
				if(!selected){
					if(ServerCommunication.login(Game.account, username, password)){
						Menu.menu = new GameModeMenu();
					}else{
						selected = true;
						password = "";
					}
				}
			}
		}
	}
	
	public void render(Graphics g, int width, int height) {
		screen.fill(0, 0, screen.width, screen.height, 0x2E2128, 20);
		
		String msg = "Username";
		screen.draw(msg, screen.width/2-msg.length()*6, 64, 0xffffff, 2);
		
		msg = "Password";
		screen.draw(msg, screen.width/2-msg.length()*6, 128, 0xffffff, 2);
		
		screen.fill(screen.width/2-200, 92, 400, 24, 0, 25);
		screen.draw(username+(selected && ticks%40>20 ? "_" : ""),  screen.width/2-username.length()*6, 96, 0xffffff, 2);
		
		String s = "";
		for(int i = 0; i < password.length(); i++){
			s += "*";
		}
		
		screen.fill(screen.width/2-200, 156, 400, 24, 0, 25);
		screen.draw(s+(!selected && ticks%40>20 ? "_" : ""),  screen.width/2-s.length()*6, 160, 0xffffff, 2);
		
		super.render(g, width, height);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		if(selected){
			if(event.getKeyChar() == KeyEvent.VK_BACK_SPACE){
				if(username.length() > 0)username = username.substring(0, username.length()-1);
			}else if(Bitmap.chars.indexOf(event.getKeyChar()) >= 0){
				username += event.getKeyChar();
			}
		}else{
			if(event.getKeyChar() == KeyEvent.VK_BACK_SPACE){
				if(password.length() > 0)password = password.substring(0, password.length()-1);
			}else if(Bitmap.chars.indexOf(event.getKeyChar()) >= 0){
				password += event.getKeyChar();
			}
		}
	}

}
