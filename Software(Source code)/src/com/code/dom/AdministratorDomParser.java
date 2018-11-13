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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.group99.javabean.Administrator;
/**
 * DOM parser of administrators.xml
 * @author group 99
 *
 */
public class AdministratorDomParser {
	/**
	 * Get a List of Administrator.
	 * @return List of object of Administrator.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<Administrator> getAdministrators()
			throws ParserConfigurationException, SAXException, IOException {
		
		File file = new File("./administrators.xml");
		InputStream inputStream = new FileInputStream(file);

		List<Administrator> administrators = new ArrayList<Administrator>();

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(inputStream);
		Element administratorElements = document.getDocumentElement();

		NodeList administratorNodes = administratorElements.getElementsByTagName("administrator");

		for (int i = 0; i < administratorNodes.getLength(); i++) {
			Administrator administrator = new Administrator();

			Element administratorElement = (Element) administratorNodes.item(i);

			NodeList childNodes = administratorElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ((childNodes.item(j).getNodeName()).equals("id")) {
						administrator.setId(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("password")) {
						administrator.setPassword(childNodes.item(j).getTextContent());
					}
				}
			}
			administrators.add(administrator);
		}
		return administrators;
	}
}
