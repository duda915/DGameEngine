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
    private ArrayList<Block> blocks;
    private MapLoader mapLoader;
    private int squareSize;

    public TileManager(BlockManager blockManager) {
        this.blockManager = blockManager;
        blocks = new ArrayList<>();
        squareSize = blockManager.getTileSet().getTileSize();

        mapLoader = new MapLoader("resources/maps/testmap.tmx");
        loadMap(mapLoader);
    }

    public void loadMap(MapLoader mapLoader) {
        map = mapLoader.getMap();


        //ugly
        for(int i = 0; i < map.size(); i++) {
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
                blocks.add(blockManager.createBlock(map.get(i).get(j), new Vector2D(
                        squareSize*width, squareSize*height)
                ));
            }
        }

    }

    public void update(ArrayList<Entity> entities) {
        for(Entity entity : entities)
            for(Block b : blocks)
                b.action(entity);
    }

    public void draw(Graphics2D graphics) {
        for(Block b : blocks)
            b.draw(graphics);
    }
}
