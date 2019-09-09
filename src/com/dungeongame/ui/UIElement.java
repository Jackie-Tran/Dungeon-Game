package com.dungeongame.ui;

import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;

public abstract class UIElement {

    protected float x, y;
    
    public UIElement (float x, float y) {
	this.x = x;
	this.y = y;
    }
    
    public abstract void update(GameContainer gc);
    
    public abstract void render(GameContainer gc);

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
