package com.aragaer.rune;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class GameScreen implements Screen {

    private final Logger logger;

    private Stage stage;
    private AssetManager manager;

    public GameScreen() {
	manager = new AssetManager();
	stage = new Stage();
	logger = new Logger("RUNE", Logger.DEBUG);
	logger.info("Starting");

	Hole.setAssetManager(manager);
	LineSegment.setAssetManager(manager);
	manager.finishLoading();

	GameField field = new GameField();
	field.addListener(new FieldInputListener(stage));
	stage.addActor(field);
    }

    @Override public void hide() {
    }

    @Override public void show() {
	Gdx.input.setInputProcessor(stage);
    }

    @Override public void resize(int width, int height) {
	stage.getViewport().update(width, height, true);
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	stage.act(delta);
	stage.draw();
    }

    @Override public void dispose() {
	manager.dispose();
	stage.dispose();
    }

    @Override public void pause() {
    }

    @Override public void resume() {
    }
}
