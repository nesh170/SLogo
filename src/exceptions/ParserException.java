package exceptions;


/**
 * This is an exception when the parser receives an error.
 * @author Sierra, Yancheng
 */
public class ParserException extends Exception {

    private static final long serialVersionUID = 1L;
    private String myErrorMessage;

    public ParserException (String error) {
        myErrorMessage = error;
    }

    public String toString () {
        return myErrorMessage;
    }
}
