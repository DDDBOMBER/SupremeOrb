package com.dddbomber.proton.menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.dddbomber.proton.assets.Bitmap;
import com.dddbomber.proton.entity.ColorInstance;
import com.dddbomber.proton.entity.PredefinedColors;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.inventory.EtherealItem;
import com.dddbomber.proton.inventory.Inventory;
import com.dddbomber.proton.inventory.Item;

public class InventoryMenu extends ScreenMenu {

	static Random random = new Random();
	
	public Inventory i;
	
	public ArrayList<ColorInstance> availableColors = new ArrayList<ColorInstance>();
	
	public InventoryMenu(){
		i = new Inventory();
		i.load();
	}
	
	public int ticks = 1;
	
	public void tick(InputHandler input) {
		ticks++;
		
		availableColors.clear();
		
		for(Item item : i.items){
			if(item.Type.equals("Standard")){
				availableColors.add(item.color);
			}else if(item.Type.equals("Ethereal")){
				EtherealItem ei = (EtherealItem)item;
				ColorInstance col = new ColorInstance();
				col.col = Bitmap.merge(ei.colors[0].col, ei.colors[1].col, pulse(ticks/4));
				col.col_light = Bitmap.merge(ei.colors[0].col_light, ei.colors[1].col_light, pulse(ticks/4));
				availableColors.add(col);
			}
		}
	}
	
	public int pulse(int ticks){
		if(ticks%200 >= 100){
			return 100-ticks%100;
		}else{
			return ticks%100;
		}
	}
	
	public void render(Graphics g, int width, int height) {
		screen.fill(0, 0, screen.width, screen.height, 0x2D2D2D, 20);
		
		int id = 0;
		int y = 0;
		int x = 48;
		for(ColorInstance c : availableColors){
			id++;
			if(id > 9){
				y += 48;
				id = 1;
				x = 48;
			}
			screen.fill(x-24, 104+y, 1, 48, c.col, 10);
			screen.fill(x-24, 104+y, 48, 1, c.col, 10);
			screen.fill(x-24, 104+47+y, 48, 1, c.col, 10);
			screen.fill(x+23, 104+y, 1, 48, c.col, 10);
			int growth = (int)(Math.random()*-3);
			screen.renderFaint(x, 128+y, 32+growth, c.col);
			screen.renderLight(x, 128+y, 8+growth, c.col);
			screen.renderLight(x, 128+y, 8+growth, c.col_light);
			
			x+=48;
		}
		
		super.render(g, width, height);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		
	}

}
