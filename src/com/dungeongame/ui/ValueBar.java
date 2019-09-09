package com.dungeongame.ui;

import com.dungeongame.game.Camera;
import com.dungeongame.game.DungeonGame;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.objects.GameObject;

public class ValueBar extends UIElement{

    private GameObject parent;
    private int width, height;
    private float value = 0;
    private int colour;
    
    public ValueBar(float x, float y, int width, int height, int colour, GameObject parent) {
	super(x, y);
	this.width = width;
	this.height = height;
	this.colour = colour;
	this.parent = parent;
    }

    @Override
    public void update(GameContainer gc) {
	float screenX = gc.getScreen().getCamX();
	float screenY = gc.getScreen().getCamY();
	x += screenX;
	y += screenY;
	
	
    }

    @Override
    public void render(GameContainer gc) {
	// TODO Auto-generated method stub
	gc.getScreen().fillRect((int) x, (int) y, width, height, 0xff828282);
	gc.getScreen().fillRect((int) x, (int) y, (int) value, height, colour);
	gc.getScreen().drawRect((int) x, (int) y, width, height, 0xff000000);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float maxValue, float value) {
        this.value = width * (value/maxValue);
    }
    
    

    
    
}
