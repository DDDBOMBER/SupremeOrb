package com.dddbomber.proton.mp.packet;

import java.util.ArrayList;

import com.dddbomber.proton.menu.*;

public class PacketManager {
	public ArrayList<Packet> sentPackets = new ArrayList<Packet>();
	public ArrayList<Packet> recievedPackets = new ArrayList<Packet>();
	
	private int packetsRecieved = 0, packetsSent = 0;
	
	public void registerSentPacket(Packet p){
		sentPackets.add(p);
		if(sentPackets.size() > 1000){
			while(sentPackets.size() > 500){
				sentPackets.remove(0);
			}
		}
		setPacketsSent(getPacketsSent() + 1);
	}
	
	public void registerRecievedPacket(Packet p){
		recievedPackets.add(p);
		if(recievedPackets.size() > 1000){
			while(recievedPackets.size() > 500){
				recievedPackets.remove(0);
			}
		}
		setPacketsRecieved(getPacketsRecieved() + 1);
		
		String data = p.getBody();
		double x = Double.parseDouble(data.split(",")[0]);
		double y = Double.parseDouble(data.split(",")[1]);

		((GameMenu)Menu.menu).level.enemy.x = x;
		((GameMenu)Menu.menu).level.enemy.y = y;
	}

	public int getPacketsRecieved() {
		return packetsRecieved;
	}

	public void setPacketsRecieved(int packetsRecieved) {
		this.packetsRecieved = packetsRecieved;
	}

	public int getPacketsSent() {
		return packetsSent;
	}

	public void setPacketsSent(int packetsSent) {
		this.packetsSent = packetsSent;
	}
}
