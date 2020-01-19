package gameobject;

import java.awt.*;
import userinterface.GameWindow;

public class Land {

	public void draw(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 280, GameWindow.SCREEN_WIDTH, GameWindow.SCREEN_HEIGHT);
	}

}
