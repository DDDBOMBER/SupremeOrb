package com.dddbomber.proton.entity;

public class ColorInstance {
	public int col, light;
	
	public ColorInstance(int col, int light){
		this.col = col;
		this.light = light;
	}
	
	public ColorInstance(int col){
		this(col, col);
	}
	
	public ColorInstance(){
		this(0xffffff, 0xffffff);
	}
}
