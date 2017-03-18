package test.java.tests;

import main.java.implementations.ContactImpl;
import main.java.implementations.MockObjectMeetingImpl;
import main.java.spec.Contact;
import main.java.spec.Meeting;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class MeetingTest {

  private Meeting test;
  private Calendar date;
  private final Set<Contact> meetingContacts = new LinkedHashSet<Contact>();

  @Before
  public void setUp() {
    date = new GregorianCalendar(2017,9,20);
    Contact mystique = new ContactImpl(84, "Mystique", "request that she does not shapeshift");
    meetingContacts.add(mystique);
    try {
      test = new MockObjectMeetingImpl(15,date, meetingContacts);
    } catch (Exception ex) {
      System.out.println("Exception problems");
      ex.printStackTrace();
    }
  }

  @Test
  public void testGetters() {
    assertTrue(test.getId() > 0);
    assertTrue(test.getDate().equals(date));
    assertTrue(test.getContacts().equals(meetingContacts));
  }

  @Test
  public void testDate() {
    assertEquals(2017,test.getDate().get(Calendar.YEAR));
    assertEquals(9, test.getDate().get(Calendar.MONTH));
    assertEquals(20, test.getDate().get(Calendar.DATE));
  }

  @Test
  public void testContactsImmutableFromOutsideObject() {
    Set<Contact> changeContacts = new HashSet<Contact>();
    changeContacts = test.getContacts();
    changeContacts.add(new ContactImpl(3, "Jean Grey", "Has a bit of a temper sometimes"));
    assertFalse(changeContacts.size() == test.getContacts().size());
  }
}
