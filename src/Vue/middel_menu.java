package Vue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class middel_menu {
	public FlowPane set_middel() {
		
		FlowPane flow_pane = new FlowPane();
		flow_pane.setHgap(25);
		flow_pane.setId("middle");
		ObservableList<Node> list = flow_pane.getChildren();
		
		
		GridPane tmp = create_item("52837.jpg", "un nom", 4, true);
		//tmp.getStyleClass().add("grid_item");
		list.addAll(tmp);
		
		return flow_pane;
	}
	
	
	
	public GridPane create_item(String img, String name, int nb_etoile, boolean favorie) {
		
		GridPane grid_pane = new GridPane();
	    grid_pane.setMinSize(300, 300);
	    grid_pane.setPadding(new Insets(10, 10, 10, 10)); 
	    grid_pane.setVgap(5); 
	    grid_pane.setHgap(5);
	    grid_pane.setAlignment(Pos.BOTTOM_LEFT);
	    grid_pane.getStyleClass().add("plat");
	    
	    
	    grid_pane.setBackground(new Background(
	    		new BackgroundImage(new Image("resources/images/"+img, 32, 32, false, true),
					BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT,
					BackgroundPosition.DEFAULT,
					new BackgroundSize(300, 300, true, true, true, true)
	    		)
	    ));
	    
	    
	    FileInputStream imageStream = null;
	    
		try {
			if (favorie) imageStream = new FileInputStream("src/resources/images/coeur_rouge.png");
			else imageStream = new FileInputStream("src/resources/images/coeur_noir.png");
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		
	    Image favorie_img = new Image(imageStream);
	    
	    Text nom = new Text(name); 
	    
	    grid_pane.add(nom, 0, 2);
	    grid_pane.add(new ImageView(favorie_img), 6, 3);
	    
	    
		return grid_pane;
	}
	

}
