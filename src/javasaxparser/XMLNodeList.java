/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.util.ArrayList;

/**
 * Essentially just a scaled down Java Bean for the purpose of creating a convenient class 
 *
 * @author Brandon
 */
public class XMLNodeList {
    private ArrayList<XMLNode> objects;
    
    public XMLNodeList() {
        objects = new ArrayList<>();
    }
    
    public ArrayList<XMLNode> getNodes() {
        return objects;
    }
    
    public void addNode(XMLNode obj) {
        objects.add(obj);
    }
}
