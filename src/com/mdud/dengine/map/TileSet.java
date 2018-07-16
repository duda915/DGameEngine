package com.mdud.dengine.map;

import com.mdud.dengine.graphics.Sprite;

import java.awt.image.BufferedImage;

public class TileSet {
    private Sprite tileSheet;
    private int tileSize;
    private int tileCount;

    public TileSet(String file, int tileSize, int newTileSize) {
        tileSheet = new Sprite(file, tileSize, newTileSize);
        this.tileSize = newTileSize;
        tileCount = tileSheet.getSpritesCount();
    }

    public BufferedImage getTile(int id) {
        return tileSheet.getSprite(id);
    }

    public int getTileCount() {
        return tileCount;
    }

    public int getTileSize() {
        return tileSize;
    }
}
