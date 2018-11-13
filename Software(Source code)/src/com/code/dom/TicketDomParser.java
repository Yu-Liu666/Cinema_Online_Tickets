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
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.group99.javabean.Ticket;
/**
 * DOM parser of tickets.xml.
 * @author group 99
 *
 */
public class TicketDomParser {
	/**
	 * Creating a ticket by providing a Ticket.
	 * @param ticket A object of Ticket.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static void createTicket(Ticket ticket)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder dbbuilder = dbfactory.newDocumentBuilder();
		Document doc = dbbuilder.parse(new File("./tickets.xml"));

		Element ticketElement = doc.createElement("ticket");

		Element filmNameElement = doc.createElement("filmName");
		Element ticketIdElement = doc.createElement("ticketId");
		Element timeStrElement = doc.createElement("timeStr");
		Element screenNameElement = doc.createElement("screenName");
		Element ticketTypeElement = doc.createElement("ticketType");
		Element seatLocationElement = doc.createElement("seatLocation");
		Element ticketPriceElement = doc.createElement("ticketPrice");
		Element studentIdElement = doc.createElement("studentId");

		Text filmName = doc.createTextNode(String.valueOf(ticket.getFilmName()));
		Text ticketId = doc.createTextNode(String.valueOf(ticket.getTicketId()));
		Text timeStr = doc.createTextNode(String.valueOf(ticket.getTimeStr()));
		Text screenName = doc.createTextNode(String.valueOf(ticket.getScreenName()));
		Text ticketType = doc.createTextNode(String.valueOf(ticket.getTiketType()));
		Text seatLocation = doc.createTextNode(String.valueOf(ticket.getSeatLocation()));
		Text ticketPrice = doc.createTextNode(String.valueOf(ticket.getTicketPrice()));
		Text studentId = doc.createTextNode(ticket.getStudentId());

		ticketElement.appendChild(filmNameElement).appendChild(filmName);
		ticketElement.appendChild(ticketIdElement).appendChild(ticketId);
		ticketElement.appendChild(timeStrElement).appendChild(timeStr);
		ticketElement.appendChild(screenNameElement).appendChild(screenName);
		ticketElement.appendChild(ticketTypeElement).appendChild(ticketType);
		ticketElement.appendChild(seatLocationElement).appendChild(seatLocation);
		ticketElement.appendChild(ticketPriceElement).appendChild(ticketPrice);
		ticketElement.appendChild(studentIdElement).appendChild(studentId);

		doc.getDocumentElement().appendChild(ticketElement);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		DOMSource source = new DOMSource(doc);
		StreamResult streamResult = new StreamResult(new File("./tickets.xml"));
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(source, streamResult);
	}
	/**
	 * Get a List of Ticket.
	 * @return	A List of Ticket.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<Ticket> getTickets() throws ParserConfigurationException, SAXException, IOException {

		File file = new File("./tickets.xml");
		InputStream inputStream = new FileInputStream(file);

		List<Ticket> tickets = new ArrayList<Ticket>();

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(inputStream);
		Element ticketElenments = document.getDocumentElement();

		NodeList ticketNodes = ticketElenments.getElementsByTagName("ticket");

		for (int i = 0; i < ticketNodes.getLength(); i++) {
			Ticket ticket = new Ticket();

			Element ticketElement = (Element) ticketNodes.item(i);

			NodeList childNodes = ticketElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ((childNodes.item(j).getNodeName()).equals("filmName")) {
						ticket.setFilmName(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("ticketId")) {
						ticket.setTicketId(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("timeStr")) {
						ticket.setTimeStr(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("screenName")) {
						ticket.setScreenName(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("ticketType")) {
						ticket.setTiketType(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("seatLocation")) {
						ticket.setSeatLocation(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("ticketPrice")) {
						ticket.setTicketPrice(Float.parseFloat(childNodes.item(j).getTextContent()));
					}else if ((childNodes.item(j).getNodeName()).equals("studentId")) {
						ticket.setStudentId(childNodes.item(j).getTextContent());
					}
				}
			}
			tickets.add(ticket);
		}
		return tickets;
	}
}
