package view;

import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;

public class ViewTurtle {
	public static final double ORIGIN_X=350.0;
	public static final double ORIGIN_Y=250.0;
	
	private Shape myShape;
	private Color penColor;
	
	public ViewTurtle(Point2D point,double size) {
		myShape = new Polygon();
		((Polygon) myShape).getPoints().addAll(new Double[]{ORIGIN_X+size, ORIGIN_Y+size, ORIGIN_X-size, ORIGIN_Y+size, ORIGIN_X, ORIGIN_Y-size});
		penColor = Color.BLACK;
	}
	
	public void setShape(String path) {
		//TODO error checking
		File imageFile = new File(path);
		myShape.setFill(new ImagePattern(new Image(imageFile.toURI().toString())));
	}
	
	public void setEventHandler(EventHandler<MouseEvent> handler){
		myShape.setOnMouseClicked(handler);
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
		Line turtleLine = new Line(myShape.getTranslateX()+ORIGIN_X, myShape.getTranslateY()+ORIGIN_Y, point.getX(), point.getY());
		turtleLine.setFill(penColor);
		return turtleLine;
	}

	public Color getPenColor() {
		return penColor;
	}
	
	public void setPenColor(Color color) {
		penColor = color;
	}
	
	public Node getViewTurtles() {
		return myShape;
	}
	
}
