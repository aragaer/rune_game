package com.aragaer.rune;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.ObjectSet;

public class LineHandle extends ObjectSet<LineSegment> {
    /* package */ float x, y;
    /* package */ final Circle range;

    private final static int RANGE = 48;

    public LineHandle(float x, float y) {
	this.x = x;
	this.y = y;
	range = new Circle(x, y, RANGE);
    }

    public void move(float x, float y) {
	this.x = x;
	this.y = y;
    }
}
