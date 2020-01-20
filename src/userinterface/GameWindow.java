package userinterface;

import util.CSVParser;

import javax.swing.JFrame;
import java.util.List;

public class GameWindow extends JFrame {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 800;
	private GameScreen gameScreen;

	public GameWindow() {
		super("Shapes Game");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		gameScreen = new GameScreen();
		addKeyListener(gameScreen);  
		add(gameScreen);
	}
	
	public void startGame() {
		setVisible(true);
		gameScreen.startGame();
	}

	@Override
	public int getHeight() {
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		return super.getWidth();
	}

	public static void main(String[] args) {
		(new GameWindow()).startGame();
	}
}
