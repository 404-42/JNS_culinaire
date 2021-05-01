package Vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class top_menu {
	
	public String txt_input = "Recherche une recette.";
	public Controleur ctrl;
	public String box_default = "nom";
	
	
	public top_menu(Controleur ctrl) {
		this.ctrl = ctrl;
	}



	public HBox set_top() throws FileNotFoundException {
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 10, 12));
	    hbox.setSpacing(10);
	    hbox.setId("top_menu_div");
	    
	    
	    //______________________________________________________________________________________

	    TextField barre_de_recherche = new TextField(txt_input);
	    barre_de_recherche.setId("barre_de_recherche");
	    barre_de_recherche.setTranslateX(primaryScreenBounds.getWidth()/7);
	    
	    
	    FileInputStream inputstream = new FileInputStream("src/resources/images/loupe.png"); 
	    Image image = new Image(inputstream, 25, 25, false, false); 
	    ImageView loupe = new ImageView(image);
	    loupe.setId("loupe");
	    loupe.setTranslateX(-60);
	    loupe.setTranslateY(5);
	    
	    
	    //______________________________________________________________________________________
	    
	    Label trie = new Label("Trier par:");
	    trie.setTranslateX(primaryScreenBounds.getWidth()/4);
	    
	    ChoiceBox list_view = new ChoiceBox(FXCollections.observableArrayList("nom", "ingrédiant", "catégorie"));
	    list_view.setValue("nom");

	    list_view.setId("list_view");
	    list_view.setTranslateX(primaryScreenBounds.getWidth()/4);
	    
	    //________________________________event_______________________________________________
	    
	    barre_de_recherche.textProperty().addListener((observable, oldValue, newValue) -> {
	    	this.txt_input = newValue;
	    });
	    
	    list_view.setOnAction(event -> {
	        this.box_default = (String) list_view.getValue();
	        System.out.println("new box value");
	    });

	    
	    loupe.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			System.out.println("click loupe");
			
			switch (box_default) {
				case "nom":
					ctrl.getRecipeSearchResults(txt_input);
					break;
				case "ingrédiant":
					ctrl.getIngredientSearchResults(txt_input);
					break;
				case "catégorie":
					ctrl.getCategorieSearchResults(txt_input);
					break;
			}
    	});
	    
	  //______________________________________________________________________________________
	    
	    
	    
	    hbox.getChildren().addAll(barre_de_recherche, trie, list_view, loupe);

	    return hbox;
	}
}
