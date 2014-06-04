package com.dddbomber.proton.entity;

import java.util.ArrayList;
import java.util.Random;

public enum Colors {
	red(0x8B0800, 0xC04424),
	green(0x4D712B, 0xB6B929),
	blue(0x2E70D1, 0x6DC2CA),

	yellow(0xD27D2C, 0xDAD45E),
	cyan(0x90E6FF, 0xFFFFFF),
	pink(0x9244ff, 0xEA00C4),

	orange(0xFB7C5B, 0xFDCDC1),
	lime(0xC7D93D, 0xE9F2A0),
	purple(0x4B374C, 0xB29ABB),
	
	gray(0x3D3D3D, 0x606060),
	crocodile(0x287256, 0xCEFF9E),
	ocean(0x7A374E, 0xD27D2C),
	
	ghost(0x341B8E,0x685E8E),
	clay(0x504C40, 0x8C4A3D),
	sand(0x54403D, 0xD9BD79),
	
	magnetic(0x2C729E, 0x8C2E2E),
	earth(0x712F14, 0xBCD074),
	vile(0x685E8E, 0xA4616D);
	
	public int col, light;
	
	Colors(int col, int light){
		this.col = col;
		this.light = light;
	}
	
	static Random random = new Random();
	
	public static Colors getRandomCol(){
		int i = random.nextInt(unused.size());
		Colors c = unused.get(i);
		unused.remove(i);
		return c;
	}
	
	public static ArrayList<Colors> unused = cols();
	
	public static ArrayList<Colors> cols;
	
	public static ArrayList<Colors> cols(){
		if(cols != null)return cols;
		ArrayList<Colors> list = new ArrayList<Colors>();
		list.add(red);
		list.add(green);
		list.add(blue);
		list.add(yellow);
		list.add(cyan);
		list.add(pink);
		list.add(orange);
		list.add(lime);
		list.add(purple);
		list.add(gray);
		list.add(crocodile);
		list.add(ocean);
		list.add(ghost);
		list.add(clay);
		list.add(sand);
		list.add(magnetic);
		list.add(earth);
		list.add(vile);
		cols = list;
		return cols;
	}
}
