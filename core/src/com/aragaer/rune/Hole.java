package com.aragaer.rune;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.ObjectSet;

public class Hole {
    private static Texture texture;
    /* package */ final int x, y;
    /* package */ final Circle range;
    final ObjectSet<LineSegment> segments;

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
	segments = new ObjectSet<LineSegment>();
    }

    public void draw(final Batch batch) {
	batch.draw(texture, x+X_OFF, y+Y_OFF);
    }
}
