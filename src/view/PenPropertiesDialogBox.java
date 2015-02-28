package view;

import java.util.Locale;
import java.util.ResourceBundle;
import slogoEnums.ViewConstants;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PenPropertiesDialogBox {
    private VBox myDialogVBox;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
    private SimpleStringProperty myPenRGB;
    private SimpleDoubleProperty myStrokeWidth;
    //TODO make stroke list work and add boolean for pen up and down
    private ObservableList<Double> myStrokeList;
    
    public PenPropertiesDialogBox(SimpleStringProperty penRGB, SimpleDoubleProperty strokeWidth, ObservableList<Double> strokeList){
        myDialogVBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        myDialogVBox.getChildren().add(new Text(myStringResources.getString("penTitle")));
        myPenRGB = penRGB;
        myStrokeWidth = strokeWidth;
        myStrokeList = strokeList;
        generateColorPicker();
        generateSlider();
        generateStrokeWidthChoiceBox();
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
        
    }

    public VBox getVBox(){
        return myDialogVBox;
    }
    
}
