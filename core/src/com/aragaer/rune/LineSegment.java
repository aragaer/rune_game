package com.aragaer.rune;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.badlogic.gdx.math.Vector2.len;

public class LineSegment extends Actor {
    private static AssetManager manager;
    private static ParticleEffectPool effects;
    private ParticleEffect effect;

    private LineHandle start, end;

    public static void setAssetManager(AssetManager manager) {
	LineSegment.manager = manager;
	manager.load("enchant.party", ParticleEffect.class);
    }

    public LineSegment(LineHandle from, LineHandle to) {
	start = from;
	end = to;
	synchronized (LineSegment.class) {
	    if (effects == null)
		effects = new ParticleEffectPool(manager.get("enchant.party",
							     ParticleEffect.class), 1, 10);
	}
	effect = effects.obtain();
	updatePosition();
	effect.start();
    }

    @Override public void draw(Batch batch, float delta) {
	updatePosition();
	effect.update(Gdx.graphics.getDeltaTime());
	effect.draw(batch);
    }

    private void updatePosition() {
	float startx = start.getX();
	float starty = start.getY();
	float diffx = end.getX() - startx;
	float diffy = end.getY() - starty;
	int emission = Math.round(len(diffx, diffy)/2);
	for (ParticleEmitter emitter : effect.getEmitters()) {
	    emitter.getEmission().setLow(emission/2);
	    emitter.getEmission().setHigh(emission);
	    emitter.setPosition(startx, starty);
	    emitter.getSpawnWidth().setHigh(diffx);
	    emitter.getSpawnHeight().setHigh(diffy);
	}
    }
}
