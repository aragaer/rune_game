package com.aragaer.rune;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;

public class Hole {
    private static Texture texture;
    private final int x, y;

    private final static int X_OFF = 16;
    private final static int Y_OFF = 16;

    public static void setTexture(final Texture texture) {
	Hole.texture = texture;
    }

    public Hole(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public void draw(final Batch batch) {
	batch.draw(texture, x-X_OFF, y-Y_OFF);
    }
}
