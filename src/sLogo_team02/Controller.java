package sLogo_team02;

import java.util.ArrayList;
import java.util.List;
import parser.*;
import slogoEnums.ViewConstants;
import Model.Program;
import Model.XmlParser;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import exceptions.*;


/**
 * This is the controller method, it passes the inputs from the view to the model. It coordinates
 * between view and model. It also holds the
 * workspaceList which can change workspaces to support multiple terminals.
 * 
 * @author Jangsoon Park, Siera, Yancheng, Sivaneshwaran
 *
 */
public class Controller {
    public static final String TITLE = "SLOGO Team_02 ";
    public static final String WORKSPACES = "Workspace ";
    public static final String INVALID_XML = "Invalid XML File";
    private Stage myStage;
    private Scene myScene;
    private List<Workspace> myWorkspaceList = new ArrayList<Workspace>();
    private SimpleIntegerProperty myTabNumber = new SimpleIntegerProperty(0);
    private Menu myMenu;
    private MenuBar myMenuBar;
    private XmlParser myXMLParser;
    private XmlWriter myXMLWriter;

    public Controller (Stage stage) {
        myStage = stage;
        myXMLParser = new XmlParser();
        myXMLWriter = new XmlWriter();

    }

    /**
     * This method adds a new workspace and switches into it. It is called when the user hits the +
     * button
     */
    private void addWorkspace () {
        Workspace tempWorkSpace = new Workspace(this);
        myWorkspaceList.add(tempWorkSpace);
        tempWorkSpace.getView().getRoot().getChildren().add(myMenuBar);
        myScene.setRoot(tempWorkSpace.getView().getRoot());
        myTabNumber.setValue(myWorkspaceList.size() - 1);
        myMenu.getItems().add(tempWorkSpace.createMenuBox(myTabNumber));
    }

    public void setUpStage () {
        myScene =
                new Scene(new Group(), ViewConstants.STAGE_WIDTH.getVal(),
                          ViewConstants.STAGE_HEIGHT.getVal(), Color.ALICEBLUE);
        createMenuBar();
        addWorkspace();
        myTabNumber.addListener(new ChangeListener<Number>() {
            @Override
            public void changed (ObservableValue<? extends Number> ov, Number value, Number newValue) {
                Group tempRoot = myWorkspaceList.get((int) newValue).getView().getRoot();
                myScene.setRoot(tempRoot);
                myStage.setTitle(TITLE + WORKSPACES + Integer.toString((int) newValue));
                if (!tempRoot.getChildren().contains(myMenuBar)) {
                    tempRoot.getChildren().add(myMenuBar);
                }
            }
        });
        ;
        myStage.setScene(myScene);
        myStage.setTitle(TITLE + WORKSPACES + Integer.toString(myTabNumber.getValue()));
        myStage.show();
    }

    private void createMenuBar () {
        myMenuBar = new MenuBar();
        myMenu = new Menu(WORKSPACES);
        myMenuBar.getMenus().add(myMenu);
        MenuItem plus = new MenuItem("+");
        plus.setOnAction(e -> addWorkspace());
        myMenu.getItems().add(plus);

    }

    public void updateVariable (String variableName, double value) {
        myWorkspaceList.get(myTabNumber.getValue()).getModel().updateVariable(variableName, value);
    }

    /**
     * This creates the syntaxTress and send it to the program builder to create programs. It ends
     * by sending those programs to the model to be executed
     * 
     * @param codeData
     */
    public void executeProgram (String codeData) {
        try {
            List<ParseNode> topNodes =
                    myWorkspaceList.get(myTabNumber.getValue()).getParser().parse(codeData);
            System.out.println("Printing after parsing: " + topNodes.size());
            Program newProg =
                    myWorkspaceList.get(myTabNumber.getValue()).getProgBuilder()
                            .buildProgram(topNodes);
            myWorkspaceList.get(myTabNumber.getValue()).getModel().processCommand(newProg);
        }
        catch (ParserException e) {
            myWorkspaceList.get(myTabNumber.getValue()).getView().printError(e.toString());
        }
        catch (NullPointerException e) {
            // If the user pressed run without any input, preprocess will return null, and we just
            // want to ignore it
        }
    }

    public void changeLanguage (String languagePath) {
        myWorkspaceList.get(myTabNumber.getValue()).getParser().changeLanguage(languagePath);
    }

    /**
     * The toggle active toggles between the active and passive turtles
     * 
     * @param iD
     */
    public void setToggleActive (int iD) {
        myWorkspaceList.get(myTabNumber.getValue()).getModel().toggleActive(iD);
    }

    public void saveXML (String path) {
        try {
            myXMLWriter.beginXMLWriting(path, myWorkspaceList.get(myTabNumber.getValue())
                    .getModel().getTurtleMap());
        }
        catch (Exception e) {
            myWorkspaceList.get(myTabNumber.getValue()).getView().printError(INVALID_XML);
        }
    }

    public void loadXML (String path) {
        try {
            myWorkspaceList.get(myTabNumber.getValue()).getView().clearScreen();
            myWorkspaceList.get(myTabNumber.getValue()).getModel().clearTurtles();
            myXMLParser.loadXML(path, myWorkspaceList.get(myTabNumber.getValue()).getModel());
        }
        catch (Exception e) {
            myWorkspaceList.get(myTabNumber.getValue()).getView().printError(INVALID_XML);
        }
    }

    public void initiateDialogBox (int iD) {
        myWorkspaceList.get(myTabNumber.getValue()).getModel().sendInfoForDialog(iD);
    }

}
