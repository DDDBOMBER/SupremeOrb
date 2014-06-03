package com.dddbomber.proton.input;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.dddbomber.proton.Game;

public class MouseHandler implements MouseListener, MouseMotionListener{
	
	public MouseHandler(Canvas c){
		c.addMouseListener(this);
		c.addMouseMotionListener(this);
	}
	
	public int x, y;
	
	public boolean left, right;

	@Override
	public void mouseDragged(MouseEvent e) {
        double xScale = ((double)Game.SCREENWIDTH/(double)Game.WIDTH);
        double yScale = ((double)Game.SCREENHEIGHT/(double)Game.HEIGHT);
		x = (int) ((e.getX())*xScale);
		y = (int) ((e.getY())*yScale);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
        double xScale = ((double)Game.SCREENWIDTH/(double)Game.WIDTH);
        double yScale = ((double)Game.SCREENHEIGHT/(double)Game.HEIGHT);
		x = (int) ((e.getX())*xScale);
		y = (int) ((e.getY())*yScale);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1)left = true;
		if(e.getButton() == 3)right = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == 1)left = false;
		if(e.getButton() == 3)right = false;
	}
}
