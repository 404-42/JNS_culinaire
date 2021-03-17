package Vue;

import Modèle.Modèle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Vue extends Application {
	public static void main(String[] args) {
		// Changer d'emplacement quand le constructeur de Vue sera créer
		Modèle modl = new Modèle();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
