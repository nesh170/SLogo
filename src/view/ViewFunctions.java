package view;

import slogoEnums.ViewConstants;

public class ViewFunctions {
    
    public static double[] rectToFXCoordinates(double X, double Y){                         
       double[] rectCoordinates = new double[2];
       rectCoordinates[0]=X + ViewConstants.ORIGIN_X.getVal();
       rectCoordinates[1]=Y*ViewConstants.REVERSE_DIRECTION.getVal() + ViewConstants.ORIGIN_Y.getVal();
       return rectCoordinates;
    }
}
