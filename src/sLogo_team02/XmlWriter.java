package sLogo_team02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import Model.SingleTurtle;


/**
 * This method is the XMLWriter. It obtains an unmodifiable hashmap from the model and uses it to
 * write to the XML File. It currently only writes in the turtlesID, X location and Y location.
 * 
 * @author Sivaneshwaran
 *
 */
public class XmlWriter {
    private XMLStreamWriter writer;
    private String XMLVersion = "1.0";
    private ResourceBundle myStringResources = ResourceBundle.getBundle("resources.XML.XMLText",
                                                                        new Locale("en", "US"));

    public void beginXMLWriting (String path, Map<Integer, SingleTurtle> map)
                                                                             throws XMLStreamException,
                                                                             FileNotFoundException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        writer = factory.createXMLStreamWriter(new FileOutputStream(new File(path)));
        writer.writeStartDocument(XMLVersion);
        writer.writeStartElement(myStringResources.getString("defaultSetting"));
        writer.writeStartElement(myStringResources.getString("root"));
        writeToXML(map);
        writer.writeEndElement();
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        writer.close();
    }

    private void writeToXML (Map<Integer, SingleTurtle> map) throws XMLStreamException {
        writeHelper(myStringResources.getString("numTurtle"), map.keySet().size());
        for (Integer k : map.keySet()) {
            writer.writeStartElement(myStringResources.getString("turtle"));
            writeHelper("x", map.get(k).getX());
            writeHelper("y", map.get(k).getY());
            writeHelper("ID", map.get(k).getID());
            writer.writeEndElement();
        }
    }

    private void writeHelper (String element, Number value) throws XMLStreamException {
        writer.writeStartElement(myStringResources.getString(element));
        writer.writeCharacters(value.toString());
        writer.writeEndElement();
    }

}
