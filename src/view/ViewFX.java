package view;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import sLogo_team02.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import slogoEnums.ViewConstants;
import javafx.scene.control.ListView;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
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
    private ColorPane myColorElements;
    private CodePane myCodeElements;
    private VariablePane myVariableElements;
    private TurtlePlayground myPlayground;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
    private Map<Integer, Shape> myShapeMap;
    private Controller myController;
    public static final Map<Boolean,Effect> ACTIVE_TURTLE_EFFECT = new HashMap<>();
     static{
         ACTIVE_TURTLE_EFFECT.put(true, new Glow(1.0));
         ACTIVE_TURTLE_EFFECT.put(false, new Glow(0.0));
     }
     
    public ViewFX (Controller controller) {
        myController = controller;
        initializeView();
    }

    private void initializeView () {
        myRoot = new Group();
        myLineRoot = new Group();
        myShapeRoot = new Group();
        myShapeMap = new HashMap<>();
        new ButtonPane(myRoot, e -> changeBackgroundImage(), new ChangeListener<Number>() {
            @Override
            public void changed (ObservableValue<? extends Number> ov, Number value, Number newValue) {
                changeLanuageinController(newValue.intValue());
            }
        });
        myPlayground = new TurtlePlayground(myRoot);
        myCodeElements = new CodePane(myRoot, e -> pushCodeToController());
        setUpVariablePane();
        myRoot.getChildren().addAll(myLineRoot, myShapeRoot);
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
    public void drawShape(double X, double Y,int ID,String penColor,double strokeWidth,String strokeType) {
        double[] startCoordinates = ViewFunctions.rectToFXCoordinates(myShapeMap.get(ID).getTranslateX(), ViewConstants.REVERSE_DIRECTION.getVal()*myShapeMap.get(ID).getTranslateY());
        System.out.println(myShapeMap.get(ID).getTranslateX() + " " + myShapeMap.get(ID).getTranslateY());
        double[] endCoordinates = ViewFunctions.rectToFXCoordinates(X, Y);
        Line turtleLine = new Line(startCoordinates[0],startCoordinates[1],endCoordinates[0],endCoordinates[1]);
        turtleLine.setStroke(Color.web(penColor));
        turtleLine.setStrokeWidth(strokeWidth);
        turtleLine.setStyle(myStringResources.getString(strokeType.toLowerCase()));
        moveShape(X, Y, ID);
        myLineRoot.getChildren().add(turtleLine);     
    }

    @Override
    public void moveShape (double X, double Y, int ID) {
        myShapeMap.get(ID).setTranslateX(X);
        myShapeMap.get(ID).setTranslateY(ViewConstants.REVERSE_DIRECTION.getVal()*Y);
    }

    @Override
    public void rotateShape (double angle, int ID) {
        myShapeMap.get(ID).rotateProperty().setValue(angle);
    }

    @Override
    public void addShape (String shapeType, double X, double Y, int ID) {
       ShapeFactory factory = new ShapeFactory();
       Shape tempShape = factory.makeShape(shapeType);
       tempShape.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle (MouseEvent mouseButton) {
            if(mouseButton.getButton()==MouseButton.PRIMARY){
                //TODO open Dialog
                System.out.println("Primary");
            }
            else if(mouseButton.getButton()==MouseButton.SECONDARY){
                myController.setToggleActive(ID);
            }  
        }
    });
       myShapeMap.put(ID, tempShape);
       visualActiveShape(true, ID);
       myShapeRoot.getChildren().add(tempShape);
    }

    @Override
    public void visibleShape(boolean hideOrShow, int ID) {
        myShapeMap.get(ID).setVisible(hideOrShow);
    }
    
    //true is active, false is inactive
    @Override
    public void visualActiveShape(boolean activeOrInactive, int ID) {
        System.out.println("Active or not " + activeOrInactive);
        myShapeMap.get(ID).setEffect(ACTIVE_TURTLE_EFFECT.get(activeOrInactive));
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
            myController.updateVariable(myVariableElements.getVariableName(t.getIndex()),t.getNewValue());
        }
        catch (NumberFormatException e) {
            printError(myStringResources.getString("wrongNumberType"));
        }
    }

    private void changeBackgroundImage () {
        String path = ViewFX.openFileChooser();
        try{
                myPlayground.changeBackground(path);
        }
        catch(IllegalArgumentException | NullPointerException e){
                printError(myStringResources.getString("invalidImageType"));
        }
    }
   
    private void changeLanuageinController (int index) {
        ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText", new Locale("en", "US"));
        myController.changeLanguage(myStringResources.getString("languageFile").split("\\s+")[index]);
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

    @Override
    public Group getRoot () {
        return myRoot;
    }

    @Override
    public void changeShape (String shapeType, int ID) {
        double[] coordinate = new double[]{myShapeMap.get(ID).getTranslateX(),myShapeMap.get(ID).getTranslateY()};
        myShapeRoot.getChildren().remove(myShapeMap.get(ID));
        addShape(shapeType, 0, 0, ID);
        Shape newShape = myShapeMap.get(ID);
        newShape.setTranslateX(coordinate[0]);newShape.setTranslateX(coordinate[1]);
    }

//    @Override
//    public void getColorArray (List<String> colorList) {
//        // TODO Auto-generated method stub
//        
//    }
    



}
