package Model;

public class Pen {

	public static final String DASHED_STROKE = "Dashed";
	public static final String DOTTED_STROKE = "Dotted";
	public static final String SOLID_STROKE = "Regular";
	public static final String DEFAULT_STROKE = DASHED_STROKE;
	public static final double DEFAULT_THICKNESS = 1.0;
	// defualt color is black
	public static final String DEFAULT_COLOR = "#000000";

	private boolean isUp;
	private String myStroke;
	private double myThickness;
	private String myColor;

	public Pen() {
		isUp = false;
		myStroke = DEFAULT_STROKE;
		myThickness = DEFAULT_THICKNESS;
		myColor = DEFAULT_COLOR;
	}

	public void calculateAndSetColor(int r, int g, int b) {
		myColor = String.format("#%02X%02X%02X", (int) (r * 255),
				(int) (g * 255), (int) (b * 255));
	}
	
	public void setStroke(String stroke){
		myStroke = stroke;
	}

}
