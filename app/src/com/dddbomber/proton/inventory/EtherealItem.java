package com.dddbomber.proton.inventory;

import com.dddbomber.proton.entity.ColorInstance;

public class EtherealItem extends Item{

	public EtherealItem(String type, String metadata) {
		super(type, metadata);
	}

	public ColorInstance[] colors;
}
