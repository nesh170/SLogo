package programBuilder;

import java.util.List;

import parser.Regex;
import view.ViewAbstract;
import Model.*;
import Statements.*;
import Statements.action_command.Backward;
import Statements.action_command.Forward;
import Statements.action_command.Home;
import Statements.action_command.Left;
import Statements.action_command.Right;
import Statements.action_command.SetHeading;
import Statements.action_command.SetPenColor;
import Statements.action_command.SetPenSize;
import Statements.action_command.SetShape;
import Statements.action_command.SetTowards;
import Statements.action_command.SetXY;
import Statements.command.MakeVariable;
import Statements.command.SetPalette;
import Statements.loop.For;
import Statements.loop.MakeUserInstruction;
import Statements.loop.Repeat;
import Statements.operator.And;
import Statements.operator.LessThan;
import Statements.operator.Not;
import Statements.operator.NotEqual;
import Statements.operator.Or;
import Statements.operator.Tell;
import Statements.query.ClearScreen;
import Statements.query.ClearStamps;
import Statements.query.Heading;
import Statements.query.HideTurtle;
import Statements.query.PenDownP;
import Statements.query.SetPenDown;
import Statements.query.SetPenUp;
import Statements.query.ShowTurtle;
import Statements.query.ShowingP;
import Statements.query.Stamp;
import Statements.query.XCor;
import Statements.query.YCor;
import Statements.view_command.ATan;
import Statements.view_command.Cos;
import Statements.view_command.Difference;
import Statements.view_command.Log;
import Statements.view_command.Pi;
import Statements.view_command.Pow;
import Statements.view_command.Quotient;
import Statements.view_command.RandomGen;
import Statements.view_command.Sin;
import Statements.view_command.Sum;
import Statements.view_command.Tan;
import exceptions.*;

public class CommandFactory {

	public CommandFactory() {
	}

	// remember to group all the parameters
	// change my
	public static Statement generateCommand(String commandType,
			List<List<Statement>> statements, ViewAbstract myView,
			TurtleManager myTurtleManager, VariableManager myVariableManager,
			Regex myRegex, MethodManager myMethodManager, List<String> colors,
			List<String> shapes) throws ParserException {

		switch (commandType) {
		case "Forward":
			System.out.println("Making forward object");
			return new Forward(statements.get(0), myView, myTurtleManager,
					colors);
		case "Backward":
			return new Backward(statements.get(0), myView, myTurtleManager,
					colors);
		case "Left":
			return new Left(statements.get(0), myView, myTurtleManager);
		case "SetTowards":
			return new SetTowards(statements.get(0), myView, myTurtleManager);
		case "SetPosition":
			return new SetXY(statements.get(0), myView, myTurtleManager, colors);
		case "Home":
			return new Home(statements.get(0), myView, myTurtleManager, colors);
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
			return new MakeVariable(statements.get(0), myView,
					myVariableManager);
		case "SetHeading":
			return new SetHeading(statements.get(0), myView, myTurtleManager);
		case "PenUp":
			return new SetPenUp(myView, myTurtleManager);
		case "PenDown":
			return new SetPenDown(myView, myTurtleManager);
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
			return new Sin(statements.get(0), myView);
		case "Cosine":
			return new Cos(statements.get(0), myView);
		case "Tangent":
			return new Tan(statements.get(0), myView);
		case "NaturalLog":
			return new Log(statements.get(0), myView);
		case "Power":
			return new Pow(statements.get(0), myView);
		case "Pi":
			return new Pi(statements.get(0), myView);
		case "Random":
			return new RandomGen(statements.get(0), myView);
		case "ShowTurtle":
			return new ShowTurtle(myView, myTurtleManager);
		case "HideTurtle":
			return new HideTurtle(myView, myTurtleManager);
		case "ClearScreen":
			return new ClearScreen(myView, myTurtleManager);
		case "For":
			return new For(statements, myVariableManager);
		case "MakeUserInstruction":
			return new MakeUserInstruction(statements, myVariableManager,
					myMethodManager);
		case "Tell":
			return new Tell(statements.get(0), myTurtleManager);
		case "SetPenColor":
			return new SetPenColor(statements.get(0), myView, myTurtleManager,
					colors);
		case "SetPenSize":
			return new SetPenSize(statements.get(0), myView, myTurtleManager);
		case "SetPalette":
			return new SetPalette(statements.get(0), myView, colors);
		case "SetShape":
			return new SetShape(statements.get(0), myView, myTurtleManager,
					shapes);
		case "Stamp":
			return new Stamp(myView, myTurtleManager);
		case "Clearstamps":
			return new ClearStamps(myView, myTurtleManager);
		default:
			throw new ParserException("Command not valid.");
		}
	}
}