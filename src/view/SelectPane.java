package view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

public class SelectPane {
	
	private Group myRoot;
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText", new Locale("en", "US"));
	
	
	public SelectPane(Group root, EventHandler<ActionEvent> handler,ChangeListener<Number> listener) {
		myRoot = root;
		initializeButtonPane(listener, handler);
	}
	
	private void initializeButtonPane(ChangeListener<Number> listener, EventHandler<ActionEvent> handler) {
		// TODO Auto-generated method stub
		GridPane selectPane = new GridPane();
		selectPane.add(generateColorBox(listener), 0, 1);
		selectPane.setTranslateY(ViewConstants.BUTTON_START_Y.getVal());
		myRoot.getChildren().add(selectPane);
	}

	private ChoiceBox<String> generateColorBox(ChangeListener<Number> listener) {
		List<String> colorList = Arrays.asList(myStringResources.getString("allColors").split("\\s+"));
		ChoiceBox<String> colorBox = new ChoiceBox<String>(FXCollections.observableArrayList(colorList));
		colorBox.setValue(colorList.get(1));
		colorBox.setMinHeight(ViewConstants.BUTTON_HEIGHT.getVal());
		colorBox.setMinWidth(ViewConstants.BUTTON_WIDTH.getVal());
		colorBox.getSelectionModel().selectedIndexProperty().addListener(listener);
		return colorBox;
	}
	
	
}
