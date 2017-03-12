package test.java.tests;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Set;

import main.java.implementations.ContactImpl;
import main.java.implementations.FutureMeetingImpl;
import main.java.spec.Contact;
import main.java.spec.FutureMeeting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class FutureMeetingTest {
  FutureMeeting test;
  Calendar date;
  Set<Contact> contacts;
  Contact jeanGrey;

  @Before
  public void setUp() throws Exception {
    contacts = new LinkedHashSet<Contact>();
    date = new GregorianCalendar(2017,9,20);
    jeanGrey = new ContactImpl(3, "Jean Grey", "Has a bit of a temper sometimes");
    contacts.add(jeanGrey);
    test = new FutureMeetingImpl(6, date, contacts);
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