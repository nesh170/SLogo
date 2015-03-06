package view;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ColorPane {
	private ObservableList<String> myColorList;
	private ListView<String> myColorListView;
	
	public ColorPane(Group root, ArrayList<String> colorList) {
		initializeSuggestedView(root, colorList);		
	}
	
	private void initializeSuggestedView(Group root, ArrayList<String> colorList) {
		myColorList = FXCollections.observableArrayList(colorList);
		VBox colorBox = new VBox();
	    myColorListView.setItems(myColorList);
	    colorBox.getChildren().add(myColorListView);
		root.getChildren().add(colorBox);
	}
	
	public void changeList(ArrayList<String> colorList) {
		myColorList = FXCollections.observableArrayList(colorList);
		myColorListView.setItems(myColorList);
	}
	
	
}
