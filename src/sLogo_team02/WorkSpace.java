package sLogo_team02;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import parser.Parser;
import programBuilder.ProgramBuilder;
import Model.Model;
import view.ViewAbstract;
import view.ViewFX;

public class WorkSpace {
    private ViewAbstract myView;
    private Model myModel;
    private Parser myParser;
    private ProgramBuilder myProgBuilder;
    
    public WorkSpace(Controller control){
        myParser = new Parser();
        myView=new ViewFX(control);
        myModel = new Model(myView);
        myProgBuilder = new ProgramBuilder(myView, myModel.getTurtleManager(), 
                                           myModel.getVariableManager(), myParser.getRegex(), myModel.getMethodManager());
    }
    
    public ViewAbstract getView(){
        return myView;
    }
    
    public Model getModel(){
        return myModel;
    }
    
    public Parser getParser(){
        return myParser;
    }
    
    public ProgramBuilder getProgBuilder(){
        return myProgBuilder;
    }
    
    public MenuItem createMenuBox(SimpleIntegerProperty value){
        MenuItem workspaceMenu = new MenuItem(Integer.toString(value.getValue()));
        workspaceMenu.setOnAction(e -> value.setValue(Integer.parseInt(workspaceMenu.getText())));
        return workspaceMenu;
    }
}
