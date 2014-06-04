package com.dddbomber.proton.mp;

import org.net.Msg.Msg;
import org.net.p2p.*;

import com.dddbomber.proton.Game;
import com.dddbomber.proton.cpu.NameGenerator;
import com.dddbomber.proton.mp.packet.Packet;
import com.dddbomber.proton.mp.packet.PacketManager;

public class StandardConnection {
	
	public Protocol protocol;
	public jnmp2p jnm;
	public Connection c;
	
	public PacketManager packetManager = new PacketManager();
	
	public StandardConnection(String ip, int port){
		if(openConnection == null)openConnection = this;
		else System.exit(1); // Connection Already Open
		
		protocol = new Protocol(this);
		protocol.addMsgHandler("[p]", "packetRecieved");
		jnm = new jnmp2p(protocol, port);
		c = jnm.connect(ip);
	}
	
	public long lastRecieved = 0;
	
	public void packetRecieved(Connection c, Msg m){
		Packet p = new Packet(m.getContent());
		packetManager.registerRecievedPacket(p);
		
		System.out.println(m.getContent());
	}
	
	public void sendMsg(Connection c, String body){
		String head = "[p]";
		Msg m = Connection.createMsg(head, body);
		c.sendMsg(m);
		
		Packet p = new Packet(body);
		packetManager.registerSentPacket(p);
	}

	private static StandardConnection openConnection;
	
	public static StandardConnection getConnection() {
		return openConnection;
	}
}
