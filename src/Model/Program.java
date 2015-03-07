package Model;

import java.util.ArrayList;
import java.util.List;
import view.*;

import Statements.Statement;


/**
 * The Program class is holds a list of statement objects and executes them when instructed to.
 * It can run the same set of statements as many times as needed by multiple calls to execute.
 * @author Sierra, Yancheng
 */
public class Program {
	
	/** The list of statements. */
	private List<Statement> myStatements;
	
	/** The view that displays the turtles for this model and that the commands will operate on. */
	private ViewAbstract myView;
	
	/**
	 * Instantiates a new program.
	 *
	 * @param view the view
	 */
	public Program(ViewAbstract view) {
		myStatements = new ArrayList<>();
		myView = view;
	}

	/**
	 * Adds a statement to the end of list of statements in the program
	 *
	 * @param toAdd the to add
	 */
	public void addStatment(Statement toAdd){
		myStatements.add(toAdd);
	}
	
	/**
	 * Executes the list of statements in the program in order and prints the results of the
	 * top statements to the terminal in the view.
	 */
	public void execute() {
		for (Statement statement : myStatements) {
			myView.printMessage(statement.execute()+ "");
		}
	}

}
