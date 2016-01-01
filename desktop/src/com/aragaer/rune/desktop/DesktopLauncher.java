package com.aragaer.rune.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.aragaer.rune.RuneGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Rune cracker";
		config.height = 480;
		config.width = 800;
		new LwjglApplication(new RuneGame(), config);
	}
}
