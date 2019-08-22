package com.mikejack.gamestate;

import java.util.ArrayList;

import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;

public class GameStateManager {

    private GameState currentState;

    // Create final int for GameState IDS

    public GameStateManager() {
    }

    private void unloadCurrentState() {
	currentState = null;
    }

    public void setState(GameState gameState) {
	unloadCurrentState();
	currentState = gameState;
	currentState.init();

    }

    public void update(GameContainer gc, float dt) {
	currentState.update(gc, dt);
    }

    public void render(GameContainer gc, Screen screen) {
	currentState.render(gc, screen);
    }

    public void keyPressed(int k) {
	currentState.keyPressed(k);
    }

    public void keyReleased(int k) {
	currentState.keyReleased(k);
    }
}
