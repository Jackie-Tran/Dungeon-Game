package com.dungeongame.dungeon;

import java.util.Random;

import com.dungeongame.game.DungeonGame;
import com.dungeongame.objects.Tile;
import com.mikejack.objects.Layer;

public class Room {

    public static final int WIDTH = DungeonGame.WIDTH;
    public static final int HEIGHT = DungeonGame.HEIGHT;
    public static final int TILE_SIZE = 16;

    private RoomLoader roomLoader;
    private int x, y;
    private boolean openUp, openRight, openDown, openLeft;

    public Room(int x, int y, boolean openUp, boolean openRight, boolean openDown, boolean openLeft) {
	this.x = x;
	this.y = y;
	this.openUp = openUp;
	this.openRight = openRight;
	this.openDown = openDown;
	this.openLeft = openLeft;
	roomLoader = new RoomLoader();
    }

    public void createRoom(Layer walls) {
	// Generate the walls of the room based on where it has openings
	Random random = new Random();
	int roomNumber = random.nextInt(1)+1;
	// Single openings
	if (openUp && openRight && openDown && openLeft) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openUpRightDownLeft/"+roomNumber+".txt");
	} else if (openUp && openRight && openDown) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openUpRightDown/"+roomNumber+".txt");
	} else if (openUp && openRight && openLeft) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openUpRightLeft/"+roomNumber+".txt");
	} else if (openUp && openDown && openLeft) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openUpDownLeft/"+roomNumber+".txt");
	} else if (openRight && openDown && openLeft) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openRightDownLeft/"+roomNumber+".txt");
	} else if (openUp && openRight) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openUpRight/"+roomNumber+".txt");
	} else if (openUp && openDown) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openUpDown/"+roomNumber+".txt");
	} else if (openRight && openDown) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openRightDown/"+roomNumber+".txt");
	} else if (openUp && openLeft) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openUpLeft/"+roomNumber+".txt");
	} else if (openRight && openLeft) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openRightLeft/"+roomNumber+".txt");
	} else if (openDown && openLeft) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openDownLeft/"+roomNumber+".txt");
	} else if (openUp) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openUp/"+roomNumber+".txt");
	} else if (openRight) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openRight/"+roomNumber+".txt");
	} else if (openDown) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openDown/"+roomNumber+".txt");
	} else if (openLeft) {
	    roomLoader.loadRoomFile("res/dungeon/rooms/openLeft/"+roomNumber+".txt");
	} else {
	    return;
	}
	roomLoader.readRoomFile(x, y, walls);
	
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

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }

    public int getY() {
	return y;
    }

    public void setY(int y) {
	this.y = y;
    }

}
