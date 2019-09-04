package com.dungeongame.weapon;

import com.mikejack.engine.GameContainer;
import com.mikejack.objects.GameObject;
import com.mikejack.objects.Layer;

public abstract class Weapon {

    // Animation and active time variables
    protected boolean canUse = true;
    protected int timer = 0;
    protected int cooldown = 40;
    
    protected GameObject parent;
    // Position of where the weapon will be used
    protected float x, y;

    public Weapon(GameObject parent) {
	this.parent = parent;
    }
    
    public abstract void update();
    
    public abstract void render(GameContainer gc);
    
    public abstract void use(Layer objects, String targetTag);
    
    
    
}
