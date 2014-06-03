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

public class RankMenu extends ScreenMenu {

	static Random random = new Random();
	
	public void tick(InputHandler input) {
	}
	
	public void render(Graphics g, int width, int height) {
		screen.fill(0, 0, screen.width, screen.height, 0x2E2128, 20);
		for(int i = 0; i < 12; i++){
			screen.draw(Asset.rank_overlay, 8, 2+i*32, 0, 0, 32, 32);
			screen.draw(Rank.rankIcons[i], 12, 6+i*32, 0, 0, 24, 24);
			screen.draw(""+Rank.rankNames[i], 48, 14+i*32, 0xffffff, 1);
		}
		
		super.render(g, width, height);
	}

}
