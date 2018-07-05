package com.mdud.dengine.graphics.font;

import com.mdud.dengine.graphics.Sprite;
import com.mdud.dengine.graphics.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImageOp;

public class Font {
    private Sprite fontSprite;
    private BufferedImageOp filter;

    public Font(String file) {
        fontSprite = new Sprite(file);
        filter = null;
    }

    public Font(String file, int charSize) {
        fontSprite = new Sprite(file, charSize);
        filter = null;
    }

    public void drawChar(Graphics2D graphics2D, char character, Vector2D pos) {
        fontSprite.drawSprite(graphics2D, (int) character - 32, pos, filter);
    }

    public void drawString(Graphics2D graphics2D, String string, Vector2D pos) {
        Vector2D posCopy = pos.copyVector();
        for(int i = 0; i < string.length(); i++) {
            drawChar(graphics2D, string.charAt(i), posCopy);
            posCopy.setX(posCopy.getX() + fontSprite.getSpriteSize());
        }
    }

    public void drawString(Graphics2D graphics2D, String string, Vector2D pos, int offset) {
        Vector2D posCopy = pos.copyVector();
        for(int i = 0; i < string.length(); i++) {
            drawChar(graphics2D, string.charAt(i), posCopy);
            posCopy.setX(posCopy.getX() + offset);
        }
    }

    public void setFilter(BufferedImageOp filter) {
        this.filter = filter;
    }

    public Sprite getFontSprite() {
        return fontSprite;
    }
}
