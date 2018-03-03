/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Java BEAAAAAAANNNNNNN
 *
 * @author Brandon
 */
public class XMLNode {
    
    private String title = "";
    private ArrayList<String> data = null;
    
    //Going to use the indexes to keep track of the "level" that the current node is at
    private int nodeIndex;
    private int parentIndex;
    
    public XMLNode(String... strings) {
        if (data == null) data = new ArrayList<>();
        data.addAll(Arrays.asList(strings)); 
    }
    
    public String getData(int index) {
        return data.get(index);
    }
    
    public ArrayList<String> getAllData() {
        return data;
    }
    
    public void addData(String string) {
        data.add(string);
    }
    
    public void setTitle(String string) {
        this.title = string;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public int getNodeIndex() {
        return this.nodeIndex;
    }
    
    public int getParentIndex() {
        return this.parentIndex;
    }
    
    public void setNodeIndex(int index) {
        this.nodeIndex = index;
    }
    
    public void setParentIndex(int index) {
        this.parentIndex = index;
    }
}
