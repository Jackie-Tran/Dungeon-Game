package com.mikejack.engine;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.dungeongame.game.DungeonGame;

public class GameApplet extends Applet{
    
    private GameContainer gc = new GameContainer(DungeonGame.WIDTH, DungeonGame.HEIGHT, DungeonGame.SCALE, DungeonGame.TITLE, new DungeonGame(), true);
    
    public GameApplet() {
	System.out.println("hello");
    }
    
    public void init() {
	Dimension s = new Dimension(DungeonGame.WIDTH * DungeonGame.SCALE, DungeonGame.HEIGHT * DungeonGame.SCALE);
	setPreferredSize(s);
	setMaximumSize(s);
	setMinimumSize(s);
	setSize(s);
	setLayout(new BorderLayout());
	add(gc);
	setVisible(true);
	requestFocus();
    }
    
    public void start() {
	gc.start();
    }
    
    public void stop() {
	gc.stop();
    }
    
}
