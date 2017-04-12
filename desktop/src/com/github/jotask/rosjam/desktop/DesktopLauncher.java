package com.github.jotask.rosjam.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.jotask.rosjam.Rosjam;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.backgroundFPS = 30;
		cfg.foregroundFPS = 30;

		cfg.title = "Rosjam";

		final int scl = 1;

		cfg.width = 1280 / scl;
		cfg.height = 720 / scl;

		cfg.addIcon("icon.png", Files.FileType.Internal);

		new LwjglApplication(new Rosjam(), cfg);

	}
}
