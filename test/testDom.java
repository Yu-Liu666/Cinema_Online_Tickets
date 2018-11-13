package com.group99.test;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.group99.dom.BankAccountDomParser;
import com.group99.dom.ScreenSeatDomParser;
import com.group99.dom.StudentDomParser;
import com.group99.dom.TicketDomParser;
import com.group99.dom.TimeTableDomParser;
import com.group99.javabean.BankAccount;
import com.group99.javabean.Screen;
import com.group99.javabean.Student;
import com.group99.javabean.Ticket;
import com.group99.javabean.TimeTable;

public class testDom {

	@Test
	public void testTicketDom() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		Ticket ticket = new Ticket("abc", "123", "abc", "123", "abc", "a8", 19, " ");
		TicketDomParser.createTicket(ticket);
	}

	@Test
	public void testGetTicket() throws ParserConfigurationException, SAXException, IOException {
		List<Ticket> tickets = TicketDomParser.getTickets();
		for (Ticket ticket : tickets) {
			System.out.println(ticket);
		}
	}

	@Test
	public void testTimeTableDom() throws ParserConfigurationException, SAXException, IOException {
		List<TimeTable> timeTables = TimeTableDomParser.getTimeTables();

		for (TimeTable timeTable : timeTables) {
			System.out.println(timeTable);
		}
	}

	@Test
	public void testScreenSeatDomParser() throws ParserConfigurationException, SAXException, IOException {
		List<Screen> screens = ScreenSeatDomParser.getScreen("screen1At21:00.xml");

		for (Screen screen : screens) {
			System.out.println(screen);
		}
	}

	@Test
	public void testUpdateScreenSeat()
			throws ParserConfigurationException, SAXException, IOException, TransformerException {

		ScreenSeatDomParser.updateScreenSeat("screen1At21:00.xml", "A1", "false");

		List<Screen> screens = ScreenSeatDomParser.getScreen("screen1At21:00.xml");

		for (Screen screen : screens) {
			System.out.println(screen);
		}
	}

	@Test
	public void testBankAccountDomParser() throws ParserConfigurationException, SAXException, IOException {

		List<BankAccount> bankAccounts = BankAccountDomParser.getBankAccounts();

		for (BankAccount bankAccount : bankAccounts) {
			System.out.println(bankAccount);
		}
	}

	@Test
	public void testStudentDomParser() throws ParserConfigurationException, SAXException, IOException {

		List<Student> students = StudentDomParser.getStudents();

		for (Student student : students) {
			System.out.println(student);
		}
	}

}
