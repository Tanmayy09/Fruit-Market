package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Fruit_Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root =(Parent)FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
			Scene scene = new Scene(root,700,450);
			scene.getStylesheets().add(getClass().getResource("loginController.css").toExternalForm());
			primaryStage.setTitle("Fruit Market Registration");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
