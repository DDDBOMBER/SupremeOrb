package com.dddbomber.proton.cpu;

import java.util.Random;

public class NameGenerator {
	
	static Random random = new Random();
	
	public static String[] names = {
		"Bonfire",
		"Lights",
		"3005",
		"Angel",
		"Captain",
		"Power",
		"Egg",
		"Machine",
		"Star",
		"Gargamel",
		"Hewie",
		"Donald",
		"Frank",
		"Ocean",
		"Shine",
		""
	};
	
	public static String generateName(){
		return names[random.nextInt(names.length)];
	}
}
