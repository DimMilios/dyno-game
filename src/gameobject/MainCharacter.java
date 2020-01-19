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
	private static final int DEATH = 2;
	
	private float posY;
	private float posX;
	private float speedX;
	private float speedY = -10.5f;	// Jump distance
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
	
	public void jump() {
		if(posY >= LAND_POSY) {
			speedY = -10.5f;
			posY += speedY;
		}
	}

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
		score += 20;
	}
	
}
