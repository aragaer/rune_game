package com.aragaer.rune;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class GameScreen implements Screen {

    private final Logger logger;

    private Texture shinyImg;
    private Array<Hole> holes;
    private Array<Line> lines;
    private Stage stage;
    private AssetManager manager;

    private static final int LEN = 115;
    private static final int LEN2 = 100;
    private static final int X_CENTER = 400;
    private static final int Y_CENTER = 240;

    public GameScreen() {
	manager = new AssetManager();
	stage = new Stage(new FitViewport(X_CENTER*2, Y_CENTER*2));
	stage.getRoot().addListener(new RootInputListener(stage));
	logger = new Logger("RUNE", Logger.DEBUG);
	logger.info("Starting");

	shinyImg = new Texture("shiny.png");
	Hole.setAssetManager(manager);
	LineSegment.setTexture(shinyImg);
	manager.finishLoading();
	holes = new Array<Hole>();
	holes.add(new Hole(X_CENTER, Y_CENTER));
	holes.add(new Hole(X_CENTER - LEN/2, Y_CENTER + LEN2));
	holes.add(new Hole(X_CENTER + LEN/2, Y_CENTER + LEN2));
	holes.add(new Hole(X_CENTER + LEN, Y_CENTER));
	holes.add(new Hole(X_CENTER + LEN/2, Y_CENTER - LEN2));
	holes.add(new Hole(X_CENTER - LEN/2, Y_CENTER - LEN2));
	holes.add(new Hole(X_CENTER - LEN, Y_CENTER));
	holes.add(new Hole(X_CENTER - LEN, Y_CENTER + 2*LEN2));
	holes.add(new Hole(X_CENTER, Y_CENTER + 2*LEN2));
	holes.add(new Hole(X_CENTER + LEN, Y_CENTER + 2*LEN2));
	holes.add(new Hole(X_CENTER + 3*LEN/2, Y_CENTER + LEN2));
	holes.add(new Hole(X_CENTER + 2*LEN, Y_CENTER));
	holes.add(new Hole(X_CENTER + 3*LEN/2, Y_CENTER - LEN2));
	holes.add(new Hole(X_CENTER + LEN, Y_CENTER - 2*LEN2));
	holes.add(new Hole(X_CENTER, Y_CENTER - 2*LEN2));
	holes.add(new Hole(X_CENTER - LEN, Y_CENTER - 2*LEN2));
	holes.add(new Hole(X_CENTER - 3*LEN/2, Y_CENTER - LEN2));
	holes.add(new Hole(X_CENTER - 2*LEN, Y_CENTER));
	holes.add(new Hole(X_CENTER - 3*LEN/2, Y_CENTER + LEN2));

	lines = new Array<Line>();
	lines.add(new Line(holes.get(0), holes.get(1), holes.get(2), holes.get(4)));
	lines.add(new Line(holes.get(3), holes.get(5), holes.get(8), holes.get(11)));
	for (Hole hole : holes)
	    stage.addActor(hole);
	for (Line line : lines)
	    stage.addActor(line);
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
	shinyImg.dispose();
	stage.dispose();
    }

    @Override public void pause() {
    }

    @Override public void resume() {
    }
}
