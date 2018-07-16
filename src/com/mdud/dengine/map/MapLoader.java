package com.mdud.dengine.map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class MapLoader {
    private int mapWidth;
    private int mapHeight;
    private ArrayList<ArrayList<Integer>> map;

    public MapLoader(String file) {
        getLayers(file);
    }

    private void getLayers(String file) {
        //
        File mapFile = new File(file);
        ArrayList<String> mapContent = new ArrayList<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(mapFile);
            document.getDocumentElement().normalize();

            NodeList dataNodes = document.getElementsByTagName("data");

            for(int i = 0; i < dataNodes.getLength(); i++) {
                Node dataNode = dataNodes.item(i);
                mapContent.add(dataNode.getTextContent());
            }

            //Height, width parsing
            NodeList mapNodeList = document.getElementsByTagName("map");
            Node mapNode = mapNodeList.item(0);
            Element mapNodeElement = (Element) mapNode;
            String width = mapNodeElement.getAttribute("width");
            String height = mapNodeElement.getAttribute("height");

            this.mapHeight = Integer.parseInt(height);
            this.mapWidth = Integer.parseInt(width);

        } catch (Exception e) {
            System.out.println("Map loading error");
            e.printStackTrace();
        }

        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        for(int i = 0; i < mapContent.size(); i++) {
            output.add(new ArrayList<>());
            String[] s = mapContent.get(i).split(",");
            for(String s1 : s) {
                try {
                    output.get(i).add(Integer.parseInt(s1) - 1);
                } catch (NumberFormatException e) {

                }
            }
        }

        map = output;


    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public ArrayList<ArrayList<Integer>> getMap() {
        return map;
    }
}
