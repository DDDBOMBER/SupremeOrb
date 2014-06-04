package com.dddbomber.proton.mp.packet;

public class Packet {
	private final String head, body;
	private final long timeStamp;

	public Packet(String head, Object body){
		this.head = head;
		this.body = (String) body;
		this.timeStamp = System.currentTimeMillis();
	}
	
	public String getHead() {
		return head;
	}

	public String getBody() {
		return body;
	}

	public long getTimeStamp() {
		return timeStamp;
	}
}
