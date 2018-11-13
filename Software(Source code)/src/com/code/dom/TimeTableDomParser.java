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

import com.group99.javabean.TimeTable;
/**
 * DOM parser of timetables.xml.
 * @author group 99
 *
 */
public class TimeTableDomParser {
	/**
	 * Get a List of TimeTable.
	 * @return A List of TimeTable.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<TimeTable> getTimeTables()
			throws ParserConfigurationException, SAXException, IOException {
		
		File file = new File("./timetables.xml");
		InputStream inputStream = new FileInputStream(file);

		List<TimeTable> timeTables = new ArrayList<TimeTable>();

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(inputStream);
		Element timeElements = document.getDocumentElement();

		NodeList timeNodes = timeElements.getElementsByTagName("timetable");

		for (int i = 0; i < timeNodes.getLength(); i++) {
			TimeTable timetable = new TimeTable();

			Element timeElement = (Element) timeNodes.item(i);

			NodeList childNodes = timeElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ((childNodes.item(j).getNodeName()).equals("time")) {
						timetable.setTime(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("movie")) {
						timetable.setMovie(childNodes.item(j).getTextContent());
					}else if ((childNodes.item(j).getNodeName()).equals("screen")) {
						timetable.setScreen(childNodes.item(j).getTextContent());
					}else if ((childNodes.item(j).getNodeName()).equals("id")) {
						timetable.setId(childNodes.item(j).getTextContent());
					}
				}
			}
			timeTables.add(timetable);
		}
		return timeTables;
	}
}
