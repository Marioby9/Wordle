package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import utilities.DataBase;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage ventana) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			ventana.setResizable(false);
			ventana.setScene(scene);
			ventana.setTitle("Wordle Mario");
			
			Image icon = new Image(getClass().getResourceAsStream("/resources/icono.png"));
			ventana.getIcons().add(icon);
			
			ventana.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			DataBase.connect();
			launch(args);
			DataBase.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
