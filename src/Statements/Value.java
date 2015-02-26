package Statements;

public class Value extends Statement {

	private double myValue;

	public Value(double value) {
		myValue = value;
	}

	@Override
	public double execute() {
		System.out.println("returning value " + myValue);
		return myValue;
	}

}
