package com.aragaer.rune;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hole extends Actor {
    private static Texture texture;
    private final int x, y;
    /* package */ final Circle range;
    private LineHandle segments;

    private final static int X_OFF = -16;
    private final static int Y_OFF = -16;
    private final static int RANGE = 48;

    public static void setTexture(final Texture texture) {
	Hole.texture = texture;
    }

    public Hole(int x, int y) {
	this.x = x;
	this.y = y;
	range = new Circle(x, y, RANGE);
    }

    public boolean isEmpty() {
	return segments == null;
    }

    public LineHandle pick() {
	LineHandle result = segments;
	segments = null;
	return result;
    }

    public void put(LineHandle handle) {
	segments = handle;
	handle.move(x, y);
    }

    @Override public void draw(Batch batch, float alpha) {
	batch.draw(texture, x+X_OFF, y+Y_OFF);
    }
}
