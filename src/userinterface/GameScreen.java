package userinterface;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gameobject.EnemiesManager;
import gameobject.Land;
import gameobject.MainCharacter;
import util.Resource;

public class GameScreen extends JPanel implements Runnable, KeyListener {

	private static final int START_GAME_STATE = 0;
	private static final int GAME_PLAYING_STATE = 1;
	private static final int GAME_OVER_STATE = 2;
	private static final int GAME_WIN_STATE = 3;

	private static final int WINNING_SCORE = 400;

	private Land land;
	private MainCharacter mainCharacter;
	private EnemiesManager enemiesManager;

	private boolean isKeyPressed;

	private int gameState = START_GAME_STATE;
	private int fps = 60;	// Change to make board faster

	private float speedY;

	private BufferedImage replayButtonImage;
	private BufferedImage gameOverButtonImage;

	public GameScreen() {
		mainCharacter = new MainCharacter();
		this.speedY = mainCharacter.getSpeedY();
		land = new Land();
		mainCharacter.setSpeedX(4);
		replayButtonImage = Resource.getResouceImage("src/data/replay_button.png");
		gameOverButtonImage = Resource.getResouceImage("src/data/gameover_text.png");
		enemiesManager = new EnemiesManager(mainCharacter);
	}

	public void startGame() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public void gameUpdate() {
		if (gameState == GAME_PLAYING_STATE) {
			mainCharacter.update();
			enemiesManager.update();
			if (enemiesManager.getCollisionStatus() == EnemiesManager.DIAMOND_COLLISION) {
				this.speedY = -13.5f;
			}
			else if (enemiesManager.getCollisionStatus() == EnemiesManager.CIRCLE_COLLISION) {
				fps = 120;
			}
			else if (enemiesManager.getCollisionStatus() == EnemiesManager.RECTANGLE_COLLISION) {
				gameState = GAME_OVER_STATE;
			}
			else if (mainCharacter.getScore() >= WINNING_SCORE) {
				gameState = GAME_OVER_STATE;
			}
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());

		Font font = new Font("Sans", Font.BOLD, 24);
		g.setFont(font);

		if (gameState == START_GAME_STATE) {
			mainCharacter.draw(g);
		}
		else if (gameState == GAME_PLAYING_STATE
				|| gameState == GAME_OVER_STATE
				|| gameState == GAME_WIN_STATE) {
			land.draw(g);
			enemiesManager.draw(g);
			mainCharacter.draw(g);
			g.setColor(Color.BLACK);
			g.drawString("Score: " + mainCharacter.getScore(), GameWindow.SCREEN_WIDTH - 150, 40);
			if (gameState == GAME_OVER_STATE || gameState == GAME_WIN_STATE) {
				g.drawImage(replayButtonImage, 283, 50, null);
				if (mainCharacter.getScore() >= WINNING_SCORE) {
					g.drawString("WINNER!", GameWindow.SCREEN_WIDTH / 2, 30);
				}
				else {
					g.drawImage(gameOverButtonImage, GameWindow.SCREEN_WIDTH / 2, 30, null);
				}
			}
		}
	}

	@Override
	public void run() {

		long msPerFrame;
		long lastTime = 0;
		long elapsed;
		
		int msSleep;
		int nanoSleep;

		while (true) {
			gameUpdate();
			msPerFrame = 1000 * 1000000 / fps;
			repaint();
			elapsed = (lastTime + msPerFrame - System.nanoTime());
			msSleep = (int) (elapsed / 1000000);
			nanoSleep = (int) (elapsed % 1000000);
			if (msSleep <= 0) {
				lastTime = System.nanoTime();
				continue;
			}
			try {
				Thread.sleep(msSleep, nanoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lastTime = System.nanoTime();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!isKeyPressed) {
			isKeyPressed = true;
			if (gameState == START_GAME_STATE) {
				if (isSpaceOrUpArrowPressed(e.getKeyCode())) {
					gameState = GAME_PLAYING_STATE;
				}
			}
			else if (gameState == GAME_PLAYING_STATE) {
				if (isSpaceOrUpArrowPressed(e.getKeyCode())) {
					mainCharacter.jump(speedY);
				}
			}
			else if (gameState == GAME_OVER_STATE || gameState == GAME_WIN_STATE) {
				if (isSpaceOrUpArrowPressed(e.getKeyCode())) {
					gameState = GAME_PLAYING_STATE;
					resetGame();
				}
			}
		}
	}

	private boolean isSpaceOrUpArrowPressed(int keyCode) {
		return keyCode == KeyEvent.VK_SPACE || keyCode ==  KeyEvent.VK_UP;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isKeyPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private void resetGame() {
		fps = 60;
		enemiesManager.reset();
		mainCharacter.reset();
	}

}
