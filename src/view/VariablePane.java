package view;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import slogoEnums.ViewConstants;


/**
 * This is a variable pane that has 2 lists for variables and a list for the method variables. The
 * number variables are editable on screen.
 * 
 * @author Sivaneshwaran
 */
public class VariablePane {
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",
                                                                        new Locale("en", "US"));

    private VBox myVariableBox;
    private ObservableList<String> myVariableName;
    private ObservableList<Double> myVariableValue;
    private ObservableList<String> myMethodList;

    /**
     * Constructor for VariablePane.
     * 
     * @param root Group
     * @param changeListener ChangeListener<String>
     * @param handler EventHandler<ListView.EditEvent<Double>>
     */
    public VariablePane (Group root,
                         ChangeListener<String> changeListener,
                         EventHandler<ListView.EditEvent<Double>> handler) {
        generateVariablePane(root);
        initializeVariableList(handler);
        initializeMethodList(changeListener);
    }

    /**
     * Method generateVariablePane.
     * 
     * @param root Group
     */
    public void generateVariablePane (Group root) {
        myVariableBox = new VBox();
        myVariableBox.setPadding(new Insets(ViewConstants.VARIABLE_TABLE_PADDING.getVal()));
        myVariableBox.setSpacing(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        myVariableBox.setMaxSize(ViewConstants.VARIABLE_TABLE_WIDTH.getVal(),
                                 ViewConstants.VARIABLE_TABLE_HEIGHT.getVal());
        myVariableBox.setTranslateX(ViewConstants.VARIABLE_TABLE_X.getVal());
        myVariableBox.getChildren()
                .add(new Text(myStringResources.getString("variableTableTitle")));
        root.getChildren().add(myVariableBox);
    }

    /**
     * Method addNumberVariables.
     * 
     * @param name String
     * @param value Double
     */
    public void addNumberVariables (String name, Double value) {
        if (myVariableName.contains(name)) {
            myVariableValue.set(myVariableName.indexOf(name), value);
        }
        else {
            myVariableName.add(name);
            myVariableValue.add(value);
        }
    }

    /**
     * Method setVariableValue.
     * 
     * @param index int
     * @param newValue Double
     */
    public void setVariableValue (int index, Double newValue) {
        myVariableValue.set(index, newValue);
    }

    /**
     * Method getVariableName.
     * 
     * @param index int
     * @return String
     */
    public String getVariableName (int index) {
        return myVariableName.get(index);
    }

    /**
     * Method initializeVariableList.
     * 
     * @param handler EventHandler<ListView.EditEvent<Double>>
     */
    private void initializeVariableList (EventHandler<ListView.EditEvent<Double>> handler) {
        HBox numberBox = new HBox();
        numberBox.setPrefWidth(ViewConstants.VARIABLE_TABLE_WIDTH.getVal());
        myVariableName = FXCollections.observableArrayList();
        myVariableValue = FXCollections.observableArrayList();
        ListView<String> variableNameList = new ListView<>(myVariableName);
        ListView<Double> variableValueList = new ListView<>(myVariableValue);
        makeListEditable(variableValueList, handler);
        numberBox.getChildren().addAll(variableNameList, variableValueList);
        myVariableBox.getChildren().add(numberBox);
    }

    /**
     * Method makeListEditable.
     * The try catch statement, ensures that the user adds in a proper number, when Double.NaN is
     * returned, the variableList does not update that variable
     * 
     * @param simpleList ListView<Double>
     * @param handler EventHandler<ListView.EditEvent<Double>>
     */
    private void makeListEditable (ListView<Double> simpleList,
                                   EventHandler<ListView.EditEvent<Double>> handler) {
        simpleList.setEditable(true);
        simpleList.setCellFactory(TextFieldListCell.forListView(new StringConverter<Double>() {
            @Override
            public Double fromString (String arg0) {
                try {
                    return Double.parseDouble(arg0);
                }
                catch (Exception e) {
                    return Double.NaN;
                }
            }

            @Override
            public String toString (Double arg0) {
                return Double.toString(arg0);
            }
        }));
        simpleList.setOnEditCommit(handler);
    }

    /**
     * Method addMethodVariable.
     * 
     * @param methodKey String
     */
    public void addMethodVariable (String methodKey) {
        if (!myMethodList.contains(methodKey)) {
            myMethodList.add(methodKey);
        }
    }

    /**
     * Method initializeMethodList.
     * 
     * @param listener ChangeListener<String>
     */
    private void initializeMethodList (ChangeListener<String> listener) {
        myMethodList = FXCollections.observableArrayList();
        ListView<String> methodListView = new ListView<String>(myMethodList);
        methodListView.getSelectionModel().selectedItemProperty().addListener(listener);
        myVariableBox.getChildren().addAll(new Text(myStringResources.getString("methodTable")),
                                           methodListView);
    }

}
