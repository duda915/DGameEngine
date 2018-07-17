package com.mdud.dengine.map.blocks;

import com.mdud.dengine.entity.Entity;
import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.managers.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {
    protected int blockId;
    protected int blockSize;
    protected Vector2D blockPosition;
    protected BufferedImage blockSprite;

    public Block(int blockId, int blockSize, Vector2D blockPosition, BufferedImage blockSprite) {
        this.blockId = blockId;
        this.blockPosition = blockPosition;
        this.blockSprite = blockSprite;
        this.blockSize = blockSize;
    }

    public void draw(Graphics2D graphics) {
        graphics.drawImage(blockSprite, null, (int) blockPosition.getWorldX(), (int) blockPosition.getWorldY());
    }

    public void action(Entity entity){}

    public void setBlockPosition(Vector2D blockPosition) {
        this.blockPosition = blockPosition;
    }

    public void onCreate() {}

    public abstract Block copy();
}
