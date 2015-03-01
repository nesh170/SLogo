package view;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sLogo_team02.Controller;
import slogoEnums.ViewConstants;


public class ViewFX extends ViewAbstract {
    private Group myRoot;
    private Group myLineRoot;
    private Group myTurtleRoot;
    private CodePane myCodeElements;
    private VariablePane myVariableElements;
    private TurtlePlayground myPlayground;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",
                                                                        new Locale("en", "US"));
    private Map<Integer, ViewTurtle> myViewTurtlesMap;
    private Controller myController;

    public ViewFX (Controller controller) {
        myController = controller;
        initializeView();
    }

    private void initializeView () {
        myRoot = new Group();
        myLineRoot = new Group();
        myTurtleRoot = new Group();
        myViewTurtlesMap = new HashMap<>();
        new ButtonPane(myRoot, e -> changeBackgroundImage(), new ChangeListener<Number>() {
            @Override
            public void changed (ObservableValue<? extends Number> ov, Number value, Number newValue) {
                changeLanuageinController(newValue.intValue());
            }
        });
        myPlayground = new TurtlePlayground(myRoot);
        Scene viewScene =
                new Scene(myRoot, ViewConstants.STAGE_WIDTH.getVal(),
                          ViewConstants.STAGE_HEIGHT.getVal(), Color.ALICEBLUE);
        myCodeElements = new CodePane(myRoot, e -> pushCodeToController());
        setUpVariablePane();
        myRoot.getChildren().addAll(myLineRoot, myTurtleRoot);
        myController.setScene(viewScene);
    }

    private void setUpVariablePane () {
        myVariableElements = new VariablePane(myRoot, new ChangeListener<String>() {
            @Override
            public void changed (ObservableValue<? extends String> ov,
                                 String oldString,
                                 String newString) {
                myCodeElements.fillCodeArea(newString);
            }
        }, new EventHandler<ListView.EditEvent<Double>>() {
            @Override
            public void handle (ListView.EditEvent<Double> t) {
                if (!t.getNewValue().equals(Double.NaN)) {
                    updateVariableFromView(t);
                }
                else {
                    printError(myStringResources.getString("wrongNumberType"));
                }
            }
        });
    }

    @Override
    public void drawTurtle (double X, double Y, int ID) {
        Line tempLine =
                myViewTurtlesMap.get(ID).drawLine(new Point2D(X + ViewConstants.ORIGIN_X.getVal(),
                                                              ViewConstants.REVERSE_DIRECTION
                                                                      .getVal() *
                                                                      Y +
                                                                      ViewConstants.ORIGIN_Y
                                                                              .getVal()));
        myLineRoot.getChildren().add(tempLine);
        moveTurtle(X, Y, ID);
    }

    @Override
    public void moveTurtle (double X, double Y, int ID) {
        Point2D point = new Point2D(X, ViewConstants.REVERSE_DIRECTION.getVal() * Y);
        myViewTurtlesMap.get(ID).setNewLocation(point);
    }

    @Override
    public void rotateTurtle (double angle, int ID) {
        myViewTurtlesMap.get(ID).rotate(angle);
    }

    @Override
    public void clearScreen () {
        myLineRoot.getChildren().clear();
        myTurtleRoot.getChildren().clear();
        myCodeElements.clearTerminal();
    }

    @Override
    public void printError (String message) {
        myCodeElements.addTerminalText(message, Color.RED);
    }

    @Override
    public void printMessage (String message) {
        myCodeElements.addTerminalText(message, Color.YELLOW);
    }

    @Override
    public void addTurtle (double X, double Y, int ID) {
        Point2D point = new Point2D(X, Y);
        SimpleBooleanProperty penStatus = new SimpleBooleanProperty(true);
        penStatus.addListener(e-> setPenStatus(penStatus.getValue(), ID));
        ViewTurtle vt = new ViewTurtle(point, ViewConstants.TURTLE_SIZE.getVal(), ID,penStatus);
        vt.setNewLocation(point);
        myViewTurtlesMap.put(ID, vt);
        myTurtleRoot.getChildren().add(vt.getViewTurtles());
    }

    @Override
    public void addVariable (String variableName, Double value) {
        myVariableElements.addNumberVariables(variableName, value);
    }

    @Override
    public void addMethodVariable (String methodName) {
        myVariableElements.addMethodVariable(methodName);
    }
    
    @Override
    public void hideTurtle (int ID) {
       myViewTurtlesMap.get(ID).hide();
    }

    @Override
    public void showTurtle (int ID) {
       myViewTurtlesMap.get(ID).show();
    }

    private void pushCodeToController () {
        myVariableElements.clearList();
        myController.executeProgram(myCodeElements.getCodeData());
    }

    private void updateVariableFromView (ListView.EditEvent<Double> t) {
        try {
            myVariableElements.setVariableValue(t.getIndex(), t.getNewValue());
            myController.updateVariable(myVariableElements.getVariableName(t.getIndex()),
                                        t.getNewValue());
        }
        catch (NumberFormatException e) {
            printError(myStringResources.getString("wrongNumberType"));
        }
    }

    private void changeBackgroundImage () {
        String imageLocation = openFileChooser();
        try{
                myPlayground.changeBackground(imageLocation);
        }
        catch(IllegalArgumentException | NullPointerException e){
                printError(myStringResources.getString("invalidImageType"));
        }
    }
    
    private void setPenStatus(Boolean penUporDown, int ID){ //Up is false
        myController.setPenUporDown(penUporDown, ID);
    }

    public static String openFileChooser () {
    	FileChooser fileChooser = new FileChooser();
	fileChooser.setInitialDirectory(new File((System.getProperty("user.dir"))));
	File file = fileChooser.showOpenDialog(new Stage());
	if(file==null){
	    return "";
	}
	return file.toString();
    }
    

    private void changeLanuageinController (int index) {
        ResourceBundle myStringResources =
                ResourceBundle.getBundle("resources.View.ViewText", new Locale("en", "US"));
        myController
                .changeLanguage(myStringResources.getString("languageFile").split("\\s+")[index]);
    }


}
