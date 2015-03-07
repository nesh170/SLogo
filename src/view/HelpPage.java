package view;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * This page renders the HTML page using the java WebEngine.
 * 
 * @author Sivaneshwaran
 */
public class HelpPage extends Application {
    private String myPath;
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.View.ViewText",
                                                                        new Locale("en", "US"));

    /**
     * Constructor for HelpPage.
     * 
     * @param path String
     */
    public HelpPage (String path) {
        myPath = path;
    }

    /**
     * Method start.
     * 
     * @param s Stage
     * @throws Exception
     */
    @Override
    public void start (Stage s) throws Exception {
        Group root = new Group();
        root.getChildren().add(initializePage());
        s.setTitle(myStringResources.getString("helpPageTitle"));
        s.setScene(new Scene(root));
        s.show();
    }

    /**
     * Method initializePage.
     * 
     * @return WebView
     */
    private WebView initializePage () {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        File htmlFile = new File(myPath);
        webEngine.load(htmlFile.toURI().toString());
        return browser;
    }

}
