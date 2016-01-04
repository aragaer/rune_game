package com.aragaer.rune;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class RuneGame extends Game {

    @Override public void create() {
	setScreen(new GameScreen());
    }
}
