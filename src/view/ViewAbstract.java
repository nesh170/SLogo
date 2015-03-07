package view;

import java.util.List;
import Model.Pen;
import javafx.scene.Group;


/**
 * The abstract class is used so if the user wants to use Swing instead of JavaFX, he/she is able to
 * extend this class and write the implementation of it in their own desired language.
 * 
 * @author Sivaneshwaran
 */
public abstract class ViewAbstract {

    /**
     * Method drawShape.
     * 
     * @param X double
     * @param Y double
     * @param ID int
     * @param penColor String
     * @param strokeWidth double
     * @param strokeType String
     */
    public abstract void drawShape (double X, double Y, int ID, String penColor,
                                    double strokeWidth, String strokeType);

    /**
     * Method moveShape.
     * 
     * @param X double
     * @param Y double
     * @param ID int
     */
    public abstract void moveShape (double X, double Y, int ID);

    /**
     * Method rotateShape.
     * 
     * @param angle double
     * @param ID int
     */
    public abstract void rotateShape (double angle, int ID);

    public abstract void clearScreen ();

    /**
     * Method printError.
     * 
     * @param message String
     */
    public abstract void printError (String message);

    /**
     * Method printMessage.
     * 
     * @param message String
     */
    public abstract void printMessage (String message);

    /**
     * Method addShape.
     * 
     * @param shapeType String
     * @param X double
     * @param Y double
     * @param ID int
     */
    public abstract void addShape (String shapeType, double X, double Y, int ID);

    /**
     * Method addVariable.
     * 
     * @param variableName String
     * @param value double
     */
    public abstract void addVariable (String variableName, double value);

    /**
     * Method addMethodVariable.
     * 
     * @param methodName String
     */
    public abstract void addMethodVariable (String methodName);

    /**
     * Method visibleShape.
     * 
     * @param hideOrShow boolean
     * @param ID int
     */
    public abstract void visibleShape (boolean hideOrShow, int ID);

    /**
     * Method visualActiveShape.
     * 
     * @param activeOrInactive boolean
     * @param ID int
     */
    public abstract void visualActiveShape (boolean activeOrInactive, int ID);

    /**
     * Method getRoot.
     * 
     * @return Group
     */
    public abstract Group getRoot ();

    /**
     * Method stamp.
     * 
     * @param ID int
     */
    public abstract void stamp (int ID);

    /**
     * Method changeShape.
     * 
     * @param shapeType String
     * @param ID int
     */
    public abstract void changeShape (String shapeType, int ID);

    /**
     * Method updateColorListView.
     * 
     * @param colorList List<String>
     */
    public abstract void updateColorListView (List<String> colorList);

    /**
     * Method clearStamps.
     * 
     * @return boolean
     */
    public abstract boolean clearStamps ();

    /**
     * Method setUpDialogBox.
     * 
     * @param pen Pen
     * @param ID int
     * @param colorList List<String>
     */
    public abstract void setUpDialogBox (Pen pen, int ID, List<String> colorList);

    /**
     * Method changeBackgroundColor.
     * 
     * @param color String
     */
    public abstract void changeBackgroundColor (String color);

}
