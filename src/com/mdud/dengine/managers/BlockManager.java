package com.mdud.dengine.managers;

import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.map.TileSet;
import com.mdud.dengine.map.blocks.*;

import java.util.ArrayList;
import java.util.HashMap;


//Block Factory

public class BlockManager {
    private ArrayList<Block> blocksList;
    private ArrayList<Integer> collisionBlocksId;
    private ArrayList<Integer> actionBlocksId;
    private HashMap<Vector2D, Vector2D> actionBlocksPos;
    private TileSet tileSet;

    public BlockManager() {
        tileSet = new TileSet("sprites/overworld.png", 16, 48);

        blocksList = new ArrayList<>();
        collisionBlocksId = new ArrayList<>();
        actionBlocksId = new ArrayList<>();
        actionBlocksPos = new HashMap<>();

        addCollisionIds();
        addActionIds();
        actionBlocksInitializer();
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
        collisionBlocksId.add(830);
    }

    private void addActionIds() {
        actionBlocksId.add(831);
    }

    private void actionBlocksInitializer() {
        actionBlocksPos.put(new Vector2D( 31 * getTileSize(), 14 * getTileSize() ),
                new Vector2D(48 * getTileSize(), 30 * getTileSize()));
        actionBlocksPos.put(new Vector2D( 46 * getTileSize(), 31 * getTileSize() ),
                new Vector2D(31 * getTileSize(), 16 * getTileSize()));
    }

    public Block createBlock(int id, Vector2D position) {
        Block newBlock = blocksList.get(id).copy();
        newBlock.setBlockPosition(position);
        newBlock.onCreate();
        if(actionBlocksId.contains(id))
            actionBlocks(newBlock, position);
        return newBlock;
    }

    private void actionBlocks(Block actionBlock, Vector2D pos) {
        ActionBlock aBlock = (ActionBlock) actionBlock;

        aBlock.setTpPos(actionBlocksPos.get(pos));
    }

    private int getTileSize() {
        return tileSet.getTileSize();
    }


}
