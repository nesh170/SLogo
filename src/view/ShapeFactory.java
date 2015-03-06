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
    
    /**
     * For the user to have more shapes, you need to add to the switch statement below.
     * @param shapeType
     * @return
     */
    public Shape makeShape(String shapeType){
        switch (shapeType) {
            case "circle": return makeCircle(ViewConstants.SIZE.getVal());
            case "triangle": return makeTriangle(ViewConstants.SIZE.getVal());
        }
        return null;
    }
}
