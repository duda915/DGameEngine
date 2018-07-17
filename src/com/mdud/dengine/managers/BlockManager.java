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
    private ArrayList<Integer> teleportBlocksId;
    private HashMap<Vector2D, Vector2D> teleportBlockPos;
    private TileSet tileSet;

    public BlockManager() {
        tileSet = new TileSet("sprites/overworld.png", 16, 48);

        blocksList = new ArrayList<>();
        collisionBlocksId = new ArrayList<>();
        teleportBlocksId = new ArrayList<>();
        teleportBlockPos = new HashMap<>();

        //configuration methods
        addCollisionIds();
        addTeleportIds();
        teleportBlocksInitializer();
        addBlocks();

    }

    private void addBlocks() {
        for(int i = 0; i < tileSet.getTileCount(); i++) {
            if(collisionBlocksId.contains(i))
                blocksList.add(new CollisionBlock(i, tileSet.getTileSize(), new Vector2D(0,0), tileSet.getTile(i)));
            else if(teleportBlocksId.contains(i))
                blocksList.add(new TeleportBlock(i, tileSet.getTileSize(), new Vector2D(0, 0), tileSet.getTile(i)));
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

    private void addTeleportIds() {
        teleportBlocksId.add(831);
    }

    private void teleportBlocksInitializer() {
        teleportBlockPos.put(new Vector2D( 31 * getTileSize(), 14 * getTileSize() ),
                new Vector2D(87 * getTileSize(), 11 * getTileSize()));
        teleportBlockPos.put(new Vector2D( 87 * getTileSize(), 8 * getTileSize() ),
                new Vector2D(31 * getTileSize(), 16 * getTileSize()));
    }

    public Block createBlock(int id, Vector2D position) {
        Block newBlock = blocksList.get(id).copy();
        newBlock.setBlockPosition(position);
        newBlock.onCreate();
        if(teleportBlocksId.contains(id))
            teleportBlocks(newBlock, position);
        return newBlock;
    }

    private void teleportBlocks(Block teleportBlock, Vector2D pos) {
        TeleportBlock tBlock = (TeleportBlock) teleportBlock;
        tBlock.setTpPos(teleportBlockPos.get(pos));
    }

    private int getTileSize() {
        return tileSet.getTileSize();
    }


}
