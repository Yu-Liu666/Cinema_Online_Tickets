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

import com.group99.javabean.Student;
/**
 * DOM parser of students.xml.
 * @author group 99
 *
 */
public class StudentDomParser {
	/**
	 * Get a List of Student.
	 * @return A List of Student.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<Student> getStudents()
			throws ParserConfigurationException, SAXException, IOException {
		
		File file = new File("./students.xml");
		InputStream inputStream = new FileInputStream(file);

		List<Student> Students = new ArrayList<Student>();

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(inputStream);
		Element studentElements = document.getDocumentElement();

		NodeList studentNodes = studentElements.getElementsByTagName("student");

		for (int i = 0; i < studentNodes.getLength(); i++) {
			Student Student = new Student();

			Element studentElement = (Element) studentNodes.item(i);

			NodeList childNodes = studentElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ((childNodes.item(j).getNodeName()).equals("name")) {
						Student.setName(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("studentNum")) {
						Student.setStudentNum(childNodes.item(j).getTextContent());
					}
				}
			}
			Students.add(Student);
		}
		return Students;
	}
}
