package view;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TurtleDisplay {
    
    private static final int VBOX_PADDING = 10;
    private static final String ERROR_MESSAGE_NO_TURTLE = "No Turtle Found";
    private Callable<Map<Integer, Shape>> myCallableToGetTurtles;
    private Runnable myTurtleRenderCaller;
    private Map<Integer, Shape> myShapeMap;
    private Map<Integer, Point2D> myLocationMap;

    public TurtleDisplay (Callable<Map<Integer, Shape>> getTurtleMap, Runnable renderTurtles) {
        myCallableToGetTurtles = getTurtleMap;
        myTurtleRenderCaller = renderTurtles;
    }

    public void createDisplay () {
        Stage stage = new Stage();

        VBox turtleBox = new VBox(VBOX_PADDING);
        try {
            myShapeMap = myCallableToGetTurtles.call();
            myShapeMap.forEach((ID, turtle) -> turtleBox.getChildren().add(turtle));
        }
        catch (Exception e) {
            turtleBox.getChildren().add(new Text(ERROR_MESSAGE_NO_TURTLE));
        }
        getTranslatesFromMap();
        stage.setScene(new Scene(turtleBox));
        stage.show();
        stage.setOnCloseRequest(closeEvent -> {
            myShapeMap.forEach((key,shape) -> {
                shape.setTranslateX(myLocationMap.get(key).getX());
                shape.setTranslateY(myLocationMap.get(key).getY());
            });
            stage.close();
            myTurtleRenderCaller.run();
        });
    }

    private void getTranslatesFromMap () {
        myLocationMap = new HashMap<Integer, Point2D>();
        myShapeMap.forEach((key,shape) -> myLocationMap.put(key, new Point2D(shape.getTranslateX(),shape.getTranslateY())));
        myShapeMap.values().forEach(shape -> {
            shape.setTranslateX(0);
            shape.setTranslateY(0);
        });
    }

}
