package com.dddbomber.proton;

import org.net.Msg.Msg;
import org.net.p2p.*;

import com.dddbomber.proton.entity.Colors;

public class GameConnection {
	public Colors opponentColor;
	
	public GameConnection(String ip, int port){
		Protocol p = new Protocol(this);
		p.addMsgHandler("up", "update");
		jnmp2p jnm = new jnmp2p(p, port);
		Connection c = jnm.connect(ip);
		
		sendMsg(c);
	}
	
	public void update(Connection c, Msg m){
		System.out.println(((Colors)m.getContent()));
		sendMsg(c);
	}
	
	public void sendMsg(Connection c){
		String head = "up";
		Colors body = Colors.cols().get(5);
		Msg m = c.createMsg(head, body);
		c.sendMsg(m);
	}
	
	public static void main(String[] args){
		GameConnection gc = new GameConnection("localhost", 12000);
	}
}
