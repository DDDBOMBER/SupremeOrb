package com.dddbomber.proton.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.dddbomber.proton.assets.Asset;
import com.dddbomber.proton.entity.Colors;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.rank.Rank;

public class GainInEloMenu extends ScreenMenu {

	static Random random = new Random();
	
	public int previousELO = 1400, newELO;
	public int displayedELO = previousELO;
	
	public GainInEloMenu(int newELO){
		this.newELO = newELO;
	}
	
	public int ticks = 0;
	
	public void tick(InputHandler input) {
		if(displayedELO < newELO)displayedELO++;
	}
	
	public void render(Graphics g, int width, int height) {
		screen.fill(0, 0, screen.width, screen.height, 0x2E2128, 20);
		
		for(int i = 0; i < 300; i++){
			screen.renderLight(screen.width/2-150+i, 256, 4, 0x404040);
		}
		for(int i = 0; i < previousELO/8; i++){
			screen.renderLight(screen.width/2-150+i, 256, 4, 0xffffff);
		}
		for(int i = 0; i < (displayedELO-previousELO)/8; i++){
			screen.renderLight(screen.width/2-150+i+previousELO/8, 256, 4, 0xC7D93D);
		}

		String msg = "You Have Gained "+(newELO - previousELO)+" Ranking Points";
		screen.draw(msg, screen.width/2-msg.length()*6, 224, 0xffffff, 2);
		

		

		screen.drawTrans(Asset.rank_overlay, screen.width/2-16, 180, 0, 0, 32, 32, 50);
		screen.drawTrans(Asset.ranks[4], screen.width/2-12, 184, 0, 0, 24, 24, 50);

		msg = "Your Current Rank";
		screen.draw(msg, screen.width/2-msg.length()*6, 164, 0xffffff, 2);
		

		screen.drawTrans(Asset.rank_overlay, 42, screen.height-80, 0, 0, 32, 32, 50);
		screen.drawTrans(Asset.ranks[4], 46, screen.height-76, 0, 0, 24, 24, 50);
		

		screen.drawTrans(Asset.rank_overlay, screen.width-78, screen.height-80, 0, 0, 32, 32, 50);
		screen.drawTrans(Asset.ranks[5], screen.width-74, screen.height-76, 0, 0, 24, 24, 50);
		
		if(displayedELO == newELO){
			
		}
		super.render(g, width, height);
	}

}
