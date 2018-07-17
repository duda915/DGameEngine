package com.mdud.dengine.map.blocks;

import com.mdud.dengine.entity.Entity;
import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.managers.TileManager;
import com.mdud.dengine.map.MapLoader;
import com.mdud.dengine.utility.collision.BoundingBox;

import java.awt.image.BufferedImage;

public class ActionBlock extends Block {
    private BoundingBox blockBox;

    public ActionBlock(int blockId, int blockSize, Vector2D blockPosition, BufferedImage blockSprite) {
        super(blockId, blockSize, blockPosition, blockSprite);
        this.blockBox = new BoundingBox(blockPosition, blockSize);
    }

    @Override
    public void action(Entity entity) {
        if(!blockBox.collides(entity.getCollisionBox()))
            return;
        entity.getPosition().incrementPosition(20*blockSize, -10*blockSize);
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
        return new ActionBlock(blockId, blockSize, blockPosition, blockSprite);
    }
}
