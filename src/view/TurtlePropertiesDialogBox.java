package view;

import java.util.Locale;
import java.util.ResourceBundle;
import slogoEnums.ViewConstants;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class TurtlePropertiesDialogBox {
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
    private VBox myDialogVBox;
    
    public TurtlePropertiesDialogBox (Shape shape) {
        initializeVbox();
        createTextParameters(shape.getTranslateX(), shape.getTranslateY(), shape.rotateProperty().get());
        createImageButton(shape);
    }

    private void initializeVbox(){
        myDialogVBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        myDialogVBox.getChildren().add(new Text(myStringResources.getString("turtleTitle")));
    }
    
    private void createTextParameters (double X, double Y, double heading) {
        String turtleCoordinate = String.format("%s" + " (%.2f,%.2f)",myStringResources.getString("turtleCoordinates"),X,ViewConstants.REVERSE_DIRECTION.getVal()*Y);
        String turtleHeading = String.format("%s" + " %.2f\u00b0", myStringResources.getString("turtleHeading"),heading);
        myDialogVBox.getChildren().addAll(new Text(turtleCoordinate),new Text(turtleHeading));
    }

    private void createImageButton(Shape shape){
        Button imageButton = new Button(myStringResources.getString("chooseImage"));
        imageButton.setOnAction(e->shape.setFill(ViewFunctions.paintFromPath(ViewFX.openFileChooser())));
        myDialogVBox.getChildren().add(imageButton);
    }

    public VBox getVBox(){
        return myDialogVBox;
    }

}
