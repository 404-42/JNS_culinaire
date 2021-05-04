package Vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Modèle.Recette;
import controleur.Controleur;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;



public class middel_menu {
	
	
	public Controleur ctrl;
	public FlowPane flow_pane;
	public ObservableList<Node> list;
	
	public middel_menu(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	


	public FlowPane set_middel() {
		
		flow_pane = new FlowPane();
		flow_pane.setHgap(3);
		flow_pane.setVgap(25);
		flow_pane.setId("middle");
		list = flow_pane.getChildren();
		
		
		ArrayList<Recette> recettes = ctrl.get_random_recette();
		
		for (Recette recette : recettes) {
			
			GridPane tmp = create_item(recette);
			tmp.getStyleClass().add("grid_item");
			flow_pane.setMargin(tmp, new Insets(5, 5, 5, 5));
			list.addAll(tmp);
		}
		
		
		return flow_pane;
	}
	
	
	
	public void new_recherche(ArrayList<Recette> recettes) {
		flow_pane.getChildren().clear();
		
		for (Recette recette : recettes) {
			GridPane tmp = create_item(recette);
			tmp.getStyleClass().add("grid_item");
			flow_pane.setMargin(tmp, new Insets(5, 5, 5, 5));
			list.addAll(tmp);
		}
	}
	
	
	
	public GridPane create_item(Recette recette) {
		String img = recette.image.getName(),
				name = recette.nom;
		int nb_etoile = 4;
		boolean favorie = recette.isFavorite,
				ma_liste = false;
			
		
		GridPane grid_pane = new GridPane();
	    grid_pane.setMinSize(300, 300);
	    grid_pane.setPadding(new Insets(50, 70, 10, 20)); 
	    grid_pane.setVgap(5); 
	    grid_pane.setHgap(5);
	    grid_pane.setAlignment(Pos.BOTTOM_LEFT);
	    grid_pane.getStyleClass().add("plat");
	    
	    
	    grid_pane.setBackground(new Background(
	    		new BackgroundImage(new Image("resources/images/"+img, 300, 300, false, true),
					BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,
					BackgroundSize.DEFAULT
	    		)
	    ));
	    
	    grid_pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("grid_pane clicked: " + name);
                ctrl.switch_middel(recette);
            }
        });
	    
	    
	    
	    /*_____________________________________add_images______________________________________*/
	    
	    
	    FileInputStream imageStream = null;
	    
		try {
			if (favorie) imageStream = new FileInputStream("src/resources/images/coeur_rouge.png");
			else imageStream = new FileInputStream("src/resources/images/coeur_noir.png");
		} catch (FileNotFoundException e) {
			System.out.println("File not found: coeur.");
		}
		
	    Image favorie_img = new Image(imageStream);
	    
	    for (int i=1; i <= 5; i++) {
    		try {
    			if (i < nb_etoile) imageStream = new FileInputStream("src/resources/images/etoile_jaune.png");
    			else imageStream = new FileInputStream("src/resources/images/etoile_noir.png");
    		} catch (FileNotFoundException e) {
    			System.out.println("File not found: etoile.");
    		}
    		
    		Image etoile_img = new Image(imageStream);
    		grid_pane.add(new ImageView(etoile_img), 13+i, 3);
	    }
	    
	    try {
			if (ma_liste) imageStream = new FileInputStream("src/resources/images/parchemin.png");
			else imageStream = new FileInputStream("src/resources/images/parchemin_noir.png");
			
    		grid_pane.add(new ImageView(new Image(imageStream)), 1, 3);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: parchemin.");
		}
	    
	    
	    /*_________________________________________________________________________________________*/
	    

	    Text nom = new Text(name);
	    nom.setStyle("-fx-font-size: 16px;");
	    
	    
	    /*_____________________________________add_new_elements___________________________________*/
	    
	    
	    grid_pane.add(nom, 0, 0, 10, 1);
	    grid_pane.add(new ImageView(favorie_img), 0, 3);
	    
	    /*_________________________________________________________________________________________*/
	    
	    
	    
		return grid_pane;
	}
	

}
