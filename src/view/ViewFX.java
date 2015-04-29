package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import Model.Pen;
import sLogo_team02.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import slogoEnums.ViewConstants;
import javafx.scene.control.ListView;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * This is the main implementation fo the View where is calls the other classes in the view to
 * arrange them in the UI. It also stores all the visual methods for the turtle.
 * 
 * @author Jangsoon Park
 * @author Sivaneshwaran
 */
public class ViewFX extends ViewAbstract {
    private Group myRoot;
    private Group myLineRoot;
    private Group myShapeRoot;
    private Group myStampRoot;
    private ColorPane myColorElements;
    private CodePane myCodeElements;
    private VariablePane myVariableElements;
    private TurtlePlayground myPlayground;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",
                                                                        new Locale("en", "US"));
    private Map<Integer, Shape> myShapeMap;
    private Controller myController;
    private ShapeFactory myShapeFactory = new ShapeFactory();
    public static final Map<Boolean, Effect> ACTIVE_TURTLE_EFFECT = new HashMap<>();
    static {
        ACTIVE_TURTLE_EFFECT.put(false, new Glow(1.0));
        ACTIVE_TURTLE_EFFECT.put(true, new Glow(0.0));
    }

    /**
     * Constructor for ViewFX.
     * 
     * @param controller Controller
     */
    public ViewFX (Controller controller) {
        myController = controller;
        initializeView();
    }

    private void initializeView () {
        myRoot = new Group();
        myLineRoot = new Group();
        myShapeRoot = new Group();
        myStampRoot = new Group();
        myShapeMap = new HashMap<>();
        myColorElements = new ColorPane(myRoot, new ArrayList<>());
        new ButtonPane(myRoot, e -> changeBackgroundImage(), new ChangeListener<Number>() {
            @Override
            public void changed (ObservableValue<? extends Number> ov, Number value, Number newValue) {
                changeLanuageinController(newValue.intValue());
            }
        }, e -> myController.saveXML(ViewFX.openFileChooser(false)), e -> myController
                .loadXML(ViewFX.openFileChooser(true)), () -> getTurtleMap(), () -> renderTurtleGroup());
        myPlayground = new TurtlePlayground(myRoot);
        myCodeElements = new CodePane(myRoot, e -> pushCodeToController());
        setUpVariablePane();
        myRoot.getChildren().addAll(myLineRoot, myShapeRoot, myStampRoot);
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

    /**
     * Method drawShape.
     * 
     * drawShape is invoked by the model when a line is needed to be created on the screen.
     * 
     * @param X double
     * @param Y double
     * @param ID int
     * @param penColor String
     * @param strokeWidth double
     * @param strokeType String
     */
    @Override
    public void drawShape (double X,
                           double Y,
                           int ID,
                           String penColor,
                           double strokeWidth,
                           String strokeType) {
        double[] startCoordinates =
                ViewFunctions.rectToFXCoordinates(myShapeMap.get(ID).getTranslateX(),
                                                  ViewConstants.REVERSE_DIRECTION.getVal() *
                                                          myShapeMap.get(ID).getTranslateY());
        System.out.println(myShapeMap.get(ID).getTranslateX() + " " +
                           myShapeMap.get(ID).getTranslateY());
        double[] endCoordinates = ViewFunctions.rectToFXCoordinates(X, Y);
        Line turtleLine =
                new Line(startCoordinates[0], startCoordinates[1], endCoordinates[0],
                         endCoordinates[1]);
        System.out.println(penColor);
        turtleLine.setStroke(Color.web(penColor));
        turtleLine.setStrokeWidth(strokeWidth);
        turtleLine.setStyle(myStringResources.getString(strokeType.toLowerCase()));
        moveShape(X, Y, ID);
        myLineRoot.getChildren().add(turtleLine);
    }

    /**
     * Method moveShape.
     * 
     * moveShape is called when the turtle is needed t obe moved without a line
     * 
     * @param X double
     * @param Y double
     * @param ID int
     */
    @Override
    public void moveShape (double X, double Y, int ID) {
        myShapeMap.get(ID).setTranslateX(X);
        myShapeMap.get(ID).setTranslateY(ViewConstants.REVERSE_DIRECTION.getVal() * Y);
    }

    /**
     * Method rotateShape.
     * 
     * rotateShape is to rotate the turtle
     * 
     * @param angle double
     * @param ID int
     */
    @Override
    public void rotateShape (double angle, int ID) {
        myShapeMap.get(ID).rotateProperty().setValue(angle);
    }

    /**
     * Method addShape.
     * 
     * addShape adds a new turtle to the view and sets them to be active. It also gives in the
     * eventhandlers to allow the user to interact with it
     * 
     * @param shapeType String
     * @param X double
     * @param Y double
     * @param ID int
     */
    @Override
    public void addShape (String shapeType, double X, double Y, int ID) {
        Shape tempShape = myShapeFactory.makeShape(shapeType, ViewConstants.SIZE.getVal());
        tempShape.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent mouseButton) {
                if (mouseButton.getButton() == MouseButton.PRIMARY) {
                    myController.initiateDialogBox(ID);
                }
                else if (mouseButton.getButton() == MouseButton.SECONDARY) {
                    myController.setToggleActive(ID);
                }
            }
        });
        tempShape.setTranslateX(X);
        tempShape.setTranslateY(ViewConstants.REVERSE_DIRECTION.getVal() * Y);
        myShapeMap.put(ID, tempShape);
        visualActiveShape(true, ID);
        myShapeRoot.getChildren().add(tempShape);
    }

    /**
     * Method visibleShape.
     * This method makes the turtle in the view to be displayed or not.
     * true is show, false is hide
     * 
     * @param hideOrShow boolean
     * @param ID int
     */
    @Override
    public void visibleShape (boolean hideOrShow, int ID) {
        myShapeMap.get(ID).setVisible(hideOrShow);
    }

    /**
     * Method visualActiveShape.
     * 
     * This method sets a glow to the shape of the called ID when it is inactive. The glow is
     * removed when the turtle is deactivated
     * 
     * true is active, false is inactive
     * 
     * @param activeOrInactive boolean
     * @param ID int
     */
    @Override
    public void visualActiveShape (boolean activeOrInactive, int ID) {
        System.out.println("Active or not " + activeOrInactive);
        myShapeMap.get(ID).setEffect(ACTIVE_TURTLE_EFFECT.get(activeOrInactive));
    }

    /**
     * Method clearScreen
     * 
     * This method clears all the roots to clear the screen
     */
    @Override
    public void clearScreen () {
        myLineRoot.getChildren().clear();
        myShapeRoot.getChildren().clear();
        myStampRoot.getChildren().clear();
        myCodeElements.clearTerminal();
    }

    /**
     * Method printError.
     * 
     * This method takes in a string and prints it in RED at the terminal to signify an error
     * 
     * @param message String
     */
    @Override
    public void printError (String message) {
        myCodeElements.addTerminalText(message, Color.RED);
    }

    /**
     * Method printMessage.
     * 
     * This method takes in a string and prints it in YELLOW at the terminal to signify a message or
     * return value.
     * 
     * @param message String
     */
    @Override
    public void printMessage (String message) {
        myCodeElements.addTerminalText(message, Color.YELLOW);
    }

    /**
     * Method addVariable.
     * 
     * This method adds the variable to the variable list so the user is able to visually see the
     * variable
     * 
     * @param variableName String
     * @param value double
     */
    @Override
    public void addVariable (String variableName, double value) {
        myVariableElements.addNumberVariables(variableName, value);
    }

    /**
     * Method addMethodVariable.
     * This method adds the method variable to the method variable list
     * 
     * @param methodName String
     */
    @Override
    public void addMethodVariable (String methodName) {
        myVariableElements.addMethodVariable(methodName);
    }

    private void pushCodeToController () {
        myController.executeProgram(myCodeElements.getCodeData());
    }

    /**
     * Method updateVariableFromView.
     * 
     * This method updates the variable from the view to the model
     * 
     * @param t ListView.EditEvent<Double>
     */
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
        String path = ViewFX.openFileChooser(true);
        try {
            myPlayground.changeBackground(path);
        }
        catch (IllegalArgumentException | NullPointerException e) {
            printError(myStringResources.getString("invalidImageType"));
        }
    }

    /**
     * Method changeLanuageinController.
     * This method corresponds to change the language in the controller by giving the string of the
     * location of the new resource bundle
     * 
     * @param index int
     */
    private void changeLanuageinController (int index) {
        ResourceBundle myStringResources =
                ResourceBundle.getBundle("resources.View.ViewText", new Locale("en", "US"));
        myController
                .changeLanguage(myStringResources.getString("languageFile").split("\\s+")[index]);
    }

    /**
     * Method openFileChooser.
     * 
     * This method creates a file chooser and returns a string of the file path.
     * True means create an openFileChooser, False means create a saveFileChooser
     * 
     * @param open boolean
     * @return String
     */
    public static String openFileChooser (boolean open) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File((System.getProperty("user.dir"))));
        File file;
        if (open) {
            file = fileChooser.showOpenDialog(new Stage());
        }
        else {
            file = fileChooser.showSaveDialog(new Stage());
        }
        if (file == null) { return ""; }
        return file.toString();
    }

    /**
     * Method getRoot.
     * 
     * @return Group
     */
    @Override
    public Group getRoot () {
        return myRoot;
    }

    /**
     * Method changeShape.
     * This method changes the shape of the turtle and sets it back to the same location
     * 
     * @param shapeType String
     * @param ID int
     */
    @Override
    public void changeShape (String shapeType, int ID) {
        double[] coordinate =
                new double[] { myShapeMap.get(ID).getTranslateX(),
                              myShapeMap.get(ID).getTranslateY() };
        myShapeRoot.getChildren().remove(myShapeMap.get(ID));
        addShape(shapeType, 0, 0, ID);
        Shape newShape = myShapeMap.get(ID);
        newShape.setTranslateX(coordinate[0]);
        newShape.setTranslateY(coordinate[1]);
    }

    /**
     * Method updateColorListView.
     * This method updates the color list view with the list
     * 
     * @param colorList List<String>
     */
    @Override
    public void updateColorListView (List<String> colorList) {
        System.out.println("in here");
        myColorElements.changeList(colorList);
    }

    /**
     * Method stamp.
     * This method generates a stamp of the turtle an puts it in the view
     * 
     * @param ID int
     */
    @Override
    public void stamp (int ID) {
        Shape stampShape = myShapeFactory.makeCopy(myShapeMap.get(ID));
        myStampRoot.getChildren().add(stampShape);
    }

    // true if empty, false is filled
    /**
     * Method clearStamps.
     * 
     * @return boolean
     */
    @Override
    public boolean clearStamps () {
        boolean stampContains = myStampRoot.getChildren().isEmpty();
        myStampRoot.getChildren().clear();
        return stampContains;
    }

    /**
     * Method setUpDialogBox.
     * This method sets up the dialog box and displays it to the user.
     * 
     * @param pen Pen
     * @param ID int
     * @param colorList List<String>
     */
    @Override
    public void setUpDialogBox (Pen pen, int ID, List<String> colorList) {
        Stage dialog = new Stage();
        VBox dialogBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        dialog.initModality(Modality.APPLICATION_MODAL);
        PenPropertiesDialogBox penPropBox = new PenPropertiesDialogBox(pen, colorList);
        TurtlePropertiesDialogBox turtlePropBox = new TurtlePropertiesDialogBox(myShapeMap.get(ID));
        dialog.setTitle(myStringResources.getString("idText") + ID);
        dialogBox.getChildren().addAll(penPropBox.getVBox(), turtlePropBox.getVBox());
        dialog.setScene(new Scene(dialogBox, ViewConstants.DBOX_WIDTH.getVal(),
                                  ViewConstants.DBOX_HEIGHT.getVal()));
        dialog.show();
    }

    /**
     * Method changeBackgroundColor.
     * changes the method color of the turtle playground.
     * 
     * @param color String
     */
    @Override
    public void changeBackgroundColor (String color) {
        myPlayground.changeColorBackground(color);
    }
    
    public Map<Integer, Shape> getTurtleMap() {
        return Collections.unmodifiableMap(myShapeMap);
    }
    
    public void renderTurtleGroup(){
        System.out.println("Run");
        myShapeMap.forEach((key,value)-> {
            value.relocate(ViewConstants.ORIGIN_X.getVal()-ViewConstants.SIZE.getVal(), ViewConstants.ORIGIN_Y.getVal()-ViewConstants.SIZE.getVal());
            value.setTranslateX(myShapeMap.get(key).getTranslateX());
            value.setTranslateY(myShapeMap.get(key).getTranslateY());
        });
        myShapeRoot.getChildren().clear();
        myShapeMap.values().stream().forEach(turtle -> myShapeRoot.getChildren().add(turtle));
    }

}
