package com.mdud.dengine.managers;

import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.map.TileSet;
import com.mdud.dengine.map.blocks.*;

import java.util.ArrayList;


//Block Factory

public class BlockManager {
    private ArrayList<Block> blocksList;
    private ArrayList<Integer> collisionBlocksId;
    private ArrayList<Integer> actionBlocksId;
    private TileSet tileSet;

    public BlockManager() {
        tileSet = new TileSet("sprites/tilesformatted.png", 16, 48);

        blocksList = new ArrayList<>();
        collisionBlocksId = new ArrayList<>();
        actionBlocksId = new ArrayList<>();

        addCollisionIds();
        addActionIds();
        addBlocks();

    }

    private void addBlocks() {
        for(int i = 0; i < tileSet.getTileCount(); i++) {
            if(collisionBlocksId.contains(i))
                blocksList.add(new CollisionBlock(i, tileSet.getTileSize(), new Vector2D(0,0), tileSet.getTile(i)));
            else if(actionBlocksId.contains(i))
                blocksList.add(new ActionBlock(i, tileSet.getTileSize(), new Vector2D(0, 0), tileSet.getTile(i)));
            else
                blocksList.add(new NormalBlock(i, tileSet.getTileSize(), new Vector2D(0,0), tileSet.getTile(i)));
        }
    }

    public TileSet getTileSet() {
        return tileSet;
    }

    private void addCollisionIds() {
        collisionBlocksId.add(164);
    }

    private void addActionIds() {
        actionBlocksId.add(165);
    }

    public Block createBlock(int id, Vector2D position) {
        Block newBlock = blocksList.get(id).copy();
        newBlock.setBlockPosition(position);
        newBlock.onCreate();
        return newBlock;
    }


}
