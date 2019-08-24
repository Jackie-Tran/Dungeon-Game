package com.dungeongame.dungeon;

import java.util.Random;

import com.dungeongame.game.Camera;
import com.dungeongame.mobs.Player;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.gamestate.GameState;
import com.mikejack.gamestate.GameStateManager;
import com.mikejack.graphics.Sprite;
import com.mikejack.objects.Layer;

/*
 * Dungeon is made up 16 rooms layed out in a 4x4 pattern. We choose one of the rooms in the first row to be our starting room
 * then we randomly choose the next room to be to the right, below, or left. We repeat this process until the last row. If
 * at the last row of levels we rnadomly choose to go down, make this room the exit. If we try to move left or right and we are
 * in one of the edge rooms, make the next room below instead. All the rooms in this path will have openings to the left and right
 * and one to the next room and previous room. Paths outside this critical path may or may not have openings. These will be randomly generated.
 */
public class Dungeon extends GameState {

    // Variables for which way the next ciritcal room will be
    private final int MOVE_UP = 0, MOVE_RIGHT = 1, MOVE_DOWN = 2, MOVE_LEFT = 3;

    public static DungeonContent content;

    private Camera camera;
    private Player player;
    private Layer walls;
    private Room rooms[];

    private int startRoom, endRoom;

    public Dungeon(GameStateManager gsm) {
	super(gsm);
	content = new DungeonContent();
	walls = new Layer();
	rooms = new Room[16];
    }

    public void init() {
	// Initialize the rooms
	for (int i = 0; i < rooms.length; i++) {
	    rooms[i] = new Room((i % 4) * Room.WIDTH, (i / 4) * Room.TILE_SIZE * (Room.HEIGHT / Room.TILE_SIZE), false,
		    false, false, false);
	}
	generateDungeon();

	// Load player and camera
	player = new Player(0, 0, 16, 16, "player", walls);
	player.setX(rooms[startRoom].getX() + Room.WIDTH / 2 - player.getWidth() / 2);
	player.setY(rooms[startRoom].getY() + Room.HEIGHT / 2 - player.getHeight() / 2);

	camera = new Camera(player);

    }

    public void update(GameContainer gc) {
	// TODO Auto-generated method stub

	player.update(gc);

	camera.update(gc);
    }

    public void render(GameContainer gc, Screen screen) {
	// TODO Auto-generated method stub
	walls.render(gc);
	player.render(gc);
    }

    private void generateDungeon() {
	generateCriticalPath();
	generateOutsidePath();
	// Create the rooms
	for (int i = 0; i < rooms.length; i++) {
	    rooms[i].createRoom(walls);
	}

    }

    private void generateCriticalPath() {
	Random random = new Random();
	// Choose a start room in the first row
	startRoom = random.nextInt(4);
	int currentRoom = startRoom;
	int prevDir = 0;
	int nextDir = 0;
	System.out.println("Start Room: " + startRoom);
	while (currentRoom < rooms.length) {

	    System.out.println(currentRoom);
	    rooms[currentRoom].setOpenLeft(true);
	    rooms[currentRoom].setOpenRight(true);

	    // Choosing the next room while avoiding going to the previous room
	    do {
		nextDir = random.nextInt(3) + 1;
	    } while (nextDir == prevDir);

	    if (nextDir == MOVE_RIGHT) {
		prevDir = MOVE_LEFT;
	    } else if (nextDir == MOVE_LEFT) {
		prevDir = MOVE_RIGHT;
	    }

	    if (nextDir == MOVE_RIGHT) {
		// If we are in the right column of rooms move down instead
		if ((currentRoom + 1) % 4 == 0) {
		    if (currentRoom == rooms.length - 1) {
			// Case we are in the bottom right room
			endRoom = currentRoom;
			break;
		    }
		    rooms[currentRoom].setOpenDown(true);
		    currentRoom += 4;
		    rooms[currentRoom].setOpenUp(true);
		    continue;
		}
		currentRoom++;
	    } else if (nextDir == MOVE_DOWN) {
		// If we are in the bottom row of rooms make the current room the end room
		if (currentRoom >= 12) {
		    endRoom = currentRoom;
		    break;
		}
		rooms[currentRoom].setOpenDown(true);
		currentRoom += 4;
		rooms[currentRoom].setOpenUp(true);
	    } else { // Moving left
		// If we are in the left column of rooms move down instead
		if (currentRoom % 4 == 0) {
		    if (currentRoom == rooms.length - 4) {
			// Case we are in the bottom left room
			endRoom = currentRoom;
			break;
		    }
		    rooms[currentRoom].setOpenDown(true);
		    currentRoom += 4;
		    rooms[currentRoom].setOpenUp(true);
		    continue;
		}
		currentRoom--;
	    }
	}
    }

    private void generateOutsidePath() {
	Random random = new Random();

	for (int i = 0; i < rooms.length; i++) {
	    if (rooms[i].isBlocked()) {
		for (int j = 0; j < 4; j++) {
		    if (random.nextInt(3) == 0) {
			if (j == 0) {
			    rooms[i].setOpenUp(true);
			} else if (j == 1) {
			    rooms[i].setOpenRight(true);
			} else if (j == 2) {
			    rooms[i].setOpenDown(true);
			} else {
			    rooms[i].setOpenLeft(true);
			}
		    }
		}
	    }
	}
    }

    public Layer getWalls() {
	return walls;
    }

    public void setWalls(Layer walls) {
	this.walls = walls;
    }

    @Override
    public void keyPressed(int arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(int arg0) {
	// TODO Auto-generated method stub

    }

}
