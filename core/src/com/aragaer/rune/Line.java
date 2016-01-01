package com.aragaer.rune;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;

public class Line {
    private final Array<LineSegment> segments;

    public Line(Hole... holes) {
	segments = new Array<LineSegment>(holes.length - 1);
	for (int i = 1; i < holes.length; i++) {
	    LineSegment segment = new LineSegment(holes[i-1], holes[i], this, i-1);
	    holes[i-1].segments.add(segment);
	    holes[i].segments.add(segment);
	    segments.add(segment);
	}
    }

    public void draw(final Batch batch) {
	for (LineSegment segment: segments)
	    segment.draw(batch);
    }

    public void drag(int pointIndex, GridPoint2 point) {
	if (pointIndex < segments.size)
	    segments.get(pointIndex).moveStart(point);
	if (pointIndex > 0)
	    segments.get(pointIndex-1).moveEnd(point);
    }
}
