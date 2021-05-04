package Vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import Modèle.Etape;
import Modèle.Ingrédient;
import controleur.Controleur;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class middel2_menu {
	public Controleur ctrl;
	
	public middel2_menu(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	public GridPane set_middel() {
		GridPane grid_pane = new GridPane();
		
	    grid_pane.setVgap(20); 
	    grid_pane.setHgap(10);
	    grid_pane.setPadding(new Insets(10, 250, 100, 100)); 
		
	    //__________________________________________________________________________
	    
	    
	    FileInputStream imageStream = null;
	    ImageView img = null;
	    
		try {
			imageStream = new FileInputStream("src/resources/images/" + ctrl.current_recette.image.getName());
			img = new ImageView(new Image(imageStream));
			img.setFitHeight(400);
			img.setFitWidth(400);
			img.setPreserveRatio(true);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: coeur.");
		}
		
		//____________________________________________________________________________________________
		
		
		TextFlow text_pane = new TextFlow();
		
		text_pane.setPrefSize(600, 300);
		text_pane.setLineSpacing(5.0);
		text_pane.setTextAlignment(TextAlignment.JUSTIFY); 
		
		
		Text nom = new Text("\n\n\n\n" + ctrl.current_recette.nom + "\n");
	    Text categorie = new Text(ctrl.current_recette.categorie + "\n");
	    Text time = new Text("temps estimée: " + String.valueOf(ctrl.current_recette.tempsEstimee) + "\n");
	    Text nb_personne = new Text("nombre de personne: " + String.valueOf(ctrl.current_recette.personne) + "\n");

	    nom.setStyle("-fx-font-size: 25px;");
	    nb_personne.setStyle("-fx-font-size: 16px;");
	    time.setStyle("-fx-font-size: 16px;");
	    categorie.setStyle("-fx-font-size: 16px;");
	    
	    
	    //____________________________________________________________________________________________
	    

    	ListView<String> list_ingredient = new ListView<String>();
    	
	    for (Ingrédient i : ctrl.current_recette.ingrédients)
	    	list_ingredient.getItems().add(i.nom + ":  " + i.quantité);
	    
	    VBox vbox = new VBox(list_ingredient);
	    
	    //____________________________________________________________________________________________
	    
	    
	    TextFlow text_pane2 = new TextFlow();
	    Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
	    Label label_recette = new Label("Étape de la recette:");
	    
		text_pane2.setPrefSize(500, 300);
		text_pane2.setLineSpacing(5.0);
		label_recette.setFont(font);
	    
	    for (Etape i : ctrl.current_recette.etapes) {
	    	Text etape = new Text(i.instruction + "\n\n");
	    	
	    	text_pane2.getChildren().add(etape);
	    }
	    
	    //____________________________________________________________________________________________
	    
	    
	    TextArea area = new TextArea();
	    Label label = new Label("Note personnel:");
	    
	    label.setFont(font);


	    area.setText("");
	    area.setPrefColumnCount(15);
	    area.setPrefHeight(120);
	    area.setPrefWidth(300);
	      
	      
	   
	    //____________________________________________________________________________________________
	    
	    
	    Button retour = new Button("retour");
	    Button retour2 = new Button("retour");
	    
	    retour.setOnAction(event -> {
	    	System.out.println("retour home");
	    	ctrl.switch_middel(null);
	    });
	    retour2.setOnAction(event -> {
	    	System.out.println("retour home");
	    	ctrl.switch_middel(null);
	    });
	    
	    
	    
	    //_____________________________________________________________________________________________
	    

		text_pane.getChildren().addAll(nom, categorie, time, nb_personne);
		
		grid_pane.add(retour, 0, 0);
		grid_pane.add(img, 0, 1);
		grid_pane.add(text_pane, 1, 1);
		grid_pane.add(vbox, 0, 2);
		grid_pane.add(label_recette, 0, 3);
		grid_pane.add(text_pane2, 0, 4);
		grid_pane.add(label, 0, 5);
		grid_pane.add(area, 0, 6);
		grid_pane.add(retour2, 1, 7);
		
		
		//____________________________________________________________________________________________
		
		
		
		return grid_pane;
	}
	
	
}
