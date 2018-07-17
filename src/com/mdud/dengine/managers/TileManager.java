package com.mdud.dengine.managers;

import com.mdud.dengine.entity.Entity;
import com.mdud.dengine.graphics.Vector2D;
import com.mdud.dengine.map.MapLoader;
import com.mdud.dengine.map.blocks.Block;

import java.awt.*;
import java.util.ArrayList;

public class TileManager {
    private BlockManager blockManager;
    private ArrayList<ArrayList<Integer>> map;
    private ArrayList<Block> specialBlocks;
    private ArrayList<Block> blocks;
    private ArrayList<Block> blocksLateRender;
    private MapLoader mapLoader;
    private int squareSize;

    public TileManager(BlockManager blockManager) {
        this.blockManager = blockManager;
        blocks = new ArrayList<>();
        specialBlocks = new ArrayList<>();
        blocksLateRender = new ArrayList<>();

        squareSize = blockManager.getTileSet().getTileSize();

        mapLoader = new MapLoader("resources/maps/dgedemo.tmx");
        loadMap(mapLoader);
    }

    public void loadMap(MapLoader mapLoader) {
        /*
         * FORCED MAP LAYOUT
         * Collision Layer
         * Action Layer
         * Terrain Layer
         * Objects Layer
         * Terrain Overlay Layer
         * Objects Overlay Layer
         *
         * RENDERING ORDER:
         * Terrain Layer
         * Objects Layer
         * --- ENTITIES ---
         * Terrain Overlay Layer
         * Objects Overlay Layer
         * Collision Layer  --- only bounding box
         * Action Layer     --- only bounding box
         *
         */
        map = mapLoader.getMap();

        // Collision + Action Blocks
        populateBlocks(0, 2, specialBlocks);

        //Normal Blocks
        populateBlocks(2, 4, blocks);

        //Late Render Blocks
        populateBlocks(4, 6, blocksLateRender);

    }

    private void populateBlocks(int layerBegin, int layerEnd, ArrayList<Block> blockList) {
        //layerEnd is exclusive
        for(int i = layerBegin; i < layerEnd; i++) {
            int width = 0;
            int height = 0;

            for(int j = 0; j < map.get(i).size(); j++) {
                width++;
                if(width == mapLoader.getMapWidth()) {
                    width = 1;
                    height++;
                }
                if(map.get(i).get(j) == -1)
                    continue;
                blockList.add(blockManager.createBlock(map.get(i).get(j), new Vector2D(
                        squareSize*width, squareSize*height)
                ));
            }
        }
    }

    public void update(ArrayList<Entity> entities) {
        for(Entity entity : entities)
            for(Block b : specialBlocks)
                b.action(entity);
    }

    public void draw(Graphics2D graphics) {
        for(Block b : blocks)
            b.draw(graphics);
    }

    public void lateDraw(Graphics2D graphics) {
        for (Block b : blocksLateRender)
            b.draw(graphics);

        //TESTING PURPOSE
        for (Block b : specialBlocks)
            b.draw(graphics);
    }
}
