package com.aragaer.rune;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class RuneGame extends ApplicationAdapter {
    SpriteBatch batch;
    private Texture holeImg;
    private Array<GridPoint2> holes;

    private static final int LEN = 115;
    
    @Override public void create() {
        batch = new SpriteBatch();
	holeImg = new Texture("hole.png");
	holes = new Array<GridPoint2>();
	holes.add(new GridPoint2(400, 240));
	holes.add(new GridPoint2(400 - LEN/2, 240 + 100));
	holes.add(new GridPoint2(400 + LEN/2, 240 + 100));
	holes.add(new GridPoint2(400 + LEN, 240));
	holes.add(new GridPoint2(400 + LEN/2, 240 - 100));
	holes.add(new GridPoint2(400 - LEN/2, 240 - 100));
	holes.add(new GridPoint2(400 - LEN, 240));
	holes.add(new GridPoint2(400 - LEN, 240 + 200));
	holes.add(new GridPoint2(400, 240 + 200));
	holes.add(new GridPoint2(400 + LEN, 240 + 200));
	holes.add(new GridPoint2(400 + 3*LEN/2, 240 + 100));
	holes.add(new GridPoint2(400 + 2*LEN, 240));
	holes.add(new GridPoint2(400 + 3*LEN/2, 240 - 100));
	holes.add(new GridPoint2(400 + LEN, 240 - 200));
	holes.add(new GridPoint2(400, 240 - 200));
	holes.add(new GridPoint2(400 - LEN, 240 - 200));
	holes.add(new GridPoint2(400 - 3*LEN/2, 240 - 100));
	holes.add(new GridPoint2(400 - 2*LEN, 240));
	holes.add(new GridPoint2(400 - 3*LEN/2, 240 + 100));
    }

    @Override public void render() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
	for (GridPoint2 hole : holes)
	    batch.draw(holeImg, hole.x - 16, hole.y - 16);
        batch.end();
    }

    @Override public void dispose() {
	batch.dispose();
	holeImg.dispose();
    }
}
