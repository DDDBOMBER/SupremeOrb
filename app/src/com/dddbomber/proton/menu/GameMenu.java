package com.dddbomber.proton.menu;

import java.util.Random;

import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.entity.Colors;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.level.Level;

public class GameMenu extends Menu {

	public GameMenu(Colors colors){
		level = new Level(colors);
	}
	
	static Random random = new Random();
	
	public void tick(InputHandler input) {
		level.tick(input);
	}

	public Level level;
	
	public void render(Screen screen) {
		level.render(screen);
	}

}
