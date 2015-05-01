package view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import slogoEnums.ViewConstants;


/**
 * The purpose of this class is to create the buttons for the UI. There is a choicebox for language.
 * Buttons for save and load XML, change background and help Page.
 * 
 * @author Sivaneshwaran
 *
 */
public class ButtonPane {

    private Group myRoot;
    private ResourceBundle myStringResources = ResourceBundle.getBundle(
                                                                        "resources.View.ViewText",
                                                                        new Locale("en", "US"));;
    private TurtleDisplay myTurtleDisplay;
    
    /**
     * Constructor for ButtonPane.
     * 
     * @param root Group
     * @param handler EventHandler<ActionEvent>
     * @param listener ChangeListener<Number>
     * @param saveHandler EventHandler<ActionEvent>
     * @param loadHandler EventHandler<ActionEvent>
     */
    public ButtonPane (Group root,
                       EventHandler<ActionEvent> handler,
                       ChangeListener<Number> listener,
                       EventHandler<ActionEvent> saveHandler,
                       EventHandler<ActionEvent> loadHandler, Callable<Map<Integer,Shape>> getTurtleMap, Runnable renderTurtles) {
        myRoot = root;
        initializeButtonPane(listener, handler, saveHandler, loadHandler);
        myTurtleDisplay = new TurtleDisplay(getTurtleMap, renderTurtles);
    }


    /**
     * Method initializeButtonPane. This method just creates the button pant
     * 
     * @param listener ChangeListener<Number>
     * @param handler EventHandler<ActionEvent> event handler to change background image
     * @param saveHandler EventHandler<ActionEvent> event handler for saving XML
     * @param loadHandler EventHandler<ActionEvent> event handler for loading XML
     * @param getTurtleMap 
     */
    private void initializeButtonPane (ChangeListener<Number> listener,
                                       EventHandler<ActionEvent> handler,
                                       EventHandler<ActionEvent> saveHandler,
                                       EventHandler<ActionEvent> loadHandler) {
        GridPane buttonPane = new GridPane();
        Node[] nodeArray = {
                            generateLanguageBox(listener),
                            createButton(handler,
                                         myStringResources.getString("changeImage")),
                            createButton(e -> loadHelpPage(),
                                         myStringResources.getString("helpScreen")),
                            createButton(saveHandler, myStringResources.getString("saveButton")),
                            createButton(loadHandler, myStringResources.getString("loadButton")),
                            createButton(e -> myTurtleDisplay.createDisplay(), myStringResources.getString("turtleDisplay"))};
        for (int col = 0; col < nodeArray.length; col++) {
            buttonPane.add(nodeArray[col], col, 1);
        }

        buttonPane.setTranslateY(ViewConstants.BUTTON_START_Y.getVal());
        myRoot.getChildren().add(buttonPane);
    }

    /**
     * Method generateLanguageBox.
     * 
     * @param listener ChangeListener<Number>
     * @return ChoiceBox<String>
     */
    private ChoiceBox<String> generateLanguageBox (
                                                   ChangeListener<Number> listener) {
        List<String> languageArray =
                Arrays.asList(myStringResources.getString(
                                                          "languageText").split("\\s+"));
        ChoiceBox<String> languageBox =
                new ChoiceBox<String>(
                                      FXCollections.observableArrayList(languageArray));
        languageBox.setValue(languageArray.get(1));
        languageBox.setMinHeight(ViewConstants.BUTTON_HEIGHT.getVal());
        languageBox.setMinWidth(ViewConstants.BUTTON_WIDTH.getVal());
        languageBox.getSelectionModel().selectedIndexProperty()
                .addListener(listener);
        return languageBox;
    }

    /**
     * Method createButton. This method is like a button factory where it creates a button and
     * returns it to be added to the UI
     * 
     * @param handler EventHandler<ActionEvent>
     * @param text String
     * @return Button
     */
    private Button createButton (EventHandler<ActionEvent> handler, String text) {
        Button button = new Button(text);
        button.setStyle(myStringResources.getString("buttonStyle"));
        button.setMinHeight(ViewConstants.BUTTON_HEIGHT.getVal());
        button.setMinWidth(ViewConstants.BUTTON_WIDTH.getVal());
        button.setOnAction(handler);
        return button;
    }

    private void loadHelpPage () {
        HelpPage newPage = new HelpPage(myStringResources.getString("helpPage"));
        try {
            newPage.start(new Stage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
