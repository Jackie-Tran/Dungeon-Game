package com.dungeongame.dungeon;

import com.dungeongame.game.DungeonGame;
import com.dungeongame.objects.Tile;
import com.mikejack.objects.Layer;

public class Room {

    public static final int ROOM_WIDTH = DungeonGame.WIDTH;
    public static final int ROOM_HEIGHT = DungeonGame.HEIGHT;
    public static final int TILE_SIZE = 16;

    private int x, y;
    private boolean openUp, openRight, openDown, openLeft;

    public Room(int x, int y, boolean openUp, boolean openRight, boolean openDown, boolean openLeft) {
	this.x = x;
	this.y = y;
	this.openUp = openUp;
	this.openRight = openRight;
	this.openDown = openDown;
	this.openLeft = openLeft;
    }

    public void createRoom(Layer walls) {
	// Top and Bottom walls
	for (int i = 0; i < ROOM_WIDTH / TILE_SIZE; i++) {
	    if (openUp && i > 7 && i < 12) {
		continue;
	    } else {
		walls.addObject(new Tile(x + (i * TILE_SIZE), y, TILE_SIZE, TILE_SIZE, true, "wall"));
	    }

	    if (openDown && i > 7 && i < 12) {
		continue;
	    } else {
		walls.addObject(
			new Tile(x + (i * TILE_SIZE), y + ROOM_HEIGHT - TILE_SIZE, TILE_SIZE, TILE_SIZE, true, "wall"));
	    }
	}
    }

}
