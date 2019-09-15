package com.dungeongame.mobs;

import com.mikejack.audio.AudioClip;
import com.mikejack.graphics.Sprite;
import com.mikejack.objects.GameObject;
import com.mikejack.objects.Layer;

public abstract class Mob extends GameObject{

    protected Layer objects;

    // Mob stats
    protected float health, maxHealth, moveSpeed;
    
    protected float collisionX, collisionY;
    protected int collisionWidth, collisionHeight;
    
    protected AudioClip deathSound;
    
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

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public AudioClip getDeathSound() {
        return deathSound;
    }

    public void setDeathSound(AudioClip deathSound) {
        this.deathSound = deathSound;
    }



}
