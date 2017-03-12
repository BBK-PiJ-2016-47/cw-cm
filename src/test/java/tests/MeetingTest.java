/*
package test.java.tests;
import  main.java.implementations.FutureMeetingImpl;
import  main.java.implementations.MeetingImpl;
import main.java.implementations.PastMeetingImpl;
import  main.java.spec.Contact;
import main.java.spec.FutureMeeting;
import main.java.spec.Meeting;

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
	
    @Test
    public void testContactsImmutableFromOutsideObject() {
    	Calendar pastDate = new GregorianCalendar(2012, 4, 12);
        Meeting meeting = new PastMeetingImpl(1, pastDate, contacts, "notes");
        Set<Contact> contacts = meeting.getContacts();
        contacts.add(new MockContactImpl());
        assertFalse(contacts.size() == meeting.getContacts().size());
    }

}
*/