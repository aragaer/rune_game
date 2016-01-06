package com.aragaer.rune;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class GameField extends Group {
    private static final int LEN = 115;
    private static final int LEN2 = 100;
    private static final int X_CENTER = 400;
    private static final int Y_CENTER = 240;

    private final Array<Hole> holes;

    public GameField() {
	holes = new Array<Hole>();
	holes.add(new Hole(X_CENTER, Y_CENTER));

	holes.add(new Hole(X_CENTER - LEN/2, Y_CENTER + LEN2));
	holes.add(new Hole(X_CENTER + LEN/2, Y_CENTER + LEN2));
	holes.add(new Hole(X_CENTER + LEN, Y_CENTER));
	holes.add(new Hole(X_CENTER + LEN/2, Y_CENTER - LEN2));
	holes.add(new Hole(X_CENTER - LEN/2, Y_CENTER - LEN2));
	holes.add(new Hole(X_CENTER - LEN, Y_CENTER));

	holes.add(new Hole(X_CENTER - LEN, Y_CENTER + 2*LEN2));
	holes.add(new Hole(X_CENTER, Y_CENTER + 2*LEN2));
	holes.add(new Hole(X_CENTER + LEN, Y_CENTER + 2*LEN2));
	holes.add(new Hole(X_CENTER + 3*LEN/2, Y_CENTER + LEN2));
	holes.add(new Hole(X_CENTER + 2*LEN, Y_CENTER));
	holes.add(new Hole(X_CENTER + 3*LEN/2, Y_CENTER - LEN2));
	holes.add(new Hole(X_CENTER + LEN, Y_CENTER - 2*LEN2));
	holes.add(new Hole(X_CENTER, Y_CENTER - 2*LEN2));
	holes.add(new Hole(X_CENTER - LEN, Y_CENTER - 2*LEN2));
	holes.add(new Hole(X_CENTER - 3*LEN/2, Y_CENTER - LEN2));
	holes.add(new Hole(X_CENTER - 2*LEN, Y_CENTER));
	holes.add(new Hole(X_CENTER - 3*LEN/2, Y_CENTER + LEN2));

	for (Hole hole : holes)
	    addActor(hole);

	LineSet setup = new LineSet(new LineDescription(0, 1, 2, 4),
				    new LineDescription(3, 5, 8, 11));

	for (LineDescription description : setup)
	    createLine(description);
    }

    public void createLine(LineDescription description) {
	Array<Hole> usedHoles = new Array<Hole>();
	for (int i : description.getHoleNumbers())
	    usedHoles.add(holes.get(i));
	Line line = new Line(usedHoles.removeIndex(0));
	for (Hole hole : usedHoles)
	    line.addSegment(hole);
	addActor(line);
    }
}
