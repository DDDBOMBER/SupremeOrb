package com.dddbomber.proton.inventory;

import java.util.ArrayList;

import com.dddbomber.proton.Game;
import com.dddbomber.proton.account.ServerCommunication;
import com.dddbomber.proton.entity.ColorInstance;
import com.google.gson.Gson;

public class Inventory {
	
	public ArrayList<Item> items = new ArrayList<Item>();
	
	public void load(){
		String s = ServerCommunication.getItems(Game.account);
		
		Gson gson = new Gson();
		Item[] itemList = gson.fromJson(s, Item[].class);
		for(int i0 = 0; i0 < itemList.length; i0++){
			Item i = itemList[i0];
			if(i.Type.equals("Standard")){
				i.color = gson.fromJson(i.Metadata, ColorInstance.class);
			}else if(i.Type.equals("Ethereal")){
				EtherealItem ei = new EtherealItem(i.Type, i.Metadata);
				ei.colors = gson.fromJson(ei.Metadata, ColorInstance[].class);
				i = ei;
			}
			
			items.add(i);
		}
		
	}
}
