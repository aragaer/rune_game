package com.aragaer.rune;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LineSegment extends Actor {
    private static Texture texture;
    private static int shinyCount;

    private static final int X_OFF = -8;
    private static final int Y_OFF = -8;

    private LineHandle start, end;

    public static void setTexture(Texture texture, int count) {
	LineSegment.texture = texture;
	LineSegment.shinyCount = count;
    }

    public LineSegment(LineHandle from, LineHandle to) {
	start = from;
	end = to;
    }

    @Override public void draw(Batch batch, float alpha) {
	float diffx = end.x - start.x;
	float diffy = end.y - start.y;
	for (int i = 1; i <= shinyCount; i++) {
	    int x = Math.round(start.x + diffx*i/(shinyCount + 1));
	    int y = Math.round(start.y + diffy*i/(shinyCount + 1));
	    batch.draw(texture, x+X_OFF, y+Y_OFF);
	}
    }
}
