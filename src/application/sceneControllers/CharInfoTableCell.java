package application.sceneControllers;

import java.io.IOException;

import backend.CharInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CharInfoTableCell extends ListCell<CharInfo> {
	
	@FXML
	private Text property;
	
	@FXML 
	public TextField textField;
	
	@FXML
	private AnchorPane anchorPane;
	
	private FXMLLoader loader;
		
	boolean once = false;
	String value;
	
	@Override
    protected void updateItem(CharInfo info, boolean empty) {
        super.updateItem(info, empty);
        String textFieldValue;
       // System.out.println("Update item");
        
        if(empty || info == null) {
           setText("No Results");
           setGraphic(null);
        } else {
            if (loader == null) {
            	value = info.getValue();
                loader = new FXMLLoader(getClass().getResource("../scenes/CharInfoTableCell.fxml"));
                loader.setController(this);
                try {
                    loader.load(); //illegalStateException here
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                textFieldValue = info.getValue();
                textField.setText(textFieldValue);
                
                
            }
          //  System.out.println("current texet feild" + textField.getText());
            
         // System.out.println(textField.getText());
            
          //  System.out.println(textField.getText().trim() == "" );
            if(textField.getText() != info.getValue() && textField.getText().trim().replace("\\r", "").replace("\\n", "")  == "") {
            	//System.out.println(textField.getText());
            	//System.out.println("Updated the object");
            	info.setValue(textField.getText());
            	//value = textField.getText();
            	//once = true;
            }
            
            property.setText(info.getName()); //the non changing heading
            textField.setText(info.getValue());
            
            System.out.println(textField.getText() + " " + info.getValue() );
           
            
          
                       
            setText(null);
            setGraphic(anchorPane);
        }

    }
	
	public String getCurrentValue() {
		return textField.getText();
	}

	
	
}
