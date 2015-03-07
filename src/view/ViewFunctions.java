package view;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import slogoEnums.ViewConstants;


/**
 * This is some functions/methods used for calculations in the view.
 * 
 * @author Sivaneshwaran
 */
public class ViewFunctions {

    /**
     * Method rectToFXCoordinates
     * 
     * @param X double
     * @param Y double
     * @return double[]
     */
    public static double[] rectToFXCoordinates (double X, double Y) {
        double[] rectCoordinates = new double[2];
        rectCoordinates[0] = X + ViewConstants.ORIGIN_X.getVal();
        rectCoordinates[1] =
                Y * ViewConstants.REVERSE_DIRECTION.getVal() + ViewConstants.ORIGIN_Y.getVal();
        return rectCoordinates;
    }

    /**
     * Method paintFromPath.
     * 
     * @param path String
     * @return Paint
     */
    public static Paint paintFromPath (String path) {
        ResourceBundle stringResources =
                ResourceBundle.getBundle("resources.View.ViewText", new Locale("en", "US"));
        List<String> imageExtArray =
                Arrays.asList(stringResources.getString("imageFileExtension").split("\\s+"));
        for (String c : imageExtArray) {
            if (path.toLowerCase().endsWith(c)) {
                File imageFile = new File(path);
                return new ImagePattern(new Image(imageFile.toURI().toString()));
            }
        }
        return Color.BEIGE;
    }

}
