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

    public TurtlePropertiesDialogBox (SimpleStringProperty imagePath, double[] coordinates, double heading) {
        myDialogVBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        myDialogVBox.getChildren().add(new Text(myStringResources.getString("turtleTitle")));
        createTextParameters(coordinates,heading);
        createButton(imagePath);
    }
    
    private void createTextParameters (double[] coordinates, double heading) {
        String turtleCoordinate = String.format("%s" + " (%.2f,%.2f)",myStringResources.getString("turtleCoordinates"),coordinates[0],-1*coordinates[1]);
        String turtleHeading = String.format("%s" + " %.2f\u00b0", myStringResources.getString("turtleHeading"),heading);
        myDialogVBox.getChildren().addAll(new Text(turtleCoordinate),new Text(turtleHeading));
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
