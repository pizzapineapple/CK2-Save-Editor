package application.sceneControllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class settingPopupController {

	@FXML
	StackPane modSettings;
		
	@FXML
	public void back() throws IOException { 
		//ystem.out.println("Back");
		Stage currentStage = (Stage) modSettings.getScene().getWindow();
		currentStage.close();
	}

	@FXML 
	public void modSettings() {
		System.out.println("Mod Settings");
	}
	
	@FXML
	public void credits() {
		System.out.println("Credits");
	}
	
	@FXML 
	public void modifySavePath() {
		System.out.println("Save Path");
	}
	
	
	
	private String url(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void initialize() {
		// TODO Auto-generated method stub
		//setBackground();
		//initBackgroundImage.setImage(initSceneController.bgImage);

}

}
