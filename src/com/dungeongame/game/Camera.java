package com.dungeongame.game;

import com.dungeongame.dungeon.Dungeon;
import com.dungeongame.dungeon.Room;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.objects.GameObject;

public class Camera {

    private float x, y;
    private float maxX, maxY;
    private float targetX, targetY;
    private GameObject target;

    public Camera(GameObject target, float maxX, float maxY) {
	this.target = target;
	this.maxX = maxX;
	this.maxY = maxY;
	x = (target.getX() + target.getWidth() / 2) - DungeonGame.WIDTH / 2;
	y = (target.getY() + target.getHeight() / 2) - DungeonGame.HEIGHT / 2;
    }

    public void update(GameContainer gc) {

	targetX = (target.getX() + target.getWidth() / 2) - DungeonGame.WIDTH / 2;
	targetY = (target.getY() + target.getHeight() / 2) - DungeonGame.HEIGHT / 2;
	x = targetX;
	y = targetY;

	//System.out.println("CamX: " + x + "\tCamY: " + y);
	// Bounding the camera to the map size
	if (x < 0) {
	    x = 0;
	} else if (x >= maxX - DungeonGame.WIDTH) {
	    x = maxX - DungeonGame.WIDTH;
	}

	if (y < 0) {
	    y = 0;
	} else if (y >= maxY - DungeonGame.HEIGHT) {
	    y = maxY - DungeonGame.HEIGHT;
	}
	
	gc.getScreen().setCamX((int) x);
	gc.getScreen().setCamY((int) y);
	
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
