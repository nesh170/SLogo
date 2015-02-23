package view;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import slogoEnums.ViewConstants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
		if(path == null)
			return;
		File imageFile = new File(path);
		Image tempImage = new Image(imageFile.toURI().toString());
		try{
			ImagePattern ip = new ImagePattern(tempImage);
			myShape.setFill(ip);
		}
		catch(Exception e){
			return;
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
        VBox dialogVbox = new VBox(20);
        dialog.setTitle(myStringResources.getString("idText") + myID);
        Button shapeButton = new Button();
        shapeButton.setText(myStringResources.getString("chooseImage"));
        shapeButton.setOnAction(e-> setImage(ViewFX.openFileChooser()));
        dialogVbox.getChildren().add(shapeButton);
        List<String> colorArray = Arrays.asList(myStringResources.getString("allColors").split("\\s+"));
		ChoiceBox<String> colorBox = new ChoiceBox<String>(FXCollections.observableArrayList(colorArray));
	colorBox.setValue(colorBox.getValue());
        colorBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
        	@Override
        	public void changed(ObservableValue<? extends String> ov, String value, String newValue){
        		penColor = Color.web(newValue);
        	}
        });
		dialogVbox.getChildren().add(new Text(myStringResources.getString("choosePenColor")));
		dialogVbox.getChildren().add(colorBox);
        Scene dialogScene = new Scene(dialogVbox, ViewConstants.DBOX_WIDTH.getVal(), ViewConstants.DBOX_HEIGHT.getVal());
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
}
