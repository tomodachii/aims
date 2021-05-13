package aims.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			
			stage.setOnCloseRequest(event -> {
				event.consume();
				logout(stage);
			});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void logout(Stage stage) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Farewell");
		alert.setHeaderText("Thanks for using this program");
		alert.setContentText("Do you really want to quit?");
		
		if (alert.showAndWait().get() == ButtonType.OK) {
			System.out.println("You've successfully logged out!");
			stage.close();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}

