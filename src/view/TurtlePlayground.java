package view;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import slogoEnums.ViewConstants;


public class TurtlePlayground {
    private Rectangle myPlayground;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",
                                                                        new Locale("en", "US"));

    public TurtlePlayground (Group root) {
        initializePlayground(root);
    }

    private void initializePlayground (Group root) {
        myPlayground =
                new Rectangle(ViewConstants.PLAYGROUND_WIDTH.getVal(),
                              ViewConstants.PLAYGROUND_HEIGHT.getVal(), Color.ROSYBROWN);
        myPlayground.toBack();
        root.getChildren().add(myPlayground);
    }

    public void changeBackground (String path) {
        List<String> imageExtArray =
                Arrays.asList(myStringResources.getString("imageFileExtension").split("\\s+"));
        for (String c : imageExtArray) {
            if (path.toLowerCase().endsWith(c)) {
                File imageFile = new File(path);
                myPlayground.setFill(new ImagePattern(new Image(imageFile.toURI().toString())));
                return;
            }
        }
        throw new NullPointerException();
    }
    
    public void changeColorBackground(String color){
        myPlayground.setFill(Color.web(color));
    }

}
