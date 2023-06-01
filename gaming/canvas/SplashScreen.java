package com.skillriser.gaming.canvas;

import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jaco.mp3.player.MP3Player;

//import jaco.mp3.player.MP3Player;

public class SplashScreen extends JFrame implements IPlayerConstants {

	private JLabel label = new JLabel();
	private MP3Player mp3player;
	public SplashScreen() {
		setResizable(false);
		setTitle(GAMETITLE);
		setSize(BOARDWIDTH, BOARDHEIGHT);
		setLocationRelativeTo(null);

		Icon icon = new ImageIcon(SplashScreen.class.getResource("splash.jpg"));
		label.setIcon(icon);
		this.add(label);
		loadMusic();
		setVisible(true);
		try {
			Thread.sleep(10000);
			setVisible(false);
			dispose();
			mp3player.stop();
			GameFrame obj = new GameFrame();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadMusic() {
		mp3player = new MP3Player(SplashScreen.class.getResource("theme.mp3"));
		mp3player.play();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SplashScreen splash = new SplashScreen();
	}

}
