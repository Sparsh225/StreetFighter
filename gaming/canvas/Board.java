package com.skillriser.gaming.canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.skillriser.gaming.sprites.OpponentPlayer;
import com.skillriser.gaming.sprites.Player;
import com.skillriser.gaming.sprites.Power;

public class Board extends JPanel implements IPlayerConstants {
	BufferedImage imageBg;
	private Player player;
	private OpponentPlayer oppPlayer;
	private Timer timer;
	private Power brucePower;
	private Power ryuPower;
	private boolean gameOver;

	public Board() throws IOException {
		player = new Player();
		oppPlayer = new OpponentPlayer();
		loadBackground();
		setFocusable(true);
		bindEvents();
		gameLoop();
		loadPower();
	}

	@Override
	public void paintComponent(Graphics pen) {
		// Object Render on screen
		showBackground(pen);
		player.drawPlayer(pen);
		oppPlayer.drawPlayer(pen);
		printPower(pen);
		printGameOver(pen);
	}

	private void showBackground(Graphics pen) {
		// image, x, y, width, height
		pen.drawImage(imageBg, 0, 0, 1500, 800, null);
	}

	private void loadBackground() {
		try {
			imageBg = ImageIO.read(Board.class.getResource(BG_IMG));
		} catch (Exception e) {
			System.out.println("Background Failed to Load...");
			System.exit(0);
		}
	}


	private void bindEvents() {
		KeyListener listener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// System.out.println("Key Typed : " + e.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// System.out.println("Key Released : " + e.getKeyCode());
				player.setSpeed(SPEED);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// System.out.println("Key Pressed : " + e.getKeyCode());
				// Bruce Lee Movements
				// Right Move
				if (e.getKeyCode() == KeyEvent.VK_D) {
					player.setSpeed(SPEED);
					player.movePlayer();
					// repaint();
				}
				// Left Move
				else if (e.getKeyCode() == KeyEvent.VK_A) {
					player.setCollide(false);
					player.setSpeed(-SPEED);
					player.movePlayer();
					// repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					player.setCurrentMove(KICK);
					player.setAttacking(true);
				}
				else if (e.getKeyCode() == KeyEvent.VK_F) {
					player.setCurrentMove(PUNCH);
					player.setAttacking(true);
				}
				else if (e.getKeyCode() == KeyEvent.VK_Z) {
					player.setCurrentMove(PUNCH);
					player.setAttacking(true);
				}

				// Ryu Movements
				// Right Move
				if (e.getKeyCode() == RIGHTKEY) {
					oppPlayer.setCollide(false);
					oppPlayer.setSpeed(SPEED);
					oppPlayer.movePlayer();
					oppPlayer.setCurrentMove(WALK);
					// repaint();
				}
				// Left Move
				else if (e.getKeyCode() == LEFTKEY) {
					oppPlayer.setSpeed(-SPEED);
					oppPlayer.movePlayer();
					oppPlayer.setCurrentMove(WALK);
					// repaint();
				}
				else if (e.getKeyCode() == KeyEvent.VK_K) {
					oppPlayer.setCurrentMove(KICK);
					oppPlayer.setAttacking(true);
				} else if (e.getKeyCode() == KeyEvent.VK_P) {
					oppPlayer.setCurrentMove(PUNCH);
					oppPlayer.setAttacking(true);
				}
			}
		};
		this.addKeyListener(listener);
	}

	private void gameLoop() {
		timer = new Timer(200, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
				if (gameOver) {
					timer.stop();
				}
				collision();
				isGameOver();
			}
		});
		timer.start();
	}

	private boolean isColloide() {
		int xDist = Math.abs(player.getX() - oppPlayer.getX());
		int yDist = Math.abs(player.getY() - oppPlayer.getY());
		int maxH = Math.max(player.getH(), oppPlayer.getH());
		int maxW = Math.max(player.getW(), oppPlayer.getW());
		System.out.println(xDist);
		return xDist <= maxW && yDist <= maxH;
	}

	private void collision() {
		if (isColloide()) {
			if (player.isAttacking() && oppPlayer.isAttacking()) {

			} else if (player.isAttacking()) {
				oppPlayer.setCurrentMove(HIT);
				ryuPower.setHealth();
			} else if (oppPlayer.isAttacking()) {
				player.setCurrentMove(HIT);
				brucePower.setHealth();
			}
//			System.out.println("Collision...");
			oppPlayer.setCollide(true);
			player.setCollide(true);
			player.setSpeed(0);
			oppPlayer.setSpeed(0);
		} else {
			System.out.println("No Collision...");
			player.setCollide(false);
			player.setSpeed(SPEED);
			oppPlayer.setCollide(false);
			oppPlayer.setSpeed(SPEED);
			// fall();
		}
	}

	private void fall() {
		player.setY((int) (player.getY() + GRAVITY));
	}

	public void jump() {

	}

	private void loadPower() {
		brucePower = new Power(30, "Bruce Lee");
		ryuPower = new Power(BOARDWIDTH - 560, "Ryu");
	}

	private void printPower(Graphics p) {
		brucePower.printHealthBar(p);
		ryuPower.printHealthBar(p);
	}

	private void isGameOver() {
		if (ryuPower.getHealth() <= 0 || brucePower.getHealth() <= 0) {
			gameOver = true;
		}
	}

	private void printGameOver(Graphics pen) {
		if (gameOver) {
			pen.setColor(Color.RED);
			pen.setFont(new Font("times", Font.BOLD, 100));
			pen.drawString("Game Over", BOARDWIDTH / 2 - 200, 200);
		}
	}
}