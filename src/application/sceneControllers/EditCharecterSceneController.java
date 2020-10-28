package application.sceneControllers;

import java.io.IOException;

import backend.Char;
import backend.CharInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;


//Scene controller for the scene that actually allows you to change the name of a charecter
public class EditCharecterSceneController {
	Char Char;
	
	public EditCharecterSceneController(Char Char) {
		this.Char = Char;	
	
	}
	
	@FXML
	public Text nameText;
	
	@FXML
	public Text dynastyNameText;

	@FXML
	public ListView<CharInfo> displayListView;
	
	@FXML
	public Button commitModify;
	
	@FXML
	public TextField newName;

	//updates the name
	public void updateName() {
		Char.setName(newName.getText());
		System.out.println("updated name");
		Char.getCharInfo()[0].setValue(newName.getText());
	}
	
	//called when you hit modify
	public void commitModify() {
		initSceneController.save.write(Char);
	}
 
	ObservableList<CharInfo> CharInfoList;
	
	//called when the scene initially loads
	public void initialize() {
		nameText.setText("Name: " + this.Char.getName());
		if(Char.getDynasty() != null) dynastyNameText.setText( Char.getDynasty().getName() ); //to catch a null pointer exception
		
		//adds all the charinfo to the observable list
		CharInfoList = FXCollections.observableArrayList();// = new ObservableList<CharInfo>();
		CharInfoList.addAll(Char.getCharInfo());
		
		//adds all elements of an observable list the listview 
		displayListView.setItems(CharInfoList);
		displayListView.setCellFactory(cellFactory -> new CharInfoTableCell() );
		
	}
	
	
}
