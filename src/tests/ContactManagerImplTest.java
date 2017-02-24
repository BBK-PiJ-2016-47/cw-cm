package tests;
import implementations.ContactManagerImpl;
import org.junit.*;
import interfaces.ContactManager;
import static org.junit.Assert.*;

public class ContactManagerImplTest {
	private ContactManagerImpl cm;
	
	@Before
	public void setUp() {
		  cm = new ContactManagerImpl();
	}
	
/**
  *
  * For addFutureMeeting() method
  *
  */

	@Test
	public void checkAddFutureMeetingId() {
		int output = cm.addFutureMeeting(parameters);
		assertTrue(output > 0);
	}

//check ID is unique

// need to test for illegalargumentexception is time is in past or contact non-existent

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_pastDate() {
		cm.addFutureMeeting(Bob, 12/12/12);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_noContact() {     
		cm.addFutureMeeting(Blank, 12/12/17);
	}

//test for nullpointerexception if meeting or date is null

	@Test(expected = NullPointerException.class)
	public void testNullException() {
		//what does it mean if meeting is null?
		//add new contact Bob so only testing date
		cm.addFutureMeeting(Bob, null);
	}

/**
  *
  * For getPastMeeting() method
  *
  */


	@Test(expected = IllegalStateException.class)
	public void testIllegalState_meetingInPast() {
		//create a Future meeting shell with ID
		cm.getPastMeeting(id);
	}

	@Test
	public void testGetPastMeeting_noMeeting() {
		//expected null
		cm.getPastMeeting(id);
		assertTrue(null)
	}

	@Test
	public void testGetPastMeeting_Meeting() {
		//create a past meeting shell with ID
		cm.getPastMeeting(//id);
	}

/**
  *
  * For getFutureMeeting() method
  *
  */

	@Test(expected = IllegalStateException.class)
	public void testIllegalState_meetingInFuture() {
		//create a Past meeting shell with ID
		cm.getFutureMeeting(//id);
	}

	@Test
	public void testGetFutureMeeting_noMeeting() {
		//expected null
		cm.getFutureMeeting(id);
	}

	@Test
	public void testGetFutureMeeting_Meeting() {
		//create a futuremeeting shell with ID
		cm.getFutureMeeting(//id);
	}



/**
  *
  * For getMeeting() method
  *
  */


	@Test
	public void testGetMeeting_noMeeting() {
		//expected null
		cm.getMeeting(//id);
	}

	
	@Test
	public void testGetMeeting_Meeting() {
		//create a meeting shell with ID
		cm.getMeeting(//id);
	}



/**
  *
  * For getFutureMeetingList() method
  *
  */

//just testing it returns a list
	@Test
	public void testGetFutureMeetingList() {
		cm.getFutureMeetingList(//contact);
	//how do you check type in JUnit?
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_noContact() {
		//make sure contact doesn't exist
		cm.getFutureMeetingList(Ben);
	}

	
	@Test(expected = NullPointerException.class)
	public void testNullPointer() {
		//create null contact?
		cm.getFutureMeetingList(null);
	}

	//needs to return a list of chronologically sorted meetings
	//no duplicate meetings
	//can be empty
	@Test
	public void testGetFutureMeetingList_Empty() {
		//must return empty List/null
		cm.getFutureMeetingList(//contact);
	}

	
	@Test
	public void testGetFutureMeetingList_Chronology() {
		cm.getFutureMeetingList(//existingcontact);
		//need to traverse through list checking dates are chronological
	}

	@Test
	public void testGetFutureMeetingList_Duplicates() {
		cm.getFutureMeetingList(//existingcontact);
		//need to traverse through list checking there are no duplicates
	}



  /**
  *
  * For getMeetingListOn() method
  *
  */

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
  */

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
  */

	// testing it returns a past list for current meeting

	@Test
	public void testAddMeetingNotes() {     
		cm.addMeetingNotes(//id, text);
	}

	//checking edits past meeting
	@Test
	public void testAddMeetingNotes_Past() {     
		cm.addMeetingNotes(//id, text);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_noMeeting() {     
		//make sure ID doesn't exist
		cm.addMeetingNotes(//id, notes);
	}

	@Test(expected = IllegalStateException.class)
	public void testIllegalState_futureMeeting() {     
		//make sure ID is for future meeting
		cm.addMeetingNotes(//id, notes);
	}

	@Test(expected = NullPointerException.class)
	public void testIllegalState_futureMeeting() {     
		//make sure ID is for relevant meeting, notes
		//are null
		cm.addMeetingNotes(//id, notes);
	}


  /**
  *
  * For addNewContact() method
  *
  */

	@Test
	public void testAddNewContact() {
		cm.addNewContact(//name, notes);
		//returns ID number
	}

	@Test
	public void testIDNumber() {
		cm.addNewContact(//name, notes);
		//check ID generated isn't a duplicate
	}

	@Test(expected = NullPointerException.class)
	public void testNullPointer_notes() {     
		//make sure name is for relevant contact, notes
		//are null
		cm.addMeetingNotes(//name, notes);
	}

	@Test(expected = NullPointerException.class)
	public void testNullPointer_name() {     
		//name null, notes not
		cm.addMeetingNotes(//name, notes);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_name() {     
		//if name is empty
		cm.addMeetingNotes(//name, notes);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_notes() {     
		//if notes empty
		cm.addMeetingNotes(//name, notes);
	}


 /**
  *
  * For getContacts(String) method
  *
  */
  
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

}