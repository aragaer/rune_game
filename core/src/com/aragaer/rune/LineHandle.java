package com.aragaer.rune;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class LineHandle extends Actor {

    public final int position;

    public LineHandle(int position) {
	this.position = position;
	setTouchable(Touchable.disabled);
    }
}
