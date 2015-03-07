package view;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import slogoEnums.ViewConstants;


/**
 * This class stores the historyCodeBox which contains a list of previously used comments. It adds
 * the whole code and the user can click on it to add it to the code box
 * 
 * @author Sivaneshwaran
 */
public class HistoryCodeBox {

    private ObservableList<String> myHistoryShortList;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",
                                                                        new Locale("en", "US"));
    private ListView<String> myHistoryListView;

    /**
     * Constructor for HistoryCodeBox.
     * 
     * @param root Group
     * @param listener ChangeListener<String>
     */
    public HistoryCodeBox (Group root, ChangeListener<String> listener) {
        initializeSuggestedView(root, listener);

    }

    /**
     * Method initializeSuggestedView.
     * 
     * @param root Group
     * @param listener ChangeListener<String>
     */
    private void initializeSuggestedView (Group root, ChangeListener<String> listener) {
        VBox historyBox = new VBox();
        historyBox.setPadding(new Insets(ViewConstants.VARIABLE_TABLE_PADDING.getVal()));
        historyBox.setSpacing(ViewConstants.VARIABLE_TABLE_SPACING.getVal());
        myHistoryListView = generateListView(listener);
        Text historyListTitle = new Text(myStringResources.getString("historyTitle"));
        historyBox.getChildren().addAll(historyListTitle, myHistoryListView);
        historyBox.setTranslateX(ViewConstants.HISTORY_START_X.getVal());
        root.getChildren().add(historyBox);
    }

    /**
     * Method generateListView.
     * 
     * @param listener ChangeListener<String>
     * @return ListView<String>
     */
    public ListView<String> generateListView (ChangeListener<String> listener) {
        myHistoryShortList = FXCollections.observableArrayList();
        ListView<String> historyListView = new ListView<>(myHistoryShortList);
        historyListView.setPrefSize(ViewConstants.HISTORY_WIDTH.getVal(),
                                    ViewConstants.HISTORY_HEIGHT.getVal());
        historyListView.setMaxSize(ViewConstants.HISTORY_WIDTH.getVal(),
                                   ViewConstants.HISTORY_HEIGHT.getVal());
        historyListView.getSelectionModel().selectedItemProperty().addListener(listener);
        return historyListView;
    }

    /**
     * Method addCodeToList.
     * Checks if the code user entered is empty, if it is, it isn't added to the history
     * 
     * @param code String
     */
    public void addCodeToList (String code) {
        if (!code.equals("")) {
            myHistoryShortList.add(code);
        }
    }

}
