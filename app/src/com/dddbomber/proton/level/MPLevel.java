package com.dddbomber.proton.level;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.dddbomber.proton.Game;
import com.dddbomber.proton.assets.Asset;
import com.dddbomber.proton.assets.Bitmap;
import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.entity.PredefinedColors;
import com.dddbomber.proton.entity.Enemy;
import com.dddbomber.proton.entity.Entity;
import com.dddbomber.proton.entity.EntitySpawner;
import com.dddbomber.proton.entity.NamedEntity;
import com.dddbomber.proton.entity.Player;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.menu.ColorSelectMenu;
import com.dddbomber.proton.menu.GainInEloMenu;
import com.dddbomber.proton.menu.Menu;

public class MPLevel extends Level{
	public final int opponentRank;
	public final PredefinedColors opponentColors;
	
	public MPLevel(PredefinedColors color, PredefinedColors opponentColors, int opponentRank){
		super(color);
		this.opponentColors = opponentColors;
		this.opponentRank = opponentRank;
	}
	
	public void render(Screen screen){
		if(countdown < 61 && countdown > 0){
			screen.draw("Go", screen.width/2-8, 64, 0xffffff, 2);
		}else if(countdown >= 60){
			screen.draw(""+(countdown)/60, screen.width/2-6, 96, 0xffffff, 2);
		}

		if(player.removed || (enemies < 1 && countdown == 0)){
			if(restartDelay-- <= 0){
				String msg = "";
				if(won == 1)msg = "You Won";
				if(won == 2)msg = "You Lose";
				if(won == 3)msg = "You Draw";
				screen.draw(msg, screen.width/2-msg.length()*6, 64, 0xffffff, 2);
				screen.draw("Press Space To Restart", screen.width/2-132, 96, 0xffffff, 2);
			}
		}
		if(slowDown > 0 && ticks % 2 == 0){
			//Skip a tick
		}else{
			if(back == -1){
				int o1 = 0x2E2128;
				int o2 = Bitmap.merge(o1, player.col, 80);
				int o3 = Bitmap.merge(o2, enemy.col, 80);
				int o4 = Bitmap.merge(o2, 0, 85);
				back = o4;
			}
			screen.fill(0, 0, screen.width, screen.height, back, 20);
			for(Entity e : entities){
				e.render(this, screen, xScroll, yScroll);
			}
			
		}
		int offset = 8;
		for(Entity e : entities){
			if(e instanceof Enemy || e instanceof Player){
				renderOverlayForEntity(screen, offset, (NamedEntity)e);
				renderRank(screen, offset, e);
				offset = screen.width-132;
			}
		}
	}


	public void renderRank(Screen screen, int offset, Entity e){
		screen.drawTrans(Asset.rank_overlay, offset-4, screen.height-36, 0, 0, 32, 32, 50);
		screen.drawTrans(Asset.ranks[(e == player ? 9 : 13)], offset, screen.height-32, 0, 0, 24, 24, 50);
	}
	
	public void renderOverlayForEntity(Screen screen, int offset, NamedEntity player){
		screen.fill(offset+28, screen.height-32, 96, 24, player.colors.col, 10);

		screen.draw(""+player.health, offset+28, screen.height-31, player.colors.light, 2);
		screen.fill(offset+28, screen.height-16, 96, 8, player.colors.col);
		screen.fill(offset+28, screen.height-16, player.health*3, 8, player.colors.light);
		
		screen.draw(player.name, offset+52, screen.height-31, player.colors.light, 1);
	}
	
	public void tick(InputHandler input){
		if(countdown > 0)countdown--;
		ticks++;
		slowDown--;
		if(slowDown > 0 && ticks % 2 == 0){
			return;
		}
		enemies = 0;
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.tick(this, input);
			if(e.removed){
				entities.remove(i--);
			}else{
				if(e instanceof Enemy || (e instanceof EntitySpawner && ((EntitySpawner)e).entityToSpawn instanceof Enemy))enemies++;
			}
		}
		if(player.removed || (enemies < 1 && countdown == 0)){
			if(player.removed){
				won = 2;
				if(enemies < 1)won = 3;
			}else{
				won = 1;
			}
			if(restartDelay-- <= 0){
				if(input.keyboard.keys[KeyEvent.VK_SPACE]){
					Menu.menu = new ColorSelectMenu();
				}
			}
		}
	}
}
