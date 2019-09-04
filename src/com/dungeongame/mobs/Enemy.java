package com.dungeongame.mobs;

import com.mikejack.objects.Layer;

public abstract class Enemy extends Mob{

	protected Player player;
	protected float damage;
	
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

	public Player getPlayer() {
	    return player;
	}

	public void setPlayer(Player player) {
	    this.player = player;
	}

	public float getDamage() {
	    return damage;
	}

	public void setDamage(float damage) {
	    this.damage = damage;
	}

	public int getDetectRadius() {
	    return detectRadius;
	}

	public void setDetectRadius(int detectRadius) {
	    this.detectRadius = detectRadius;
	}

	public int getXc() {
	    return xc;
	}

	public void setXc(int xc) {
	    this.xc = xc;
	}

	public int getYc() {
	    return yc;
	}

	public void setYc(int yc) {
	    this.yc = yc;
	}

}
