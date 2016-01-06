package com.aragaer.rune;

import com.badlogic.gdx.scenes.scene2d.*;


class FieldInputListener extends InputListener {
    private LineHandle dragged;
    private Hole draggedFrom, hovering;
    private final Stage stage;

    public FieldInputListener(Stage stage) {
	this.stage = stage;
    }

    @Override public boolean touchDown(InputEvent event, float x, float y,
				       int pointer, int button) {
	if (pointer > 0
	    || button != com.badlogic.gdx.Input.Buttons.LEFT)
	    return false;
	Hole hole = (Hole) stage.hit(x, y, false);
	if (hole == null || hole.isEmpty())
	    return false;
	hovering = hole;
	hole.setState(Hole.State.GOOD);
	dragged = hole.pick();
	dragged.setPosition(x, y);
	draggedFrom = hole;
	return true;
    }

    private void finishDragAt(Hole hole) {
	hole.put(dragged);
	dragged = null;
	draggedFrom = null;
    }

    @Override public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
	if (pointer > 0
	    || button != com.badlogic.gdx.Input.Buttons.LEFT)
	    return;
	Hole hole = (Hole) stage.hit(x, y, true);
	if (hole != null)
	    hole.setState(Hole.State.NORMAL);
	if (dragged != null) {
	    if (hole != null && hole.isEmpty())
		finishDragAt(hole);
	    else
		finishDragAt(draggedFrom);
	}
    }

    @Override public void touchDragged(InputEvent event, float x, float y, int pointer) {
	if (pointer > 0 || dragged == null)
	    return;
	Hole hole = (Hole) stage.hit(x, y, true);
	if (hole == null) {
	    if (hovering != null) {
		hovering.setState(Hole.State.NORMAL);
		hovering = null;
	    }
	} else {
	    hovering = hole;
	    if (hole.isEmpty())
		hole.setState(Hole.State.GOOD);
	    else
		hole.setState(Hole.State.BAD);
	}
	dragged.setPosition(x, y);
    }
}
