package view;

import java.util.Locale;
import java.util.ResourceBundle;
import slogoEnums.ViewConstants;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DialogBox {
    private VBox myDialogVBox;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
    private SimpleStringProperty myImagePath;
    private SimpleStringProperty myPenRGB;
    
    public DialogBox(SimpleStringProperty ImagePath, SimpleStringProperty penRGB){
        initializeDialogBox();
        myImagePath = ImagePath;
        myPenRGB = penRGB;
        initilizeColorPicker();
    }

    private void initializeDialogBox () {
       myDialogVBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
       Button shapeButton = new Button();
       shapeButton.setText(myStringResources.getString("chooseImage"));
       shapeButton.setOnAction(e->myImagePath.set(ViewFX.openFileChooser()));
       myDialogVBox.getChildren().add(shapeButton);
    }
    
    private void initilizeColorPicker(){
      ColorPicker colorPick = new ColorPicker(Color.web(myPenRGB.get()));
      colorPick.setOnAction(e-> myPenRGB.setValue(toRGBCode(colorPick.getValue())));
      myDialogVBox.getChildren().addAll(new Text(myStringResources.getString("choosePenColor")),colorPick);
    }
    
    private String toRGBCode (Color color) {
        return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));
    }

    public VBox getVBox(){
        return myDialogVBox;
    }
    
}
