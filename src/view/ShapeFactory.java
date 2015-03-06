package view;

import slogoEnums.ViewConstants;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class ShapeFactory {
    
    private Shape makeTriangle(double size){
        Shape tempShape = new Polygon();
        ((Polygon) tempShape).getPoints().addAll(new Double[]{ViewConstants.ORIGIN_X.getVal()+size, ViewConstants.ORIGIN_Y.getVal()+size, ViewConstants.ORIGIN_X.getVal()-size, ViewConstants.ORIGIN_Y.getVal()+size, ViewConstants.ORIGIN_X.getVal(), ViewConstants.ORIGIN_Y.getVal()-size});
        return tempShape;
    }
    
    private Shape makeCircle(double size){
        double[] centerCoordinates = ViewFunctions.rectToFXCoordinates(0, 0);
        Shape tempShape = new Circle(centerCoordinates[0], centerCoordinates[1], size);
        return tempShape;
    }
    
    public Shape makeCopy(Shape shape){
        Shape copyShape = makeShape(shape.getClass().getName().substring((int) Math.round(ViewConstants.REFLECTIONSUBSTRING.getVal())), ViewConstants.SIZE.getVal());
        copyShape.setTranslateX(shape.getTranslateX());copyShape.setTranslateY(shape.getTranslateY());
        copyShape.setFill(shape.getFill());copyShape.setEffect(shape.getEffect());
        return copyShape;
    }
    
    /**
     * For the user to have more shapes, you need to add to the switch statement below.
     * @param shapeType
     * @return
     */
    public Shape makeShape(String shapeType, double size){
        switch (shapeType.toLowerCase()) {
            case "circle": return makeCircle(size);
            case "triangle": return makeTriangle(size);
            case "polygon": return makeTriangle(size);
        }
        return null;
    }
}
