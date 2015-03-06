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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import slogoEnums.ViewConstants;

public class ButtonPane {

	private Group myRoot;
	private ResourceBundle myStringResources = ResourceBundle.getBundle(
			"resources.View.ViewText", new Locale("en", "US"));;

	public ButtonPane(Group root, EventHandler<ActionEvent> handler,
			ChangeListener<Number> listener,EventHandler<ActionEvent> saveHandler, EventHandler<ActionEvent> loadHandler) {
		myRoot = root;
		initializeButtonPane(listener, handler,saveHandler,loadHandler);
	}

	private void initializeButtonPane(ChangeListener<Number> listener,
			EventHandler<ActionEvent> handler,EventHandler<ActionEvent> saveHandler, EventHandler<ActionEvent> loadHandler) {
		GridPane buttonPane = new GridPane();
		Node[] nodeArray = {
				generateLanguageBox(listener),
				createButton(handler,
						myStringResources.getString("changeImage")),
				createButton(e -> loadHelpPage(),
						myStringResources.getString("helpScreen")),
				createButton(saveHandler,myStringResources.getString("saveButton")),
				createButton(loadHandler, myStringResources.getString("loadButton"))};
		for (int col = 0; col < nodeArray.length; col++) {
			buttonPane.add(nodeArray[col], col, 1);
		}

		buttonPane.setTranslateY(ViewConstants.BUTTON_START_Y.getVal());
		myRoot.getChildren().add(buttonPane);
	}

	private ChoiceBox<String> generateLanguageBox(
			ChangeListener<Number> listener) {
		List<String> languageArray = Arrays.asList(myStringResources.getString(
				"languageText").split("\\s+"));
		ChoiceBox<String> languageBox = new ChoiceBox<String>(
				FXCollections.observableArrayList(languageArray));
		languageBox.setValue(languageArray.get(1));
		languageBox.setMinHeight(ViewConstants.BUTTON_HEIGHT.getVal());
		languageBox.setMinWidth(ViewConstants.BUTTON_WIDTH.getVal());
		languageBox.getSelectionModel().selectedIndexProperty()
				.addListener(listener);
		return languageBox;
	}

	private Button createButton(EventHandler<ActionEvent> handler, String text) {
		Button button = new Button(text);
		button.setStyle(myStringResources.getString("buttonStyle"));
		button.setMinHeight(ViewConstants.BUTTON_HEIGHT.getVal());
		button.setMinWidth(ViewConstants.BUTTON_WIDTH.getVal());
		button.setOnAction(handler);
		return button;
	}

	private void loadHelpPage() {
		HelpPage newPage = new HelpPage(myStringResources.getString("helpPage"));
		try {
			newPage.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
