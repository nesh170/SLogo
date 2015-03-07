package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import slogoEnums.ViewConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ColorPane {
	private ListView<String> myColorListView = new ListView<String>();
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	
	public ColorPane(Group root, List<String> colorList) {
		initializeSuggestedView(root, colorList);		
	}
	
	private void initializeSuggestedView(Group root, List<String> colorList) {
		VBox colorBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
		colorBox.setTranslateY(ViewConstants.COLORPANE_Y.getVal());
		colorBox.setTranslateX(ViewConstants.COLORPANE_X.getVal());
	        colorBox.setPrefSize(ViewConstants.COLORPANE_WIDTH.getVal(),ViewConstants.COLORPANE_HEIGHT.getVal());
		colorBox.setMaxSize(ViewConstants.COLORPANE_WIDTH.getVal(),ViewConstants.COLORPANE_HEIGHT.getVal());
	        colorBox.getChildren().addAll(new Text(myStringResources.getString("colorPalette")),myColorListView);
		root.getChildren().add(colorBox);
	}

    private void loopFormatStringArray (List<String> colorList) {
        List<String> temp = new ArrayList<String>();
        for(int i = 0; i < colorList.size(); i++) {
		        String added = colorList.get(i)+" : "+ Integer.toString(i);
			temp.add(added);
		}
        ObservableList<String> ColorList = FXCollections.observableArrayList(temp);
        myColorListView.setItems(ColorList);
    }
	
	public void changeList(List<String> colorList) {
		myColorListView.setItems(FXCollections.observableArrayList());
		loopFormatStringArray(colorList);
	}
	
	
}
