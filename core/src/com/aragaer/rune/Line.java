package com.aragaer.rune;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

public class Line {
    private final Array<LineSegment> segments;

    public Line(Hole... holes) {
	segments = new Array<LineSegment>(holes.length - 1);
	for (int i = 1; i < holes.length; i++)
	    segments.add(new LineSegment(holes[i-1], holes[i]));
    }

    public void draw(final Batch batch) {
	for (LineSegment segment: segments)
	    segment.draw(batch);
    }
}
