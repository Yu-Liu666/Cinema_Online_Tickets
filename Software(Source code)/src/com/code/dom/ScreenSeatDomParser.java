package com.group99.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.group99.javabean.Screen;
/**
 * DOM parser of screen*At*.xml.
 * @author group 99
 *
 */
public class ScreenSeatDomParser {
	/**
	 * The update the seat by providing fileName and seat id.
	 * @param fileName The file name you want to update.
	 * @param updateSeatId	The id of the seat you want to update.
	 * @param updateValue The value you want to update(true of false).
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static void updateScreenSeat(String fileName, String updateSeatId, String updateValue)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {

		File file = new File("./" + fileName);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		document.getDocumentElement().normalize();
		
        NodeList filmNode = document.getElementsByTagName("seat");
        Element element = null;

        for(int i=0; i<filmNode.getLength();i++){    	
        	element = (Element) filmNode.item(i);
        	if(element.getElementsByTagName("seatId").item(0).getFirstChild().getTextContent().equals(updateSeatId)){
                Node name = element.getElementsByTagName("seatIsEmpty").item(0).getFirstChild();
                name.setNodeValue(updateValue);
                
        		document.getDocumentElement().normalize();
        		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        		Transformer transformer = transformerFactory.newTransformer();
        		DOMSource source = new DOMSource(document);
        		StreamResult result = new StreamResult(new File(fileName));
        		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        		transformer.transform(source, result);
        		
                break;
        	}else{
        		if(i == filmNode.getLength() - 1)
        			System.out.println("The film don't exist!\tXML file updated failed!");
        	}
        }
	}
	
	
	/**
	 * Get a List of Screen by providing file name.
	 * @param fileName The name of a file you want to update.
	 * @return A List of Screen.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<Screen> getScreen(String fileName)
			throws ParserConfigurationException, SAXException, IOException {
		
		File file = new File("./" + fileName);
		InputStream inputStream = new FileInputStream(file);

		List<Screen> screens = new ArrayList<Screen>();

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(inputStream);
		Element screenElements = document.getDocumentElement();

		NodeList screenNodes = screenElements.getElementsByTagName("seat");

		for (int i = 0; i < screenNodes.getLength(); i++) {
			Screen screen = new Screen();

			Element screenElement = (Element) screenNodes.item(i);
			
			NodeList childNodes = screenElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ((childNodes.item(j).getNodeName()).equals("seatId")) {
						screen.setSeatId(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("seatMov")) {
						screen.setSeatMov(childNodes.item(j).getTextContent());
					}else if ((childNodes.item(j).getNodeName()).equals("seatIsEmpty")) {
						screen.setSeatIsEmpty(childNodes.item(j).getTextContent());
					}
				}
			}
			screens.add(screen);
		}
		return screens;
	}
}
