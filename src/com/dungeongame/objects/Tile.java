package com.dungeongame.objects;

import com.mikejack.engine.GameContainer;
import com.mikejack.graphics.Light;
import com.mikejack.graphics.Sprite;
import com.mikejack.objects.GameObject;

public class Tile extends GameObject {

    public Tile(float x, float y, int width, int height, String tag) {
	super(x, y, width, height, tag);
    }

    public Tile(float x, float y, int width, int height, String tag, Sprite sprite) {
	super(x, y, width, height, tag, sprite);
    }

    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub

    }

    @Override
    public void render(GameContainer gc) {
	if (sprite == null) {
	    if (tag.equals("solid")) {
		gc.getScreen().drawRect((int) x, (int) y, width, height, 0xffff0000);
	    } else {
		gc.getScreen().drawRect((int) x, (int) y, width, height, 0xff00ff00);
	    }
	} else {
	    gc.getScreen().drawSprite(sprite, (int) x, (int) y, false, false); 
	}

    }

}
