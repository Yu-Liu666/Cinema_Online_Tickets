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

import com.group99.javabean.Film;
/**
 * DOM parser of films.xml
 * @author group 99
 *
 */
public class FilmDomParser {
	
	public static String FILM_NAME = "filmName";
	public static String FILM_DURATION = "filmDuration";
	public static String FILM_DIRECTOR = "filmDirector";
	public static String FILM_STARS = "filmStars";
	public static String FILM_PRICE = "filmPrice";
	public static String FILM_STORYLINE = "filmStoryline";
	
	/**
	 * To update the films by providing the film name you want update.
	 * @param updateFilmName The film name you want to update.
	 * @param updateAttribute The attribute you want to update.
	 * @param updateValue	The value you want to update.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static void updateFilms(String updateFilmName, String updateAttribute, String updateValue)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {

		File file = new File("./films.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		document.getDocumentElement().normalize();
		
        NodeList filmNode = document.getElementsByTagName("film");
        Element element = null;

        for(int i=0; i<filmNode.getLength();i++){    	
        	element = (Element) filmNode.item(i);
        	if(element.getElementsByTagName("filmName").item(0).getFirstChild().getTextContent().equals(updateFilmName)){
                Node name = element.getElementsByTagName(updateAttribute).item(0).getFirstChild();
                name.setNodeValue(updateValue);
                
        		document.getDocumentElement().normalize();
        		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        		Transformer transformer = transformerFactory.newTransformer();
        		DOMSource source = new DOMSource(document);
        		StreamResult result = new StreamResult(new File("films.xml"));
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
	 * Get a List of Film.
	 * @return List of object of Film.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<Film> getFilms()
			throws ParserConfigurationException, SAXException, IOException {
		
		File file = new File("./films.xml");
		InputStream inputStream = new FileInputStream(file);

		List<Film> films = new ArrayList<Film>();

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(inputStream);
		Element filmsElements = document.getDocumentElement();

		NodeList filmNodes = filmsElements.getElementsByTagName("film");

		for (int i = 0; i < filmNodes.getLength(); i++) {
			Film film = new Film();

			Element filmElement = (Element) filmNodes.item(i);

			film.setFilmId(Integer.parseInt(filmElement.getAttribute("filmId")));

			NodeList childNodes = filmElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ((childNodes.item(j).getNodeName()).equals("filmName")) {
						film.setFilmName(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("filmDuration")) {
						film.setFilmDuration(Integer.parseInt(childNodes.item(j).getTextContent()));
					} else if ((childNodes.item(j).getNodeName()).equals("filmDirector")) {
						film.setFilmDirector(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("filmStars")) {
						film.setFilmStars(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("filmStoryline")) {
						film.setFilmStoryline(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("filmPrice")) {
						film.setFilmPrice(Float.parseFloat(childNodes.item(j).getTextContent()));
					} else if ((childNodes.item(j).getNodeName()).equals("filmType")) {
						film.setFilmType(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("filmScore")) {
						film.setFilmScore(childNodes.item(j).getTextContent());
					}
				}
			}
			films.add(film);
		}
		return films;
	}
}
