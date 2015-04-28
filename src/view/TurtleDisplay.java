package view;

import java.util.Map;
import java.util.concurrent.Callable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TurtleDisplay {
    
    private static final int VBOX_PADDING = 10;
    private static final String ERROR_MESSAGE_NO_TURTLE = "No Turtle Found";
    private Callable<Map<Integer, Shape>> myCallableToGetTurtles;

    public TurtleDisplay (Callable<Map<Integer, Shape>> getTurtleMap) {
        myCallableToGetTurtles = getTurtleMap;
    }

    public void createDisplay () {
        Stage stage = new Stage();
        VBox turtleBox = new VBox(VBOX_PADDING);
        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            myCallableToGetTurtles.call().forEach((ID, turtle) -> turtleBox.getChildren().add(turtle));
           myCallableToGetTurtles.call().forEach((key,val)-> System.out.println(key + "turtle" + val.getTranslateX() + " " + val.getTranslateY()));
        }
        catch (Exception e) {
            turtleBox.getChildren().add(new Text(ERROR_MESSAGE_NO_TURTLE));
        }
        stage.setScene(new Scene(turtleBox));
        stage.show();
    }

}
