package tests;
import implementations.FutureMeetingImpl;
import implementations.MeetingImpl;
import interfaces.Contact;
import interfaces.FutureMeeting;
import interfaces.Meeting;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class MeetingTest {
	
	Meeting test;
	Calendar date;
	Set<Contact> contacts;
	
	@Before
	public void setUp() {
		date = new GregorianCalendar(2017,9,20);
		test = new MeetingImpl(date, contacts);
		contacts = new LinkedHashSet<Contact>();
	}

	@Test
	public void testGetters() {
    	assertTrue(test.getId() != 0);
    	assertTrue(test.getId() > 1000);
    	assertTrue(test.getDate().equals(date));
    	assertTrue(test.getContacts().equals(contacts));
	}
	
	@Test
	public void testDate() {
		assertEquals(2017,test.getDate().get(Calendar.YEAR));
		assertEquals(9, test.getDate().get(Calendar.MONTH));
		assertEquals(20, test.getDate().get(Calendar.DATE));
	}

}
