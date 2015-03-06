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


public class XmlParser {
	private File myFile;
	private String myFilename;
	private Element myRoot;
	private TurtleManager myTurtleManager;
	
	public XmlParser (File file, TurtleManager tm) {
		myFile = file;
		myFilename = myFile.getPath();
		myTurtleManager = tm;
	}

	public void parseXmlFile(){
		try {		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			//parse using builder to get DOM representation of the XML file
			Document dom = db.parse(myFilename);			
			//get the root element
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
	
	public void parseImage(Element e) {
		
	}
	
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
			myTurtleManager.addTurtle(myID, x, y);
		}
	}
	
	public void parseLanguage(Element e) {
		String language = e.getTextContent();
		
	}
	
	public void parseFile(Element e) {
		
	}
}

