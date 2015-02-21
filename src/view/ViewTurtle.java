package view;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
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
	
	public Color getColor() {
		return (Color)myShape.getFill();
	}
	
	public void setColor(Color color) {
		myShape.setFill(color);
	}

	public void setPenColor(Color color) {
		penColor = color;
	}
	
	public Node getViewTurtles() {
		return myShape;
	}
	
}
