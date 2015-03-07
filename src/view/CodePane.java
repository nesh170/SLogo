package view;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import slogoEnums.ViewConstants;


/**
 * The codePane contains the code area where the user types in their code and the enter button and
 * the terminal where the user is able to see messages and also errors. It is stored in a gridPane
 * to sort them.
 * 
 * @author Sivaneshwaran
 */
public class CodePane {
    public static final String TERMINAL_ID = "Terminal";
    private TextArea myCodeArea;
    private TextFlow myTerminal;
    private Button myEnterButton;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",
                                                                        new Locale("en", "US"));
    private int myTerminalLineNumber = 1;
    private HistoryCodeBox myHistoryList;

    /**
     * Constructor for CodePane.
     * 
     * @param root Group
     * @param handler EventHandler<ActionEvent>
     */
    public CodePane (Group root, EventHandler<ActionEvent> handler) {
        initializeCodePane(root, handler);
    }

    /**
     * Method initializeCodePane.
     * 
     * @param root Group
     * @param handler EventHandler<ActionEvent>
     */
    private void initializeCodePane (Group root, EventHandler<ActionEvent> handler) {
        GridPane gridPane = new GridPane();
        generateCodeArea();
        generateTerminal();
        generateEnterButton(handler);
        Node[] nodeArray = { myCodeArea, myEnterButton, generateScrollTerminal() };
        for (int col = 0; col < nodeArray.length; col++) {
            gridPane.add(nodeArray[col], col, 1);
        }
        gridPane.setTranslateY(ViewConstants.CODE_PANE_Y.getVal());
        myHistoryList = new HistoryCodeBox(root, new ChangeListener<String>() {
            @Override
            public void changed (ObservableValue<? extends String> ov, String oldValue,
                                 String newValue) {
                clearAndFillCodeArea(newValue);
            }

        });
        root.getChildren().add(gridPane);
    }

    /**
     * Method generateScrollTerminal.
     * 
     * @return ScrollPane
     */
    private ScrollPane generateScrollTerminal () {
        ScrollPane scrollTerminal = new ScrollPane();
        scrollTerminal.setContent(myTerminal);
        scrollTerminal.setPrefSize(ViewConstants.TERMINAL_WIDTH.getVal(),
                                   ViewConstants.TERMINAL_HEIGHT.getVal());
        scrollTerminal.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollTerminal.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        return scrollTerminal;
    }

    private void generateCodeArea () {
        myCodeArea = new TextArea();
        myCodeArea.setPromptText(myStringResources.getString("codeAreaPrompt"));
        myCodeArea.setMinSize(ViewConstants.CODE_AREA_WIDTH.getVal(),
                              ViewConstants.CODE_AREA_HEIGHT.getVal());
    }

    private void generateTerminal () {
        Text termText = new Text(myStringResources.getString("terminalText"));
        termText.setFill(Color.YELLOW);
        myTerminal = new TextFlow(termText);
        myTerminal.setPrefSize(ViewConstants.TERMINAL_WIDTH.getVal() -
                               ViewConstants.TERMINAL_SCROLL_WIDTH_PADDING.getVal(),
                               ViewConstants.TERMINAL_HEIGHT.getVal() +
                                       ViewConstants.TERMINAL_SCROLL_HEIGHT_PADDING.getVal());
        Background termBlack = new Background(new BackgroundFill(Color.BLACK, null, null));
        myTerminal.setBackground(termBlack);
    }

    /**
     * Method generateEnterButton.
     * 
     * @param handler EventHandler<ActionEvent>
     */
    private void generateEnterButton (EventHandler<ActionEvent> handler) {
        myEnterButton = new Button(myStringResources.getString("enter"));
        myEnterButton.setMinSize(ViewConstants.ENTER_WIDTH.getVal(),
                                 ViewConstants.ENTER_HEIGHT.getVal());
        myEnterButton.setOnAction(handler);
        myEnterButton.setStyle(myStringResources.getString("enterButtonStyle"));
    }

    /**
     * Method getCodeData.
     * 
     * @return String
     */
    public String getCodeData () {
        myHistoryList.addCodeToList(myCodeArea.getText());
        return myCodeArea.getText();
    }

    /**
     * Method addTerminalText.
     * 
     * @param text String
     * @param color Color
     */
    public void addTerminalText (String text, Color color) {
        StringBuilder tempBuilder = new StringBuilder(Integer.toString(myTerminalLineNumber));
        tempBuilder.append(". ");
        tempBuilder.append(text);
        tempBuilder.append("\n"); // Adds a newLine character to ensure it goes to the next line
        Text TerminalText = new Text(tempBuilder.toString());
        TerminalText.setFill(color);
        myTerminal.getChildren().add(TerminalText);
        myTerminalLineNumber++;
    }

    public void clearTerminal () {
        myTerminal.getChildren().clear();
        myTerminalLineNumber = 1;
    }

    /**
     * Method clearAndFillCodeArea.
     * 
     * @param code String
     */
    private void clearAndFillCodeArea (String code) {
        myCodeArea.setText(code);
    }

    /**
     * Method fillCodeArea.
     * 
     * @param newValue String
     */
    public void fillCodeArea (String newValue) {
        myCodeArea.setText(myCodeArea.getText() + " " + newValue);
    }

}
