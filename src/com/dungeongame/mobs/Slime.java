package com.dungeongame.mobs;

import com.mikejack.engine.GameContainer;
import com.mikejack.graphics.Sprite;
import com.mikejack.graphics.SpriteSheet;
import com.mikejack.objects.GameObject;
import com.mikejack.objects.Layer;

public class Slime extends Enemy {

    private float moveSpeed = 0.5f;

    private float collisionX, collisionY;
    private int collisionWidth, collisionHeight;

    // Graphics
    private SpriteSheet slimeSprites;
    private final int SPRITE_WIDTH = 16, SPRITE_HEIGHT = 16;
    private Sprite idleSprites[];
    private int dir = 1;
    private int animClock = 0;
    private int frame = 0;

    public Slime(float x, float y, String tag, Layer objects, Player player) {
	super(x, y, 16, 16, tag, objects, player);
	health = 10;
	damage = 2;
	detectRadius = 125;

	// Collision Variables
	collisionWidth = width;
	collisionHeight = height / 2 - 2;
	collisionX = x;
	collisionY = y + height / 2 + 2;

	// Sprites
	slimeSprites = new SpriteSheet("/dungeon/monsters/slime.png");
	idleSprites = new Sprite[4];
	idleSprites[0] = slimeSprites.grabSprite(0, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
	idleSprites[1] = slimeSprites.grabSprite(1, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
	idleSprites[2] = slimeSprites.grabSprite(2, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
	idleSprites[3] = slimeSprites.grabSprite(3, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
    }

    @Override
    public void update(GameContainer gc) {
	
	move();

	// Animation Clock
	if (animClock > 8) {
	    animClock = 0;
	    if (frame >= 3) {
		frame = 0;
	    } else {
		frame++;
	    }
	} else {
	    animClock++;
	}
    }

    @Override
    public void render(GameContainer gc) {
	if (dir == 1) {
	    gc.getScreen().drawSprite(idleSprites[frame], (int) x, (int) y, false, false);
	} else {
	    gc.getScreen().drawSprite(idleSprites[frame], (int) x, (int) y, false, true);
	}
	// gc.getScreen().drawRect((int) collisionX, (int) collisionY, collisionWidth,
	// collisionHeight, 0xff00aa00);
	// gc.getScreen().drawCricle(xc, yc, detectRadius, 0xffff00ff);
    }

    protected void move() {

	xc = (int) (x + width / 2);
	yc = (int) (y + height / 2);

	if (velX < 0) {
	    dir = 3;
	} else if (velX > 0) {
	    dir = 1;
	}

	if (checkPlayerInRange()) {
	    if (xc < player.getX()) {
		velX = moveSpeed;
	    } else if (xc > player.getX() + player.getWidth()) {
		velX = -moveSpeed;
	    } else {
		velX = 0;
	    }

	    if (yc < player.getY()) {
		velY = moveSpeed;
	    } else if (yc > player.getY() + player.getHeight()) {
		velY = -moveSpeed;
	    } else {
		velY = 0;
	    }
	} else {
	    velX = 0;
	    velY = 0;
	    return;
	}

	if (!collision(velX, 0)) {
	    x += velX;
	    collisionX = x;
	}

	if (!collision(0, velY)) {
	    y += velY;
	    collisionY = y + height / 2 + 2;
	}

    }

    protected boolean collision(float velX, float velY) {
	// Checks to see if the next tile we are going to move on is solid
	for (GameObject object : objects.getGameObjects()) {
	    if(object == this) continue;
	    
	    float nextX = collisionX + velX;
	    float nextY = collisionY + velY;
	    if (object.getTag().equals("solid") || object.getTag().equals("enemy") || object.getTag().equals("player")) {
		if (nextX < object.getX() + object.getWidth() && nextX + collisionWidth > object.getX()
			&& nextY < object.getY() + object.getHeight() && nextY + collisionHeight > object.getY()) {
		    return true;
		}
	    }
	}
	return false;
    }

}
