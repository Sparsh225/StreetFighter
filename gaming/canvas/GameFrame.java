package com.skillriser.gaming.canvas;

import java.io.IOException;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements IPlayerConstants {
	public GameFrame() throws IOException {
		setResizable(false);
		setTitle(GAMETITLE);
		setSize(BOARDWIDTH, BOARDHEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Board board = new Board();
		add(board);
		setVisible(true);
	}


}
