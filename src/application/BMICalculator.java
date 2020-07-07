package application;

/*
 * Name: Alvin Kwan
 * CS422 Midterm Program
 * 
 */
//Main application class that loads and displays BMI Calculator's GUI
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class BMICalculator extends Application {
	@Override
	public void start (Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("BMICalculator.fxml"));	
		
		Scene scene = new Scene(root); //attach to scene
		stage.setTitle("BMI Calculator");  //Display in window's title bar
		stage.setScene(scene); //attach scene to stage
		stage.show(); //display stage
	}
	
	public static void main(String[] args) {
		//Create BMICalculator object and call its start methods
		launch(args);
	}
}
