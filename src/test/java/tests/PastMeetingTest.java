package test.java.tests;
import main.java.implementations.ContactImpl;
import main.java.implementations.PastMeetingImpl;
import main.java.spec.Contact;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.*; 
import static org.junit.Assert.*;
import org.junit.Before;

public class PastMeetingTest {
	
	PastMeetingImpl test;
	Set<Contact> contacts;
	Calendar date;
	int count;
	Contact profX;
	
	
	@Before
	public void setUp() throws Exception {
		profX = new ContactImpl(5, "Professor X", "Headmaster at Xavier's School for Gifted Youngsters");
		date = new GregorianCalendar(2016,9,20);
		contacts = new LinkedHashSet<Contact>();
		contacts.add(profX);
		test = new PastMeetingImpl(5,date,contacts,"Here are some notes");
	}
	
	@Test
	public void checkConstructorAndGetters() {
    	assertTrue(test.getId() != 0);
    	assertTrue(test.getId() > 0);
    	assertTrue(test.getDate().equals(date));
    	assertTrue(test.getContacts().equals(contacts));
	}
	
	public void testDate() {
		assertEquals(2016,test.getDate().get(Calendar.YEAR));
		assertEquals(9, test.getDate().get(Calendar.MONTH));
		assertEquals(20, test.getDate().get(Calendar.DATE));
	}
	
	@Test
	public void checkGetNotes_WithNotes() {
		String output = test.getNotes();
		assertTrue(output.equals("Here are some notes"));
	}
	
	@Test
	public void checkGetNotes_NoNotes() throws Exception {
		PastMeetingImpl emptyNoteTest = new PastMeetingImpl(5,date, contacts, "");
		String tester = emptyNoteTest.getNotes();
		assertTrue(tester.equals(""));
	}


}
