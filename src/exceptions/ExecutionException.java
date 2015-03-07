package exceptions;


/**
 * This is an exception when there is a runtime execution error
 * @author Sierra, Yancheng
 */
public class ExecutionException extends Exception {
    
    private static final long serialVersionUID = 1L;
    private String myErrorMessage;

    public ExecutionException (String error) {
        myErrorMessage = error;
    }

    public String toString () {
        return myErrorMessage;
    }

}
