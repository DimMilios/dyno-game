package gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.CSVParser;
import util.Resource;

import static gameobject.ShapeType.*;

public class EnemiesManager {

	public static final int DIAMOND_COLLISION = 0;
	public static final int CIRCLE_COLLISION = 1;
	public static final int RECTANGLE_COLLISION = 2;

	private BufferedImage diamond;
	private BufferedImage circle;
	private BufferedImage rectangle;

	private List<Enemy> enemies;
	private List<List<String>> shapeList;
	private MainCharacter mainCharacter;


	public EnemiesManager(MainCharacter mainCharacter) {
		diamond = Resource.getResouceImage("src/data/diamond.png");
		circle = Resource.getResouceImage("src/data/circle.png");
		rectangle = Resource.getResouceImage("src/data/rectangle.png");
		enemies = new ArrayList<>();
		this.mainCharacter = mainCharacter;
		shapeList = CSVParser.getShapeList();
		enemies.add(createEnemy());
	}
	
	public void update() {
		for(Enemy e : enemies) {
			e.update();
		}
		Enemy enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			mainCharacter.upScore();
			enemies.clear();
			enemies.add(createEnemy());
		}
	}
	
	public void draw(Graphics g) {
		for(Enemy e : enemies) {
			e.draw(g);
		}
	}
	
	public Enemy createEnemy() {
		// if (enemyType = getRandom)
		Random rand = new Random();
		int type = rand.nextInt(3);

//		for(List<String> strings : shapeList) {
//			System.out.println(strings);
//		}
//
		int randomNum = rand.nextInt((shapeList.size() - 2) + 1) + 2;
		switch (type) {
			case 0:
				return getCactus(2, diamond, DIAMOND);
			case 1:
				return getCactus(3, circle, CIRCLE);
			default:
				return getCactus(4, rectangle, RECTANGLE);
		}
	}

	private Cactus getCactus(int i, BufferedImage image, ShapeType type) {
		return new Cactus(
				mainCharacter,
//						800,
				Integer.parseInt(shapeList.get(i).get(1)),
//						280,
				Integer.parseInt(shapeList.get(i).get(2)),
				image.getWidth() - 10,
				image.getHeight() - 10,
				image,
				type);
	}

	public int getCollisionStatus() {
		for(Enemy e : enemies) {
			if (mainCharacter.getBound().intersects(e.getBound()) ) {
				if (e.getType().equals(DIAMOND)) {
					return DIAMOND_COLLISION;
				}
				else if (e.getType().equals(CIRCLE)) {
					return CIRCLE_COLLISION;
				}
				else if (e.getType().equals(RECTANGLE)) {
					return RECTANGLE_COLLISION;
				}
			}
		}
		return -1;
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(createEnemy());
	}
	
}
