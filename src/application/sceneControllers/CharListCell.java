package application.sceneControllers;

import java.io.IOException;

import backend.Char;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

//A class to display each a charecter as a entry in a list in the editScene
public class CharListCell extends ListCell<Char> {

	//each cell has a label
	@FXML
	Label dynastyText;

	//another label
	@FXML
	Label nameText;

	@FXML
	private VBox parent; //the absolute parent object in the scene

	private FXMLLoader loader;

	@Override
    protected void updateItem(Char Chars, boolean empty) {
        super.updateItem(Chars, empty);

        if(empty || Chars == null) {
           setText("No Results");
           setGraphic(null);
        } else {
            if (loader == null) { //if the fxml loader hasn't been initialized 
                loader = new FXMLLoader(getClass().getResource("../scenes/CharTableCell.fxml"));  //actually loads the fxml scene
                loader.setController(this); //sets it scene controller as this object
                try {
                    loader.load(); //attempts to load the scene inside the listView
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            nameText.setText(Chars.getName()); //sets the name of the cell as the name of the character
            if(Chars.getDynasty() != null) { //if the charecter has a dynasty object
            	//System.out.println(Chars.getDynasty().getName());
            	dynastyText.setText(Chars.getDynasty().getName()); //it will set the name of the charecers dynasty to said name
            }
            else {
            	dynastyText.setText("Lowborn"); //otherwise say the charecter is a pleb
            }
            //dynastyText.setText("TExt");
            setText(null);
            setGraphic(parent);
        }

    }

}
