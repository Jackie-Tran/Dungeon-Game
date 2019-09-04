package com.dungeongame.dungeon;

import com.mikejack.graphics.Light;
import com.mikejack.graphics.Sprite;
import com.mikejack.graphics.SpriteSheet;

public class DungeonContent {

    private final int TILE_SIZE = 16;
    private SpriteSheet spriteSheet;

    // Sprites
    // Dungeon Walls
    public final Sprite TOP_WALL1, TOP_WALL2, TOP_WALL3, TOP_WALL4;
    public final Sprite RIGHT_WALL1, RIGHT_WALL2, RIGHT_WALL3, RIGHT_WALL4;
    public final Sprite BOTTOM_WALL1, BOTTOM_WALL2, BOTTOM_WALL3, BOTTOM_WALL4;
    public final Sprite LEFT_WALL1, LEFT_WALL2, LEFT_WALL3, LEFT_WALL4;
    public final Sprite TOP_LEFT_CORNER_WALL, TOP_RIGHT_CORNER_WALL;
    public final Sprite BOTTOM_LEFT_CORNER_WALL, BOTTOM_RIGHT_CORNER_WALL;

    // Dungeon Floor
    public final Sprite FLOORS[] = new Sprite[12];

    public DungeonContent() {
	spriteSheet = new SpriteSheet("/dungeon/dungeon_tileset.png");
	TOP_WALL1 = spriteSheet.grabSprite(1, 0, TILE_SIZE, TILE_SIZE);
	TOP_WALL2 = spriteSheet.grabSprite(2, 0, TILE_SIZE, TILE_SIZE);
	TOP_WALL3 = spriteSheet.grabSprite(3, 0, TILE_SIZE, TILE_SIZE);
	TOP_WALL4 = spriteSheet.grabSprite(4, 0, TILE_SIZE, TILE_SIZE);

	RIGHT_WALL1 = spriteSheet.grabSprite(5, 0, TILE_SIZE, TILE_SIZE);
	RIGHT_WALL2 = spriteSheet.grabSprite(5, 1, TILE_SIZE, TILE_SIZE);
	RIGHT_WALL3 = spriteSheet.grabSprite(5, 2, TILE_SIZE, TILE_SIZE);
	RIGHT_WALL4 = spriteSheet.grabSprite(5, 3, TILE_SIZE, TILE_SIZE);

	BOTTOM_WALL1 = spriteSheet.grabSprite(1, 4, TILE_SIZE, TILE_SIZE);
	BOTTOM_WALL2 = spriteSheet.grabSprite(2, 4, TILE_SIZE, TILE_SIZE);
	BOTTOM_WALL3 = spriteSheet.grabSprite(3, 4, TILE_SIZE, TILE_SIZE);
	BOTTOM_WALL4 = spriteSheet.grabSprite(4, 4, TILE_SIZE, TILE_SIZE);

	LEFT_WALL1 = spriteSheet.grabSprite(0, 0, TILE_SIZE, TILE_SIZE);
	LEFT_WALL2 = spriteSheet.grabSprite(0, 1, TILE_SIZE, TILE_SIZE);
	LEFT_WALL3 = spriteSheet.grabSprite(0, 2, TILE_SIZE, TILE_SIZE);
	LEFT_WALL4 = spriteSheet.grabSprite(0, 3, TILE_SIZE, TILE_SIZE);

	TOP_LEFT_CORNER_WALL = spriteSheet.grabSprite(0, 5, TILE_SIZE, TILE_SIZE);
	TOP_RIGHT_CORNER_WALL = spriteSheet.grabSprite(5, 5, TILE_SIZE, TILE_SIZE);
	BOTTOM_LEFT_CORNER_WALL = spriteSheet.grabSprite(0, 4, TILE_SIZE, TILE_SIZE);
	BOTTOM_RIGHT_CORNER_WALL = spriteSheet.grabSprite(5, 4, TILE_SIZE, TILE_SIZE);

	int index = -1;
	for (int j = 0; j < 3; j++) {
	    for (int i = 6; i < 10; i++) {
		index++;
		FLOORS[index] = spriteSheet.grabSprite(i, j, TILE_SIZE, TILE_SIZE);
		FLOORS[index].setLightBlock(Light.NONE);
	    }
	}

    }

}
