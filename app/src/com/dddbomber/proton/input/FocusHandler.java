package com.dddbomber.proton.input;

import java.awt.Canvas;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusHandler implements FocusListener{
	
	public KeyboardHandler keyboard;
	
	public FocusHandler(Canvas c, KeyboardHandler keyboard){
		c.addFocusListener(this);
		this.keyboard = keyboard;
	}
	public boolean has;
	
	public void focusGained(FocusEvent arg0) {
		has = true;
	}
	
	public void focusLost(FocusEvent arg0) {
		has = false;
		for(int i  = 0; i < keyboard.keys.length; i++){
			keyboard.keys[i] = false;
		}
	}
}
