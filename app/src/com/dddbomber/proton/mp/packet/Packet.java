package com.dddbomber.proton.mp.packet;

public class Packet {
	private final String body;
	private final long timeStamp;

	public Packet(Object body){
		this.body = (String) body;
		this.timeStamp = System.currentTimeMillis();
	}

	public String getBody() {
		return body;
	}

	public long getTimeStamp() {
		return timeStamp;
	}
}
