package sLogo_team02;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.MenuItem;
import parser.Parser;
import programBuilder.ProgramBuilder;
import Model.Model;
import view.ViewAbstract;
import view.ViewFX;


/**
 * This class stores the Model, view,parser and programbuilder. It is also able to create it's own
 * menuItem button, this allows the user to switch between multiple workspaces as each of it has
 * it's own individual view,model,parser and programBuilder
 * 
 * @author Sivaneshwaran
 *
 */
public class Workspace {
    private ViewAbstract myView;
    private Model myModel;
    private Parser myParser;
    private ProgramBuilder myProgBuilder;

    public Workspace (Controller control) {
        myParser = new Parser();
        myView = new ViewFX(control);
        myModel = new Model(myView);
        myProgBuilder =
                new ProgramBuilder(myView, myModel.getTurtleManager(),
                                   myModel.getVariableManager(), myParser.getRegex(),
                                   myModel.getMethodManager(), myModel.getColors(),
                                   myModel.getShapes());
    }

    public ViewAbstract getView () {
        return myView;
    }

    public Model getModel () {
        return myModel;
    }

    public Parser getParser () {
        return myParser;
    }

    public ProgramBuilder getProgBuilder () {
        return myProgBuilder;
    }

    public MenuItem createMenuBox (SimpleIntegerProperty value) {
        MenuItem workspaceMenu = new MenuItem(
                                              Integer.toString(value.getValue()));
        workspaceMenu.setOnAction(e -> value.setValue(Integer
                .parseInt(workspaceMenu.getText())));
        return workspaceMenu;
    }
}
