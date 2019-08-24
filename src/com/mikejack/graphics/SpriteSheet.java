package com.mikejack.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

    private BufferedImage spriteSheet = null;
    
    public SpriteSheet(String path) {
	try {
	    spriteSheet = ImageIO.read(Image.class.getResourceAsStream(path));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
    }
    
    public Sprite grabSprite(int x, int y, int width, int height) {
	Sprite sprite;
	int pixels[];
	BufferedImage subImage = spriteSheet.getSubimage(x * width, y * height, width, height);
	pixels = subImage.getRGB(0, 0, width, height, null, 0, width);
	sprite = new Sprite(pixels, subImage.getWidth(), subImage.getHeight());
	return sprite;
    }
    
}
