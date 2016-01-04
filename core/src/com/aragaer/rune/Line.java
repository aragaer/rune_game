package com.aragaer.rune;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class Line extends Group {
    private final Array<LineHandle> handles;
    private final Array<LineSegment> segments;

    public Line(Hole... holes) {
	segments = new Array<LineSegment>(holes.length - 1);
	handles = new Array<LineHandle>(holes.length);
	for (Hole hole : holes) {
	    LineHandle handle = new LineHandle();
	    handles.add(handle);
	    addActor(handle);
	    hole.put(handle, true);
	}

	Iterator<LineHandle> iterator = handles.iterator();
	LineHandle start = iterator.next();
	while (iterator.hasNext()) {
	    LineHandle end = iterator.next();
	    LineSegment segment = new LineSegment(start, end);
	    segments.add(segment);
	    start = end;
	}
    }

    @Override public void draw(Batch batch, float alpha) {
	for (LineSegment segment: segments)
	    segment.draw(batch, alpha);
    }
}
