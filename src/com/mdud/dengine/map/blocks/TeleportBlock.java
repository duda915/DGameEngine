package com.mdud.dengine.map.blocks;

import com.mdud.dengine.entity.Entity;
import com.mdud.dengine.graphics.Vector2D;

import java.awt.image.BufferedImage;

public class TeleportBlock extends ActionBlock {
    private Vector2D tpPos;

    public TeleportBlock(int blockId, int blockSize, Vector2D blockPosition, BufferedImage blockSprite) {
        super(blockId, blockSize, blockPosition, blockSprite);
        tpPos = new Vector2D(0,0);
    }

    @Override
    public void action(Entity entity) {
        if(!blockBox.collides(entity.getCollisionBox()))
            return;
        entity.getPosition().setPosition(tpPos.getX(), tpPos.getY());
        entity.getCollisionBox().updateBox(tpPos);
    }

    public void setTpPos(Vector2D pos) {
        tpPos = pos;
    }

    @Override
    public Block copy() {
        return new TeleportBlock(blockId, blockSize, blockPosition, blockSprite);
    }
}
