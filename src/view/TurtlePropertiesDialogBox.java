package view;

import java.util.Locale;
import java.util.ResourceBundle;
import slogoEnums.ViewConstants;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TurtlePropertiesDialogBox {
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
    private VBox myDialogVBox;

    public TurtlePropertiesDialogBox (SimpleStringProperty imagePath, double[] coordinates) {
        myDialogVBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        myDialogVBox.getChildren().add(new Text(myStringResources.getString("turtleTitle")));
        createButton(imagePath);
    }
    
    private void createButton(SimpleStringProperty imagePath){
        Button imageButton = new Button(myStringResources.getString("chooseImage"));
        imageButton.setOnAction(e->imagePath.setValue(ViewFX.openFileChooser()));
        myDialogVBox.getChildren().add(imageButton);
    }

    public VBox getVBox(){
        return myDialogVBox;
    }

}
