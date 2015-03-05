package view;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import sLogo_team02.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import slogoEnums.ViewConstants;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class ViewFX extends ViewAbstract {
    private Group myRoot;
    private Group myLineRoot;
    private Group myShapeRoot;
    private CodePane myCodeElements;
    private VariablePane myVariableElements;
    private TurtlePlayground myPlayground;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",
                                                                        new Locale("en", "US"));
    private Map<Integer, Shape> myShapeMap;
    private Controller myController;

    public ViewFX (Controller controller) {
        myController = controller;
        initializeView();
    }

    private void initializeView () {
        myRoot = new Group();
        myLineRoot = new Group();
        myShapeRoot = new Group();
        myShapeMap = new HashMap<>();
        //TODO change this
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
        myRoot.getChildren().addAll(myLineRoot, myShapeRoot);
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
    public void clearScreen () {
        myLineRoot.getChildren().clear();
        myShapeRoot.getChildren().clear();
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
    public void addVariable (String variableName, Double value) {
        myVariableElements.addNumberVariables(variableName, value);
    }

    @Override
    public void addMethodVariable (String methodName) {
        myVariableElements.addMethodVariable(methodName);
    }
    

    private void pushCodeToController () {
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
        ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText", new Locale("en", "US"));
        myController.changeLanguage(myStringResources.getString("languageFile").split("\\s+")[index]);
    }

    @Override
    public void drawShape(double X, double Y,int ID,String penColor,double strokeWidth,String strokeType) {
        double[] startCoordinates = ViewFunctions.rectToFXCoordinates(myShapeMap.get(ID).getTranslateX(), myShapeMap.get(ID).getTranslateY());
        double[] endCoordinates = ViewFunctions.rectToFXCoordinates(X, Y);
        Line turtleLine = new Line(startCoordinates[0],startCoordinates[1],endCoordinates[0],endCoordinates[1]);
        turtleLine.setStroke(Color.web(penColor));
        turtleLine.setStrokeWidth(strokeWidth);
        turtleLine.setStyle(myStringResources.getString(strokeType));
        myLineRoot.getChildren().add(turtleLine);     
    }

    @Override
    public void moveShape (double X, double Y, int ID) {
        double[] newCoordinates = ViewFunctions.rectToFXCoordinates(X, Y);
        myShapeMap.get(ID).setTranslateX(newCoordinates[0]);
        myShapeMap.get(ID).setTranslateY(newCoordinates[1]);
    }

    @Override
    public void rotateShape (double angle, int ID) {
        myShapeMap.get(ID).rotateProperty().setValue(angle);
    }

    @Override
    public void addShape (String shapeType, double X, double Y, int ID) {
       ShapeFactory factory = new ShapeFactory();
       Shape tempShape = factory.makeShape(shapeType);
       tempShape.setTranslateX(ViewConstants.ORIGIN_X.getVal());tempShape.setTranslateY(ViewConstants.ORIGIN_Y.getVal());
       tempShape.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle (MouseEvent mouseButton) {
            if(mouseButton.getButton()==MouseButton.PRIMARY){
                //TODO open Dialog
                System.out.println("Primary");
            }
            else if(mouseButton.getButton()==MouseButton.SECONDARY){
                //TODO set active
                System.out.println("Secondary");
            }  
        }
    });
       myShapeMap.put(ID, tempShape);
       myShapeRoot.getChildren().add(tempShape);
    }

    @Override
    public void visibleShape(boolean hideOrShow, int ID) {
        // TODO Auto-generated method stub
        
    }


}
