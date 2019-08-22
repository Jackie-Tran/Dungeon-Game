package com.dungeongame.mobs;

import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Input;
import com.mikejack.objects.GameObject;
import com.sun.glass.events.KeyEvent;

public class Player extends GameObject{

    private float moveSpeed = 2;
    private int strength=5, dexterity=5, intelligence=5, luck=5; 
    
    public Player(float x, float y, int width, int height, String tag) {
	super(x, y, width, height, tag);

	
    }
    
    @Override
    public void update(GameContainer gc, float dt) {
	// TODO Auto-generated method stub
	x += velX;
	y += velY;
	keyInput(gc);
	
	//System.out.println("player x: " + x + "\tplayer y: " + y);
	
    }
    
    @Override
    public void render(GameContainer gc) {
	gc.getScreen().fillRect((int) x, (int) y, width, height, 0xff00ff00);
	
	gc.getScreen().drawSprite(sprite, (int)x, (int)y);
    }
    
    private void keyInput(GameContainer gc) {
	Input input = gc.getInput();
	
	// Movement
	if (input.isKey(KeyEvent.VK_W)) {
	    velY = -moveSpeed;
	}
	if (input.isKey(KeyEvent.VK_S)) {
	    velY = moveSpeed;
	}
	if (!input.isKey(KeyEvent.VK_W) && !input.isKey(KeyEvent.VK_S)) {
	    velY = 0;
	}
	
	if (input.isKey(KeyEvent.VK_A)) {
	    velX = -moveSpeed;
	}
	if (input.isKey(KeyEvent.VK_D)) {
	    velX = moveSpeed;
	}
	if (!input.isKey(KeyEvent.VK_A) && !input.isKey(KeyEvent.VK_D)) {
	    velX = 0;
	}
	
    }

   

}
