package Statements;

/**
 * @author Yancheng, Sierra
 */
public class Value extends Statement {

	private double myValue;

	/**
	 * Constructor for Value.
	 * @param value double
	 */
	public Value(double value) {
		myValue = value;
	}

	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		System.out.println("returning value " + myValue);
		return myValue;
	}

}
