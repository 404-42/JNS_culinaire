package Vue;


import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class left_menu {
	
	public VBox set_left() throws FileNotFoundException {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		

		VBox menu_bar = new VBox();
		MenuButton m = new MenuButton("Ingrédient");
        

		Button favorie = new Button("Favorie");
		Button aide = new Button("Aide");
		
        MenuItem viande = new MenuItem("poulet");
        MenuItem legume = new MenuItem("Open File");
        MenuItem exitItem = new MenuItem("Exit");
        
        
		menu_bar.setId("menu_left_div");
		m.setId("m");
		favorie.getStyleClass().add("button_left");
		aide.getStyleClass().add("button_left");
        
		
        m.getItems().addAll(viande, legume, exitItem);
        menu_bar.getChildren().addAll(m, favorie, aide);
	    
	    //______________________________________________________________________________________
	    
        
	    
		return menu_bar;
	}
}
