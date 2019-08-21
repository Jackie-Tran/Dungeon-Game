package com.dungeongame.dungeon;

import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.objects.Layer;

public class Dungeon {

    private Layer walls;
    private Room tempRoom;
    
    public Dungeon() {
	walls = new Layer();
	tempRoom = new Room(0, 0, true, true, true, true);
	init();
    }

    public void init() {
	// TODO Auto-generated method stub
	tempRoom.createRoom(walls);
	
    }
    
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	
    }

    public void render(GameContainer gc, Screen screen) {
	// TODO Auto-generated method stub
	walls.render(gc);
    }

    public Layer getWalls() {
        return walls;
    }

    public void setWalls(Layer walls) {
        this.walls = walls;
    }


    
    
}
