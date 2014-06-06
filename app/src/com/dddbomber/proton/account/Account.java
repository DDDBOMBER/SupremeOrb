package com.dddbomber.proton.account;

public class Account {
	
	public int userID;
	public String private_key;
	
	public Account(int userID, String private_key){
		this.userID = userID;
		this.private_key = private_key;
	}
	
	public String username;
	public int elo;
	
	public void reportCheat(){
		// Send server info client has been cheating
	}
}
