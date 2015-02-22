package view;

import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class ViewTurtle {
	public static final double ORIGIN_X=350.0;
	public static final double ORIGIN_Y=250.0;
	
	private ImageView myImage;
	private Shape myShape;
	private Color penColor;
	
	public ViewTurtle(Point2D point,double size) {
		Image image = new Image("view/turtle1.jpg");
		myImage = new ImageView();
		myImage.setImage(image);
		myImage.setFitHeight(size);
		myImage.setFitWidth(size);
		myImage.setX(point.getX()+335);
		myImage.setY(point.getY()+235);
		myShape = new Polygon();
		((Polygon) myShape).getPoints().addAll(new Double[]{ORIGIN_X+size, ORIGIN_Y+size, ORIGIN_X-size, ORIGIN_Y+size, ORIGIN_X, ORIGIN_Y-size});
		penColor = Color.BLACK;
	}
	
	public void setShape(File file) {
		String path = "view/" + file.getName();
		Image image = new Image(path);
		myImage.setImage(image);
	}
	
	public void setEventHandler(EventHandler<MouseEvent> handler){
		myImage.setOnMouseClicked(handler);
	}
	
	public Point2D getCoordinate() {
		Point2D retPoint = new Point2D(myImage.getTranslateX() , myImage.getTranslateY());
		return retPoint;
	}
	
	public void setNewLocation(Point2D point) {
		myImage.setX(point.getX()+335);
		myImage.setY(point.getY()+235);
	}
	
	public void rotate(double angle){
		myImage.rotateProperty().set(myImage.rotateProperty().get()+angle);
	}
	
	public Line drawLine(Point2D point) {
		Line turtleLine = new Line(myImage.getTranslateX()+ORIGIN_X, myImage.getTranslateY()+ORIGIN_Y, point.getX(), point.getY());
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
		return myImage;
	}
	
}
