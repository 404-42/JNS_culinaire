package Vue;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class middel_menu {
	public FlowPane set_middel() {
		
		FlowPane flow_pane = new FlowPane();
		flow_pane.setHgap(25);
		flow_pane.setId("middle");
		ObservableList<Node> list = flow_pane.getChildren();
		
		
		//GridPane tmp = create_item();
		//tmp.getStyleClass().add("grid_item");
		//list.addAll(tmp);
		
		return flow_pane;
	}
	
	
	public GridPane create_item() {
		
		GridPane grid_pane = new GridPane();
	    grid_pane.setMinSize(400, 200);
	    grid_pane.setPadding(new Insets(10, 10, 10, 10)); 
	    grid_pane.setVgap(5); 
	    grid_pane.setHgap(5);
	    grid_pane.setAlignment(Pos.BOTTOM_LEFT);
	    
	    Text nom = new Text("Nomdu plat"); 
	    
	    grid_pane.add(nom, 0, 2); 
		return null;
	}
	

}
