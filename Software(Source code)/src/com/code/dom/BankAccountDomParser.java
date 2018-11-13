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

import com.group99.javabean.BankAccount;
/**
 * DOM parser of bank_accounts.xml
 * @author group 99
 *
 */
public class BankAccountDomParser {
	
	/**
	 * To update bank account by providing account number.
	 * @param updateAccNo Which account number you want to update.
	 * @param updateAttribute The attribute you want to update.
	 * @param updateValue The value you want to update.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException
	 */
	public static void updateAccounts(String updateAccNo, String updateAttribute, String updateValue)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {

		File file = new File("bank_accounts.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		document.getDocumentElement().normalize();
		
        NodeList accountNode = document.getElementsByTagName("account");
        Element element = null;

        for(int i=0; i<accountNode.getLength();i++){    	
        	element = (Element) accountNode.item(i);
        	if(element.getElementsByTagName("accountNum").item(0).getFirstChild().getTextContent().equals(updateAccNo)){
                Node name = element.getElementsByTagName(updateAttribute).item(0).getFirstChild();
                name.setNodeValue(updateValue);
                
        		document.getDocumentElement().normalize();
        		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        		Transformer transformer = transformerFactory.newTransformer();
        		DOMSource source = new DOMSource(document);
        		StreamResult result = new StreamResult(new File("bank_accounts.xml"));
        		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        		transformer.transform(source, result);
                break;
        	}else{
        		if(i == accountNode.getLength() - 1)
        			System.out.println("The account don't exist!");
        	}
        }
	}
	/**
	 *  Get a List of BankAccount.
	 * @return List of object of BankAccount.
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static List<BankAccount> getBankAccounts()
			throws ParserConfigurationException, SAXException, IOException {
		
		File file = new File("./bank_accounts.xml");
		InputStream inputStream = new FileInputStream(file);

		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(inputStream);
		Element accountElements = document.getDocumentElement();

		NodeList accountNodes = accountElements.getElementsByTagName("account");

		for (int i = 0; i < accountNodes.getLength(); i++) {
			BankAccount BankAccount = new BankAccount();

			Element accountElement = (Element) accountNodes.item(i);

			NodeList childNodes = accountElement.getChildNodes();

			for (int j = 0; j < childNodes.getLength(); j++) {
				if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					if ((childNodes.item(j).getNodeName()).equals("name")) {
						BankAccount.setName(childNodes.item(j).getTextContent());
					} else if ((childNodes.item(j).getNodeName()).equals("accountNum")) {
						BankAccount.setAccountNum(childNodes.item(j).getTextContent());
					}else if ((childNodes.item(j).getNodeName()).equals("accountPassword")) {
						BankAccount.setAccountPassword(childNodes.item(j).getTextContent());
					}else if ((childNodes.item(j).getNodeName()).equals("balance")) {
						BankAccount.setBalance(Float.parseFloat(childNodes.item(j).getTextContent()));
					}
				}
			}
			bankAccounts.add(BankAccount);
		}
		return bankAccounts;
	}
}
