package com.skillriser.gaming.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Power extends CommonPlayer {
	Color color;
	String playerName;
	public Power(int x,String playerName) {
		this.x = x;
		this.color = color;
		y = 20;
		w = 500;
		h = 50;
		this.playerName=playerName;
	}
	
	public void printHealthBar(Graphics pen) {
		//by default green will be shown
		pen.setColor(Color.RED);
		pen.fillRect(x, y, w, h);
	    //copy 2 rectangles
		if (health > 0) {
		pen.setColor(Color.GREEN);
		pen.fillRect(x, y, health, h);
	}
		pen.setColor(Color.BLACK);
		pen.setFont(new Font("times",Font.BOLD,40));
		pen.drawString(playerName, x, y+h+40);
	}

	@Override
	protected BufferedImage defaultImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
