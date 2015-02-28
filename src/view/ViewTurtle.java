package view;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
	private ObservableList<Double> myStrokeList;

	
	public ViewTurtle(Point2D point,double size, int ID) {
		myShape = new Polygon();
		((Polygon) myShape).getPoints().addAll(new Double[]{ViewConstants.ORIGIN_X.getVal()+size, ViewConstants.ORIGIN_Y.getVal()+size, ViewConstants.ORIGIN_X.getVal()-size, ViewConstants.ORIGIN_Y.getVal()+size, ViewConstants.ORIGIN_X.getVal(), ViewConstants.ORIGIN_Y.getVal()-size});
		myID = ID;
		myShape.setOnMouseClicked(e->setUpDialogBox());
		initializeObservables();
	}
	
	public void setImage() {
	        String path = myImagePath.get();
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
		turtleLine.getStrokeDashArray().clear();
		turtleLine.getStrokeDashArray().addAll(myStrokeList);
		return turtleLine;
	}
	
	public Node getViewTurtles() {
		return myShape;
	}
	
	private void initializeObservables(){
	    myImagePath = new SimpleStringProperty();
	    myImagePath.addListener(e -> setImage());
	    myPenRGB = new SimpleStringProperty(myStringResources.getString("defaultPenColor"));
	    myStrokeWidth= new SimpleDoubleProperty(1.0);
	    myStrokeList = FXCollections.observableArrayList();
	}
	
	
	
	private void setUpDialogBox(){
	    Stage dialog = new Stage();
	    VBox dialogBox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
            dialog.initModality(Modality.APPLICATION_MODAL);
            PenPropertiesDialogBox penPropBox = new PenPropertiesDialogBox(myPenRGB,myStrokeWidth,myStrokeList);
            TurtlePropertiesDialogBox turtlePropBox = new TurtlePropertiesDialogBox(myImagePath);
            dialog.setTitle(myStringResources.getString("idText") + myID);
            dialogBox.getChildren().addAll(penPropBox.getVBox(),turtlePropBox.getVBox());
            dialog.setScene(new Scene(dialogBox, ViewConstants.DBOX_WIDTH.getVal(), ViewConstants.DBOX_HEIGHT.getVal()));
            dialog.show();
	}
	
}
