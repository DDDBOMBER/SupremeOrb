package com.dddbomber.proton.menu;

import java.awt.Graphics;
import java.util.Random;

import com.dddbomber.proton.assets.Asset;
import com.dddbomber.proton.input.InputHandler;

public class NeedMoreWinsMenu extends ScreenMenu {

	static Random random = new Random();
	
	public int gamesNeeded, gamesPlayed;
	
	public NeedMoreWinsMenu(int gamesPlayed){
		this.gamesNeeded = 15-gamesPlayed;
		this.gamesPlayed = gamesPlayed;
	}
	
	public int ticks = 0;
	
	public void tick(InputHandler input) {
		
	}
	
	public void render(Graphics g, int width, int height) {
		screen.fill(0, 0, screen.width, screen.height, 0x2E2128, 20);

		screen.drawTrans(Asset.rank_overlay, screen.width/2-16, 180, 0, 0, 32, 32, 50);
		screen.drawTrans(Asset.ranks[0], screen.width/2-12, 184, 0, 0, 24, 24, 50);

		String msg = "Your Current Rank Is Unknown";
		screen.draw(msg, screen.width/2-msg.length()*6, 164, 0xffffff, 2);
		
		for(int i = 0; i < 300; i++){
			screen.renderLight(screen.width/2-150+i, 256, 4, 0x404040);
		}
		for(int i = 0; i < 20*gamesPlayed; i++){
			screen.renderLight(screen.width/2-150+i, 256, 4, 0xffffff);
		}

		msg = "You Have Played "+gamesPlayed+" of "+gamesNeeded+" Games";
		screen.draw(msg, screen.width/2-msg.length()*6, 224, 0xffffff, 2);
		
		msg = "Needed To Obtain A Rank";
		screen.draw(msg, screen.width/2-msg.length()*6, 272, 0xffffff, 2);
		
		super.render(g, width, height);
	}

}
