package com.dungeongame.ui;

import com.mikejack.engine.GameContainer;
import com.mikejack.engine.Input;
import com.mikejack.graphics.Font;
import com.mikejack.graphics.Sprite;
import com.sun.glass.events.MouseEvent;

import javafx.scene.input.MouseButton;

public class Button extends UIElement {

    private int width, height;
    private Sprite image = null;
    private boolean clicked = false;
    private String text;

    public Button(float x, float y, int width, int height, String text, String path) {
	super(x, y);
	this.width = width;
	this.height = height;
	this.text = text;
	if (path != "" || path != null)
	    this.image = new Sprite(path);
    }

    @Override
    public void update(GameContainer gc) {
	// TODO Auto-generated method stub
	Input input = gc.getInput();
	clicked = false;
	if (input.getMouseX() > x && input.getMouseX() < x + width && input.getMouseY() > y
		&& input.getMouseY() < y + height) {
	    // Mouse is in the button
	    if (input.isButton(1)) {
		clicked = true;
	    }
	}
    }

    @Override
    public void render(GameContainer gc) {
	// TODO Auto-generated method stub
	if (image != null) {
	    gc.getScreen().drawSprite(image, (int) x, (int) y, false, false);
	} else {
	    gc.getScreen().fillRect((int) x, (int) y, width, height, 0xffff00ff);
	    gc.getScreen().drawText(text, (int) x, (int) y, 0xffffffff, Font.STANDARD);
	}

    }

    public boolean isClicked() {
	return clicked;
    }

    public int getWidth() {
	return width;
    }

    public void setWidth(int width) {
	this.width = width;
    }

    public int getHeight() {
	return height;
    }

    public void setHeight(int height) {
	this.height = height;
    }

}
