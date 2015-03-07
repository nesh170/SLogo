package Model;

import java.util.ArrayList;
import java.util.List;
import view.*;

import Statements.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class Program.
 */
public class Program {
	
	/** The my statements. */
	private List<Statement> myStatements;
	
	/** The my view. */
	private ViewAbstract myView;
	
	// execute all of the statement objects
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
	 * Adds the statment.
	 *
	 * @param toAdd the to add
	 */
	public void addStatment(Statement toAdd){
		myStatements.add(toAdd);
	}
	
	/**
	 * Execute.
	 */
	public void execute() {
		System.out.println("Size of the statement: " + myStatements.size());
		System.out.println(myStatements.get(0).getClass().toString());
		for (Statement statement : myStatements) {
			myView.printMessage(statement.execute()+ "");
		}
	}

}
