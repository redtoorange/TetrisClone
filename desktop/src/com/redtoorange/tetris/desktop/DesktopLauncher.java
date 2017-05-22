package com.redtoorange.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.redtoorange.tetris.Constants;
import com.redtoorange.tetris.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Constants.WINDOW_TITLE;

		config.width = Constants.WINDOW_WIDTH;
		config.height = Constants.WINDOW_HEIGHT;

		new LwjglApplication(new MainGame(), config);
	}
}
