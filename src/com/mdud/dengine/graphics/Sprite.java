package com.mdud.dengine.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

public class Sprite {
    private BufferedImage spriteSheet = null;
    private BufferedImage[][] spriteArray;

    // Size of one image in sheet spriteSize X spriteSize
    private int spriteSize = 32;

    private int spriteSheetWidth;
    private int spriteSheetHeight;
    private int spriteArrayRows;
    private int spriteArrayCols;

    public Sprite(String file) {
        loadSpriteSheet(file);
        loadSpriteArray();
    }

    public Sprite(String file, int spriteSize) {
        this.spriteSize = spriteSize;
        loadSpriteSheet(file);
        loadSpriteArray();
    }

    private void loadSpriteSheet(String file) {
        try {
            spriteSheet = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (Exception e) {
            System.out.println("Image loading error: " + file);
            e.printStackTrace();
        }
    }

    private void loadSpriteArray() {
        spriteSheetWidth = spriteSheet.getWidth();
        spriteSheetHeight = spriteSheet.getHeight();
        spriteArrayCols = spriteSheetWidth / spriteSize;
        spriteArrayRows = spriteSheetHeight / spriteSize;

        spriteArray = new BufferedImage[spriteArrayRows][spriteArrayCols];

        for(int i = 0 ; i < spriteArrayRows; i++) {
            for(int j = 0; j < spriteArrayCols; j++) {
                spriteArray[i][j] = spriteSheet.getSubimage(j * spriteSize, i * spriteSize, spriteSize, spriteSize);
            }
        }

        System.out.println("Loaded {rows: " + spriteArrayRows + ", cols: " + spriteArrayCols + "}");
    }

    public void drawSpriteSheet(Graphics2D graphics2D, Vector2D pos, BufferedImageOp filter) {
        graphics2D.drawImage(spriteSheet, filter, pos.getX(), pos.getY());
    }

    public void drawSprite(Graphics2D graphics2D, int row, int col, Vector2D pos, BufferedImageOp filter) {
        graphics2D.drawImage(spriteArray[row][col], filter, pos.getX(), pos.getY());
    }

    public void drawSprite(Graphics2D graphics2D, int i, Vector2D pos, BufferedImageOp filter) {
        graphics2D.drawImage(getSprite(i), filter, pos.getX(), pos.getY());
    }

    public void drawSpriteArray(Graphics2D graphics2D, Vector2D pos, int xOffset, int yOffset, BufferedImageOp filter) {
        for(int i = 0 ; i < spriteArrayRows; i++) {
            for(int j = 0; j < spriteArrayCols; j++) {
                graphics2D.drawImage(spriteArray[i][j], filter, pos.getX() + xOffset * j, pos.getY() + yOffset * i);
            }
        }
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public BufferedImage[][] getSpriteArray() {
        return spriteArray;
    }

    public BufferedImage[] getSpriteArrayRow(int row) {
        return spriteArray[row];
    }

    public BufferedImage getSprite(int row, int col) {
        return spriteArray[row][col];
    }

    //For font indexing
    public BufferedImage getSprite(int i) {
        return spriteArray[i / spriteArrayCols][ i % spriteArrayCols];
    }

    public int getSpriteSize() {
        return spriteSize;
    }

    public int getSpriteSheetWidth() {
        return spriteSheetWidth;
    }

    public int getSpriteSheetHeight() {
        return spriteSheetHeight;
    }

    public int getSpriteArrayRows() {
        return spriteArrayRows;
    }

    public int getSpriteArrayCols() {
        return spriteArrayCols;
    }
}
