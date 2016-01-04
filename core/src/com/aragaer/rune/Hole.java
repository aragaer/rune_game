package com.aragaer.rune;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Hole extends Actor {
    private static Texture texture;
    /* package */ final Circle range;
    private LineHandle segments;

    private final static int X_OFF = -16;
    private final static int Y_OFF = -16;
    private final static int RANGE = 48;

    public static void setTexture(final Texture texture) {
	Hole.texture = texture;
    }

    public Hole(float x, float y) {
	setPosition(x, y);
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
	put(handle, false);
    }

    public void put(LineHandle handle, boolean immediately) {
	segments = handle;
	if (immediately)
	    handle.setPosition(getX(), getY());
	else {
	    MoveToAction action = new MoveToAction();
	    action.setPosition(getX(), getY());
	    action.setDuration(.2f);
	    handle.addAction(action);
	}
    }

    @Override public void draw(Batch batch, float alpha) {
	batch.draw(texture, getX()+X_OFF, getY()+Y_OFF);
    }
}
