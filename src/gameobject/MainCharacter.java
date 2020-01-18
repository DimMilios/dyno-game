package gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Resource;

public class MainCharacter {

	public static final int LAND_POSY = 240;
	public static final float GRAVITY = 0.5f;
	
	private static final int NORMAL_RUN = 0;
	private static final int WON = 1;
//	private static final int JUMPING = 1;
//	private static final int DOWN_RUN = 2;
	private static final int DEATH = 3;
	
	private float posY;
	private float posX;
	private float speedX;
	private float speedY = -10.5f;	// Jump distance
	private Rectangle rectBound;
	
	private int score = 0;
	
	private int state = NORMAL_RUN;
	
	private BufferedImage triangle;
	
	public MainCharacter() {
		posX = 50;
		posY = LAND_POSY;
		rectBound = new Rectangle();
		triangle = Resource.getResouceImage("src/data/triangle.png");
	}
	
	public void draw(Graphics g) {
//		switch(state) {
//			case NORMAL_RUN:
//			case JUMPING:
//
//			case DEATH:
				g.drawImage(triangle, (int) posX, (int) posY, null);
//				break;
//		}
//		Rectangle bound = getBound();
//		g.setColor(Color.RED);
//		g.drawRect(bound.x, bound.y, bound.width, bound.height);
	}
	
	public void update() {
		if(posY >= LAND_POSY) {
			posY = LAND_POSY;
		} else {
			speedY += GRAVITY;
			posY += speedY;
		}
	}
	
	public void jump() {
		if(posY >= LAND_POSY) {
			speedY = -10.5f;
			posY += speedY;
//			state = JUMPING;
		}
	}
	
//	public void down(boolean isDown) {
//		if(state == JUMPING) {
//			return;
//		}
//		state = isDown ? DOWN_RUN : NORMAL_RUN;
//	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getScore() {
		return score;
	}

	public Rectangle getBound() {
		rectBound = new Rectangle();
//		if(state == DOWN_RUN) {
//			rectBound.x = (int) posX + 5;
//			rectBound.y = (int) posY + 20;
//		} else {
		rectBound.x = (int) posX + 5;
		rectBound.y = (int) posY;
		rectBound.width = triangle.getWidth() - 10;
		rectBound.height = triangle.getHeight();
//		}
		return rectBound;
	}
	
	public void dead(boolean isDead) {
		state = isDead ? DEATH : NORMAL_RUN;
	}

	public void win(boolean won) {
		state = won ? WON : NORMAL_RUN;
	}

	public void reset() {
		posY = LAND_POSY;
	}

	public void upScore() {
		score += 20;
	}
	
}
