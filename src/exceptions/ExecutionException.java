package exceptions;

public class ExecutionException extends Exception {

	private static final long serialVersionUID = 1L;
	private String myErrorMessage;
	
	public ExecutionException(String error){
		myErrorMessage = error;
	}
	
	public String toString(){
		return myErrorMessage;
	}
	
}
