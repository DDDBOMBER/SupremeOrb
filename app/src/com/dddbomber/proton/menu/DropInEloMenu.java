package com.dddbomber.proton.menu;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.dddbomber.proton.assets.Asset;
import com.dddbomber.proton.entity.PredefinedColors;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.rank.Rank;

public class DropInEloMenu extends ScreenMenu {

	static Random random = new Random();
	
	public int previousELO = 1800, newELO;
	public int displayedELO = previousELO;
	
	public DropInEloMenu(int newELO){
		this.newELO = newELO;
	}
	
	public int ticks = 0;
	
	public void tick(InputHandler input) {
		if(displayedELO > newELO)displayedELO--;
	}
	
	public void render(Graphics g, int width, int height) {
		screen.fill(0, 0, screen.width, screen.height, 0x2E2128, 20);
		
		for(int i = 0; i < 300; i++){
			screen.renderLight(screen.width/2-150+i, 256, 4, 0x404040);
		}
		for(int i = 0; i < previousELO/8; i++){
			screen.renderLight(screen.width/2-150+i, 256, 4, 0xffffff);
		}
		for(int i = 0; i < (previousELO-displayedELO)/8; i++){
			screen.renderLight(screen.width/2-150+(previousELO/8)-i, 256, 4, 0x8B0800);
		}

		String msg = "You Have Lost "+(newELO - previousELO)+" Ranking Points";
		screen.draw(msg, screen.width/2-msg.length()*6, 224, 0xffffff, 2);
		

		

		screen.drawTrans(Asset.rank_overlay, screen.width/2-16, 180, 0, 0, 32, 32, 50);
		screen.drawTrans(Asset.ranks[10], screen.width/2-12, 184, 0, 0, 24, 24, 50);

		msg = "Your Current Rank";
		screen.draw(msg, screen.width/2-msg.length()*6, 164, 0xffffff, 2);
		

		screen.drawTrans(Asset.rank_overlay, 42, screen.height-80, 0, 0, 32, 32, 50);
		screen.drawTrans(Asset.ranks[10], 46, screen.height-76, 0, 0, 24, 24, 50);
		

		screen.drawTrans(Asset.rank_overlay, screen.width-78, screen.height-80, 0, 0, 32, 32, 50);
		screen.drawTrans(Asset.ranks[11], screen.width-74, screen.height-76, 0, 0, 24, 24, 50);
		
		if(displayedELO == newELO){
			
		}
		super.render(g, width, height);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

}
