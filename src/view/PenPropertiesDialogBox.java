package view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import slogoEnums.ViewConstants;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PenPropertiesDialogBox {
    private VBox myDialogVBox;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
    private SimpleStringProperty myPenRGB;
    private SimpleDoubleProperty myStrokeWidth;
    private SimpleStringProperty myStrokeType;
    private SimpleBooleanProperty myPenStatus;
    
    public PenPropertiesDialogBox(SimpleStringProperty penRGB, SimpleDoubleProperty strokeWidth, SimpleStringProperty strokeType, SimpleBooleanProperty penStatus){
        myDialogVBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        myDialogVBox.getChildren().add(new Text(myStringResources.getString("penTitle")));
        myPenRGB = penRGB;
        myStrokeWidth = strokeWidth;
        myStrokeType = strokeType;
        myPenStatus = penStatus;
        generateColorPicker();
        generateSlider();
        generateStrokeWidthChoiceBox();
        generateTogglePen();
    }

    private void generateColorPicker(){
      HBox ColorBox = new HBox();
      ColorPicker colorPick = new ColorPicker(Color.web(myPenRGB.get()));
      colorPick.setOnAction(e-> myPenRGB.setValue(toRGBCode(colorPick.getValue())));
      ColorBox.getChildren().addAll(new Text(myStringResources.getString("choosePenColor")),colorPick);
      myDialogVBox.getChildren().add(ColorBox);
    }
    
    private String toRGBCode (Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));
    }
    
    private void generateSlider(){
        HBox SliderBox = new HBox();
        Slider strokeWidth = new Slider(0, 10.0, myStrokeWidth.get());
        Text SlideValue = new Text(String.format("%.2f",myStrokeWidth.get()));
        strokeWidth.valueProperty().addListener(e-> updateValue(strokeWidth.getValue(),SlideValue));
        strokeWidth.setShowTickLabels(true);
        strokeWidth.setShowTickMarks(true);
        strokeWidth.setMajorTickUnit(2);
        strokeWidth.setMinorTickCount(1);
        SliderBox.getChildren().addAll(new Text(myStringResources.getString("choosePenWidth")),strokeWidth,SlideValue);
        myDialogVBox.getChildren().add(SliderBox);
    }
    
    private void updateValue (double value, Text slideValue) {
        myStrokeWidth.setValue(value);
        slideValue.setText(String.format("%.2f",myStrokeWidth.getValue()));
    }
    
    private void generateStrokeWidthChoiceBox(){
        HBox strokeTypeBox = new HBox();
        List<String> strokeTypeArray = Arrays.asList(myStringResources.getString("strokeWidthChoice").split("\\s+"));
        List<String> strokeTypeCSS = Arrays.asList(myStringResources.getString("strokeWidthCSS").split("\\s+"));
        ChoiceBox<String> strokeChoice = new ChoiceBox<>(FXCollections.observableArrayList(strokeTypeArray));
        strokeChoice.setValue(strokeTypeArray.get(strokeTypeCSS.indexOf(myStrokeType.getValue())));
        strokeChoice.getSelectionModel().selectedIndexProperty().addListener(e-> myStrokeType.set(strokeTypeCSS.get(strokeChoice.getSelectionModel().getSelectedIndex())));
        strokeTypeBox.getChildren().addAll(new Text(myStringResources.getString("penStroke")),strokeChoice);
        myDialogVBox.getChildren().add(strokeTypeBox);
    }
    
    private void generateTogglePen(){
        HBox toggleBox = new HBox();
        ToggleButton penToggle = new ToggleButton(); //Up is false
        createPenToggleString(penToggle);
        penToggle.selectedProperty().addListener(e-> togglePenStatus(penToggle));
        toggleBox.getChildren().addAll(new Text(myStringResources.getString("penToggle")),penToggle);
        myDialogVBox.getChildren().add(toggleBox);
    }
    
    private void togglePenStatus (ToggleButton penToggle) {
        myPenStatus.set(!myPenStatus.getValue());
        createPenToggleString(penToggle);
    }

    private void createPenToggleString (ToggleButton penToggle) {
        if(myPenStatus.get()){
            penToggle.setText(myStringResources.getString("penDown"));
        }
        else{
            penToggle.setText(myStringResources.getString("penUp"));
        }
    }

    public VBox getVBox(){
        return myDialogVBox;
    }
    
}
