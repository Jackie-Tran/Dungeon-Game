package com.dungeongame.mobs;

import com.dungeongame.dungeon.Room;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Input;
import com.mikejack.gamestate.GameState;
import com.mikejack.objects.GameObject;
import com.mikejack.objects.Layer;
import com.sun.glass.events.KeyEvent;

public class Player extends GameObject {

    private float moveSpeed = 1.5f;
    private int strength = 5, dexterity = 5, intelligence = 5, luck = 5;
    private Layer objects;
    private float collisionX, collisionY;
    private int collisionWidth, collisionHeight;

    public Player(float x, float y, int width, int height, String tag, Layer objects) {
	super(x, y, width, height, tag);
	this.objects = objects;
	collisionX = x;
	collisionY = y;
	collisionWidth = width;
	collisionHeight = height/2;
    }

    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	x += velX;
	y += velY;

	collisionX = x;
	collisionY = y;
	
	if (x < 0) {
	    x = 0;
	} else if (x + width > Room.WIDTH*4) {
	    x = Room.WIDTH*4 - width;
	}
	
	

	keyInput(gc);

	// System.out.println("player x: " + x + "\tplayer y: " + y);

    }

    @Override
    public void render(GameContainer gc) {
	gc.getScreen().fillRect((int) x, (int) y, width, height, 0xff00ff00);
	gc.getScreen().drawRect((int) collisionX, (int) collisionY, collisionWidth, collisionHeight, 0xffff00ff);
	// gc.getScreen().drawSprite(sprite, (int)x, (int)y);
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
