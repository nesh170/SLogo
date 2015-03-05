package sLogo_team02;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import parser.Parser;
import programBuilder.ProgramBuilder;
import Model.Model;
import view.ViewAbstract;
import view.ViewFX;

public class WorkSpace {
    private List<ViewAbstract> myViewList = new ArrayList<>();
    private List<Model> myModelList = new ArrayList<>();
    private List<Parser> myParserList = new ArrayList<>();
    private List<ProgramBuilder> myProgramBuilderList = new ArrayList<>();
    private ObservableList<Integer> myTabNumber = FXCollections.observableArrayList();
    private Controller myController;
    
    public WorkSpace(Controller controller){
        myController = controller;
        openStage(initializeViewList());
    }
    
    private Scene initializeViewList(){
        VBox tabBox = new VBox();
        ChoiceBox<Integer> tabList = new ChoiceBox<>(myTabNumber);
        Button createButton = new Button("+");
        createButton.setOnAction(e->addTab());
        tabBox.getChildren().addAll(new Text("Tab"),tabList, createButton); 
        return new Scene(tabBox, 100, 40);
    }
    
    private void openStage(Scene scene){
        Stage s = new Stage();
        s.setScene(scene);
        s.show();
    }
    
    private void addTab(){
        Parser tempParser = new Parser();
        ViewAbstract tempView = new ViewFX(myController);
        Model tempModel = new Model(tempView);
        ProgramBuilder tempProgBuilder = new ProgramBuilder(tempView, tempModel.getTurtleManager(), tempModel.getVariableManager(), tempParser.getRegex(), tempModel.getMethodManager());
        myViewList.add(tempView);myModelList.add(tempModel);myParserList.add(tempParser);myProgramBuilderList.add(tempProgBuilder);
        myTabNumber.add(myViewList.indexOf(tempView));
    }
}
