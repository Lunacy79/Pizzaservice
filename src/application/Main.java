package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.net.URL;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			final URL fxmlUrl = getClass().getResource("fxml/First.fxml");
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
			fxmlLoader.setController(new FirstController());
			final Parent root = fxmlLoader.load();

			Scene scene = new Scene(root,700,500);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Hallo");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
