package tests;
import implementations.ContactImpl;
import implementations.ContactManagerImpl;
import implementations.FutureMeetingImpl;
import implementations.PastMeetingImpl;
import interfaces.Contact;
import interfaces.ContactManager;
import interfaces.FutureMeeting;
import interfaces.Meeting;
import interfaces.PastMeeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.*;
import static org.junit.Assert.*;

public class ContactManagerImplTest {
	private ContactManager cm;
	private Calendar pastDate;
	private Calendar futureDate;
	private Set<Contact> contacts;
	private List<Meeting> meetings;
	private Meeting pastMeeting;
	private Meeting futureMeeting;
	private Contact profX;
	
	@Before
	public void setUp() throws Exception {
		contacts = new HashSet<Contact>();
		meetings = new ArrayList<Meeting>();
		pastDate = new GregorianCalendar(2016,9,20);
		futureDate = new GregorianCalendar(2017,9,20);
		cm = new ContactManagerImpl();
		pastMeeting = new PastMeetingImpl(1, pastDate,contacts,"Here are some notes");
		futureMeeting = new FutureMeetingImpl(1001, futureDate, contacts);
		profX = new ContactImpl(5, "Professor X", "headmaster of Xavier's school for gifted youngsters");
		contacts.add(profX);
		
	}
	
	//method for checking meetings against each other
	private boolean meetingEquivalence(Object meeting1, Object meeting2) {
		return (meeting1.equals(meeting2));
	}
	
/**
  *
  * For addFutureMeeting() method
  *
  */
	
