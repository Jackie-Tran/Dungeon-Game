package com.dungeongame.game;

import com.dungeongame.dungeon.Dungeon;
import com.dungeongame.dungeon.Room;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.objects.GameObject;

public class Camera {

    private float x,y;
    private GameObject target;
    
    public Camera(GameObject target) {
	this.target = target;
	x = (target.getX() + target.getWidth()/2) - DungeonGame.WIDTH / 2;
	y = (target.getY() + target.getHeight()/2) - DungeonGame.HEIGHT / 2;
    }
    
    public void update(GameContainer gc, float dt) {
	if (target == null)
	    return;
	
	float targetX = (target.getX() + target.getWidth()/2) - DungeonGame.WIDTH / 2;
	float targetY = (target.getY() + target.getHeight()/2) - DungeonGame.HEIGHT / 2;
	
	x += dt * (targetX - x) * 10;
	y += dt * (targetY - y) * 10;
	
	// Bounding the camera to the map size
	if (x < 0) {
	    x = 0;
	} else if (x > 3*Room.WIDTH) {
	    x = 3*Room.WIDTH;
	}
	
	if (y < 0) {
	    y = 0;
	} else if (y > 3*Room.HEIGHT) {
	    y = 3*Room.HEIGHT;
	}
    }
    
    public void render(Screen screen) {
	screen.setCamX((int) x);
	screen.setCamY((int) y); 
    }
}
