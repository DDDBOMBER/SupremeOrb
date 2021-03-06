package com.dddbomber.proton;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
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

import com.dddbomber.proton.account.Account;
import com.dddbomber.proton.account.ServerCommunication;
import com.dddbomber.proton.assets.Asset;
import com.dddbomber.proton.cpu.NameGenerator;
import com.dddbomber.proton.debugger.Debugger;
import com.dddbomber.proton.input.InputHandler;
import com.dddbomber.proton.inventory.Inventory;
import com.dddbomber.proton.menu.Menu;
import com.dddbomber.proton.mp.GameConnection;
import com.dddbomber.proton.mp.StandardConnection;

public class Game extends Canvas implements Runnable{
	public static int tickRate = 64;
	
	private static final long serialVersionUID = 1L;
	//public static int WIDTH = 1138, HEIGHT = 640;
	public static int WIDTH = 960, HEIGHT = 640;
	public static final String NAME = "Red Proton";
	
	
	public InputHandler input;
	
	public static Music ts;
	public static Sound hit;
	
	public static Account account = new Account();
	
	public Game(){
		try {
			TinySound.init();
			hit = TinySound.loadSound("/sound/hit.wav");
			ts = TinySound.loadMusic("/sound/Quadratic.wav");
			ts.play(true);
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
		double nsPerTick = 1000000000.0 / 64.0;
		
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
			if(render && allowRendering){
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
				Debugger.debug5 = "FPS - "+fps;
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
		
		if(Debugger.debuggerEnabled){
			g.setColor(Color.GREEN);
			g.drawString(Debugger.debug1, 2, 10);
			g.drawString(Debugger.debug2, 2, 22);
			g.drawString(Debugger.debug3, 2, 34);
			g.drawString(Debugger.debug4, 2, 46);
			g.drawString(Debugger.debug5, 2, 58);
		}
		
		g.dispose();
		bs.show();
	}
	
	public boolean pressed = false;
	
	Random random = new Random();
	
	public static StandardConnection sc = new StandardConnection("localhost", 12000);

	private void tick() {
		ticks++;
		Menu.menu.tick(input);
		if(input.keyboard.keys[KeyEvent.VK_ESCAPE]){
			System.exit(0);
		}
		Debugger.debug1 = "GLOBAL - Packets Recieved - "+sc.packetManager.getPacketsRecieved() +" , Packets Sent - "+sc.packetManager.getPacketsSent();
		Debugger.debug2 = "STORAGE - Packets Recieved - "+sc.packetManager.recievedPackets.size() +" , Packets Sent - "+sc.packetManager.sentPackets.size();
		if(sc != null)Debugger.debug3 = "P2P - Connection Open on port '"+sc.port+"', to address '"+sc.ip+"'"+" LOGIN SERVER - "+ServerCommunication.webAddress;
		Debugger.debug4 = "ACCOUNT - "+account.username +"("+account.userID+") - ELO "+account.elo + " - "+(ServerCommunication.lastAuthentication == 0 ? "NEVER AUTHORIZED" : "Authorized "+(System.currentTimeMillis()/1000-ServerCommunication.lastAuthentication)+"s ago");
	}

	public static Image icon;

	public static JFrame frame;
	
	public static final Game game = new Game();
	
	public static void main(String[] args){
		ServerCommunication.login(account, "Cheezy", "disturbed1");
		Asset.loadAssets();
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

	public static boolean allowRendering = true;
	
	public static void disableRendering() {
		allowRendering = false;
	}

	public static void enableRendering() {
		allowRendering = true;
	}
}
