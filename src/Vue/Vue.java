package Vue;

import Modèle.Modèle;
import controleur.Controleur;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Vue extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Modèle modl = new Modèle();
		Controleur ctrl = new Controleur(modl);
		
		
		ScrollBar scroll = new ScrollBar();
	    scroll.setMin(0);
	    scroll.setOrientation(Orientation.VERTICAL);
		
		
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root,800,700);
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		HBox top = new top_menu().set_top();
		VBox left = new left_menu().set_left();
		FlowPane middel = new middel_menu().set_middel();
		
		/*scroll.setContent(middel);
		
		BorderPane root2 = new BorderPane();
		root2.setRight(scroll);
		root2.setCenter(middel);*/
		
		ScrollPane root2 = new ScrollPane();
		middel.setMinWidth(1046);
		root2.setContent(middel);
		root2.setPannable(true);
		
		root.setTop(top);
		root.setCenter(root2);
		root.setLeft(left);
		
		
		
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
