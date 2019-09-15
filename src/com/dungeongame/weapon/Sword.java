package com.dungeongame.weapon;

import java.util.Random;

import com.dungeongame.mobs.Mob;
import com.dungeongame.mobs.Player;
import com.mikejack.engine.GameContainer;
import com.mikejack.graphics.Sprite;
import com.mikejack.objects.GameObject;
import com.mikejack.objects.Layer;

public class Sword extends Weapon {

    private int width, height;

    private Sprite swingSprite = new Sprite("/weapons/sword/swing.png");

    // Animation and active time variables
    private int activeLength = 15;
    private int activeTimer = 0;

    public Sword(GameObject parent) {
	super(parent);
	width = 17;
	height = 20;
	knockBack = 25;
    }

    public void update() {
	if (((Player) parent).getDir() == 1) {
	    x = parent.getX() + parent.getWidth();
	    y = parent.getY();
	} else if (((Player) parent).getDir() == 3) {
	    x = parent.getX() - parent.getWidth();
	    y = parent.getY();
	}

	// Cooldown timer for weapon
	if (timer < cooldown) {
	    timer++;
	} else {
	    canUse = true;
	}

	// Visual active frames timer (how long the sprite appears on screen)
	if (!canUse) {
	    if (activeTimer < activeLength) {
		activeTimer++;
	    }
	}
    }

    public void render(GameContainer gc) {
	if (activeTimer < activeLength && !canUse) {
	    if (((Player) parent).getDir() == 1) {
		gc.getScreen().drawSprite(swingSprite, (int) x, (int) y, false, false);
	    } else {
		gc.getScreen().drawSprite(swingSprite, (int) x, (int) y, false, true);
	    }
	}
    }

    @Override
    public void use(Layer objects, String targetTag) {

	if (!canUse)
	    return;

	// Get the direction of where to attack
	if (((Player) parent).getDir() == 1) {
	    x = parent.getX() + parent.getWidth();
	    y = parent.getY();
	} else if (((Player) parent).getDir() == 3) {
	    x = parent.getX() - parent.getWidth();
	    y = parent.getY();
	}

	for (int i = 0; i < objects.getGameObjects().size(); i++) {
	    GameObject object = objects.getGameObjects().get(i);

	    if (object.getTag().equals(targetTag)) {
		if (object.getX() < x + width && object.getX() + object.getWidth() > x && object.getY() < y + height
			&& object.getY() + object.getHeight() > y) {

		    applyHit((Mob) object, objects);

		}
	    }

	}

	timer = 0;
	activeTimer = 0;
	canUse = false;

    }

    private void applyHit(Mob target, Layer objects) {
	Random random = new Random();
	int averageDamage = 5; // TEMP
	int damage = random.nextInt(3) + averageDamage;
	target.setHealth(target.getHealth() - damage);

	if (target.getHealth() <= 0) {
	    target.getDeathSound().play();
	    objects.removeObject(target);
	    return;
	}
	
	// Knock back effect
	if (target.getX() > parent.getX()) {
	    target.setX(target.getX() + knockBack);
	} else if (target.getX() < parent.getX()) {
	    target.setX(target.getX() - knockBack); 
	}
    }

}
