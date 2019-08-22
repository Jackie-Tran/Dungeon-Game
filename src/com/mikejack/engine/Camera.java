package com.mikejack.engine;

import com.mikejack.objects.GameObject;

public class Camera {

    private float x = 0, y = 0;
    private GameObject target;
    
    public Camera(GameObject target) {
	this.target = target;
    }
    
    public void update(GameContainer gc, float dt) {
	if (target == null)
	    return;
	
	x = (target.getX() + target.getWidth()/2) - gc.getWidth() / 2;
	y = (target.getY() + target.getHeight()/2) - gc.getHeight() / 2;
	
	gc.getScreen().setCamX((int) x);
	gc.getScreen().setCamY((int) y);
    }
    
}
