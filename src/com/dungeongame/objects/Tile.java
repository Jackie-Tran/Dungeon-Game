package com.dungeongame.objects;

import com.dungeongame.dungeon.Dungeon;
import com.mikejack.engine.GameContainer;
import com.mikejack.objects.GameObject;

public class Tile extends GameObject {

    private boolean isSolid = false;
    
    public Tile(float x, float y, int width, int height, boolean isSolid, String tag) {
	super(x, y, width, height, tag);
	this.isSolid = isSolid;
	sprite = Dungeon.content.topWall1;
	
    }

    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	
    }
    
    @Override
    public void render(GameContainer gc) {
//	gc.getScreen().fillRect((int) x, (int) y, width, height, 0xff3b3936);
	if (isSolid)
	    gc.getScreen().drawRect((int) x, (int) y, width, height, 0xffff0000);
	gc.getScreen().drawSprite(sprite, (int) x, (int) y); 
	
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    

}
