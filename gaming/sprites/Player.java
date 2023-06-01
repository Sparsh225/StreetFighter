
package com.skillriser.gaming.sprites;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.skillriser.gaming.canvas.IPlayerConstants;

public class Player extends CommonPlayer implements IPlayerConstants {
	
	private BufferedImage idleImages[] = new BufferedImage[6];
	//private BufferedImage walkImages[] = new BufferedImage[6];
	private BufferedImage kickImages[] = new BufferedImage[5];
	private BufferedImage punchImages[] = new BufferedImage[5];
	private BufferedImage hitImages[] = new BufferedImage[3];
	public Player() throws IOException {
		x = 200;
		h = 250;
		w = 300;
		y = FLOOR - h;
		speed = SPEED;
		playerImg = ImageIO.read(Player.class.getResource(PLAYER_IMAGE));
		loadIdleImages();
		loadKickImages();
		loadPunchImages();
		loadHitImages();
	}
	
	private void loadHitImages() {
		hitImages[0] = playerImg.getSubimage(575, 22, 49, 88);
		// hitImages[1] = playerImg.getSubimage(539, 137, 59, 92);
		hitImages[1] = playerImg.getSubimage(607, 155, 74, 73);
	}

	private void loadIdleImages() {
		idleImages[0] = playerImg.getSubimage(21, 15, 60, 94);
		idleImages[1] = playerImg.getSubimage(89, 15, 64, 94);
		idleImages[2] = playerImg.getSubimage(158, 15, 62, 94);
		idleImages[3] = playerImg.getSubimage(223, 15, 61, 94);
		idleImages[4] = playerImg.getSubimage(294, 15, 63, 94);
		idleImages[5] = playerImg.getSubimage(369, 15, 61, 94);
	}
	
	private void loadKickImages() {
		kickImages[0] = playerImg.getSubimage(23, 349, 45, 91);
		kickImages[1] = playerImg.getSubimage(75, 352, 45, 84);
		kickImages[2] = playerImg.getSubimage(130, 352, 51, 77);
		kickImages[3] = playerImg.getSubimage(189, 356, 51, 74);
		kickImages[4] = playerImg.getSubimage(249, 357, 86, 63);
	}
	
	private void loadPunchImages() {
		punchImages[0] = playerImg.getSubimage(163, 129, 59, 94);
		punchImages[1] = playerImg.getSubimage(221, 129, 56, 94);
		punchImages[2] = playerImg.getSubimage(275, 127, 65, 94);
		punchImages[3] = playerImg.getSubimage(339, 127, 57, 94);
		punchImages[4] = playerImg.getSubimage(404, 129, 57, 91);
	}
	
	public BufferedImage printIdle() {
		isAttacking = false;
		if(imageIndex > 5) {
			imageIndex = 0;
		}
		BufferedImage img = idleImages[imageIndex];
		imageIndex++;
		return img;
	}

	public BufferedImage printKick() {
		if(imageIndex > 4) {
			imageIndex = 0;
			currentMove = IDLE;
			isAttacking = false;
		}
		isAttacking = true;
		BufferedImage img = kickImages[imageIndex];
		imageIndex++;
		return img;
	}
	
	public void healthControl() {
		
	}
	
	public BufferedImage printPunch() {
		if(imageIndex > 4) {
			imageIndex = 0;
			currentMove = IDLE;
			isAttacking = false;
		}
		isAttacking = true;
		BufferedImage img = punchImages[imageIndex];
		imageIndex++;
		return img;
	}
	
	public BufferedImage printHit() {
		if (imageIndex > 1) {
			imageIndex = 0;
			currentMove = IDLE;
		}
		BufferedImage img = hitImages[imageIndex];
		imageIndex++;
		return img;
	}
	
	@Override
	public BufferedImage defaultImage() {
		if(currentMove == KICK) {
			return printKick();
		}
		else if(currentMove == PUNCH) {
			return printPunch();
		}
		else if (currentMove == HIT) {
			return printHit();
		}
		else {
			return printIdle();
		}
	}
	
}