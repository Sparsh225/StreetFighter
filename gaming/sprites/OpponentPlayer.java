package com.skillriser.gaming.sprites;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.skillriser.gaming.canvas.IPlayerConstants;

public class OpponentPlayer extends CommonPlayer implements IPlayerConstants {
	private BufferedImage idleImages[] = new BufferedImage[6];
	private BufferedImage walkImages[] = new BufferedImage[6];
	private BufferedImage kickImages[] = new BufferedImage[5];
	private BufferedImage punchImages[] = new BufferedImage[4];
	private BufferedImage hitImages[] = new BufferedImage[3];

	public OpponentPlayer() throws IOException {
		x = BOARDWIDTH - 500;
		h = 250;
		w = 200;
		y = FLOOR - h;
		speed = -SPEED;
		playerImg = ImageIO.read(Player.class.getResource(OPPONENT_PLAYER_IMG));
		loadIdleImages();
		loadWalkImages();
		loadHitImages();
		loadpunchImages();
		loadkickImages();
	}

	private void loadkickImages() {
		kickImages[0] = playerImg.getSubimage(2313, 890, 134, 240);
		kickImages[1] = playerImg.getSubimage(2493, 882, 204, 238);
		kickImages[2] = playerImg.getSubimage(2762, 892, 127, 237);
		kickImages[3] = playerImg.getSubimage(2947, 894, 125, 228);
	}
	private void loadpunchImages() {
		punchImages[0] = playerImg.getSubimage(2554, 592, 148, 227);
		punchImages[1] = playerImg.getSubimage(2741, 592, 195, 230);
		punchImages[2] = playerImg.getSubimage(2554, 592, 148, 227);
	}
	private void loadIdleImages() {
		idleImages[0] = playerImg.getSubimage(2959, 37, 107, 232);
		idleImages[1] = playerImg.getSubimage(2751, 37, 105, 232);
		idleImages[2] = playerImg.getSubimage(2557, 37, 104, 232);
		idleImages[3] = playerImg.getSubimage(2361, 37, 102, 232);
		idleImages[4] = playerImg.getSubimage(2173, 48, 93, 215);
	}
	private void loadWalkImages() {
		walkImages[0] = playerImg.getSubimage(2962, 329, 89, 215);
		walkImages[1] = playerImg.getSubimage(2758, 315, 112, 229);
		walkImages[2] = playerImg.getSubimage(2574, 315, 108, 229);
		walkImages[3] = playerImg.getSubimage(2395, 315, 94, 229);
		walkImages[4] = playerImg.getSubimage(2216, 315, 91, 229);
	}
	private void loadHitImages() {
		hitImages[0] = playerImg.getSubimage(1001, 1584, 115, 226);
		hitImages[1] = playerImg.getSubimage(1170, 1574, 146, 236);
		hitImages[2] = playerImg.getSubimage(1373, 1585, 127, 225);
	}

//	@Override
//	public BufferedImage defaultImage() {
//		//X = 2557 Y = 31 Width = 101 Height = 235
//		return playerImg.getSubimage(2557, 31, 101, 235);
//	}

	public BufferedImage printIdle() {
		isAttacking = false;
		if (imageIndex > 4) {
			imageIndex = 0;
		}
		BufferedImage img = idleImages[imageIndex];
		imageIndex++;
		return img;
	}

	public BufferedImage printKick() {
		if (imageIndex > 3) {
			imageIndex = 0;
			currentMove = IDLE;
		}
		BufferedImage img = kickImages[imageIndex];
		imageIndex++;
		return img;
	}

	public BufferedImage printPunch() {
		if (imageIndex > 2) {
			imageIndex = 0;
			currentMove = IDLE;
		}
		BufferedImage img = punchImages[imageIndex];
		imageIndex++;
		return img;
	}

	public BufferedImage printWalk() {
		if (imageIndex > 4) {
			imageIndex = 0;
			currentMove = IDLE;
		}
		BufferedImage img = walkImages[imageIndex];
		imageIndex++;
		return img;
	}

	public BufferedImage printHitImages() {
		if (imageIndex > 2) {
			imageIndex = 0;
			currentMove = IDLE;
		}
		BufferedImage img = hitImages[imageIndex];
		imageIndex++;
		return img;
	}

	@Override
	public BufferedImage defaultImage() {
		if (currentMove == KICK) {
			return printKick();
		} else if (currentMove == PUNCH) {
			return printPunch();
		} else if (currentMove == WALK) {
			return printWalk();
		} else if (currentMove == HIT) {
			return printHitImages();
		} else {
			return printIdle();
		}
	}
}