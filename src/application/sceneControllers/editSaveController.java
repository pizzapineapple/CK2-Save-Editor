package application.sceneControllers;

import java.io.IOException;

import application.Main;
import backend.Char;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class editSaveController {

	@FXML
	ImageView initBackgroundImage;

	@FXML
	private TextField charecterSearchBox;


	//When you press tha back button
	@FXML
	public void back() throws IOException {
		System.out.println("Back");
		Stage currentStage = (Stage) initBackgroundImage.getScene().getWindow();
		currentStage.setScene(Main.initScene);
	}

	//Method called when the findCharecter Button is pressed
	@FXML
	public void modChar() {
		findChar();
	}

	@FXML 
	ListView<Char> charDisplayPane; //creates listView object with refrence to FXMl file
	
	ObservableList<Char> charObservableArrayList = FXCollections.observableArrayList(); //Creates an observable list that will display in the listView
	
	
	@FXML
	//This method is used by the scene to find chars based on their name, charid, or title
	public void findChar() {
		charObservableArrayList.clear(); //clears everything in the observable list
//		System.out.println("Searching");
//		System.out.println(charecterSearchBox == null);
//		System.out.println(charecterSearchBox.getText());
		String searchBoxInput = charecterSearchBox.getText();
		String delimitedInput = searchBoxInput.trim();

		try {
			Char returnedCharbyId = initSceneController.save.findCharbyId(Integer.parseInt(delimitedInput)); //calls findCharById on the save object if you enter a charid
			charObservableArrayList.add(returnedCharbyId);
			System.out.println("parsed int");
			//display the one result in the side panel
		}
		catch(NumberFormatException e) { //if a numberformatException is thrown therefore you didnt ennter a number it searches all the charecters by their name
			Char[] returnedCharArray = initSceneController.save.findCharbyName(delimitedInput);
			charObservableArrayList.addAll(returnedCharArray); //adds all the entire content returned by the search to the observable array
			System.out.println("failed parsing int");
			//display all the results in the side panel
		}
		//if(charObservableArrayList.size() >= 1) 
		charDisplayPane.setItems(charObservableArrayList); //sets all the items in the listView to be everything in the observable list
		charDisplayPane.setCellFactory(randomName -> new CharListCell()); //creates a custom cell so that just more then one line of text is displayed
		//a new CharListCell object is passed via lambda
		
		
	}
	
	//method called when you press the modify character button
	@FXML
	public void modCB() {
		try {
			if(charDisplayPane.getSelectionModel().getSelectedItem() != null) { //will only call the method on the char if you have selected a char from the actual list
				initSceneController.save.moreCharInfo(charDisplayPane.getSelectionModel().getSelectedItem() ); //Reparses the character after you have selected one getting more info
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../scenes/EditCharecterScene.fxml")); //loads the next scene
				loader.setController( new EditCharecterSceneController(charDisplayPane.getSelectionModel().getSelectedItem()) ); //sets the scene controller for the text scene
				Stage currentStage = (Stage) initBackgroundImage.getScene().getWindow(); //creates the refrence to the stage object
				currentStage.setScene( new Scene((Parent)loader.load()) ); //finally sets the windows stage as this new scene	
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void initialize() {
		// TODO Auto-generated method stub
		initBackgroundImage.setImage(initSceneController.bImage); //initially set the background image of the scene
		
}

	
}
