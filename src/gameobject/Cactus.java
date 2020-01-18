package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Cactus extends Enemy {
	
	public static final int Y_LAND = 280;
	
	private int posX;
	private int posY;
	private int width;
	private int height;
	
	private BufferedImage image;
	private MainCharacter mainCharacter;

	private ShapeType type;
	
	private Rectangle rectBound;
	
	public Cactus(MainCharacter mainCharacter,
				  int posX,
				  int posY,
				  int width,
				  int height,
				  BufferedImage image,
				  ShapeType type) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.image = image;
		this.mainCharacter = mainCharacter;
		this.type = type;
		rectBound = new Rectangle();
	}
	
	public void update() {
		posX -= mainCharacter.getSpeedX();
	}
	
	public void draw(Graphics g) {
//		g.drawImage(image, posX, Y_LAND - image.getHeight(), null);
		g.drawImage(image, posX, posY - image.getHeight(), null);

		g.setColor(Color.red);
		Rectangle bound = getBound();
		g.drawRect(bound.x, bound.y, bound.width, bound.height);
	}

	public Rectangle getBound() {
		rectBound = new Rectangle();
		rectBound.x = posX + (image.getWidth() - width)/2;
//		rectBound.y = Y_LAND - image.getHeight() + (image.getHeight() - height)/2;
		rectBound.y = posY - image.getHeight() + (image.getHeight() - height)/2;
		rectBound.width = width;
		rectBound.height = height;
		return rectBound;
	}

	public ShapeType getType() {
		return type;
	}

	@Override
	public boolean isOutOfScreen() {
		return posX < -image.getWidth();
	}
	
}
