package Constants;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;


/**
 * This is where all the constants are stored for the model. The number of parameters constants are
 * obtained using reflection as shown in the method below
 * 
 * @author Sivaneshwaran,Sierra, Yancheng
 *
 */
public class Constants {

    public static final String DEFAULT_LANGUAGE = "resources/languages/English";
    public static final String SYNTAX = "resources/languages/Syntax";

    public static final double FULL_CIRCLE = 360;
    public static final double FORWARD_ANGLE = 0;
    public static final double BACKWARD_ANGLE = 180;
    public static final double LEFT_DIRECTION = -1;
    public static final double RIGHT_DIRECRION = 1;
    public static final double STARTING_XCOR = 0;
    public static final double STARTING_YCOR = 0;
    public static final double PEN_UP_VALUE = 0;
    public static final double PEN_DOWN_VALUE = 1;
    public static final double SHOWING_VALUE = 1;
    public static final double HIDING_VALUE = 0;
    public static final double REVERSE_SIGN = -1;
    public static final boolean SET_PEN_UP = false;
    public static final boolean SET_PEN_DOWN = true;
    public static final int LOOP_START = 1;
    public static final String REPEAT_VARIABLE = ":repcount";
    public static final Integer FIRST_TURTLE_ID = 0;
    public static final Integer FIRST_TURTLE_XCOR = 0;
    public static final Integer FIRST_TURTLE_YCOR = 0;
    public static final String DEFAULT_SHAPE = "triangle";
    public static final String MISSING_VARIABLE_MESSAGE = "Trying to use undefined variable";

    public static final int FORWARD = 1;
    public static final int BACKWARD = 1;
    public static final int LEFT = 1;
    public static final int RIGHT = 1;
    public static final int SETHEADING = 1;
    public static final int SETTOWARDS = 2;
    public static final int SETPOSITION = 2;
    public static final int PENDOWN = 0;
    public static final int PENUP = 0;
    public static final int SHOWTURTLE = 0;
    public static final int HIDETURTLE = 0;
    public static final int HOME = 0;
    public static final int CLEARSCREEN = 0;
    public static final int XCOORDINATE = 0;
    public static final int STAMP = 0;
    public static final int CLEARSTAMPS = 0;
    public static final int YCOORDINATE = 0;
    public static final int HEADING = 0;
    public static final int ISPENDOWN = 0;
    public static final int ISPENSHOWING = 0;
    public static final int SUM = 2;
    public static final int DIFFERENCE = 2;
    public static final int PRODUCT = 2;
    public static final int QUOTIENT = 2;
    public static final int REMAINDER = 2;
    public static final int MINUS = 1;
    public static final int RANDOM = 1;
    public static final int SINE = 1;
    public static final int COSINE = 1;
    public static final int TANGENT = 1;
    public static final int ARCTANGENT = 1;
    public static final int NATURALLOG = 1;
    public static final int POWER = 1;
    public static final int PI = 0;
    public static final int LESSTHAN = 2;
    public static final int GREATERTHAN = 2;
    public static final int EQUAL = 2;
    public static final int NOTEQUAL = 2;
    public static final int AND = 2;
    public static final int OR = 2;
    public static final int NOT = 1;
    public static final int MAKEVARIABLE = 2;
    public static final int REPEAT = 2;
    public static final int DOTIMES = 2;
    public static final int FOR = 2;
    public static final int IF = 2;
    public static final int IFELSE = 3;
    public static final int MAKEUSERINSTRUCTION = 3;
    public static final int TELL = 1;
    public static final int SETPENCOLOR = 1;
    public static final int SETPENSIZE = 1;
    public static final int SETPALETTE = 4;
    public static final int SETSHAPE = 1;

    public static final List<String> DEFAULT_SHAPES = Arrays.asList("triangle",
                                                                    "circle");
    public static final List<String> DEFAULT_COLORS = Arrays
            .asList("#000000",
                    "#FFFFFF", "#FF0000", "#70B8FF");

    public static int getNumParam (String paramName)
                                                    throws IllegalArgumentException,
                                                    IllegalAccessException {
        Field[] fields = Constants.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldString = field.toString();
            if (fieldString.endsWith("." + paramName.toUpperCase())) { return (int) field.get(null); }
        }
        System.out.println("returning max value");
        return Integer.MAX_VALUE;
    }

}
