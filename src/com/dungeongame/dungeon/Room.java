package com.dungeongame.dungeon;

import com.dungeongame.game.DungeonGame;
import com.dungeongame.objects.Tile;
import com.mikejack.objects.Layer;

public class Room {

    public static final int WIDTH = DungeonGame.WIDTH;
    public static final int HEIGHT = DungeonGame.HEIGHT;
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
	// Generate the walls of the room based on where it has openings
	// Top and Bottom walls
	for (int i = 0; i < WIDTH / TILE_SIZE; i++) {

	    if (!openUp || i <= 7 || i >= 12) {
		walls.addObject(new Tile(x + (i * TILE_SIZE), y, TILE_SIZE, TILE_SIZE, true, "wall"));
	    }

	    if (!openDown || i <= 7 || i >= 12) {
		walls.addObject(new Tile(x + (i * TILE_SIZE), y + (HEIGHT / TILE_SIZE - 1) * TILE_SIZE, TILE_SIZE,
			TILE_SIZE, true, "wall"));
	    }
	}

	// Left and Right walls
	for (int i = 1; i < HEIGHT / TILE_SIZE; i++) {
	    if (!openLeft || i <= 3 || i >= 7) {
		walls.addObject(new Tile(x, y + (i * TILE_SIZE), TILE_SIZE, TILE_SIZE, true, "wall"));
	    }
	    if (!openRight || i <= 3 || i >= 7) {
		walls.addObject(new Tile(x + (WIDTH / TILE_SIZE - 1) * TILE_SIZE, y + (i * TILE_SIZE), TILE_SIZE,
			TILE_SIZE, true, "wall"));
	    }
	}
    }

    public boolean isOpenUp() {
        return openUp;
    }

    public void setOpenUp(boolean openUp) {
        this.openUp = openUp;
    }

    public boolean isOpenRight() {
        return openRight;
    }

    public void setOpenRight(boolean openRight) {
        this.openRight = openRight;
    }

    public boolean isOpenDown() {
        return openDown;
    }

    public void setOpenDown(boolean openDown) {
        this.openDown = openDown;
    }

    public boolean isOpenLeft() {
        return openLeft;
    }

    public void setOpenLeft(boolean openLeft) {
        this.openLeft = openLeft;
    }
    
    public boolean isBlocked() {
	return !openUp && !openRight && !openDown && !openLeft;
    }

}
