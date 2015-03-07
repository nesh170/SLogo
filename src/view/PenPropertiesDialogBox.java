package view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import Model.Pen;
import slogoEnums.ViewConstants;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * The pen properties class generates a VBox which contains the visual elements of the pen such as
 * pen color, stroke width, type of stroke, pen up or down. It also allows the user to interact with
 * the values.
 * 
 * @author Sivaneshwaran
 */
public class PenPropertiesDialogBox {
    private VBox myDialogVBox;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",
                                                                        new Locale("en", "US"));
    private Pen myPen;

    /**
     * Constructor for PenPropertiesDialogBox.
     * 
     * @param pen Pen
     * @param colorList List<String>
     */
    public PenPropertiesDialogBox (Pen pen, List<String> colorList) {
        myDialogVBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        myDialogVBox.getChildren().add(new Text(myStringResources.getString("penTitle")));
        myPen = pen;
        generateColorPicker(colorList); // need to check whether it is working
        generateSlider();
        generateStrokeWidthChoiceBox();
        generateTogglePen();
    }

    /**
     * Method generateColorPicker.
     * 
     * @param colorList List<String>
     */
    private void generateColorPicker (List<String> colorList) {
        ChoiceBox<String> colorChoice =
                choiceBoxGenerator(colorList, colorList.get(myPen.getColorIndex()),
                                   "choosePenColor");
        colorChoice
                .getSelectionModel()
                .selectedIndexProperty()
                .addListener(e -> myPen.setColorIndex(colorChoice.getSelectionModel()
                        .getSelectedIndex()));
    }

    private void generateSlider () {
        HBox SliderBox = new HBox();
        Slider strokeWidth = new Slider(0, 10.0, myPen.getThickness());
        Text SlideValue = new Text(String.format("%.2f", myPen.getThickness()));
        strokeWidth.valueProperty()
                .addListener(e -> updateValue(strokeWidth.getValue(), SlideValue));
        strokeWidth.setShowTickLabels(true);
        strokeWidth.setShowTickMarks(true);
        strokeWidth.setMajorTickUnit(2);
        strokeWidth.setMinorTickCount(1);
        SliderBox.getChildren().addAll(new Text(myStringResources.getString("choosePenWidth")),
                                       strokeWidth, SlideValue);
        myDialogVBox.getChildren().add(SliderBox);
    }

    /**
     * Method updateValue.
     * 
     * @param value double
     * @param slideValue Text
     */
    private void updateValue (double value, Text slideValue) {
        myPen.setPenThickness(value);
        slideValue.setText(String.format("%.2f", myPen.getThickness()));
    }

    private void generateStrokeWidthChoiceBox () {
        List<String> strokeTypeArray =
                Arrays.asList(myStringResources.getString("strokeWidthChoice").split("\\s+"));
        ChoiceBox<String> strokeChoice =
                choiceBoxGenerator(strokeTypeArray, myPen.getPenStroke(), "penStroke"); // this
                                                                                        // string
                                                                                        // literal
                                                                                        // is used
                                                                                        // to get
                                                                                        // the
                                                                                        // string
                                                                                        // from
                                                                                        // resource
                                                                                        // bundle
        strokeChoice
                .getSelectionModel()
                .selectedIndexProperty()
                .addListener(e -> myPen.setStroke(strokeTypeArray.get(strokeChoice
                        .getSelectionModel().getSelectedIndex())));
    }

    /**
     * Method choiceBoxGenerator.
     * 
     * @param list List<String>
     * @param initialValue String
     * @param title String
     * @return ChoiceBox<String>
     */
    private ChoiceBox<String> choiceBoxGenerator (List<String> list,
                                                  String initialValue,
                                                  String title) {
        HBox tempBox = new HBox();
        ChoiceBox<String> tempChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(list));
        tempChoiceBox.setValue(initialValue);
        tempBox.getChildren().addAll(new Text(myStringResources.getString(title)), tempChoiceBox);
        myDialogVBox.getChildren().add(tempBox);
        return tempChoiceBox;
    }

    private void generateTogglePen () {
        // Up or down pen
        HBox toggleBox = new HBox();
        ToggleButton penToggle = new ToggleButton(); // Up is false
        createPenToggleString(penToggle);
        penToggle.selectedProperty().addListener(e -> togglePenStatus(penToggle));
        toggleBox.getChildren().addAll(new Text(myStringResources.getString("penToggle")),
                                       penToggle);
        myDialogVBox.getChildren().add(toggleBox);
    }

    /**
     * Method togglePenStatus.
     * 
     * @param penToggle ToggleButton
     */
    private void togglePenStatus (ToggleButton penToggle) {
        myPen.setPenDown(!myPen.isDown());
        createPenToggleString(penToggle);
    }

    /**
     * Method createPenToggleString.
     * 
     * @param penToggle ToggleButton
     */
    private void createPenToggleString (ToggleButton penToggle) {
        if (myPen.isDown()) {
            penToggle.setText(myStringResources.getString("penDown"));
        }
        else {
            penToggle.setText(myStringResources.getString("penUp"));
        }
    }

    /**
     * Method getVBox.
     * 
     * @return VBox
     */
    public VBox getVBox () {
        return myDialogVBox;
    }

}
