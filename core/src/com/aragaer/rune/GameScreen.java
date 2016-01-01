package com.aragaer.rune;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class GameScreen implements Screen {

    private final RuneGame game;

    private Texture holeImg;
    private Array<Hole> holes;

    private static final int LEN = 115;
    private static final int LEN2 = 100;
    private static final int X_CENTER = 400;
    private static final int Y_CENTER = 240;

    public GameScreen(final RuneGame game) {
	this.game = game;

	holeImg = new Texture("hole.png");
	Hole.setTexture(holeImg);
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
    }

    @Override public void hide() {
    }

    @Override public void show() {
    }

    @Override public void resize(int width, int height) {
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
	for (Hole hole : holes)
	    hole.draw(game.batch);
        game.batch.end();
    }

    @Override public void dispose() {
	holeImg.dispose();
    }

    @Override public void pause() {
    }

    @Override public void resume() {
    }
}
