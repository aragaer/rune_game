package com.aragaer.rune;

import com.badlogic.gdx.utils.ObjectSet;

public class LineSet extends ObjectSet<LineDescription> {

    public LineSet() {
	super();
    }

    public LineSet(LineDescription... descriptions) {
	this();
	addAll(descriptions);
    }
}
