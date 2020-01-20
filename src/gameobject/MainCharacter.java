package gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Resource;

public class MainCharacter {

	public static final int LAND_POSY = 530;
	public static final float GRAVITY = 0.5f;

	private static final int SCORE_INCREMENT = 20;
	
	private float posY;
	private float posX;
	private float speedX;
	private float speedY = -16.5f;	// Jump distance
	private Rectangle rectBound;
	
	private int score = 0;
	
	private BufferedImage triangle;
	
	public MainCharacter() {
		posX = 50;
		posY = LAND_POSY;
		rectBound = new Rectangle();
		triangle = Resource.getResouceImage("src/data/triangle.png");
	}
	
	public void draw(Graphics g) {
		g.drawImage(triangle, (int) posX, (int) posY, null);
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
	
	public void jump(float newSpeedY) {
		if(posY >= LAND_POSY) {
			speedY = newSpeedY;
			posY += speedY;
		}
	}

	public float getSpeedY() {
		return speedY;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public int getScore() {
		return score;
	}

	public Rectangle getBound() {
		rectBound = new Rectangle();
		rectBound.x = (int) posX + 5;
		rectBound.y = (int) posY;
		rectBound.width = triangle.getWidth() - 10;
		rectBound.height = triangle.getHeight();
		return rectBound;
	}
	
	public void reset() {
		posY = LAND_POSY;
		score = 0;
	}

	public void upScore() {
		score += SCORE_INCREMENT;
	}

	public void upScore(int times) {
		score += SCORE_INCREMENT * times;
	}
}
