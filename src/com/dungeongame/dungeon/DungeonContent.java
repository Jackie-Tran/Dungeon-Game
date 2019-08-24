package com.dungeongame.dungeon;

import com.mikejack.graphics.Sprite;
import com.mikejack.graphics.SpriteSheet;

public class DungeonContent {

    private final int TILE_SIZE = 16;
    private SpriteSheet spriteSheet;
    // Sprites
    // Dungeon Walls
    public Sprite topWall1, topWall2, topWall3, topWall4;
    
    
    public DungeonContent() {
	spriteSheet = new SpriteSheet("/dungeon/dungeon_tileset.png");
	topWall1 = spriteSheet.grabSprite(1, 0, TILE_SIZE, TILE_SIZE);
	topWall2 = spriteSheet.grabSprite(2, 0, TILE_SIZE, TILE_SIZE);
	topWall3 = spriteSheet.grabSprite(3, 0, TILE_SIZE, TILE_SIZE);
	topWall4 = spriteSheet.grabSprite(4, 0, TILE_SIZE, TILE_SIZE);
	
    }
    
}
