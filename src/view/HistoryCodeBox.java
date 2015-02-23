package view;

import java.util.Locale;
import java.util.ResourceBundle;
import slogoEnums.ViewConstants;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HistoryCodeBox {
	
	private ObservableList<String> myHistoryShortList;
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",new Locale("en", "US"));
	private ListView<String> myHistoryListView;
	
	public HistoryCodeBox(Group root,ChangeListener<String> listener){
		initializeSuggestedView(root,listener);
		
	}
	
	private void initializeSuggestedView(Group root,ChangeListener<String> listener){
		VBox historyBox = new VBox();
		historyBox.setPadding(new Insets(ViewConstants.VARIABLE_TABLE_PADDING.getVal()));
	    historyBox.setSpacing(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
		myHistoryListView = generateListView(listener);
		Text historyListTitle = new Text(myStringResources.getString("historyTitle"));
		historyBox.getChildren().add(historyListTitle);
		historyBox.getChildren().add(myHistoryListView);
		historyBox.setTranslateX(ViewConstants.HISTORY_START_X.getVal());
		root.getChildren().add(historyBox);
	}
	
	public ListView<String> generateListView(ChangeListener<String> listener){
		myHistoryShortList = FXCollections.observableArrayList();
		ListView<String> historyListView = new ListView<>(myHistoryShortList);
		historyListView.setPrefSize(ViewConstants.HISTORY_WIDTH.getVal(),ViewConstants.HISTORY_HEIGHT.getVal());
		historyListView.setMaxSize(ViewConstants.HISTORY_WIDTH.getVal(),ViewConstants.HISTORY_HEIGHT.getVal());
		historyListView.getSelectionModel().selectedItemProperty().addListener(listener);
		return historyListView;
	}
	
	public void addCodeToList(String code){
		if(!code.equals("")){
			myHistoryShortList.add(code);
		}
	}
	

	
	
}
