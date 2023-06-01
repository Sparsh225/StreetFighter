package com.skillriser.gaming.sprites;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.skillriser.gaming.canvas.IPlayerConstants;

public abstract class CommonPlayer implements IPlayerConstants {

	protected int force;
	protected int x;
	protected int y;
	protected int w;
	protected int h;
	protected int speed;
	protected int imageIndex;
	protected int currentMove;
	protected BufferedImage playerImg;
	protected boolean isCollide;
	protected boolean isAttacking;
	protected int health;

	public CommonPlayer() {
		health = MAX_HEALTH;
	}

	protected abstract BufferedImage defaultImage();

	public void drawPlayer(Graphics pen) {
		pen.drawImage(defaultImage(), x, y, w, h, null);
	}

	public boolean isCollide() {
		return isCollide;
	}

	public void setCollide(boolean isCollide) {
		this.isCollide = isCollide;
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public void movePlayer() {
		if (!isCollide) {
			x = x + speed;
		}
	}


	public int getCurrentMove() {
		return currentMove;
	}

	public void setCurrentMove(int currentMove) {
		this.currentMove = currentMove;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public BufferedImage getPlayerImg() {
		return playerImg;
	}

	public void setPlayerImg(BufferedImage playerImg) {
		this.playerImg = playerImg;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth() {
		this.health = (int) (health - MAX_HEALTH * 0.10);
	}
}