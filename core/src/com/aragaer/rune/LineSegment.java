package com.aragaer.rune;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.badlogic.gdx.math.Vector2.len;

public class LineSegment extends Actor {
    private static Texture texture;

    private static final int X_OFF = -8;
    private static final int Y_OFF = -8;

    private LineHandle start, end;

    public static void setTexture(Texture texture) {
	LineSegment.texture = texture;
    }

    public LineSegment(LineHandle from, LineHandle to) {
	start = from;
	end = to;
    }

    @Override public void draw(Batch batch, float alpha) {
	float startx = start.getX();
	float starty = start.getY();
	float diffx = end.getX() - startx;
	float diffy = end.getY() - starty;
	int shinyCount = Math.round(len(diffx, diffy)/10);
	for (int i = 1; i <= shinyCount; i++) {
	    int x = Math.round(startx + diffx*i/(shinyCount + 1));
	    int y = Math.round(starty + diffy*i/(shinyCount + 1));
	    batch.draw(texture, x+X_OFF, y+Y_OFF);
	}
    }
}
