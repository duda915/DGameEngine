package com.mdud.dengine.map.blocks;

import com.mdud.dengine.entity.Entity;
import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.managers.TileManager;
import com.mdud.dengine.utility.collision.BoundingBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CollisionBlock extends Block {
    private BoundingBox blockBox;

    public CollisionBlock(int blockId, int blockSize, Vector2D blockPosition, BufferedImage blockSprite) {
        super(blockId, blockSize, blockPosition, blockSprite);
        this.blockBox = new BoundingBox(blockPosition, blockSize);
    }

    @Override
    public void action(Entity entity) {
        if(blockBox.collides(entity.getCollisionBox()))
            entity.handleCollision(blockBox);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.drawImage(blockSprite, null,(int) blockPosition.getWorldX(), (int) blockPosition.getWorldY());
    }

    @Override
    public void onCreate() {
        updateBoundingBox();
    }

    public void updateBoundingBox() {
        blockBox.updateBox(blockPosition);
    }

    @Override
    public Block copy() {
        return new CollisionBlock(blockId, blockSize, blockPosition, blockSprite);
    }
}
