package com.dungeongame.objects;

import com.dungeongame.mobs.Player;
import com.mikejack.graphics.Sprite;
import com.mikejack.objects.GameObject;

public abstract class Interactable extends GameObject{

    protected Player player;
    
    public Interactable(float x, float y, int width, int height, String tag, Player player) {
	super(x, y, width, height, tag);
	this.player = player;
	// TODO Auto-generated constructor stub
    }
    
    public Interactable(float x, float y, int width, int height, String tag, Sprite sprite, Player player) {
	super(x, y, width, height, tag, sprite);
	this.player = player;
    }
    
    public abstract void action();

}
