package com.dungeongame.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import com.dungeongame.objects.Tile;
import com.mikejack.objects.Layer;

/*
 * There are different room layouts depending on how many openings the room has. In total, for each room with
 * x openings there will be 10(?) different possible room layouts.
 */

public class RoomLoader {

    private final int TILE_SIZE = 16;
    private Scanner scanner;

    // Enum to hold the ids for the specifc tiles
    public enum TileId {
	VOID(0),
	FLOOR(0),
	TOP_WALL(1),
	RIGHT_WALL(2),
	BOTTOM_WALL(3),
	LEFT_WALL(4),
	TOP_RIGHT_CORNER(5),
	BOTTOM_RIGHT_CORNER(6),
	BOTTOM_LEFT_CORNER(7),
	TOP_LEFT_CORNER(8);
	
	private final int id;
	private TileId(int id) {
	    this.id = id;
	}
	private int id() {return id;}
    }

    public RoomLoader() {
	scanner = null;
    }

    public void loadRoomFile(String path) {
	try {
	    scanner = new Scanner(new File(path));
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void readRoomFile(int roomX, int roomY, Layer objects) {
	Random random = new Random();
	int newX, newY;
	int width = scanner.nextInt();
	int height = scanner.nextInt();

	for (int y = 0; y < height; y++) {
	    newY = roomY + y*TILE_SIZE;
	    for (int x = 0; x < width; x++) {
		newX = roomX + x*TILE_SIZE;
		int tileid = scanner.nextInt();
		if (tileid == TileId.FLOOR.id) {
		    int floorType = random.nextInt(12);
		    objects.addObject(new Tile(newX, newY, TILE_SIZE, TILE_SIZE, "floor", Dungeon.content.FLOORS[floorType]));
		}
		if (tileid == TileId.TOP_WALL.id) {
		    objects.addObject(makeTopWall(newX, newY));
		} else if (tileid == TileId.RIGHT_WALL.id) {
		    objects.addObject(makeRightWall(newX, newY));
		} else if (tileid == TileId.BOTTOM_WALL.id) {
		    objects.addObject(makeBottomWall(newX, newY));
		} else if (tileid == TileId.LEFT_WALL.id) {
		    objects.addObject(makeLeftWall(newX, newY));
		} else if (tileid == TileId.TOP_RIGHT_CORNER.id) {
		    objects.addObject(new Tile(newX, newY, TILE_SIZE, TILE_SIZE, "wall", Dungeon.content.TOP_RIGHT_CORNER_WALL));
		} else if (tileid == TileId.BOTTOM_RIGHT_CORNER.id) {
		    objects.addObject(new Tile(newX, newY, TILE_SIZE, TILE_SIZE, "wall", Dungeon.content.BOTTOM_RIGHT_CORNER_WALL));
		} else if (tileid == TileId.BOTTOM_LEFT_CORNER.id) {
		    objects.addObject(new Tile(newX, newY, TILE_SIZE, TILE_SIZE, "wall", Dungeon.content.BOTTOM_LEFT_CORNER_WALL));
		} else if (tileid == TileId.TOP_LEFT_CORNER.id) {
		    objects.addObject(new Tile(newX, newY, TILE_SIZE, TILE_SIZE, "wall", Dungeon.content.TOP_LEFT_CORNER_WALL));
		}
	    }
	}
    }
    
    private Tile makeTopWall(float x, float y) {
	Random random = new Random();
	int tileType = random.nextInt(4);
	Tile wall = new Tile(x, y, TILE_SIZE, TILE_SIZE, "wall");
	if (tileType == 0) {
	    wall.setSprite(Dungeon.content.TOP_WALL1);
	} else if (tileType == 1) {
	    wall.setSprite(Dungeon.content.TOP_WALL2);
	} else if (tileType == 2) {
	    wall.setSprite(Dungeon.content.TOP_WALL3);
	} else {
	    wall.setSprite(Dungeon.content.TOP_WALL4);
	}
	return wall;
    }

    private Tile makeRightWall(float x, float y) {
	Random random = new Random();
	int tileType = random.nextInt(4);
	Tile wall = new Tile(x, y, TILE_SIZE, TILE_SIZE, "wall");
	if (tileType == 0) {
	    wall.setSprite(Dungeon.content.RIGHT_WALL1);
	} else if (tileType == 1) {
	    wall.setSprite(Dungeon.content.RIGHT_WALL2);
	} else if (tileType == 2) {
	    wall.setSprite(Dungeon.content.RIGHT_WALL3);
	} else {
	    wall.setSprite(Dungeon.content.RIGHT_WALL4);
	}
	return wall;
    }
    
    private Tile makeBottomWall(float x, float y) {
	Random random = new Random();
	int tileType = random.nextInt(4);
	Tile wall = new Tile(x, y, TILE_SIZE, TILE_SIZE, "wall");
	if (tileType == 0) {
	    wall.setSprite(Dungeon.content.BOTTOM_WALL1);
	} else if (tileType == 1) {
	    wall.setSprite(Dungeon.content.BOTTOM_WALL2);
	} else if (tileType == 2) {
	    wall.setSprite(Dungeon.content.BOTTOM_WALL3);
	} else {
	    wall.setSprite(Dungeon.content.BOTTOM_WALL4);
	}
	return wall;
    }
    
    private Tile makeLeftWall(float x, float y) {
	Random random = new Random();
	int tileType = random.nextInt(4);
	Tile wall = new Tile(x, y, TILE_SIZE, TILE_SIZE, "wall");
	if (tileType == 0) {
	    wall.setSprite(Dungeon.content.LEFT_WALL1);
	} else if (tileType == 1) {
	    wall.setSprite(Dungeon.content.LEFT_WALL2);
	} else if (tileType == 2) {
	    wall.setSprite(Dungeon.content.LEFT_WALL3);
	} else {
	    wall.setSprite(Dungeon.content.LEFT_WALL4);
	}
	return wall;
    }
}
