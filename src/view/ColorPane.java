package view;

import java.util.ArrayList;

import slogoEnums.ViewConstants;
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
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < colorList.size(); i++) {
			String added = temp.get(i)+" : "+i+1;
			temp.add(added);
		}
		myColorList = FXCollections.observableArrayList(temp);
		VBox colorBox = new VBox();
		colorBox.setTranslateY(ViewConstants.COLORPANE_Y.getVal());
		colorBox.setTranslateX(ViewConstants.COLORPANE_X.getVal());
	    myColorListView.setItems(myColorList);
	    myColorListView.setPrefSize(ViewConstants.COLORPANE_WIDTH.getVal(),ViewConstants.COLORPANE_HEIGHT.getVal());
		myColorListView.setMaxSize(ViewConstants.COLORPANE_WIDTH.getVal(),ViewConstants.COLORPANE_HEIGHT.getVal());
	    colorBox.getChildren().add(myColorListView);
		root.getChildren().add(colorBox);
	}
	
	public void changeList(ArrayList<String> colorList) {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < colorList.size(); i++) {
			String added = temp.get(i)+" : "+i+1;
			temp.add(added);
		}
		myColorList = FXCollections.observableArrayList(temp);
		myColorListView.setItems(myColorList);
	}
	
	
}
