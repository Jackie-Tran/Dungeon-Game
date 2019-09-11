package com.dungeongame.game.village;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.dungeongame.dungeon.Dungeon;
import com.dungeongame.game.Camera;
import com.dungeongame.game.DungeonGame;
import com.dungeongame.mobs.Player;
import com.dungeongame.objects.Door;
import com.dungeongame.objects.Tile;
import com.mikejack.audio.AudioClip;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.gamestate.GameState;
import com.mikejack.gamestate.GameStateManager;
import com.mikejack.graphics.Sprite;
import com.mikejack.objects.Layer;

public class Village extends GameState {

    private AudioClip bgm;

    private Sprite villageBottomLayer;
    private Sprite villageTopLayer;

    private Layer objects;
    private Camera camera;
    private Player player;
    private Door door;

    // Variables for loading solid tiles
    private final String path = "res/village/village.txt";

    public Village(GameStateManager gsm) {
	super(gsm);
	villageBottomLayer = new Sprite("/village/villageBottomLayer.png");
	villageTopLayer = new Sprite("/village/villageTopLayer.png");
	
	objects = new Layer();
	player = new Player(120, 120, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT, "player", objects);
	door = new Door(400, 36, 32, 18, player, Door.DUNGEON, this);
	camera = new Camera(player, 30 * DungeonGame.TILE_SIZE, 30 * DungeonGame.TILE_SIZE);
	bgm = new AudioClip("/village/bgm.wav");

	loadSolidTiles();
	bgm.loop();

    }

    @Override
    public void init() {
	// TODO Auto-generated method stub

    }

    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	player.update(gc);
	objects.update(gc);
	door.update(gc);
	camera.update(gc);
    }

    @Override
    public void render(GameContainer gc, Screen screen) {
	// TODO Auto-generated method stub
	// objects.render(gc);
	screen.drawSprite(villageBottomLayer, 0, 0, false, false);
	//objects.render(gc);
	player.render(gc);
	screen.drawSprite(villageTopLayer, 0, 0, false, false);
	door.render(gc);
    }

    @Override
    public void keyPressed(int k) {
	// TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(int k) {
	// TODO Auto-generated method stub

    }

    private void loadSolidTiles() {
	System.out.println("load tiles");
	Scanner scanner;
	try {
	    scanner = new Scanner(new File(path));
	    int width = scanner.nextInt();
	    int height = scanner.nextInt();

	    for (int y = 0; y < height; y++) {
		for (int x = 0; x < width; x++) {
		    if (scanner.nextInt() == 1)
			objects.addObject(new Tile(x * 16, y * 16, 16, 16, "solid"));
		}
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

    }

    @Override
    public void changeState(GameState gameState) {
	bgm.stop();
	gsm.setState(gameState);
	
    }
    
    

}
