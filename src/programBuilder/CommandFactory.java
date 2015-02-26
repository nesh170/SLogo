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
		case "Sum":
			System.out.println("Making sum object");
			return new Sum(statements.get(0), myView);
		default:
			// throw an error
			return new Forward(statements.get(0), myView, myTurtleManager);
		}
	}
}