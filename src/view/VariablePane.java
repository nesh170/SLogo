package view;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import slogoEnums.ViewConstants;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class VariablePane {
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	
	private VBox myVariableBox;
	private ObservableList<String> myVariableName;
	private ObservableList<Double> myVariableValue;
	private ObservableList<String> myMethodList;
	private Map<String,String> myMethodMap;
	
	public VariablePane(Group root,ChangeListener<String> changeListener,EventHandler<ListView.EditEvent<Double>> handler){
		generateVariablePane(root);
		initializeVariableList(handler);
		initializeMethodList(changeListener);
	}


	public void generateVariablePane(Group root){
		myVariableBox = new VBox();
		myVariableBox.setPadding(new Insets(ViewConstants.VARIABLE_TABLE_PADDING.getVal()));
		myVariableBox.setSpacing(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
		myVariableBox.setMaxSize(ViewConstants.VARIABLE_TABLE_WIDTH.getVal(), ViewConstants.VARIABLE_TABLE_HEIGHT.getVal());
		myVariableBox.setTranslateX(ViewConstants.VARIABLE_TABLE_X.getVal());
		myVariableBox.getChildren().add(new Text(myStringResources.getString("variableTableTitle")));
		root.getChildren().add(myVariableBox);
	}

	public void addNumberVariables(String name,Double value) {
		if(myVariableName.contains(name)){
			myVariableValue.set(myVariableName.indexOf(name), value);
		}
		else{
			myVariableName.add(name);
			myVariableValue.add(value);
		}
	}
	
	public void setVariableValue(int index, Double newValue){
		myVariableValue.set(index, newValue);
	}
	
	public String getVariableName(int index){
		return myVariableName.get(index);
	}
	
	private void initializeVariableList(EventHandler<ListView.EditEvent<Double>> handler){
		HBox numberBox = new HBox();
		numberBox.setPrefWidth(ViewConstants.VARIABLE_TABLE_WIDTH.getVal());
		myVariableName = FXCollections.observableArrayList();
		myVariableValue = FXCollections.observableArrayList();
		ListView<String> variableNameList = new ListView<>(myVariableName);
		ListView<Double> variableValueList = new ListView<>(myVariableValue);
		makeListEditable(variableValueList,handler);
		numberBox.getChildren().addAll(variableNameList,variableValueList);
		myVariableBox.getChildren().add(numberBox);
	}
	
	private void makeListEditable(ListView<Double> simpleList,EventHandler<ListView.EditEvent<Double>> handler){
		simpleList.setEditable(true);
		simpleList.setCellFactory(TextFieldListCell.forListView(new StringConverter<Double>() {
			@Override
			public Double fromString(String arg0) {
				try{
					return Double.parseDouble(arg0);
				}
				catch(Exception e){
					return Double.NaN;
				}
			}

			@Override
			public String toString(Double arg0) {
				return Double.toString(arg0);
			}
		}));		
		simpleList.setOnEditCommit(handler);
	}
	
	
	public void addMethodVariable(String methodKey, String methodValue){
		if(!myMethodList.contains(methodKey)){
			myMethodList.add(methodKey);
		}
		myMethodMap.put(methodKey, methodValue);
	}

	private void initializeMethodList(ChangeListener<String> listener){
		myMethodMap = new HashMap<String, String>();
		myMethodList = FXCollections.observableArrayList();
		ListView<String> methodListView = new ListView<String>(myMethodList);
		methodListView.getSelectionModel().selectedItemProperty().addListener(listener);
		myVariableBox.getChildren().add(methodListView);
	}


	public String getFullMethod(String newString) {
		return myMethodMap.get(newString);
	}
	
	
}
