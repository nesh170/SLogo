package Model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


// TODO: Auto-generated Javadoc
/**
 * The Class XmlParser.
 */
public class XmlParser {
	
	/** The my file. */
	private File myFile;
	
	/** The my filename. */
	private String myFilename;
	
	/** The my root. */
	private Element myRoot;
	
	/** The my turtle manager. */
	private MultipleTurtles myTurtleManager;

	/**
	 * Parses the xml file.
	 */
	public void parseXmlFile(){
		try {		
		        System.out.println("XML FILE PASS"+myFilename);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(myFilename);			
			myRoot = dom.getDocumentElement();
		}catch(ParserConfigurationException pce) {			
			pce.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//parse data from the three sections of the XML file
		parseBackground((Element)myRoot.getElementsByTagName("background").item(0));
		parseImage((Element)myRoot.getElementsByTagName("turtleImage").item(0));
		parseTurtle((Element)myRoot.getElementsByTagName("num_Turtles").item(0));
		parseLanguage((Element)myRoot.getElementsByTagName("fileToLoad").item(0));
		parseFile((Element)myRoot.getElementsByTagName("command_language").item(0));
	}
	
	/**
	 * Parses the background.
	 *
	 * @param e the e
	 */
	public void parseBackground(Element e) {
		Element width = (Element)e.getElementsByTagName("width");
		Element height = (Element)e.getElementsByTagName("height");
		Element color = (Element)e.getElementsByTagName("color");
		Element imagePath = (Element)e.getElementsByTagName("imagePath");
		int backWidth = Integer.parseInt(width.getTextContent());
		int backHeight = Integer.parseInt(height.getTextContent());
		String image = imagePath.getTextContent();
		String backColor = color.getTextContent();
	}
	
	/**
	 * Parses the image.
	 *
	 * @param e the e
	 */
	public void parseImage(Element e) {
		
	}
	
	/**
	 * Parses the turtle.
	 *
	 * @param e the e
	 */
	public void parseTurtle(Element e) {
		Element number = (Element)e.getElementsByTagName("numTurtle").item(0);
		int num = Integer.parseInt(number.getTextContent());
		for(int i = 0; i < num; i++) {
			Element turtle = (Element)e.getElementsByTagName("turtle").item(i);
			Element xPos = (Element)turtle.getElementsByTagName("xPos").item(0);
			Element yPos = (Element)turtle.getElementsByTagName("yPos").item(0);
			Element myID = (Element)turtle.getElementsByTagName("id").item(0);
			double x = Double.parseDouble(xPos.getTextContent());
			double y = Double.parseDouble(yPos.getTextContent());
			int id = Integer.parseInt(myID.getTextContent());
			//myTurtleManager.addTurtle(myID, x, y);
		}
	}
	
	/**
	 * Parses the language.
	 *
	 * @param e the e
	 */
	public void parseLanguage(Element e) {
		String language = e.getTextContent();
		
	}
	
	/**
	 * Parses the file.
	 *
	 * @param e the e
	 */
	public void parseFile(Element e) {
		
	}

    /**
     * Load xml.
     *
     * @param path the path
     * @param model the model
     */
    public void loadXML (String path, Model model) {
        myFilename = path;
        parseXmlFile();
        
        
    }

}

