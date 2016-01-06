package com.aragaer.rune;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class Line extends Group {
    private final Array<LineHandle> handles;
    private final Array<LineSegment> segments;

    public Line(Hole... holes) {
	segments = new Array<LineSegment>();
	handles = new Array<LineHandle>();
	LineHandle handle = new LineHandle(0);
	handles.add(handle);
	holes[0].put(handle, true);
	addActor(handle);

	for (int i = 1; i < holes.length; i++)
	    addSegment(holes[i]);
    }

    public void addSegment(Hole hole) {
	LineHandle handle = new LineHandle(handles.size);
	hole.put(handle, true);
	LineSegment segment = new LineSegment(handles.peek(), handle);
	handles.add(handle);
	segments.add(segment);
	addActor(segment);
	addActor(handle);
    }

    @Override public void draw(Batch batch, float delta) {
	for (LineSegment segment: segments)
	    segment.draw(batch, delta);
    }
}
