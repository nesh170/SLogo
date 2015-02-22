package view;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import sLogo_team02.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.ViewConstants;

public class ViewFX extends ViewAbstract {
	private Group myRoot;
	private Group myLineRoot;
	private Group myTurtleRoot;
	private Stage myStage;
	private CodePane myCodeElements;
	private VariablePane myVariableElements;
	private Map<String,ViewVariable> myVariableMap=new HashMap<String, ViewVariable>();
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	private Map<Integer,ViewTurtle> myViewTurtlesMap;
	private Controller myController;
	
	public ViewFX(Controller controller, Stage stage){
		myController = controller;
		myStage = stage;
		initializeView();
	}
	
	private void initializeView(){
		myRoot = new Group();
		myLineRoot = new Group();
		myTurtleRoot = new Group();
		myViewTurtlesMap = new HashMap<>();
		new ButtonPane(myRoot,e-> changeBackgroundImage(),new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number value, Number newValue){
				changeLanuageinController(newValue.intValue());
			}	
		});
		Scene viewScene = new Scene(myRoot,ViewConstants.STAGE_WIDTH.getVal(),ViewConstants.STAGE_HEIGHT.getVal(),Color.ALICEBLUE);
		myCodeElements = new CodePane(myRoot, e->pushCodeToController());
		myVariableElements = new VariablePane(myRoot);
		myRoot.getChildren().addAll(myLineRoot, myTurtleRoot);
		myController.setScene(viewScene);
//		test();
	}

	
	private void test(){
		addTurtle(0,0, 0);
		drawTurtle(350,250,0);
		rotateTurtle(235,0);
		drawTurtle(100,100,0);
		
		drawTurtle(15, 18, 0);
//		clearScreen();
//		addTurtle(0,0,0);
		addVariable("lol", 62.0);
	}


	@Override
	public void drawTurtle(double X, double Y, int ID) {
		Line tempLine = myViewTurtlesMap.get(ID).drawLine(new Point2D(X + ViewTurtle.ORIGIN_X, ViewConstants.REVERSE_DIRECTION.getVal()*Y + ViewTurtle.ORIGIN_Y));
		myLineRoot.getChildren().add(tempLine);
		moveTurtle(X,Y,ID);
	}

	@Override
	public void moveTurtle(double X, double Y, int ID){
		Point2D point = new Point2D(X, ViewConstants.REVERSE_DIRECTION.getVal()*Y);
		myViewTurtlesMap.get(ID).setNewLocation(point);
	}
	
	@Override
	public void rotateTurtle(double angle, int ID) {
		myViewTurtlesMap.get(ID).rotate(angle);
	}

	@Override
	public void clearScreen(){
		myLineRoot.getChildren().clear();
		myTurtleRoot.getChildren().clear();
		myCodeElements.clearTerminal();
	}

	@Override
	public void printError(String message) {
		myCodeElements.addTerminalText(message, Color.RED);
	}

	@Override
	public void printMessage(String message) {
		myCodeElements.addTerminalText(message, Color.YELLOW);
	}

	@Override
	public void addTurtle(double X, double Y, int ID) {
		Point2D point = new Point2D(X, Y);
		ViewTurtle vt = new ViewTurtle(point, ViewConstants.TURTLE_SIZE.getVal());
		vt.setNewLocation(point);
		EventHandler<ActionEvent> shapeChoose = new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent evt) {
				changeShape(vt);
			}
		};
		EventHandler<MouseEvent> myHandler = new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent evt) {
				final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(myStage);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("ID : "+ID));
                Button shapeButton = new Button();
                shapeButton.setText("Choose the Image for the Turtle");
                shapeButton.setOnAction(shapeChoose);
                dialogVbox.getChildren().add(shapeButton);
                List<String> colorArray = Arrays.asList(myStringResources.getString(
        				"allColors").split("\\s+"));
        		ChoiceBox<String> colorBox = new ChoiceBox<String>(
        				FXCollections.observableArrayList(colorArray));
                colorBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
                	@Override
                	public void changed(ObservableValue<? extends String> ov, String value, String newValue){
                		vt.setPenColor(Color.web(newValue));
                	}
                });
        		dialogVbox.getChildren().add(new Text("Pen Color : "));
        		dialogVbox.getChildren().add(colorBox);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
			}
		};
		vt.setEventHandler(myHandler);
		myViewTurtlesMap.put(ID,vt);
		myTurtleRoot.getChildren().add(vt.getViewTurtles());
	}

	@Override
	public void addVariable(String variableName, Double value) {
		if(myVariableMap.containsKey(variableName)){
			myVariableMap.get(variableName).updateValue(value);
		}
		else{
			ViewVariable tempVariable = new ViewVariable(variableName, value);
			myVariableElements.add(tempVariable.generateVisualVariable(e->{
				if(e.getCode().equals(KeyCode.ENTER)){
					updateVariableFromView(variableName);
				}
			}));
			myVariableMap.put(variableName, tempVariable);
		}
		
	}
	
	private void pushCodeToController(){
		myController.executeCommand(myCodeElements.getCodeData());
	}
	
	private void updateVariableFromView(String variableName){
		try{
		double newVariableValue = myVariableMap.get(variableName).getValueInField();
		myController.updateVariable(variableName, newVariableValue);
		}
		catch(Exception e){
			printError(myStringResources.getString("wrongNumberType"));
		}
		
	}
	
	private void changeShape(ViewTurtle vt){
		FileChooser fileChooser = new FileChooser();
		File initDir = new File("C:\\Users\\johnwood\\Documents\\cs308\\slogo_team02\\src\\view");
		fileChooser.setInitialDirectory(initDir);
        File turtleShape = fileChooser.showOpenDialog(myStage);
        if(turtleShape != null)
        	vt.setShape(turtleShape);
	}
	
	private void changeBackgroundImage(){
		//TODO add code to change the Image of the TurtlePlayGround with turtlePlaygroundBackGround
	}
	
	private void changeLanuageinController(int index){
		ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
		myController.changeLanguage(myStringResources.getString("languageFile").split("\\s+")[index]);
	}


	
}
