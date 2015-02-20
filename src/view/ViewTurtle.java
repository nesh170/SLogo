package view;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class ViewTurtle {
	private Shape myShape;
	private Color penColor;
	
	public ViewTurtle(Point2D point) {
		myShape = new Circle(point.getX(), point.getY(), 10, Color.BLACK);
		penColor = Color.BLACK;
	}
	
	public Point2D getCoordinate() {
		Point2D retPoint = new Point2D(myShape.getTranslateX() , myShape.getTranslateY());
		return retPoint;
	}
	
	public void setNewLocation(Point2D point) {
		myShape.setTranslateX(point.getX());
		myShape.setTranslateY(point.getY());
	}
	
	public Line drawLine(Point2D point) {
		Line turtleLine = new Line(myShape.getTranslateX(), myShape.getTranslateY(), point.getX(), point.getY());
		turtleLine.setFill(penColor);
		return turtleLine;
	}
	
	public Color getColor() {
		return (Color)myShape.getFill();
	}

	public Node getViewTurtles() {
		return myShape;
	}
}
