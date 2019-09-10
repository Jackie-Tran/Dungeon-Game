package com.dungeongame.game;

import com.dungeongame.dungeon.Dungeon;
import com.dungeongame.game.village.Village;
import com.dungeongame.ui.Button;
import com.mikejack.audio.AudioClip;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.gamestate.GameState;
import com.mikejack.gamestate.GameStateManager;
import com.mikejack.graphics.Font;
import com.mikejack.graphics.Sprite;

public class MainMenu extends GameState{

    // Animation counters
    private int frameTime = 5;
    private int timer = 0;
    
    // Background Image
    private Sprite background[];
    private int currentBgFrame = 0;
    private AudioClip menuMusic;
    
    // Title image
    private Sprite title;
    
    // Buttons
    private Button playButton;
    private Button controlsButton;
    private Button exitButton;
    
    public MainMenu(GameStateManager gsm) {
	super(gsm);
	// TODO Auto-generated constructor stub
	// Loading background
	background = new Sprite[4];
	for (int i = 0; i < background.length; i++) {
	    background[i] = new Sprite("/main_menu/background/" + i + ".png");
	}
	
	title = new Sprite("/main_menu/title.png");
	menuMusic = new AudioClip("/main_menu/Stunning Night.wav");
	playButton = new Button(DungeonGame.WIDTH/2 - 100/2, 60, 100, 20, "Play", "/main_menu/buttons/playButton.png");
	controlsButton = new Button (DungeonGame.WIDTH/2 - 100/2, 90, 100, 20, "Controls", "/main_menu/buttons/controlsButton.png");
	exitButton = new Button (DungeonGame.WIDTH/2 - 100/2, 120, 100, 20, "Exit", "/main_menu/buttons/exitButton.png");
	menuMusic.loop();
    }

    @Override
    public void init() {
	
    }

    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	if (timer < frameTime) {
	    timer++;
	} else {
	    currentBgFrame++;
	    if (currentBgFrame >= background.length) {
		currentBgFrame = 0;
	    }
	    timer = 0;
	}
	playButton.update(gc);
	controlsButton.update(gc);
	exitButton.update(gc);
	
	
	if (playButton.isClicked()) {
	    menuMusic.stop();
	    gsm.setState(new Village(gsm));
	}
	
	if (controlsButton.isClicked()) {
	    
	}
	
	if (exitButton.isClicked()) {
	    System.exit(0);
	}
	
    }

    @Override
    public void render(GameContainer gc, Screen screen) {
	// TODO Auto-generated method stub
	screen.drawSprite(background[currentBgFrame], 0, 0, false, false);
	screen.drawSprite(title, 25, 0, false, false);
	playButton.render(gc);
	controlsButton.render(gc);
	exitButton.render(gc);
	
	
    }

    @Override
    public void keyPressed(int k) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void keyReleased(int k) {
	// TODO Auto-generated method stub
	
    }

}
