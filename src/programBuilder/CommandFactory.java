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
		case "Right":
                    return new Right(statements.get(0), myView, myTurtleManager);
		case "Left":
		        return new Left(statements.get(0),myView,myTurtleManager);
		case "SetHeading":
		        return new SetHeading(statements.get(0), myView, myTurtleManager);
		case "SetTowards":
		        return new SetTowards(statements.get(0),myView,myTurtleManager);
		case "SetPosition":
		        return new SetXY(statements.get(0),myView,myTurtleManager);
		case "Home":
		        return new Home(statements.get(0),myView,myTurtleManager);
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
		case "YCoordinate":
		        return new YCor(myView, myTurtleManager);
		case "IsPenDown":
		        return new PenDownP(myView, myTurtleManager);
		case "IsShowing":
		        return new ShowingP(myView, myTurtleManager);
		case "Heading":
			return new Heading(myView, myTurtleManager);
		case "LessThan":
			return new LessThan(statements.get(0));
		case "Repeat":
			return new Repeat(statements, myVariableManager);
		case "MakeVariable":
			return new MakeVariable(statements.get(0), myView, myVariableManager);
		case "And":
		        return new And(statements.get(0));
		case "NotEqual":
		        return new NotEqual(statements.get(0));
		case "Or":
		        return new Or(statements.get(0));
		case "Not":
		        return new Not(statements.get(0));
		case "ArcTangent":
		        return new ATan(statements.get(0), myView);
		case "Sine":
		        return new Sin(statements.get(0),myView);
		case "Cosine":
		        return new Cos(statements.get(0), myView);
		case "Tangent":
		        return new Tan(statements.get(0),myView);
		case "NaturalLog":
		        return new Log(statements.get(0),myView);
		case "Power" :
		        return new Pow(statements.get(0), myView);
		case "Pi" :
		        return new Pi(statements.get(0),myView);
		        
		default:
			// throw an error
			return new Forward(statements.get(0), myView, myTurtleManager);
		}
	}
}