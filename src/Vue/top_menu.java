package Vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class top_menu {
	
	public HBox set_top() {
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #369;");


	    TextField barre_de_recherche = new TextField("Je recherche une recette par nom, ingrédient, ou catégorie.");
	    barre_de_recherche.setId("barre_de_recherche");
	    barre_de_recherche.setTranslateX(primaryScreenBounds.getWidth()/10);
	    
	    
	    Label trie = new Label("Trier par:");
	    ObservableList<String> trie_nom = FXCollections.observableArrayList("nom", "ingrédiant", "catégorie");
	    ListView<String> list_view = new ListView<String>(trie_nom);
	    list_view.setId("list_view");
	    list_view.setTranslateX(primaryScreenBounds.getWidth()/3);
	    
	    
	    hbox.getChildren().addAll(barre_de_recherche, list_view);

	    return hbox;
	}
}
