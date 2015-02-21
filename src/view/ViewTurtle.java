package view;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceBoxBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class ViewTurtle {
	public static final int ORIGIN_X=350;
	public static final int ORIGIN_Y=250;
	
	private Shape myShape;
	private Color penColor;
	
	public static final ObservableList<Color> colors = FXCollections.observableArrayList(
			Color.BLACK,
			Color.BLUE,
			Color.CYAN,
			Color.RED
	);
	private ChoiceBox<Color> cb1 = new ChoiceBox<Color>(colors);
	private ChoiceBox<Color> cb2 = new ChoiceBox<Color>(colors);
	
	public ViewTurtle(Point2D point) {
		myShape = new Circle(ORIGIN_X,ORIGIN_Y, 10, Color.BLACK);
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
	
	public void clickToChange(MouseEvent evt) {
		if(evt.getEventType() == MouseEvent.MOUSE_CLICKED) {
			System.out.println("HI");
		}
	}
	
}
