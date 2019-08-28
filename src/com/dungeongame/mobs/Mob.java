package com.dungeongame.mobs;

import com.mikejack.graphics.Sprite;
import com.mikejack.objects.GameObject;
import com.mikejack.objects.Layer;

public abstract class Mob extends GameObject{

    protected Layer objects;

    protected float moveSpeed;
    protected float collisionX, collisionY;
    protected int collisionWidth, collisionHeight;
    
    public Mob(float x, float y, int width, int height, String tag, Layer objects, Sprite sprite) {
	super(x, y, width, height, tag, sprite);
	this.objects = objects;
	collisionX = x;
	collisionY = y;
	collisionWidth = width;
	collisionHeight = height/2;
    }

    public Mob(float x, float y, int width, int height, String tag, Layer objects) {
	super(x, y, width, height, tag);
	this.objects = objects;
	collisionX = x;
	collisionY = y;
	collisionWidth = width;
	collisionHeight = height/2;
    }
    
    protected void move() {
	if (!collision(velX, 0)) {
	    x += velX;
	    collisionX = x;
	}
	
	if (!collision(0, velY)) {
	    y += velY;
	    collisionY = y;
	}
    }
    
    protected abstract boolean collision(float velX, float velY);


}
