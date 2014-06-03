package com.dddbomber.proton;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import com.dddbomber.proton.assets.Asset;
import com.dddbomber.proton.assets.Screen;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.menu.Menu;

public class Game extends Canvas implements Runnable{
	public static int tickRate = 64;
	
	private static final long serialVersionUID = 1L;
	//public static int WIDTH = 1138, HEIGHT = 640;
	public static int WIDTH = 960, HEIGHT = 640;
	public static final String NAME = "Red Proton";
	
	
	public InputHandler input;
	
	public static Music ts;
	public static Sound hit;
	
	public Game(){
		try {
			TinySound.init();
			hit = TinySound.loadSound("/sound/hit.wav");
			ts = TinySound.loadMusic("/sound/Quadratic.wav");
			//ts.play(true);
			//setVolume(0.15);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageIO.read(Game.class.getResourceAsStream("/cursor.png")), new Point(0, 0), "invisible"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(new Dimension(WIDTH, HEIGHT));
		input = new InputHandler(this);
	}
	
	public static void setVolume(double volume){
		ts.setVolume(volume);
	}
	
	public static int ticks, renders;
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0 / 60.0;
		
		double time = 0;
		
		long lastSecond = System.currentTimeMillis();
		
		this.requestFocus();
		
		while(running){
			long now = System.nanoTime();
			time += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean render = false;
			while(time >= 1){
				tick();
				time -= 1;
				render = true;
			}
			if(render){
				render();
			}else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					
				}
			}
			if(System.currentTimeMillis() - lastSecond > 1000){
				lastSecond += 1000;
				fps = renders;
				ticks = renders = 0;
				System.out.println("FPS - "+fps);
			}
		}
	}
	
	public static int fps;
	
	private void render() {
		renders++;

		
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		Menu.menu.render(g, getWidth(), getHeight());

		g.dispose();
		bs.show();
	}
	
	public boolean pressed = false;
	
	Random random = new Random();

	private void tick() {
		ticks++;
		if(!input.focus.has)return;
		Menu.menu.tick(input);
		if(input.keyboard.keys[KeyEvent.VK_ESCAPE]){
			System.exit(0);
		}
		//gpc.poll();
	}

	public static Image icon;

	public static JFrame frame;
	
	public static void main(String[] args){
		
		Asset.loadAssets();
		Game game = new Game();
		frame = new JFrame(NAME);
		try{
			icon = ImageIO.read(Game.class.getResourceAsStream("/icon.png"));
			frame.setIconImage(icon);
		}catch(Exception e){
			
		}
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(game, BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		game.start();
	}

	private Thread gameThread;

	public boolean running = false;

	public void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void stop() {
		running = false;
	}
}
