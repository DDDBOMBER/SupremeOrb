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
	
	gray(0x3D3D3D, 0xBCBCBC),
	crocodile(0x287256, 0xCEFF9E),
	ocean(0x7A374E, 0xD27D2C),
	
	ghost(0x341B8E,0x685E8E),
	clay(0x504C40, 0x8C4A3D),
	sand(0x54403D, 0xD9BD79),
	
	magnetic(0x2C729E, 0x8C2E71),
	earth(0x712F14, 0xBCD074),
	vile(0x685E8E, 0xA4616D),
	
	test(0x423B5E, 0x903F45),
	test2(0x4A4140, 0x168A83),
	test3(0x809369, 0x809369),
	
	test4(0x04D9D9, 0xF2CF66),
	test5(0x685E8E, 0xECD1CD),
	test6(0xA13964, 0x8E7049),

	test7(0x82AE86, 0xffffff),
	test8(0x9B57A5, 0xffffff),
	test9(0xD27F56, 0xffffff);
	
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
		ArrayList<Colors> list = new ArrayList<Colors>();
		
		list.add(purple);
		list.add(gray);
		list.add(clay);
		list.add(sand);
		list.add(earth);
		list.add(vile);
		list.add(test);
		list.add(test2);
		list.add(test3);

		list.add(ghost);
		list.add(test7);
		list.add(test8);
		list.add(yellow);
		list.add(cyan);
		list.add(red);
		list.add(test9);
		list.add(orange);
		list.add(lime);

		list.add(test5);
		list.add(test6);
		list.add(green);
		list.add(magnetic);
		list.add(test4);
		list.add(blue);
		list.add(crocodile);
		list.add(ocean);
		list.add(pink);

		//if(cols == null){
			cols = new ArrayList<Colors>();
			cols.addAll(list);
		//}
		
		return list;
	}
	
	public static void printColors(){
		for(Colors c : cols()){
			System.out.println("new Color(\""+c.col+"\", \""+c.light+"\"),");
		}
	}
}
