package com.aragaer.rune;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;


public class LineSegment {
    private static Texture texture;
    private static int shinyCount;

    private static final int X_OFF = -8;
    private static final int Y_OFF = -8;

    private Array<GridPoint2> shinies;

    public static void setTexture(Texture texture, int count) {
	LineSegment.texture = texture;
	LineSegment.shinyCount = count;
    }

    public LineSegment(Hole from, Hole to) {
	shinies = new Array<GridPoint2>(shinyCount);
	int diffx = to.x - from.x;
	int diffy = to.y - from.y;
	for (int i = 1; i <= shinyCount; i++)
	    shinies.add(new GridPoint2(from.x + diffx*i/(shinyCount + 1),
				       from.y + diffy*i/(shinyCount + 1)));
    }

    public void draw(final Batch batch) {
	for (GridPoint2 shiny : shinies)
	    batch.draw(texture, shiny.x+X_OFF, shiny.y+Y_OFF);
    }
}
