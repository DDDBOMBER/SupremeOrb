package com.dddbomber.proton.menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.dddbomber.proton.Game;
import com.dddbomber.proton.entity.Colors;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.level.Level;

public class GameMenu extends ScreenMenu {

	
	public GameMenu(Colors colors){
		level = new Level(colors);
	}
	
	static Random random = new Random();
	
	public void tick(InputHandler input) {
		level.tick(input);
	}

	public Level level;
	
	public void render(Graphics g, int width, int height) {
		level.render(screen);
		super.render(g, width, height);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		if(event.getKeyChar() == KeyEvent.VK_CLOSE_BRACKET){
			try {
				Game.disableRendering();
				ImageIO.write(screen.getImage(), "PNG", new File("C:////Screenshots//"+System.currentTimeMillis()+".png"));
				System.out.println("Saved Image - C:////Screenshots//"+System.currentTimeMillis()+".png");
				Game.enableRendering();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
