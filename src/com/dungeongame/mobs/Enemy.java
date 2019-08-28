package com.dungeongame.mobs;

import com.mikejack.objects.Layer;

public abstract class Enemy extends Mob{

	protected Player player;
	protected float health, damage;
	
	// Detection variables
	protected int detectRadius;
	protected int xc, yc;
	
	public Enemy(float x, float y, int width, int height, String tag, Layer objects, Player player) {
		super(x, y, width, height, tag, objects);
		this.player = player;
		xc = (int) (x + width/2);
		yc = (int) (y + height/2);
	}

	@Override
	protected boolean collision(float velX, float velY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected boolean checkPlayerInRange() {
		int pcx = (int) (player.getX() + player.getWidth()/2);
		int pcy = (int) (player.getY() + player.getHeight()/2);
		int a = (int) Math.abs(pcx - xc);
		int b = (int) Math.abs(pcy - yc);
		int c = (int) Math.sqrt(a*a + b*b);
		if (c <= detectRadius) {
			// Player is in range
			return true;
		}
		return false;
	}

}
