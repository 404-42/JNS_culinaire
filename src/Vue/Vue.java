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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Vue extends Application {
	public Modèle modl = new Modèle();
	public Controleur ctrl = new Controleur(modl, this);
	public BorderPane root = new BorderPane();
	public ScrollPane root2, root3;
	public HBox top;
	public VBox left;
	public Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		ScrollBar scroll = new ScrollBar();
	    scroll.setMin(0);
	    scroll.setOrientation(Orientation.VERTICAL);
	    
	    scene = new Scene(root,800,700);
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		top = new top_menu(ctrl).set_top();
		left = new left_menu(ctrl).set_left();
		FlowPane middel1 = new middel_menu(ctrl).set_middel();
		
		root2 = new ScrollPane();
		middel1.setMinWidth(1046);
		root2.setContent(middel1);
		root2.setPannable(true);
		
		
		this.switch_middel();
		
		
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
	
	
	public void switch_middel() {
		System.out.println("switch_middel");
		
		root.setTop(top);
		root.setLeft(left);

		if (ctrl.current_recette == null) root.setCenter(root2);
		else {
			GridPane middel2 = new middel2_menu(ctrl).set_middel();
			root3 = new ScrollPane();
			middel2.setMinWidth(1046);
			root3.setContent(middel2);
			root3.setPannable(true);
			
			root.setCenter(root3);
		}
	}
	
	
}
