package view;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import slogoEnums.ViewConstants;

public class ViewTurtle {
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	private Shape myShape;
	private int myID;
	private SimpleStringProperty myImagePath;
	private SimpleStringProperty myPenRGB;
	private SimpleDoubleProperty myStrokeWidth;
	private SimpleStringProperty myStrokeType;
	private SimpleBooleanProperty myPenStatus;
	private SimpleBooleanProperty myActiveStatus;
	public static final Map<Boolean,Effect> ACTIVE_EFFECT_MAP = new HashMap<>();
	    static{
	        ACTIVE_EFFECT_MAP.put(true, new Glow(1.0));
	        ACTIVE_EFFECT_MAP.put(false, new Glow(0.0));
	    }
	

	
	public ViewTurtle(Point2D point,double size, int ID, SimpleBooleanProperty penStatus, SimpleBooleanProperty activeStatus) {
		myShape = new Polygon();
		((Polygon) myShape).getPoints().addAll(new Double[]{ViewConstants.ORIGIN_X.getVal()+size, ViewConstants.ORIGIN_Y.getVal()+size, ViewConstants.ORIGIN_X.getVal()-size, ViewConstants.ORIGIN_Y.getVal()+size, ViewConstants.ORIGIN_X.getVal(), ViewConstants.ORIGIN_Y.getVal()-size});
		myID = ID;
		myShape.setOnMouseClicked(e->setUpDialogBox());
		myShape.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle (MouseEvent mouseClick) {
                if(mouseClick.getButton().equals(MouseButton.PRIMARY)){
                    setUpDialogBox();
                }
                else if(mouseClick.getButton().equals(MouseButton.SECONDARY)){
                    myActiveStatus.setValue(!myActiveStatus.getValue());
                } 
            }
                });
		myPenStatus = penStatus;
		myActiveStatus=activeStatus;
		initializeObservables();
	}
	
	public void setImage(String path) {
		List<String> imageExtArray = Arrays.asList(myStringResources.getString("imageFileExtension").split("\\s+"));
		for(String c:imageExtArray){
		    if(path.toLowerCase().endsWith(c)){
		        File imageFile = new File(path);
	                myShape.setFill(new ImagePattern(new Image(imageFile.toURI().toString())));
	                return;
		    }
		}
	}
	
	
	public Point2D getCoordinate() {
		Point2D retPoint = new Point2D(myShape.getTranslateX() , myShape.getTranslateY());
		return retPoint;
	}
	
	public void setNewLocation(Point2D point) {
		myShape.setTranslateX(point.getX());
		myShape.setTranslateY(point.getY());
	}
	
	public void rotate(double angle){
		myShape.rotateProperty().set(angle);
	}
	
	public Line drawLine(Point2D point) {
		Line turtleLine = new Line(myShape.getTranslateX()+ViewConstants.ORIGIN_X.getVal(), myShape.getTranslateY()+ViewConstants.ORIGIN_Y.getVal(), point.getX(), point.getY());
		turtleLine.setStroke(Color.web(myPenRGB.get()));
		turtleLine.setStrokeWidth(myStrokeWidth.get());
		turtleLine.setStyle(myStrokeType.getValue());
		return turtleLine;
	}
	
	public Node getViewTurtles() {
		return myShape;
	}
	
	private void initializeObservables(){
	    myImagePath = new SimpleStringProperty();
	    myImagePath.addListener(e -> setImage(myImagePath.getValue()));
	    myPenRGB = new SimpleStringProperty(myStringResources.getString("defaultPenColor"));
	    myStrokeWidth= new SimpleDoubleProperty(1.0);
	    myStrokeType = new SimpleStringProperty(myStringResources.getString("defaultStrokeChoice"));
	}
	
	
	
	private void setUpDialogBox(){
	    Stage dialog = new Stage();
	    VBox dialogBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
            dialog.initModality(Modality.APPLICATION_MODAL);
            PenPropertiesDialogBox penPropBox = new PenPropertiesDialogBox(myPenRGB,myStrokeWidth,myStrokeType,myPenStatus);
            TurtlePropertiesDialogBox turtlePropBox = new TurtlePropertiesDialogBox(myImagePath,new double[]{myShape.getTranslateX(),myShape.getTranslateY()},myShape.rotateProperty().get());
            dialog.setTitle(myStringResources.getString("idText") + myID);
            dialogBox.getChildren().addAll(penPropBox.getVBox(),turtlePropBox.getVBox());
            dialog.setScene(new Scene(dialogBox, ViewConstants.DBOX_WIDTH.getVal(), ViewConstants.DBOX_HEIGHT.getVal()));
            dialog.show();
	}
	
        public void activateTurtle () {
            myShape.setEffect(ACTIVE_EFFECT_MAP.get(myActiveStatus.getValue()));
        }
        

    public void show () {
        myShape.setVisible(true);
    }

    public void hide () {
        myShape.setVisible(false);
    }
	
}
