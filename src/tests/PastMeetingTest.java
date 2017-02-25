package tests;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.*; 
import static org.junit.Assert.*;
import org.junit.Before;

import implementations.PastMeetingImpl;

public class PastMeetingTest {
	
	PastMeetingImpl test;
	Set<Contact> contacts;
	Calendar date;
	
	
	@Before
	public void setUp() {
		date = new GregorianCalendar(2016,9,20);
		test = new PastMeetingImpl(date,contacts,"Here are some notes");
	}
	
	@Test
	public void checkConstructorAndGetters() {
    	assertTrue(test.getId() != 0);
    	assertTrue(test.getId() > 0);
    	assertTrue(test.getDate().equals(date));
    	assertTrue(test.getContacts().equals(contacts));
	}
	
	
	@Test
	public void checkGetNotes_WithNotes() {
		String output = test.getNotes();
		assertTrue(output.equals("Here are some notes"));
	}
	
	@Test
	public void checkGetNotes_NoNotes() {
		PastMeetingImpl emptyNoteTest = new PastMeetingImpl(date, contacts, "");
		String output = test.getNotes();
		assertTrue(output.equals(""));
	}


}
