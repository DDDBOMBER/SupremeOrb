package com.dddbomber.proton.account;

public class Account {
	
	public final int userID;
	public final String private_key;
	
	public Account(int userID, String private_key){
		this.userID = userID;
		this.private_key = private_key;
	}
	
	public String username;
	public int elo;
	
	public void getUserInfo(){
		this.username = "Cheezy";
		this.elo = 1400;
	}
	
	public void reportCheat(){
		// Send server info client has been cheating
	}
}
