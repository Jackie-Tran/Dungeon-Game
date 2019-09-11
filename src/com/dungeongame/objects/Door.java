package com.dungeongame.objects;

import com.dungeongame.dungeon.Dungeon;
import com.dungeongame.game.village.Village;
import com.dungeongame.mobs.Player;
import com.mikejack.engine.GameContainer;
import com.mikejack.gamestate.GameState;
import com.mikejack.gamestate.GameStateManager;
import com.mikejack.graphics.Sprite;
import com.sun.glass.events.KeyEvent;

public class Door extends Interactable{

    public static final int VILLAGE = 1, DUNGEON = 2;
    
    private GameState currentState;
    private int dest;
    
    public Door(float x, float y, int width, int height, Player player, int dest, GameState currentState) {
	super(x, y, width, height, "door", player);
	this.dest = dest;
	this.currentState = currentState;
    }

    public Door(float x, float y, int width, int height, Sprite sprite, Player player, int dest, GameState currentState) {
	super(x, y, width, height, "door", sprite, player);
	this.dest = dest;
	this.currentState = currentState;
    }

    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	x = 400;
	y = 36;
	width = 32;
	height = 18;
	
	if (player.getX() < x + width && player.getX() + player.getWidth() > x &&
		player.getY() < y + height && player.getY() + player.getHeight() > y) {
	    if (gc.getInput().isKey(KeyEvent.VK_K)) {
		action();
	    }
	}
    }

    @Override
    public void render(GameContainer gc) {
	// TODO Auto-generated method stub
	gc.getScreen().drawRect((int) x, (int) y, width, height, 0xffff00ff);
	
    }

    public void action() {
	// TODO Auto-generated method stub
	if (dest == DUNGEON) {
	    currentState.changeState(new Dungeon(currentState.getGsm()));
	} else if (dest == VILLAGE) {
	    currentState.changeState(new Village(currentState.getGsm()));
	}
	
	
    }

}
