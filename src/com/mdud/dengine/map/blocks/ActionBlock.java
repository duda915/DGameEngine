package com.mdud.dengine.map.blocks;

import com.mdud.dengine.entity.Entity;
import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.utility.collision.BoundingBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class ActionBlock extends Block {
    protected BoundingBox blockBox;


    public ActionBlock(int blockId, int blockSize, Vector2D blockPosition, BufferedImage blockSprite) {
        super(blockId, blockSize, blockPosition, blockSprite);
        this.blockBox = new BoundingBox(blockPosition, blockSize);

    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.YELLOW);
        blockBox.drawBoundingBox(graphics);
    }

    @Override
    public void action(Entity entity) {

        // Not collision snippet
        if(!blockBox.collides(entity.getCollisionBox()))
            return;
    }

    @Override
    public void onCreate() {
        updateBoundingBox();
    }

    public void updateBoundingBox() {
        blockBox.updateBox(blockPosition);
    }


}
