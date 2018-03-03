/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Brandon
 */
public class XMLLoad {
    private static int tabCount;
    private static Stack<Integer> parentStack;
    
    public static XMLNodeList load(File file) {
        
        XMLNodeList xmlNodes = new XMLNodeList();

        try {
            //factory for sax parser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser;
            saxParser = factory.newSAXParser();

            //Could've extended this in this class itself, but creating an instance of a DefaultHandler works here too
            DefaultHandler handler = new DefaultHandler() {
                XMLNode node = null;
                String title = "";

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    //add one since we're starting on a new element to create a new tab when we're printing this out for the DOM
                    ++tabCount;
                    int parentDepth = parentStack.peek();

                    node = new XMLNode();
                    node.setTitle(qName);

                    node.setNodeIndex(tabCount);
                    node.setParentIndex(parentDepth);

                    parentStack.push(tabCount);
                    xmlNodes.addNode(node);
                }

                @Override
                public void endElement(String uri, String localName, String qName) {
                    //popping off the stack so we need to subtract one from the layer 
                    parentStack.pop();
                    tabCount--;
                }

                @Override
                public void characters(char ch[], int start, int length) {
                    if (node != null) {
                        node.addData(new String(ch, start, length));
                    }
                }

                @Override
                public void startDocument() {
                    //initialize node int to -1 since the first node added should start at 0 (+1 will be added for each node)
                    tabCount = -1;
                    parentStack = new Stack<>();
                    parentStack.push(tabCount);
                }

            };
            
            //honestly not 100% sure what all of the try-catches are for, but there were too many errors and I'm getting tired
            try {
                saxParser.parse(file.getAbsoluteFile(), handler);
            } catch (SAXException ex) {
                Logger.getLogger(XMLLoad.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(XMLLoad.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLLoad.class.getName()).log(Level.SEVERE, null, ex);
        }


        return xmlNodes;
    }
}