	@Test
	public void checkAddFutureMeetingId() {
		int idToTest = 0;
		try {
			idToTest = cm.addFutureMeeting(contacts, futureDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(idToTest > 0);
		boolean unique = true;
		/* list for meetings needed
		for(Meeting meeting : contacts) {
			int existingId = contact.getId();
			if (existingId == idToTest) {
				unique = false;
			}
		}*/
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_pastDate() throws Exception {
		cm.addFutureMeeting(contacts, pastDate);
	}
	/* What if the contact doesn't exist? How to test for this?
	 * 
	 * @Test(expected = IllegalArgumentException.class)
	 * public void testIllegalArgumentFuture_noContact() {
	 *	cm.addFutureMeeting()
	 * }
	 */

	@Test(expected = NullPointerException.class)
	public void testNullException_Contacts() throws Exception { 
		cm.addFutureMeeting(null, futureDate);
	}

	@Test(expected = NullPointerException.class)
	public void testNullException_Date() throws Exception {
		cm.addFutureMeeting(contacts, null);
	}

/**
  *
  * For getPastMeeting() method
  *
  */

	@Test(expected = IllegalStateException.class)
	public void testIllegalState_meetingInPast() {
		cm.getPastMeeting(1001);
	}

	@Test
	public void testGetPastMeeting_noMeeting() {
		PastMeeting getted = cm.getPastMeeting(77);
		assertTrue(meetingEquivalence(null, getted));
	}


	@Test
	public void testGetPastMeeting_Meeting() {
		PastMeeting getted = cm.getPastMeeting(1);
		assertTrue(meetingEquivalence(pastMeeting, getted));
	}



/**
  *
  * For getFutureMeeting() method
  *
  */


	@Test(expected = IllegalStateException.class)
	public void testIllegalState_meetingInFuture() {
		cm.getFutureMeeting(1);
	}

	@Test
	public void testGetFutureMeeting_noMeeting() {
		FutureMeeting getted = cm.getFutureMeeting(800000);
		assertTrue(meetingEquivalence(null, getted));
	}

	@Test
	public void testGetFutureMeeting_Meeting() {
		FutureMeeting getted = cm.getFutureMeeting(1001);
		assertTrue(meetingEquivalence(pastMeeting, getted));
	}



/**
  *
  * For getMeeting() method
  *
  */


	@Test
	public void testGetMeeting_noMeeting() {
		Meeting test = cm.getMeeting(8000000);
		assertTrue(meetingEquivalence(null, test));
	}

	
	@Test
	public void testGetMeeting_pastMeeting() {
		Meeting test = cm.getMeeting(1);
		assertTrue(meetingEquivalence(pastMeeting, test));
	}
	
	@Test
	public void testGetMeeting_futureMeeting() {
		Meeting test = cm.getMeeting(1001);
		assertTrue(meetingEquivalence(futureMeeting, test));
	}



/**
  *
  * For getFutureMeetingList() method
  *
  */

//just testing it returns a list
/*
 *	@Test
 *	public void testGetFutureMeetingList() {
 *		cm.getFutureMeetingList(Wolverine);
 *	//how do you check type in JUnit?
 *	}
 *
 */
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_noFutureContact() {
		Contact noLinkedContact = new ContactImpl(1, "Test", "notes");
		cm.getFutureMeetingList(noLinkedContact);
	}

	
	@Test(expected = NullPointerException.class)
	public void testNullPointer_NullContact() {
		cm.getFutureMeetingList(null);
	}

	//needs to return a list of chronologically sorted meetings
	//no duplicate meetings
	//can be empty
	@Test
	public void testGetFutureMeetingList_Empty() {
		Contact cyclops = new ContactImpl(2, "Cyclops", "don't let him take off his glasses");
		contacts.add(cyclops);
		cm.getFutureMeetingList(cyclops);
	}

	
	@Test
	public void testGetFutureMeetingList_Chronology() {
		List filteredList = cm.getFutureMeetingList(profX);
		int size = filteredList.size();
		//in chronological order
	}

	@Test
	public void testGetFutureMeetingList_Duplicates() {
		List filteredList = cm.getFutureMeetingList(profX);
		int size = filteredList.size();
		boolean duplicate = false;
		for(int i = 0; i< size; i++){
			if (meetingEquivalence(filteredList.get(i), filteredList.get(i+1))){
				duplicate = true;
			}
		}
		
		assertFalse(duplicate);
	}



  /**
  *
  * For getMeetingListOn() method
  *
  

//just testing it returns a list
	
	@Test
	public void testGetMeetingListOn() {     
		cm.getMeetingListOn(//date);
	}

	@Test
	public void testGetMeetingListOn_Empty() {
		//must return empty List/null
		cm.getMeetingListOn(//date);
	}

	@Test
	public void testGetMeetingListOn_Chronology() {
		cm.getMeetingListOn(//date);
		//need to traverse through list checking dates are chronological
	}

	@Test
	public void testGetMeetingListOn_Duplicates() {
		cm.getMeetingListOn(//date);
		//need to traverse through list checking there are no duplicates
	}

	
	@Test(expected = NullPointerException.class)
	public void testNullPointer() {     
		//create null contact?
		cm.getMeetingListOn(null);
	}

  /**
  *
  * For getPastMeetingListFor() method
  *
  

//just testing it returns a list
	
	@Test
	public void testGetPastMeetingListFor() {
		cm.getPastMeetingListFor(//contact);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_noContact() {
		//make sure contact doesn't exist
		cm.getPastMeetingListFor(Ben);
	}

	@Test(expected = NullPointerException.class)
	public void testNullPointer() {     
    	//create null contact?
    	cm.getPastMeetingListFor(null);
	}

	//needs to return a list of chronologically sorted meetings
	//no duplicate meetings
	//can be empty
	@Test
	public void testGetPastMeetingListFor_Empty() {
		//must return empty List/null
		cm.getPastMeetingListFor(//contact);
	}

	@Test
	public void testGetPastMeetingListFor_Chronology() {
    	cm.getPastMeetingListFor(//existingcontact)
    	//need to traverse through list checking dates are chronological
	}

	@Test
	public void testGetPastMeetingListFor_Duplicates() {
		cm.getPastMeetingListFor(//existingcontact);
		//need to traverse through list checking there are no duplicates
	}


  /**
  *
  * For addMeetingNotes() method
  *
  

	// testing it returns a past list for current meeting
	@Test
	public void testAddMeetingNotes() {     
		PastMeeting result = cm.addMeetingNotes(1001, "That was nice wasn't it");
	}

	//checking edits past meeting
	@Test
	public void testAddMeetingNotes_Past() {     
		cm.addMeetingNotes(1, "That was nice wasn't it");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_noMeeting() {     
		cm.addMeetingNotes(77, "That was nice wasn't it");;
	}

	@Test(expected = IllegalStateException.class)
	public void testIllegalState_futureMeeting() {     
		cm.addMeetingNotes(1001, "That was nice wasn't it");;
	}

	@Test(expected = NullPointerException.class)
	public void testNullMeetingNotes(){
		cm.addMeetingNotes(1, null);
	}


  /**
  *
  * For addNewContact() method
  *
  */

	@Test
	public void testAddNewContact() {
		cm.addNewContact("Bob", "makes a great burger");
	}

	@Test
	public void testIDNumber() {
		int idToTest = cm.addNewContact("Bob", "makes a great burger");
		assertTrue(idToTest > 0);
		boolean unique = true;
		for(Contact contact : contacts) {
			int existingId = contact.getId();
			if (existingId == idToTest) {
				unique = false;
			}
		}
		assertTrue(unique);
	}

	@Test(expected = NullPointerException.class)
	public void testNullPointer_ContactNotes() {     
		cm.addNewContact("Linda", null);
	}

	@Test(expected = NullPointerException.class)
	public void testNullPointer_ContactName() {     
		cm.addNewContact(null, "likes wine");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_ContactName() {     
		cm.addNewContact("", "likes wine");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_ContactNotes() {     
		cm.addNewContact("Linda", "");
	}


 /**
  *
  * For getContacts(String) method
  *
  
  
	@Test
	public void testGetContacts_knownContact() {
		cm.getContacts("//name");
		//Expected = contacts with name in
	}
  
	@Test
	public void testGetContacts_noContacts() {
		cm.getContacts("hfikb");
		//Expected error
	}
  
	@Test
	public void testEmptyString() {
		cm.getContacts("");
		//Expected = set of all current contacts
	}
  
	@Test(expected = NullPointerException.class)
	public void testNullPointer_name() {     
		//name null, notes not
		cm.getContacts(null);
	}
*/
}