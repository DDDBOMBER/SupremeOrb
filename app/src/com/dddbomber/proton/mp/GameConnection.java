package com.dddbomber.proton.mp;

import org.net.Msg.Msg;
import org.net.p2p.*;

import com.dddbomber.proton.Game;
import com.dddbomber.proton.cpu.NameGenerator;
import com.dddbomber.proton.entity.Colors;
import com.dddbomber.proton.menu.Menu;

public class GameConnection {
	public Colors opponentColor;
	
	public void connectionLoop(){
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0 / 1.0;
		while(true){
			if(System.nanoTime()-lastTime > nsPerTick){
				// update
				sendMsg(c);
			}else{
				try{
					//Thread.sleep(1);
				}catch(Exception e){
					
				}
			}
		}
	}
	
	public Protocol p;
	public jnmp2p jnm;
	public Connection c;
	
	public GameConnection(String ip, int port){
		if(openConnection == null)openConnection = this;
		else System.exit(1); // Connection Already Open
		p = new Protocol(this);
		p.addMsgHandler("up", "update");
		jnm = new jnmp2p(p, port);
		c = jnm.connect(ip);
		
		new Thread(new Runnable(){
			public void run(){
				//connectionLoop();
			}
		}).start();
	}
	
	public long lastRecieved = 0;
	
	public void update(Connection c, Msg m){
		System.out.println(m.getContent());
		if(lastRecieved == 0){
			lastRecieved = System.currentTimeMillis();
		}else{
			System.out.println("Last Recieved - "+(System.currentTimeMillis()-lastRecieved));
			lastRecieved = System.currentTimeMillis();
		}
	}
	
	public void sendMsg(Connection c){
		String head = "up";
		String body = NameGenerator.generateName();
		Msg m = Connection.createMsg(head, body);
		c.sendMsg(m);
	}
	
	public static void main(String[] args){
		Game.main(args);
		GameConnection gc = new GameConnection("localhost", 12000);
	}

	private static GameConnection openConnection;
	
	public static GameConnection getConnection() {
		return openConnection;
	}
}
