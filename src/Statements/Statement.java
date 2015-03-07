package Statements;

/**
 * The purpose of this class is to act as the head of the hierarchy for all
 * statement objects. In reference to the command design pattern, this is the
 * base class that outlines the interface for executing any operation.
 * 
 * @author Sierra, Yancheng
 *
 */
public abstract class Statement {

	/**
	 * This is the method that all statement objects must implement. It is used
	 * to execute whatever operation the statement carries out and returns a
	 * value which represents some result of that operation and can be used the
	 * the caller.
	 * 
	 * @return the result of the execute operation
	 */
	public abstract double execute();
}
