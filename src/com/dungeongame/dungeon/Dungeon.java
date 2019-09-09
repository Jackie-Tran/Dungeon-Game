package com.dungeongame.dungeon;

import java.util.Random;

import com.dungeongame.game.Camera;
import com.dungeongame.mobs.Player;
import com.dungeongame.mobs.Slime;
import com.dungeongame.ui.PlayerUI;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;
import com.mikejack.gamestate.GameState;
import com.mikejack.gamestate.GameStateManager;
import com.mikejack.objects.Layer;

/*
 * Dungeon is made up 16 rooms layed out in a 4x4 pattern. We choose one of the rooms in the first row to be our starting room
 * then we randomly choose the next room to be to the right, below, or left. We repeat this process until the last row. If
 * at the last row of levels we randomly choose to go down, make this room the exit. If we try to move left or right and we are
 * in one of the edge rooms, make the next room below instead. All the rooms in this path will have openings to the left and right
 * and one to the next room and previous room. Paths outside this critical path may or may not have openings. These will be randomly generated.
 */
public class Dungeon extends GameState {

    // Variables for which way the next critical room will be
    private final int MOVE_UP = 0, MOVE_RIGHT = 1, MOVE_DOWN = 2, MOVE_LEFT = 3;

    public static DungeonContent content;

    private Camera camera;
    private Layer objects;
    private Room rooms[];


    private Player player;
    private PlayerUI playerUI;

    private int startRoom = 0, endRoom = 0;

    public Dungeon(GameStateManager gsm) {
	super(gsm);
	content = new DungeonContent();
	objects = new Layer();
	rooms = new Room[16];
    }

    public void init() {

	player = new Player(100, 100, Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT, "player", objects);
	
	// Initialize the rooms
	for (int i = 0; i < rooms.length; i++) {
	    rooms[i] = new Room((i % 4) * Room.WIDTH, (i / 4) * Room.TILE_SIZE * (Room.HEIGHT / Room.TILE_SIZE), false,
		    false, false, false);
	}
	generateDungeon();
	// Load player and camera
	int startX = rooms[startRoom].getX() + Room.WIDTH / 2 - Player.PLAYER_WIDTH / 2;
	int startY = rooms[startRoom].getY() + Room.HEIGHT / 2 - Player.PLAYER_HEIGHT / 2;
	camera = new Camera(player);
	objects.addObject(new Slime(100, 100, "enemy", objects, player));
	
	playerUI = new PlayerUI(player);
    }

    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	player.update(gc);
	objects.update(gc);
	camera.update(gc);
	playerUI.update(gc);
    }

    public void render(GameContainer gc, Screen screen) {
	// TODO Auto-generated method stub
	// screen.fillRect(0, 0, Room.WIDTH*4, Room.HEIGHT*4, 0xff25131a);
	objects.render(gc);
	player.render(gc);
	playerUI.render(gc);
    }

    private void generateDungeon() {
	// Generate outside objects

	generateCriticalPath();
	generateOutsidePath();

	fixDungeonBorder();
	fixDeadOpenings();

	// Create the rooms
	for (int i = 0; i < rooms.length; i++) {
	    rooms[i].createRoom(objects);
	}
	
	for (int i = 0; i < rooms.length; i++) {
	    rooms[i].addEnemies(objects, player);;
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
		    if (random.nextInt(2) == 0) {
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

    private void fixDungeonBorder() {
	// Check border rooms to see if there's an opening going no where
	// Top rooms
	for (int i = 0; i < 4; i++) {
	    if (rooms[i].isOpenUp())
		rooms[i].setOpenUp(false);
	}
	// Bottom rooms
	for (int i = 12; i < rooms.length; i++) {
	    if (rooms[i].isOpenDown())
		rooms[i].setOpenDown(false);
	}
	// Left rooms
	for (int i = 0; i < rooms.length; i += 4) {
	    if (rooms[i].isOpenLeft())
		rooms[i].setOpenLeft(false);
	}
	// Right rooms
	for (int i = 3; i < rooms.length; i += 4) {
	    if (rooms[i].isOpenRight())
		rooms[i].setOpenRight(false);
	}
    }

    private void fixDeadOpenings() {
	// Fix openings leading no where
	for (int i = 0; i < rooms.length; i++) {
	    int up = i - 4, right = i + 1, down = i + 4, left = i - 1;
	    // Make sure the indexes are in the bounds
	    if (rooms[i].isOpenUp() && (up >= 0 && up < rooms.length) && !rooms[up].isOpenDown()) {
		rooms[i].setOpenUp(false);
	    }
	    if (rooms[i].isOpenRight() && (right >= 0 && right < rooms.length) && !rooms[right].isOpenLeft()) {
		rooms[i].setOpenRight(false);
	    }
	    if (rooms[i].isOpenDown() && (down >= 0 && down < rooms.length) && !rooms[down].isOpenUp()) {
		rooms[i].setOpenDown(false);
	    }
	    if (rooms[i].isOpenLeft() && (left >= 0 && left < rooms.length) && !rooms[left].isOpenRight()) {
		rooms[i].setOpenLeft(false);
	    }
	}
    }

    public Layer getObjects() {
	return objects;
    }

    public void setObjects(Layer objects) {
	this.objects = objects;
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
