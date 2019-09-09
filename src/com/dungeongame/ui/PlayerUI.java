package com.dungeongame.ui;

import com.dungeongame.game.DungeonGame;
import com.dungeongame.mobs.Player;
import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Screen;

public class PlayerUI {

    private Player player;
    
    private ValueBar healthBar;
    private ValueBar xpBar;
    
    public PlayerUI(Player player) {
	this.player = player;
	healthBar = new ValueBar(0, 0, 100, 10, 0xffff0000, player);
	xpBar = new ValueBar(0, 0, 100, 10, 0xffffd359, player);
    }
    
    public void update(GameContainer gc) {
	healthBar.setX(DungeonGame.WIDTH/2 - healthBar.getWidth()/2);
	healthBar.setY( DungeonGame.HEIGHT - healthBar.getHeight()*3);
	xpBar.setX(DungeonGame.WIDTH/2 - healthBar.getWidth()/2);
	xpBar.setY(DungeonGame.HEIGHT - xpBar.getHeight()*2);
	healthBar.update(gc);
	xpBar.update(gc);
	
	if (player.getHealth() >= 0)
	    healthBar.setValue(player.getMaxHealth(), player.getHealth());
	xpBar.setValue(player.getMaxXp(), player.getXp());
    }
    
    public void render(GameContainer gc) {
	healthBar.render(gc);
	xpBar.render(gc);
    }
    
}
