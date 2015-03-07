package sLogo_team02;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import Model.Model;


public class XmlParser {
	private String myFilename;
	private Element myRoot;
	private Model myModel;
	private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.XML.XMLText",new Locale("en", "US"));
	
	
	public void loadXML(String path, Model model){
	        myFilename = path;
	        myModel = model;
		try {		
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
		parseTurtle((Element)myRoot.getElementsByTagName(myStringResources.getString("root")).item(0));
	}
	
	public void parseTurtle(Element e) {
		Element number = (Element)e.getElementsByTagName(myStringResources.getString("numTurtle")).item(0);
		int num = Integer.parseInt(number.getTextContent());
		for(int i = 0; i < num; i++) {
			Element turtle = (Element)e.getElementsByTagName(myStringResources.getString("turtle")).item(i);
			Element xPos = (Element)turtle.getElementsByTagName(myStringResources.getString("x")).item(0);
			Element yPos = (Element)turtle.getElementsByTagName(myStringResources.getString("y")).item(0);
			Element myID = (Element)turtle.getElementsByTagName(myStringResources.getString("ID")).item(0);
			double x = Double.parseDouble(xPos.getTextContent());
			double y = Double.parseDouble(yPos.getTextContent());
			int id = Integer.parseInt(myID.getTextContent());
			myModel.addTurtle(x,y,id);
		}
	}


}

