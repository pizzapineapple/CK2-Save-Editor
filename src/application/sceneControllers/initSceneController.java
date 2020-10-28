package application.sceneControllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import backend.Save;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class initSceneController implements Initializable{

	public static Save save;
	Scene newScene;
	Stage currentStage;

	@FXML
	ImageView initBackgroundImage;

//This is method when the scene is initially displayed
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	//The method called initially when the scene loads
	setBackground(); //calls the method that sets the background of the scene
	try {
		newScene = new Scene(FXMLLoader.load(getClass().getResource("/application/scenes/editSave.fxml")) ); //creates the scene object for the next scene displayable
		//System.out.println("found the stage");
	} catch (IOException e) {
		e.printStackTrace();
		}
}
	
final static int randomImageNumber = new Random().nextInt(19) + 1; //Creates a new random int that is used to switch setBackground
public static Image bImage; //a static image that is used for the background. this is so that other scene controllers can set their background to be this image

	public void setBackground() { //sets the background for the scenes
		Image bgImage; //inits bgImage to avoid errors after switch
		//Depending on what the random number sets the background as a respective image
		switch(randomImageNumber) { //sets the image bgImage is set to via randomNum
			case 1:
				bgImage = new Image("application/resources/images/background/load_1.png");
				break;

			case 2:
				bgImage = new Image("application/resources/images/background/load_2.png");
				break;

			case 3:
				bgImage = new Image("application/resources/images/background/load_3.png");
				break;

			case 4:
				bgImage = new Image("application/resources/images/background/load_4.png");
				break;

			case 5:
				bgImage = new Image("application/resources/images/background/load_5.png");
				break;

			case 6:
				bgImage = new Image("application/resources/images/background/load_7.png");
				break;

			case 7:
				bgImage = new Image("application/resources/images/background/load_8.png");
				break;

			case 8:
				bgImage = new Image("application/resources/images/background/load_9.png");
				break;

			case 9:
				 bgImage = new Image("application/resources/images/background/load_10.png");
				break;

			case 10:
				 bgImage = new Image("application/resources/images/background/load_11.png");
				break;

			case 11:
				 bgImage = new Image("application/resources/images/background/load_12.png");
				break;

			case 12:
				 bgImage = new Image("application/resources/images/background/load_13.png");
				break;

			case 13:
				 bgImage = new Image("application/resources/images/background/load_14.png");
				break;

			case 14:
				 bgImage = new Image("application/resources/images/background/load_15.png");
				break;

			case 15:
				 bgImage = new Image("application/resources/images/background/load_16.png");
				break;

			case 16:
				 bgImage = new Image("application/resources/images/background/load_17.png");
				break;

			case 17:
				 bgImage = new Image("application/resources/images/background/load_18.png");
				break;

			case 18:
				 bgImage = new Image("application/resources/images/background/load_19.png");
				break;

			default:
				 bgImage = new Image("application/resources/images/background/load_6.png");
				break;
		}
		initBackgroundImage.setImage(bgImage); //sets the backgrounds image
		bImage = bgImage; //passes this variable to the static class variable so that it is not recreated everytime the scene is reinstiniated
	}

//Method called when you press earlyMiddleAges button
	@FXML
	public void earlyMidAges() {
		System.out.println("Constor called");
		save = new Save("../ckIISaveEditor/src/application/resources/defaultStarts/early.txt");			
		currentStage = (Stage) initBackgroundImage.getScene().getWindow();
		currentStage.setScene(newScene);
	}

//When a button is pressed
	@FXML
	public void vikingAge() {
		System.out.println("viking");
		save = new Save("../ckIISaveEditor/src/application/resources/defaultStarts/vikings.txt");
		Stage currentStage = (Stage) initBackgroundImage.getScene().getWindow();
		currentStage.setScene(newScene);
	}

	//When a button is pressed
	@FXML
	public void ironCentury() {
		System.out.println("iron");
		save = new Save("../ckIISaveEditor/src/application/resources/defaultStarts/iron.txt");
		Stage currentStage = (Stage) initBackgroundImage.getScene().getWindow();
		currentStage.setScene(newScene);
	}

	//When a button is pressed
	@FXML
	public void normans() {
		System.out.println("normans");
		save = new Save("../ckIISaveEditor/src/application/resources/defaultStarts/normans.txt");
		Stage currentStage = (Stage) initBackgroundImage.getScene().getWindow();
		currentStage.setScene(newScene);
	}

	//When a button is pressed
	@FXML
	public void lateMidAges() {
		System.out.println("late");
		save = new Save("../ckIISaveEditor/src/application/resources/defaultStarts/late.txt");
		Stage currentStage = (Stage) initBackgroundImage.getScene().getWindow();
		currentStage.setScene(newScene);
	}

	@FXML
	public void custom() {
		System.out.println("custom");

		//Creates a new instance of file chooser (a popup that allows you to eaisly select a file)
		FileChooser filechooser =  new FileChooser();
		filechooser.setTitle("Load Save Game");
		filechooser.getExtensionFilters().addAll(
				new ExtensionFilter("All Files", "*.txt", "*.ck2") //only allows .txt and .ck2 files
				);
		
		filechooser.setInitialDirectory( new File(System.getProperty("user.home")  + "\\Documents\\Paradox Interactive\\Crusader Kings II\\save games" ) ); //input the file version of the string path of the ck2 save file directory
		//sets the initial directory in the ck2 save games folder

		File selectedFile = filechooser.showOpenDialog((Stage) initBackgroundImage.getScene().getWindow()); //Opens dialog box
		//The cryptic shit is to get the stage to open the dialog window on

		if(selectedFile != null) {
			save = new Save(selectedFile.toString());

			//Changes the scene
			Stage currentStage = (Stage) initBackgroundImage.getScene().getWindow();
			currentStage.setScene(newScene);

		}
		else {
			
		}
		//System.out.print(selectedFile.exists());

	}

	@FXML
	public void other() throws IOException {
		System.out.println("other");

		Stage otherSave = new Stage();
		Scene otherSaveScene = new Scene(FXMLLoader.load(getClass().getResource("../scenes/otherSavePopup.fxml")) );
		otherSave.setScene(otherSaveScene);
		otherSave.show();
	}

	@FXML
	public void settings() throws IOException {
		System.out.println("Settings");
		Scene popupScene = new Scene( FXMLLoader.load(getClass().getResource("../scenes/settingPopup.fxml")) );
		Stage settingPopup = new Stage();
		settingPopup.setScene(popupScene);
		settingPopup.show();
	}

}
