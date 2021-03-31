package Vue;

import java.io.FileNotFoundException;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Screen;

public class left_menu {
	
	public MenuBar set_left() throws FileNotFoundException {	
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		
		MenuBar menu_bar = new MenuBar();

		menu_bar.setId("menu_left_div");
        

        Menu categorie = new Menu("Catégorie");
        Menu favorie = new Menu("favorie");
        Menu aide = new Menu("Aide");
        
        MenuItem viande = new MenuItem("");
        MenuItem legume = new MenuItem("Open File");
        MenuItem exitItem = new MenuItem("Exit");
        
        
        
        // Add menuItems to the Menus
        categorie.getItems().addAll(viande, legume, exitItem);
        menu_bar.getMenus().addAll(categorie, favorie, aide);

	    
	    //______________________________________________________________________________________
	    
	    
	    
	    
		return menu_bar;
	}
}
