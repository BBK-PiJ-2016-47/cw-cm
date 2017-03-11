package test.java.tests;
import main.java.implementations.*;
import main.java.spec.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.*;
import static org.junit.Assert.*;

public class ContactManagerImplTest {
	private ContactManager cm;
	private Calendar emptyDate;
	private Calendar pastDate;
	private Calendar futureDate;
	private Set<Contact> contacts;
	private List<Meeting> filteredList;
	private Contact profX;
	
	@Before
	public void setUp() throws Exception {
		cm = new ContactManagerImpl();
		
		contacts = new HashSet<Contact>();
		//filteredList = new ArrayList<Meeting>();
		
		emptyDate = new GregorianCalendar(2014,11,25);
		pastDate = new GregorianCalendar(2016,9,20);
		futureDate = new GregorianCalendar(2019,9,20);

		Contact profX = new ContactImpl(1, "Professor X", "Head of school");
		contacts.add(profX);
	}
	
	//method for checking meetings against each other
	private boolean meetingEquivalence(Object meeting1, Object meeting2) {
		return (meeting1.equals(meeting2));
	}
	
/**
  *
  * For addFutureMeeting() method
  */
  
	
	@Test
	public void checkAddFutureMeetingId() {
		
		int idToTest = 0;
			try {
				idToTest = cm.addFutureMeeting(contacts, futureDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		assertTrue(idToTest > 0);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_pastDate() throws Exception {
		cm.addFutureMeeting(contacts, pastDate);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentFuture_noContact() throws Exception {
		Set<Contact> emptyContacts = new HashSet<Contact>();
		cm.addFutureMeeting(emptyContacts, futureDate);
	}
	 

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
  */
  
	@Test(expected = IllegalStateException.class)
	public void testIllegalState_meetingInFuture() {
		int id = cm.addFutureMeeting(contacts, futureDate);
		cm.getPastMeeting(id);
	}

	
	@Test
	public void testGetPastMeeting_noMeeting() {
		PastMeeting getted = cm.getPastMeeting(98);
		assertTrue(getted == null);
	}


	@Test
	public void testGetPastMeeting_Meeting() {
		cm.addNewContact("Prof X", "notes");
		int id = cm.addNewPastMeeting(contacts, pastDate, "Meeting");
		Meeting test = cm.getPastMeeting(id);
		assertTrue(test.getId() == id);
	}



/**
  *
  * For getFutureMeeting() method
  *
  */
  


	@Test(expected = IllegalStateException.class)
	public void testGetFutureIllegalState_meetingInFuture() {
		int id = cm.addNewPastMeeting(contacts, pastDate, "notes");
		cm.getFutureMeeting(id);
	}

	@Test
	public void testGetFutureMeeting_noMeeting() {
		FutureMeeting getted = cm.getFutureMeeting(800000);
		assertTrue(getted == null);
	}

	@Test
	public void testGetFutureMeeting_Meeting() {
		cm.addNewContact("Prof X", "notes");
		int id = cm.addFutureMeeting(contacts, futureDate);
		Meeting test = cm.getFutureMeeting(id);
		assertTrue(test.getId() == id);
	}



/**
  *
  * For getMeeting() method
  *
  */
  


	@Test
	public void testGetMeeting_noMeeting() {
		Meeting test = cm.getMeeting(8000000);
		assertTrue(test == null);
	}

	
	@Test
	public void testGetMeeting_pastMeeting() {
		int id = cm.addNewPastMeeting(contacts, pastDate, "??");
		Meeting test = cm.getMeeting(id);
		assertTrue(test.getId() == id);
	}
	
	@Test
	public void testGetMeeting_futureMeeting() {
		cm.addNewContact("Prof X", "notes");
		cm.addFutureMeeting(contacts, futureDate);
		Meeting test = cm.getMeeting(1);
		assertTrue(test.getId() == 1);
	}



/**
  *
  * For getFutureMeetingList() method
  *
  */


 	@Test
 	public void testGetFutureMeetingList() {
 		boolean aList;
 		cm.addNewContact("Shadowcat", "don't worry about opening the door");
        Set<Contact> shadowSet = cm.getContacts("Shadowcat");
        cm.addFutureMeeting(shadowSet, futureDate);
 		Iterator<Contact> it = shadowSet.iterator();
        Contact shadowCat = it.next();
 		try {
 		
 		List<Meeting> testFutureList = cm.getFutureMeetingList(shadowCat);
 		aList = (testFutureList != null);
 		} catch (Exception e){
 			System.out.println("EXCEPTION HERE");
 			aList = false;
 		}
 		assertTrue(aList);
 	}
 
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_noFutureContact() throws Exception {
		Contact noLinkedContact = new ContactImpl(1, "Test", "notes");
		cm.getFutureMeetingList(noLinkedContact);
	}

	
	@Test(expected = NullPointerException.class)
	public void testNullPointer_NullContact() {
		cm.getFutureMeetingList(null);
	}

	@Test
	public void testGetFutureMeetingList_Empty() {		
		cm.addNewContact("Cyclops", "don't let him take off his glasses");
        Set<Contact> cyclopsSet = cm.getContacts("Cyclops");
 		Iterator<Contact> it = cyclopsSet.iterator();
        Contact cyclops = it.next();


		List<Meeting> cyclopsMeetings = cm.getFutureMeetingList(cyclops);
		assertTrue(cyclopsMeetings.isEmpty());
	}

	
	@Test
	public void testGetFutureMeetingList_Chronology() {
		cm.addNewContact("Professor X", "Head of school");
	    Set<Contact> profXSet = cm.getContacts("Professor X");
		Iterator<Contact> it = profXSet.iterator();
	    Contact profX = it.next();
	    cm.addFutureMeeting(profXSet, futureDate);
	    
		filteredList = cm.getFutureMeetingList(profX);
		int size = filteredList.size();
		boolean chronological = true;
		for(int i = 0; i < size; i++){
			Meeting first = filteredList.get(i);
			Meeting second = null;
			if ((i+1) != size){
				second = filteredList.get(i + 1);
				if(first.getDate().after(second.getDate())){
					chronological = false;
				}
			}
		}
		assertTrue(chronological);
	}
	
	@Test
	public void testGetFutureMeetingList_Duplicates() {
		filteredList = cm.getFutureMeetingList(profX);
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
  */
  

//just testing it returns a list
	
	@Test
	public void testGetMeetingListOn() {     
		 		boolean aList;
 		try {
 		List<Meeting> testList = cm.getMeetingListOn(pastDate);
 		aList = (testList != null);
 		} catch (Exception e){
 			aList = false;
 		}
 		assertTrue(aList);
 	}

	@Test
	public void testGetMeetingListOn_Empty() {
		List<Meeting> emptyList = cm.getMeetingListOn(emptyDate);
		assertTrue(emptyList.isEmpty());
	}

	@Test
	public void testGetMeetingListOn_Chronology() {
		filteredList = cm.getMeetingListOn(pastDate);
		int size = filteredList.size();
		boolean chronological = true;
		for(int i = 0; i < size; i++){
			Meeting first = filteredList.get(i);
			Meeting second = null;
			if ((i+1) != size){
				second = filteredList.get(i + 1);
				if(first.getDate().after(second.getDate())){
					chronological = false;
				}
			}
		}
		assertTrue(chronological);
	}

	@Test
	public void testGetMeetingListOn_Duplicates() {
		filteredList = cm.getMeetingListOn(pastDate);
		int size = filteredList.size();
		boolean duplicate = false;
		for(int i = 0; i< size; i++){
			if (meetingEquivalence(filteredList.get(i), filteredList.get(i+1))){
				duplicate = true;
			}
		}
		
		assertFalse(duplicate);
	}

	
	@Test(expected = NullPointerException.class)
	public void testNullPointer() {
		Calendar nullDate = null;
		cm.getMeetingListOn(nullDate);
	}

  /**
  *
  * For getPastMeetingListFor() method
  *
  */
  

 	@Test
 	public void testGetPastMeetingListFor() {
 		boolean aList;
 		cm.addNewContact("Shadowcat", "don't worry about opening the door");
        Set<Contact> shadowSet = cm.getContacts("Shadowcat");
        cm.addNewPastMeeting(shadowSet, pastDate, "notes");
 		Iterator<Contact> it = shadowSet.iterator();
        Contact shadowCat = it.next();
 		try {
 		
 		List<PastMeeting> testPastList = cm.getPastMeetingListFor(shadowCat);
 		aList = (testPastList != null);
 		} catch (Exception e){
 			System.out.println("EXCEPTION HERE");
 			aList = false;
 		}
 		assertTrue(aList);
 	}
 
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_noPastContact() {
		Contact noLinkedContact = new ContactImpl(1, "Test", "notes");
		cm.getPastMeetingListFor(noLinkedContact);
	}

	
	@Test(expected = NullPointerException.class)
	public void testNullPointer_NullPastContact() {
		cm.getPastMeetingListFor(null);
	}

	@Test
	public void testGetPastMeetingListFor_Empty() {
		cm.addNewContact("Cyclops", "don't let him take off his glasses");
        Set<Contact> cyclopsSet = cm.getContacts("Cyclops");
 		Iterator<Contact> it = cyclopsSet.iterator();
        Contact cyclops = it.next();

		List<PastMeeting> cyclopsPastMeetings = cm.getPastMeetingListFor(cyclops);
		assertTrue(cyclopsPastMeetings.isEmpty());
	}

	
	@Test
	public void testGetPastMeetingList_Chronology() {
		cm.addNewContact("Professor X", "Head of school");
        Set<Contact> profXSet = cm.getContacts("Professor X");
 		Iterator<Contact> it = profXSet.iterator();
        Contact profX = it.next();
        cm.addNewPastMeeting(profXSet, pastDate, "got to see Cerebro");
        
		List<PastMeeting> pastFilteredList = cm.getPastMeetingListFor(profX);
		int size = pastFilteredList.size();
		boolean chronological = true;
		for(int i = 0; i < size; i++){
			Meeting first = pastFilteredList.get(i);
			Meeting second = null;
			if ((i+1) != size){
				second = pastFilteredList.get(i + 1);
				if(first.getDate().after(second.getDate())){
					chronological = false;
				}
			}
		}
		assertTrue(chronological);
	}


	@Test
	public void testGetPastMeetingList_Duplicates() {
		List<PastMeeting> pastFilteredList = cm.getPastMeetingListFor(profX);
		int size = pastFilteredList.size();
		boolean duplicate = false;
		for(int i = 0; i< size; i++){
			if (meetingEquivalence(pastFilteredList.get(i), pastFilteredList.get(i+1))){
				duplicate = true;
			}
		}
		
		assertFalse(duplicate);
	}
	
	//addNewPastMeeting()
	
	@Test
	public void checkAddPastMeetingId() {
		int idToTest = 0;
		try {
			idToTest = cm.addNewPastMeeting(contacts, pastDate, "forgot about this one");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(idToTest > 0);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_futureDate() throws Exception {
		cm.addNewPastMeeting(contacts, futureDate, "notes");
	}


	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgumentPast_noContact() throws Exception {
		Set<Contact> emptyContacts = new HashSet<Contact>();
		cm.addNewPastMeeting(emptyContacts, pastDate, "notes");
	}
	 

	@Test(expected = NullPointerException.class)
	public void testNullException_pastContacts() throws Exception { 
		cm.addNewPastMeeting(null, pastDate, "notes");
	}

	@Test(expected = NullPointerException.class)
	public void testNullException_pastDate() throws Exception {
		cm.addNewPastMeeting(contacts, null, "notes");
	}



  /**
  *
  * For addMeetingNotes() method
  */
  
  
	@Test
	public void testAddMeetingNotes_Past() { 
		int id = cm.addNewPastMeeting(contacts, pastDate, "some notes");
		PastMeeting result = cm.addMeetingNotes(id, "That was nice wasn't it");
		assertTrue(result.getDate().before(Calendar.getInstance()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument_noMeeting() {     
		cm.addMeetingNotes(77, "That was nice wasn't it");;
	}

	@Test(expected = IllegalStateException.class)
	public void testIllegalState_futureMeeting() throws Exception {  
		int id = cm.addFutureMeeting(contacts, futureDate);
		cm.addMeetingNotes(id, "That was nice wasn't it");;
	}

	@Test(expected = NullPointerException.class)
	public void testNullMeetingNotes(){
		cm.addMeetingNotes(1, null);
	}


  /**
  *
  * For addNewContact() method
  */
  

	@Test
	public void testAddNewContact() {
		cm.addNewContact("Bob", "makes a great burger");
	}

	@Test
	public void testIDNumber() {
		Set<Contact> preAddContacts = contacts;
		int idToTest = cm.addNewContact("Bob", "makes a great burger");
		assertTrue(idToTest > 0);
		System.out.print(idToTest);
		boolean unique = true;
		for(Contact contact : preAddContacts) {
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
  */
  
	
	@Test
	public void testGetStringContacts_knownContact() {
		cm.addNewContact("Storm", "notes");
		Set<Contact> success = cm.getContacts("Storm");
		assertTrue(!success.isEmpty());
	}
  
	@Test
	public void testGetContacts_noContacts() {
		Set<Contact> success = cm.getContacts("wrkgw");
		assertTrue(success.isEmpty());
	}
  
	@Test
	public void testEmptyString() {
		ContactManager cMa = new ContactManagerImpl();
		cMa.addNewContact("Iceman", "Bring a coat");
		Set<Contact> newSet = cMa.getContacts("");
		int originalSize = cMa.getContacts("").size();
		int compareSize = newSet.size();
		assertEquals(originalSize, compareSize);
	}
 
	
	 /**
	  *
	  * For getContacts(int... ids) method
	  */
	  
	  
		@Test
		public void testGetContacts_knownContact() {
			Set<Contact> got = cm.getContacts(1);
			assertTrue(got.size() == 1);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void testIllegalArgument_unknownContact() {     
			cm.getContacts(77);
		}

		@Test(expected = IllegalArgumentException.class)
		public void testIllegalArgument_nullContact() {     
			cm.getContacts();
		}
		
		/*
		 * For flush() method
		 * 
		 */
		 
		@Test
		public void testFlush(){
			int id = cm.addNewPastMeeting(contacts, pastDate, "notes");
			cm.flush();
			ContactManager cmNew = new ContactManagerImpl();
			int loadedId = cmNew.getPastMeeting(id).getId();
			assertEquals(id, loadedId);
			
		}
}