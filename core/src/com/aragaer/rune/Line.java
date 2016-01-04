package com.aragaer.rune;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Line extends Actor {
    private final Array<LineHandle> handles;
    private final Array<LineSegment> segments;

    public Line(Hole... holes) {
	segments = new Array<LineSegment>(holes.length - 1);
	handles = new Array<LineHandle>(holes.length);
	for (Hole hole : holes) {
	    LineHandle handle = new LineHandle(0, 0);
	    handles.add(handle);
	    hole.put(handle);
	}

	Iterator<LineHandle> iterator = handles.iterator();
	LineHandle start = iterator.next();
	while (iterator.hasNext()) {
	    LineHandle end = iterator.next();
	    LineSegment segment = new LineSegment(start, end);
	    start.add(segment);
	    end.add(segment);
	    segments.add(segment);
	    start = end;
	}
    }

    @Override public void draw(Batch batch, float alpha) {
	for (LineSegment segment: segments)
	    segment.draw(batch, alpha);
    }
}
