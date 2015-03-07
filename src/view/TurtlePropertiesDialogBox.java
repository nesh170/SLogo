package view;

import java.util.Locale;
import java.util.ResourceBundle;
import slogoEnums.ViewConstants;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;


/**
 * This class generates a VBox which has the information such as turtle position and turtle heading.
 * It also allows the user to change background with it.
 * 
 * @author Jangsoon Park
 */
public class TurtlePropertiesDialogBox {
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",
                                                                        new Locale("en", "US"));
    private VBox myDialogVBox;

    /**
     * Constructor for TurtlePropertiesDialogBox.
     * 
     * @param shape Shape
     */
    public TurtlePropertiesDialogBox (Shape shape) {
        initializeVbox();
        createTextParameters(shape.getTranslateX(), shape.getTranslateY(), shape.rotateProperty()
                .get());
        createImageButton(shape);
    }

    private void initializeVbox () {
        myDialogVBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        myDialogVBox.getChildren().add(new Text(myStringResources.getString("turtleTitle")));
    }

    /**
     * Method createTextParameters.
     * 
     * @param X double
     * @param Y double
     * @param heading double
     */
    private void createTextParameters (double X, double Y, double heading) {
        String turtleCoordinate =
                String.format("%s" + " (%.2f,%.2f)",
                              myStringResources.getString("turtleCoordinates"), X,
                              ViewConstants.REVERSE_DIRECTION.getVal() * Y);
        String turtleHeading =
                String.format("%s" + " %.2f\u00b0", myStringResources.getString("turtleHeading"),
                              heading);
        myDialogVBox.getChildren().addAll(new Text(turtleCoordinate), new Text(turtleHeading));
    }

    /**
     * Method createImageButton.
     * 
     * @param shape Shape
     */
    private void createImageButton (Shape shape) {
        Button imageButton = new Button(myStringResources.getString("chooseImage"));
        imageButton.setOnAction(e -> shape.setFill(ViewFunctions.paintFromPath(ViewFX
                .openFileChooser(true))));
        myDialogVBox.getChildren().add(imageButton);
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
