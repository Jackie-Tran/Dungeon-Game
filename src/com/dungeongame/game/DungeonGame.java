package com.dungeongame.game;

import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;

public class DungeonGame extends AbstractGame{

    public static final int WIDTH=320, HEIGHT=180, SCALE=4;
    public static final String TITLE = "Dungeon Game";
    
    public DungeonGame() {
	
    }

    @Override
    public void update(GameContainer gc, float dt) {
	
    }
    
    @Override
    public void render(GameContainer gc, Screen screen) {
	
    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(WIDTH, HEIGHT, SCALE, TITLE, new DungeonGame());
	gc.setFpsVisibility(true);
	gc.start();
    }

}
