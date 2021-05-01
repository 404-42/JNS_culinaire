package Vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controleur.Controleur;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class middel2_menu {
	public Controleur ctrl;
	
	public middel2_menu(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	public GridPane set_middel() {
		GridPane grid_pane = new GridPane();
		
	    grid_pane.setVgap(5); 
	    grid_pane.setHgap(5);
	    grid_pane.setPadding(new Insets(10, 50, 10, 10)); 
		
	    //__________________________________________________________________________
	    
	    
	    FileInputStream imageStream = null;
	    ImageView img = null;
	    
		try {
			imageStream = new FileInputStream("src/resources/images/" + ctrl.current_recette.image.getName());
			img = new ImageView(new Image(imageStream));
			img.setFitHeight(300);
			img.setFitWidth(300);
			img.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: coeur.");
		}
		
		//____________________________________________________________________________________________
		
		TextFlow text_pane = new TextFlow();
		
		text_pane.setPrefSize(600, 300);
		text_pane.setLineSpacing(5.0);
		
		
		Text nom = new Text(ctrl.current_recette.nom + "\n");
	    Text categorie = new Text(ctrl.current_recette.categorie + "\n");
	    Text time = new Text("temps estimée: " + String.valueOf(ctrl.current_recette.tempsEstimee) + "\n");
	    Text nb_personne = new Text("nombre de personne: " + String.valueOf(ctrl.current_recette.personne) + "\n");
	    
	    nb_personne.setStyle("-fx-font-size: 16px;");
	    nom.setStyle("-fx-font-size: 25px;");
	    time.setStyle("-fx-font-size: 16px;");
	    categorie.setStyle("-fx-font-size: 16px;");
	    
	    //____________________________________________________________________________________________
	    
		
		grid_pane.add(img, 0, 0);
		
		text_pane.getChildren().addAll(nom, categorie, time, nb_personne);
		
		grid_pane.add(text_pane, 1, 0);
		/*grid_pane.add(nom, 1, 0);
		grid_pane.add(categorie, 1, 0);
		grid_pane.add(time, 2, 0);
		grid_pane.add(nb_personne, 1, 0);*/
		
		
		
		//____________________________________________________________________________________________
		
		return grid_pane;
	}
	
	
}
