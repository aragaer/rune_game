package com.aragaer.rune;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Hole extends Actor {
    private Texture texture;
    private LineHandle segments;

    private int X_OFF, Y_OFF;
    private int RANGE = 48;

    private static AssetManager manager;

    public static void setAssetManager(AssetManager manager) {
	Hole.manager = manager;
	manager.load("hole.png", Texture.class);
	manager.load("hole_red.png", Texture.class);
	manager.load("hole_blue.png", Texture.class);
    }

    public Hole(float x, float y) {
	texture = manager.get("hole.png");
	X_OFF = texture.getWidth()/2;
	Y_OFF = texture.getHeight()/2;
	setBounds(x-X_OFF, y-Y_OFF, X_OFF*2, Y_OFF*2);
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

    public enum State {
	NORMAL,
	GOOD,
	BAD
    };

    public void setState(State state) {
	switch (state) {
	case NORMAL: texture = manager.get("hole.png"); break;
	case GOOD: texture = manager.get("hole_blue.png"); break;
	case BAD: texture = manager.get("hole_red.png"); break;
	}
    }

    public void put(LineHandle handle, boolean immediately) {
	segments = handle;
	float x = getX() + X_OFF;
	float y = getY() + Y_OFF;
	if (immediately)
	    handle.setPosition(x, y);
	else {
	    MoveToAction action = new MoveToAction();
	    action.setPosition(x, y);
	    action.setDuration(.5f);
	    action.setInterpolation(Interpolation.circle);
	    handle.addAction(action);
	}
    }

    @Override public void draw(Batch batch, float alpha) {
	batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
		   getScaleX(), getScaleY(), getRotation(), 0, 0,
		   texture.getWidth(), texture.getHeight(), false, false);
    }
}
