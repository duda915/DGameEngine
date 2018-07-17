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
        tileSet = new TileSet("sprites/tilesformatted.png", 16, 48);

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
        collisionBlocksId.add(164);
    }

    private void addActionIds() {
        actionBlocksId.add(165);
    }

    private void actionBlocksInitializer() {
        actionBlocksPos.put(new Vector2D( 23 * getTileSize(), 20 * getTileSize() ),
                new Vector2D(35 * getTileSize(), 18 * getTileSize()));
        actionBlocksPos.put(new Vector2D( 34 * getTileSize(), 17 * getTileSize() ),
                new Vector2D(22 * getTileSize(), 21 * getTileSize()));
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
