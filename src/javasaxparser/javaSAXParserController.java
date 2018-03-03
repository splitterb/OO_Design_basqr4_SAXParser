/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasaxparser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;


/**
 *
 * @author Brandon
 */
public class javaSAXParserController implements Initializable {

    @FXML
    AnchorPane anchorPane;
    
    @FXML
    TextArea textArea;

    File xmlFile;

    @FXML
    Button openFileButton;

    public void openFile(ActionEvent event) {
        //using this node to give chooser a spot to access a root element
        Node nodeRoot = (Node) event.getSource();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        xmlFile = chooser.showOpenDialog(nodeRoot.getScene().getWindow());
        if (xmlFile != null) {
            try {
                textArea.clear(); //make sure we aren't writing on top of existing text
                XMLNodeList xmlNodeList = XMLLoad.load(xmlFile);
                ArrayList<XMLNode> xmlNode_ = xmlNodeList.getNodes();

                for (XMLNode node : xmlNode_) {
                    int depth = node.getNodeIndex();
                    for (int i = 0; i < depth; i++) {
                        textArea.appendText("\t");
                    }
                    // It would be better to just do 1 string for data
                    textArea.appendText(node.getTitle() + ": " + node.getData(0) + "\n");
                }
            } catch (Exception ex) {
                System.out.printf("Exception parsing XML file. %s\n", ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
}
