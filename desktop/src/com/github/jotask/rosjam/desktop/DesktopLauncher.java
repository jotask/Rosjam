package com.github.jotask.rosjam.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.jotask.rosjam.Rosjam;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.title = "Rosjam";
		cfg.width = 1280 / 3;
		cfg.height = 720 / 3;

		cfg.addIcon("icon.png", Files.FileType.Internal);

		new LwjglApplication(new Rosjam(), cfg);

	}
}
