package com.mdud.dengine.map.blocks;

import com.mdud.dengine.graphics.Vector2D;

import java.awt.image.BufferedImage;

public class NormalBlock extends Block {
    public NormalBlock(int blockId, int blockSize, Vector2D blockPosition, BufferedImage blockSprite) {
        super(blockId, blockSize, blockPosition, blockSprite);
    }

    @Override
    public Block copy() {
        return new NormalBlock(blockId, blockSize, blockPosition, blockSprite);
    }
}
