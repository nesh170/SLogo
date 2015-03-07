package view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import slogoEnums.ViewConstants;


/**
 * The turtle playground is a rectangle which allows the user to differentiate the turtle area from
 * the other parts of the UI. The user is also able to change the colors/ background image
 * 
 * @author Sivaneshwaran
 */
public class TurtlePlayground {
    private Rectangle myPlayground;

    /**
     * Constructor for TurtlePlayground.
     * 
     * @param root Group
     */
    public TurtlePlayground (Group root) {
        initializePlayground(root);
    }

    /**
     * Method initializePlayground.
     * 
     * @param root Group
     */
    private void initializePlayground (Group root) {
        myPlayground =
                new Rectangle(ViewConstants.PLAYGROUND_WIDTH.getVal(),
                              ViewConstants.PLAYGROUND_HEIGHT.getVal(), Color.ROSYBROWN);
        myPlayground.toBack();
        root.getChildren().add(myPlayground);
    }

    /**
     * Method changeBackground.
     * 
     * @param path String
     */
    public void changeBackground (String path) {
        myPlayground.setFill(ViewFunctions.paintFromPath(path));
    }

    /**
     * Method changeColorBackground.
     * 
     * @param color String
     */
    public void changeColorBackground (String color) {
        myPlayground.setFill(Color.web(color));
    }

}
