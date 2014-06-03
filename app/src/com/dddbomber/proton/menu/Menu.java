package com.dddbomber.proton.menu;

import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.input.InputHandler;

public abstract class Menu {
	public static Menu menu = new RankMenu();
	
	public abstract void tick(InputHandler input);
	
	public abstract void render(Screen screen);
	
}
