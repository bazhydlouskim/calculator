import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class Calculator extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("app.fxml"));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

			primaryStage.setTitle("JavaFX and Maven");
			primaryStage.setScene(scene);
			primaryStage.show();

			String javaVersion = System.getProperty("java.version");
			String javafxVersion = System.getProperty("javafx.version");
			Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
			//Scene scene = new Scene(new StackPane(l), 640, 480);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
