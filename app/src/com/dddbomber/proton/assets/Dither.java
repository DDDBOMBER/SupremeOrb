package com.dddbomber.proton.assets;

public class Dither {
	public int[] matrix = {
			0, 0, 0,
			0, 0, 0,
			0, 0, 0
	};
	
	public static int[] translationMatrix = {
		4, 0, 2, 6, 8, 1, 3, 5, 7 
	};
	
	public Dither(int amount){
		this.createDither(amount);
	}

	private void createDither(int amount) {
		for(int i = 0; i < amount; i++){
			matrix[translationMatrix[i]] = 1;
		}
	}
}
