package com.dddbomber.proton.inventory;

import com.dddbomber.proton.entity.ColorInstance;

public class Item {
	
	public Item(String Type, String Metadata){
		this.Type = Type;
		this.Metadata = Metadata;
	}
	
	public Item(){
		
	}
	
	public String Type, Metadata;
	
	public ColorInstance color;
}
