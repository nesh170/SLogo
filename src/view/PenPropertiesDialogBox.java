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

public class PenPropertiesDialogBox {
    private VBox myDialogVBox;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
    private Pen myPen;
    
    public PenPropertiesDialogBox(Pen pen, List<String> colorList){
        myDialogVBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        myDialogVBox.getChildren().add(new Text(myStringResources.getString("penTitle")));
        myPen = pen;
        generateColorPicker(colorList); //need to check whether it is working
        generateSlider();
        generateStrokeWidthChoiceBox();
        generateTogglePen();
    }

    private void generateColorPicker(List<String> colorList){
      ChoiceBox<String> colorChoice = choiceBoxGenerator(colorList, colorList.get(myPen.getColorIndex()), "choosePenColor");
      colorChoice.getSelectionModel().selectedIndexProperty().addListener(e-> myPen.setColorIndex(colorChoice.getSelectionModel().getSelectedIndex()));
    }
    
    private void generateSlider(){
        HBox SliderBox = new HBox();
        Slider strokeWidth = new Slider(0, 10.0, myPen.getThickness());
        Text SlideValue = new Text(String.format("%.2f",myPen.getThickness()));
        strokeWidth.valueProperty().addListener(e-> updateValue(strokeWidth.getValue(),SlideValue));
        strokeWidth.setShowTickLabels(true);
        strokeWidth.setShowTickMarks(true);
        strokeWidth.setMajorTickUnit(2);
        strokeWidth.setMinorTickCount(1);
        SliderBox.getChildren().addAll(new Text(myStringResources.getString("choosePenWidth")),strokeWidth,SlideValue);
        myDialogVBox.getChildren().add(SliderBox);
    }
    
    private void updateValue (double value, Text slideValue) {
        myPen.setPenThickness(value);
        slideValue.setText(String.format("%.2f",myPen.getThickness()));
    }
    
    private void generateStrokeWidthChoiceBox(){
        List<String> strokeTypeArray = Arrays.asList(myStringResources.getString("strokeWidthChoice").split("\\s+"));
        ChoiceBox<String> strokeChoice= choiceBoxGenerator(strokeTypeArray,myPen.getPenStroke(),"penStroke"); //this string literal is used to get the string from resource bundle
        strokeChoice.getSelectionModel().selectedIndexProperty().addListener(e-> myPen.setStroke(strokeChoice.getSelectionModel().getSelectedItem()));
    }
    
    private ChoiceBox<String> choiceBoxGenerator(List<String> list, String initialValue, String title){
        HBox tempBox = new HBox();
        ChoiceBox<String> tempChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(list));
        tempChoiceBox.setValue(initialValue);
        tempBox.getChildren().addAll(new Text(myStringResources.getString(title)),tempChoiceBox);
        myDialogVBox.getChildren().add(tempBox);
        return tempChoiceBox;
    }
    
    private void generateTogglePen(){
        //Up or down pen
        HBox toggleBox = new HBox();
        ToggleButton penToggle = new ToggleButton(); //Up is false
        createPenToggleString(penToggle);
        penToggle.selectedProperty().addListener(e-> togglePenStatus(penToggle));
        toggleBox.getChildren().addAll(new Text(myStringResources.getString("penToggle")),penToggle);
        myDialogVBox.getChildren().add(toggleBox);
    }
    
    private void togglePenStatus (ToggleButton penToggle) {
        myPen.setPenDown(!myPen.isDown());;
        createPenToggleString(penToggle);
    }

    private void createPenToggleString (ToggleButton penToggle) {
        if(myPen.isDown()){
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
