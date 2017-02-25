package tests;

import interfaces.FutureMeeting;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import implementations.FutureMeetingImpl;
import interfaces.Contact;
public class FutureMeetingTest {

	FutureMeeting test;
	Calendar date;
	Set<Contact> contacts;
	
	@Before
	public void setUp() {
		contacts = new LinkedHashSet<Contact>();
		date = new GregorianCalendar(2017,9,20);
		test = new FutureMeetingImpl(date, contacts);
	}

	@Test
	public void testGetters() {
    	assertTrue(test.getId() != 0);
    	assertTrue(test.getId() > 0);
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
