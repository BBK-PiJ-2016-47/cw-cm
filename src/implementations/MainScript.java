package implementations;
import interfaces.Contact;
import interfaces.ContactManager;
import interfaces.Meeting;
import interfaces.PastMeeting;

import java.io.File;
import java.util.*;

public class MainScript {
	public static void main(String[] args) throws Exception{

		ContactManager cm = new ContactManagerImpl();
		Calendar emptyDate = new GregorianCalendar(2014,12,25);
		Calendar pastDate = new GregorianCalendar(2016,9,20);
		Calendar futureDate = new GregorianCalendar(2017,9,20);
		Set<Contact> contacts = ContactManagerImpl.contacts;
		List<Meeting> meetings = ContactManagerImpl.meetings;
		List<Meeting> filteredList = new ArrayList<Meeting>();
		
		/*
		//Meeting pastMeeting = new PastMeetingImpl(1, pastDate,contacts,"Here are some notes");
		//Meeting futureMeeting = new FutureMeetingImpl(1001, futureDate, contacts);
	

		System.out.println(cm.addNewContact("Storm", "don't do weather small talk"));

		System.out.println(cm.addNewContact("Professor X", "headmaster of Xavier's school for gifted youngsters"));
		System.out.println(cm.addNewContact("Rogue", "make sure she's wearing gloves"));
		
		Set<Contact> test = cm.getContacts("");
		
		Contact[] testArr = test.toArray(new Contact[test.size()]);
		for(int i = 0; i < testArr.length; i++){
			System.out.print(testArr[i].getId() + ": ");
			System.out.println(testArr[i].getName());
		}
		
		Set<Contact> idTest = cm.getContacts(1,2);
		
		Contact[] idTestArr = idTest.toArray(new Contact[idTest.size()]);
		for(int i = 0; i < idTestArr.length; i++){
			System.out.println(idTestArr[i].getName());
		}
		
		System.out.println(cm.addFutureMeeting(contacts, futureDate));
		System.out.println(cm.addFutureMeeting(contacts, futureDate));
		System.out.println(cm.addFutureMeeting(contacts, new GregorianCalendar(2017, 4, 19)));
		System.out.println(cm.getMeeting(1).getId());
		
		System.out.println(cm.addNewPastMeeting(contacts, pastDate, "whatever"));
		
		System.out.println(cm.getFutureMeeting(2).getId());
		System.out.println(cm.getPastMeeting(4).getId());
		
		List<Meeting> toPrint = (cm.getMeetingListOn(futureDate));
		for(int i = 0; i < toPrint.size(); i++){
			System.out.println("Meeting IDs: " + toPrint.get(i).getId());
		}
		
		cm.addMeetingNotes(4, "some extra notes");
		System.out.println(cm.getPastMeeting(4).getNotes());
		
		
		Set<Contact> stormSet = cm.getContacts("Storm");
		Contact[] contactArr = stormSet.toArray(new Contact[stormSet.size()]);
		Contact storm = contactArr[0];
		Contact barry = null;
		
 		cm.addNewContact("Shadowcat", "don't worry about opening the door");
        Set<Contact> shadowSet = cm.getContacts("Shadowcat");
        cm.addFutureMeeting(shadowSet, futureDate);
        cm.addFutureMeeting(shadowSet, futureDate);
 		Iterator<Contact> it = shadowSet.iterator();
        Contact shadowCat = it.next();
        
		
		List<Meeting> contactToPrint = (cm.getFutureMeetingList(shadowCat));
		for(int i = 0; i < contactToPrint.size(); i++){
			System.out.println("Meeting IDs: " + contactToPrint.get(i).getId());
		}
			
		cm.addNewContact("Nightcrawler", "don't mention his hands");
        Set<Contact> nightCrawlerSet = cm.getContacts("Nightcrawler");
        Iterator<Contact> iterate = nightCrawlerSet.iterator();
        Contact nightcrawler = iterate.next();
        
		List<Meeting> contact2Print = (cm.getFutureMeetingList(nightcrawler));
		System.out.println("HELLo");
		for(int i = 0; i < contact2Print.size(); i++){
			System.out.println("Non-existent Meeting IDs: " + contact2Print.get(i).getId());
		}
        
        cm.addNewPastMeeting(nightCrawlerSet, pastDate, "didn't mention his hands");
        cm.addNewPastMeeting(nightCrawlerSet, pastDate, "didn't mention his hands");
 		
        
		
		List<PastMeeting> pastContactToPrint = (cm.getPastMeetingListFor(nightcrawler));
		for(int i = 0; i < pastContactToPrint.size(); i++){
			System.out.println("Meeting IDs: " + pastContactToPrint.get(i).getId());
		}
		
		cm.flush();
		
*/
		System.out.println(cm.addNewContact("Storm", "don't do weather small talk"));
		System.out.println(cm.addNewContact("Professor X", "headmaster of Xavier's school for gifted youngsters"));
		System.out.println(cm.addNewContact("Rogue", "make sure she's wearing gloves"));
		System.out.println(cm.addFutureMeeting(contacts, futureDate));
		
		System.out.println(cm.getFutureMeeting(1).getId());
		cm.flush();
		
		ContactManager newCm = new ContactManagerImpl();
	
		
		System.out.println(newCm.getContacts(""));
		System.out.println(newCm.addNewContact("Gambit", "he's a betting man"));
		System.out.println(newCm.getContacts(""));
		//System.out.println(newCm.getMeeting(1).getId());
		System.out.println(newCm.getFutureMeeting(1).getId());
	}
}
