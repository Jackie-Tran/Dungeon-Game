package com.dungeongame.game;

import com.dungeongame.dungeon.Dungeon;
import com.mikejack.engine.AbstractGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.gamestate.GameStateManager;

public class DungeonGame extends AbstractGame{

    public static final int WIDTH=320, HEIGHT=180, SCALE=4;
    public static final String TITLE = "Drinking Water - Jackie Tran";
    
    public static final int DUNGEON_STATE = 1;
    
    private GameStateManager gsm;
    
    
    public DungeonGame() {
	gsm = new GameStateManager();
	gsm.setState(new MainMenu(gsm));
    }

    @Override
    public void update(GameContainer gc) {
	gsm.update(gc);
    }
    
    @Override
    public void render(GameContainer gc, Screen screen) {
	gsm.render(gc, screen);
    }
    
    public static void main(String args[]) {
	GameContainer gc = new GameContainer(WIDTH, HEIGHT, SCALE, TITLE, new DungeonGame());
	gc.setFpsVisibility(true);
	gc.requestFocus();
	gc.start();
    }

}
