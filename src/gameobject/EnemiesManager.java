package gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
		Random rand = new Random();
		int randomShape = rand.nextInt((shapeList.size() - 2) + 1) + 1;
		return getEnemyShape(shapeList.get(randomShape));
	}

	private EnemyShape getEnemyShape(List<String> row) {

		String shapeType = row.get(0);

		int posX = Integer.parseInt(row.get(1));
		int posY = Integer.parseInt(row.get(2));

		switch(shapeType) {
			case "DIAMOND":
				return new EnemyShape(
						mainCharacter,
						posX,
						posY,
						diamond.getWidth() - 10,
						diamond .getHeight() - 10,
						diamond,
						DIAMOND);
			case "CIRCLE":
				return new EnemyShape(
						mainCharacter,
						posX,
						posY,
						circle.getWidth() - 10,
						circle.getHeight() - 10,
						circle,
						CIRCLE);
			case "RECTANGLE":
				return new EnemyShape(
						mainCharacter,
						posX,
						posY,
						rectangle.getWidth() - 10,
						rectangle.getHeight() - 10,
						rectangle,
						RECTANGLE);
			default:
				throw new RuntimeException("Error while parsing shape type from file");
		}

	}

//	private EnemyShape getEnemyShape(int i, BufferedImage image, ShapeType type) {
//		return new EnemyShape(
//				mainCharacter,
//				Integer.parseInt(shapeList.get(i).get(1)),
//				Integer.parseInt(shapeList.get(i).get(2)),
//				image.getWidth() - 10,
//				image.getHeight() - 10,
//				image,
//				type);
//	}

	public int getCollisionStatus() {
		for(Enemy enemy : enemies) {
			if (mainCharacter.getBound().intersects(enemy.getBound()) ) {
				if (enemy.getType().equals(DIAMOND)) {
					return DIAMOND_COLLISION;
				}
				else if (enemy.getType().equals(CIRCLE)) {
					return CIRCLE_COLLISION;
				}
				else if (enemy.getType().equals(RECTANGLE)) {
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
