package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
	public static Scene initScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			initScene = new Scene(FXMLLoader.load(getClass().getResource("scenes/initScene.fxml"))); //creates a scene object
			
			//Stage things
			//Pimrary Stage is what every scene is displayed in
			primaryStage.setTitle("Crusader Kings II Save Editor");
			primaryStage.getIcons().add(new Image("application/resources/images/ck2icon.png")); //adds window favicon
			primaryStage.setResizable(false); //window is now allowed to resize
			primaryStage.setScene(initScene); //sets the default scene
			primaryStage.sizeToScene(); //sizes the size of the stage to the indivgual scene 
			primaryStage.show(); //actually shows the stage
			
		} catch(Exception e) {
			e.printStackTrace(); //if an error is thrown anywhere it gets caught here
		}
		
	}

	public static void main(String[] args) {
		launch(args); //javafx things
	}
}
