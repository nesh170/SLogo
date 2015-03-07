package Statements;

/**This class handles the creation of user-defined method name.
 * @author Yancheng, Sierra
 */
public class MethodName extends Statement {
	private String myName;
	
	/**
	 * Constructor for MethodName.
	 * @param s String
	 */
	public MethodName(String s){
		myName = s;
	}
	
	/**
	 * Method execute.
	 * @return double
	 */
	@Override
	public double execute() {
		return 0;
	}
	
	/**
	 * Method getName.
	 * @return String
	 */
	public String getName(){
		return myName;
	}

}
