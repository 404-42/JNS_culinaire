package Vue;

import Modèle.Modèle;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Vue extends Application {
	public static void main(String[] args) {

		Modèle modl = new Modèle();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root,800,700);
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		HBox top = new top_menu().set_top();
		VBox left = new left_menu().set_left();
		FlowPane middel = new middel_menu().set_middel();
		
		root.setTop(top);
		root.setLeft(left);
		root.setCenter(middel);
		
		
		
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("JNS culinaire");
		scene.setFill(Color.GREY);
		
		primaryStage.setX(primaryScreenBounds.getMinX());
		primaryStage.setY(primaryScreenBounds.getMinY());
		primaryStage.setWidth(primaryScreenBounds.getWidth()-100);
		primaryStage.setHeight(primaryScreenBounds.getHeight()-100);
		primaryStage.setResizable(true);

		
		primaryStage.show();
	}
	
	
}
