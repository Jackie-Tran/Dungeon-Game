package com.dungeongame.dungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.dungeongame.objects.Tile;
import com.mikejack.objects.Layer;

public class RoomLoader {

    private final static int TILE_SIZE = 16;
    private static Scanner scanner;
    
    // Wall Ids
    private static final int TOP_WALL = 1;
    private static final int RIGHT_WALL = 2;
    private static final int BOTTOM_WALL = 3;
    private static final int LEFT_WALL = 4;
    private static final int BOTOMRIGHT_CORNER_WALL = 5;
    private static final int BOTTOMLEFT_CORNER_WALL = 6;
    
    public RoomLoader() {
	
    }
    
    public static void loadRoomFile(String path) {
	try {
	    scanner = new Scanner(new File(path));
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public static void readRoomFile(Layer objects) {
	int width = scanner.nextInt();
	int height = scanner.nextInt();
	
	for (int y = 0; y < height; y++) {
	    for (int x = 0; x < width; x++) {
		
	    }
	}
    }
    
}
