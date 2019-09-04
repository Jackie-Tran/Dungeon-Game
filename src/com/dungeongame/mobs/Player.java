package com.dungeongame.mobs;

import com.dungeongame.weapon.Sword;
import com.dungeongame.weapon.Weapon;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Input;
import com.mikejack.graphics.Sprite;
import com.mikejack.graphics.SpriteSheet;
import com.mikejack.objects.GameObject;
import com.mikejack.objects.Layer;
import com.sun.glass.events.KeyEvent;

public class Player extends Mob {

    public static final int PLAYER_WIDTH = 16, PLAYER_HEIGHT = 22;
    private SpriteSheet playerSprites;
    
    private float moveSpeed = 1.5f;
    private int strength = 5, dexterity = 5, intelligence = 5, luck = 5;

    private float collisionX, collisionY;
    private int collisionWidth, collisionHeight;

    // Weapon Variables
    Weapon weapon = null;
    
    // Animations/Graphics
    private PlayerState currentState = PlayerState.idle;
    private final int SPRITE_WIDTH = 16, SPRITE_HEIGHT = 22;
    private Sprite idleSprites[];
    private Sprite runningSprites[];
    private int dir=1;
    private int animClock = 0;
    private int frame = 0;

    private enum PlayerState {
	idle(), running();
    }

    public Player(float x, float y, int width, int height, String tag, Layer objects) {
	super(x, y, width, height, tag, objects);
	health = 50;
	
	
	// Collision variables
	collisionX = x;
	collisionY = y;

	collisionWidth = width-3;
	collisionHeight = height/2-3;

	// Weapon
	weapon = new Sword(this);
	
	// Loading sprites
	playerSprites = new SpriteSheet("/player/player_spritesheet.png");
	idleSprites = new Sprite[4];
	runningSprites = new Sprite[4];

	idleSprites[0] = playerSprites.grabSprite(0, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
	idleSprites[1] = playerSprites.grabSprite(1, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
	idleSprites[2] = playerSprites.grabSprite(2, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
	idleSprites[3] = playerSprites.grabSprite(3, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
	
	runningSprites[0] = playerSprites.grabSprite(0, 1, SPRITE_WIDTH, SPRITE_HEIGHT);
	runningSprites[1] = playerSprites.grabSprite(1, 1, SPRITE_WIDTH, SPRITE_HEIGHT);
	runningSprites[2] = playerSprites.grabSprite(2, 1, SPRITE_WIDTH, SPRITE_HEIGHT);
	runningSprites[3] = playerSprites.grabSprite(3, 1, SPRITE_WIDTH, SPRITE_HEIGHT);

    }

    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	keyInput(gc);
	move();
	
	// Animation Clock
	if (animClock > 5) {
	    animClock = 0;
	    if (frame >= 3) {
		frame = 0;
	    } else {
		frame++;
	    }
	} else {
	    animClock++;
	}

	((Sword) weapon).update();
	// System.out.println("player x: " + x + "\tplayer y: " + y);

    }

    @Override
    public void render(GameContainer gc) {

	if (currentState.equals(PlayerState.idle)) {
	    if (dir == 1) {
		gc.getScreen().drawSprite(idleSprites[frame], (int) x, (int) y, false, false);
	    } else {
		gc.getScreen().drawSprite(idleSprites[frame], (int) x, (int) y, false, true);
	    }
	} else if (currentState.equals(PlayerState.running)) {
	    if (dir == 1) {
		gc.getScreen().drawSprite(runningSprites[frame], (int) x, (int) y, false, false);
	    } else {
		gc.getScreen().drawSprite(runningSprites[frame], (int) x, (int) y, false, true);
	    }
	}
	((Sword) weapon).render(gc);
//	gc.getScreen().drawRect((int) collisionX, (int) collisionY, collisionWidth, collisionHeight, 0x5aff0000);
//	gc.getScreen().fillRect((int) x, (int) y, 16, 16, 0x5aff0000);
    }

    protected void move() {
	if (velX != 0 || velY != 0) {
	    currentState = PlayerState.running;
	    if (velX < 0) {
		dir = 3;
	    } else if (velX > 0) {
		dir = 1;
	    }
	} else {
	    currentState = PlayerState.idle;
	}

	if (!collision(velX, 0)) {
	    x += velX;
	    collisionX = x + 2;
	}

	if (!collision(0, velY)) {
	    y += velY;
	    collisionY = y + collisionHeight + 7;
	}
    }

    protected boolean collision(float velX, float velY) {
	// Checks to see if the next tile we are going to move on is a
	for (GameObject object : objects.getGameObjects()) {
	    float nextX = collisionX + velX;
	    float nextY = collisionY + velY;
	    if (object.getTag().equals("wall")) {
		if (nextX < object.getX() + object.getWidth() && nextX + collisionWidth > object.getX()
			&& nextY < object.getY() + object.getHeight() && nextY + collisionHeight > object.getY()) {
		    return true;
		}
	    }
	}
	return false;
    }

    private void keyInput(GameContainer gc) {
	Input input = gc.getInput();

	// Movement
	if (input.isKey(KeyEvent.VK_W)) {
	    velY = -moveSpeed;
	}
	if (input.isKey(KeyEvent.VK_S)) {
	    velY = moveSpeed;
	}
	if (!input.isKey(KeyEvent.VK_W) && !input.isKey(KeyEvent.VK_S)) {
	    velY = 0;
	}

	if (input.isKey(KeyEvent.VK_A)) {
	    velX = -moveSpeed;
	}
	if (input.isKey(KeyEvent.VK_D)) {
	    velX = moveSpeed;
	}
	if (!input.isKey(KeyEvent.VK_A) && !input.isKey(KeyEvent.VK_D)) {
	    velX = 0;
	}
	
	if (input.isKey(KeyEvent.VK_J)) {
	    weapon.use(objects, "enemy");
	}

    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

}
