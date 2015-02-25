package Constants;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static final String DEFAULT_LANGUAGE = "resources/languages/English";
	public static final String SYNTAX = "resources/languages/Syntax";
	
	public static final double FULL_CIRCLE = 360;
	public static final double FORWARD_ANGLE = 0;
	public static final double BACKWARD_ANGLE = 180;
	public static final double LEFT_DIRECTION = -1;
	public static final double RIGHT_DIRECRION= 1;
	public static final double STARTING_XCOR = 0;
	public static final double STARTING_YCOR = 0;
	public static final double PEN_UP_VALUE = 0;
	public static final double PEN_DOWN_VALUE = 1;
	public static final double SHOWING_VALUE = 1;
	public static final double HIDING_VALUE = 0;
	public static final double REVERSE_SIGN = -1;
	public static final int LOOP_START = 1;
	public static final String REPEAT_VARIABLE = ":repcount";
	
	public static final int FORWARD_PARAMS = 1;
	public static final int BACK_PARAMS = 1;
	public static final int LEFT_PARAMS = 1;
	public static final int RIGHT_PARAMS = 1;
	public static final int SET_HEADING_PARAMS = 1;
	public static final int TOWARDS_PARAMS = 2;
	public static final int SET_XY_PARAMS = 2;
	public static final int PENDOWN_PARAMS = 0;
	public static final int PENUP_PARAMS = 0;
	public static final int SHOW_TURTLE_PARAMS = 0;
	public static final int HIDE_TURTLE_PARAMS = 0;
	public static final int HOME_PARAMS = 0;
	public static final int CLEAR_SCREEN_PARAMS = 0;
	public static final int XCOR_PARAMS = 0;
	public static final int YCOR_PARAMS = 0;
	public static final int HEADING_PARAMS = 0;
	public static final int PENDOWNP_PARAMS = 0;
	public static final int SHOWINGP_PARAMS = 0;
	public static final int SUM_PARAMS = 2;
	public static final int DIFFERENCE_PARAMS = 2;
	public static final int PRODUCT_PARAMS = 2;
	public static final int QUOTIENT_PARAMS = 2;
	public static final int REMAINDER_PARAMS = 2;
	public static final int MINUS_PARAMS = 1;
	public static final int RANDOM_PARAMS = 1;
	public static final int SIN_PARAMS = 1;
	public static final int COS_PARAMS = 1;
	public static final int TAN_PARAMS = 1;
	public static final int ATAN_PARAMS = 1;
	public static final int LOG_PARAMS = 1;
	public static final int POW_PARAMS = 1;
	public static final int PI_PARAMS = 0;
	public static final int LESS_PARAMS = 2;
	public static final int GREATER_PARAMS = 2;
	public static final int EQUAL_PARAMS = 2;
	public static final int NOT_EQUAL_PARAMS = 2;
	public static final int AND_PARAMS = 2;
	public static final int OR_PARAMS = 2;
	public static final int NOT_PARAMS = 1;
	public static final int MAKE_PARAMS = 2;
	public static final int REPEAT_PARAMS = 2;
	public static final int DO_TIMES_PARAMS = 2;
	public static final int FOR_PARAMS = 2;
	public static final int IF_PARAMS = 2;
	public static final int IF_ELSE_PARAMS = 3;
	public static final int TO_PARAMS = 3;
	
	public static final Map<String, Integer> myStatementParamMap = new HashMap<>();
	
	public static void initializeMap(){
		myStatementParamMap.put("Forward", FORWARD_PARAMS);
		myStatementParamMap.put("Sum", SUM_PARAMS);
	}
}
