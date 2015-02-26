package programBuilder;

import java.util.List;

import parser.Regex;
import view.ViewAbstract;
import Model.TurtleManager;
import Model.VariableManager;
import Statements.*;

public class CommandFactory {

	public CommandFactory() {
	}

	public static Statement generateCommand(String commandType,
			List<List<Statement>> statements, ViewAbstract myView,
			TurtleManager myTurtleManager, VariableManager myVariableManager,
			Regex myRegex) {
		switch (commandType) {
		case "Forward":
			System.out.println("Making forward object");
			return new Forward(statements.get(0), myView, myTurtleManager);
		case "Backward":
			return new Backward(statements.get(0), myView, myTurtleManager);
		case "Sum":
			System.out.println("Making sum object");
			return new Sum(statements.get(0), myView);
		case "Difference":
			System.out.println("Making difference object");
			return new Difference(statements.get(0), myView);
		case "Quotient":
			System.out.println("Making quotient object");
			return new Quotient(statements.get(0), myView);
		case "XCoordinate":
			return new XCor(myView, myTurtleManager);
		case "Right":
			return new Right(statements.get(0), myView, myTurtleManager);
		case "Heading":
			return new Heading(myView, myTurtleManager);
		case "LessThan":
			return new LessThan(statements.get(0));
		case "Repeat":
			return new Repeat(statements, myVariableManager);
		case "MakeVariable":
			return new Make(statements.get(0), myView, myVariableManager);
		default:
			// throw an error
			return new Forward(statements.get(0), myView, myTurtleManager);
		}
	}
}