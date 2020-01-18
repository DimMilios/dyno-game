package gameobject;

import java.awt.*;
import java.util.Random;

import userinterface.GameWindow;

public class Land {

	public void draw(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 280, GameWindow.SCREEN_WIDTH, GameWindow.SCREEN_HEIGHT);
	}

//	private int getTypeOfLand() {
//		Random rand = new Random();
//		int type = rand.nextInt(10);
//		if(type == 1) {
//			return 1;
//		} else if(type == 9) {
//			return 3;
//		} else {
//			return 2;
//		}
//	}

}
