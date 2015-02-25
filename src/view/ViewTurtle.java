package view;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import slogoEnums.ViewConstants;

public class ViewTurtle {
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	private Shape myShape;
	private Color penColor;
	private int myID;
	
	public ViewTurtle(Point2D point,double size, int ID) {
		myShape = new Polygon();
		((Polygon) myShape).getPoints().addAll(new Double[]{ViewConstants.ORIGIN_X.getVal()+size, ViewConstants.ORIGIN_Y.getVal()+size, ViewConstants.ORIGIN_X.getVal()-size, ViewConstants.ORIGIN_Y.getVal()+size, ViewConstants.ORIGIN_X.getVal(), ViewConstants.ORIGIN_Y.getVal()-size});
		penColor = Color.BLACK;
		myID = ID;
		myShape.setOnMouseClicked(e->setUpDialogBox());
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
		turtleLine.setStroke(penColor);
		return turtleLine;
	}

	public Color getPenColor() {
		return penColor;
	}
	
	public Node getViewTurtles() {
		return myShape;
	}
	
	
	private void setUpDialogBox(){
	Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        dialog.setTitle(myStringResources.getString("idText") + myID);
        Button shapeButton = new Button();
        shapeButton.setText(myStringResources.getString("chooseImage"));
        shapeButton.setOnAction(e-> setImage(ViewFX.openFileChooser()));
        ColorPicker colorPick = new ColorPicker(penColor);
        colorPick.setOnAction(e-> penColor=colorPick.getValue());
	dialogVbox.getChildren().addAll(shapeButton,new Text(myStringResources.getString("choosePenColor")),colorPick);
        dialog.setScene(new Scene(dialogVbox, ViewConstants.DBOX_WIDTH.getVal(), ViewConstants.DBOX_HEIGHT.getVal()));
        dialog.show();
	}
	
}
