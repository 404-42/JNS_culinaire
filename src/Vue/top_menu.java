package Vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class top_menu {
	
	public HBox set_top() throws FileNotFoundException {
		
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 10, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #369;");
	    
	    
	    //______________________________________________________________________________________

	    TextField barre_de_recherche = new TextField("Je recherche une recette par nom, ingrédient, ou catégorie.");
	    barre_de_recherche.setId("barre_de_recherche");
	    barre_de_recherche.setTranslateX(primaryScreenBounds.getWidth()/10);
	    
	    
	    FileInputStream inputstream = new FileInputStream("src/resources/images/loupe.png"); 
	    Image image = new Image(inputstream, 25, 25, false, false); 
	    ImageView loupe = new ImageView(image);
	    loupe.setId("loupe");
	    loupe.setTranslateX(-110);
	    loupe.setTranslateY(5);
	    
	    
	    //______________________________________________________________________________________
	    
	    Label trie = new Label("Trier par:");
	    trie.setTranslateX(primaryScreenBounds.getWidth()/5);
	    
	    ObservableList<String> trie_nom = FXCollections.observableArrayList("nom", "ingrédiant", "catégorie");
	    ListView<String> list_view = new ListView<String>(trie_nom);
	    list_view.setId("list_view");
	    list_view.setTranslateX(primaryScreenBounds.getWidth()/5);
	    
	    //______________________________________________________________________________________
	    
	    
	    
	    hbox.getChildren().addAll(barre_de_recherche, trie, list_view, loupe);

	    return hbox;
	}
}
