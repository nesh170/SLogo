package Statements;

public class MethodName extends Statement {
	private String myName;
	
	public MethodName(String s){
		myName = s;
	}
	
	@Override
	public double execute() {
		return 0;
	}
	
	public String getName(){
		return myName;
	}

}
