package com.aragaer.rune;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ObjectSet;


public class GameScreen implements Screen, InputProcessor {

    private final Logger logger;

    private Texture holeImg, shinyImg;
    private Array<Hole> holes;
    private Array<Line> lines;
    private OrthographicCamera camera;
    private Stage stage;

    private static final int LEN = 115;
    private static final int LEN2 = 100;
    private static final int X_CENTER = 400;
    private static final int Y_CENTER = 240;

    public GameScreen() {
	stage = new Stage();
	logger = new Logger("RUNE", Logger.DEBUG);
	logger.info("Starting");

	camera = new OrthographicCamera();
	camera.setToOrtho(false, 800, 480);

	holeImg = new Texture("hole.png");
	shinyImg = new Texture("shiny.png");
	Hole.setTexture(holeImg);
	LineSegment.setTexture(shinyImg, 16);
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
	// Change this to stage
	Gdx.input.setInputProcessor(this);
    }

    @Override public void resize(int width, int height) {
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	camera.update();
	stage.draw();
    }

    @Override public void dispose() {
	holeImg.dispose();
	shinyImg.dispose();
	stage.dispose();
    }

    @Override public void pause() {
    }

    @Override public void resume() {
    }

    @Override public boolean keyDown(int keycode) {
	return false;
    }

    @Override public boolean keyUp(int keycode) {
	return false;
    }

    @Override public boolean keyTyped(char character) {
	return false;
    }

    private LineHandle dragged;
    private Hole draggedFrom;
    private Vector3 touchVector = new Vector3();

    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	if (pointer > 0)
	    return false;
	if (button != com.badlogic.gdx.Input.Buttons.LEFT)
	    return false;
	touchVector.set(screenX, screenY, 0);
	camera.unproject(touchVector);
	for (Hole hole: holes) {
	    if (!hole.range.contains(touchVector.x, touchVector.y))
		continue;
	    if (hole.isEmpty())
		return false;
	    dragged = hole.pick();
	    draggedFrom = hole;
	    return true;
	}
	return false;
    }

    private void finishDragAt(Hole hole) {
	hole.put(dragged);
	dragged = null;
	draggedFrom = null;
    }

    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
	if (pointer > 0)
	    return false;
	if (button != com.badlogic.gdx.Input.Buttons.LEFT)
	    return false;
	if (draggedFrom == null)
	    return false;
	touchVector.set(screenX, screenY, 0);
	camera.unproject(touchVector);
	for (Hole hole: holes) {
	    if (!hole.range.contains(touchVector.x, touchVector.y))
		continue;
	    if (hole.isEmpty())
		finishDragAt(hole);
	    else
		finishDragAt(draggedFrom);
	    return true;
	}

	return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer) {
	if (pointer > 0)
	    return false;
	if (draggedFrom != null) {
	    touchVector.set(screenX, screenY, 0);
	    camera.unproject(touchVector);
	    dragged.move(touchVector.x, touchVector.y);
	    return true;
	}
	return false;
    }

    @Override public boolean mouseMoved(int screenX, int screenY) {
	return false;
    }

    @Override public boolean scrolled(int amount) {
	return false;
    }
}
