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

    private GridPoint2 start, end;
    private Array<GridPoint2> shinies;
    final Line line;
    final int position;

    public static void setTexture(Texture texture, int count) {
	LineSegment.texture = texture;
	LineSegment.shinyCount = count;
    }

    public LineSegment(Hole from, Hole to, Line line, int position) {
	this.line = line;
	this.position = position;
	start = new GridPoint2(from.x, from.y);
	end = new GridPoint2(to.x, to.y);
	shinies = new Array<GridPoint2>(shinyCount);
	for (int i = 0; i < shinyCount; i++)
	    shinies.add(new GridPoint2(0, 0));
	move();
    }

    private void move() {
	int diffx = end.x - start.x;
	int diffy = end.y - start.y;
	for (int i = 1; i <= shinyCount; i++)
	    shinies.get(i-1).set(start.x + diffx*i/(shinyCount + 1),
				 start.y + diffy*i/(shinyCount + 1));
    }

    void moveStart(GridPoint2 start) {
	this.start = start;
	move();
    }

    void moveEnd(GridPoint2 end) {
	this.end = end;
	move();
    }

    public void draw(final Batch batch) {
	for (GridPoint2 shiny : shinies)
	    batch.draw(texture, shiny.x+X_OFF, shiny.y+Y_OFF);
    }
}
