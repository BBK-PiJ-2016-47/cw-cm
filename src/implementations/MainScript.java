package implementations;
import interfaces.Contact;
import interfaces.ContactManager;
import interfaces.Meeting;
import interfaces.PastMeeting;


import java.util.*;

import org.junit.Before;
public class MainScript {
	public static void main(String[] args) throws Exception{

		ContactManager cm = new ContactManagerImpl();
		Calendar emptyDate = new GregorianCalendar(2014,12,25);
		Calendar pastDate = new GregorianCalendar(2016,9,20);
		Calendar futureDate = new GregorianCalendar(2017,9,20);
		Set<Contact> contacts = ContactManagerImpl.contacts;
		List<Meeting> meetings = ContactManagerImpl.meetings;
		List<Meeting> filteredList = new ArrayList<Meeting>();
		
		
		//Meeting pastMeeting = new PastMeetingImpl(1, pastDate,contacts,"Here are some notes");
		//Meeting futureMeeting = new FutureMeetingImpl(1001, futureDate, contacts);
	
			
		
		System.out.println(cm.addNewContact("Storm", "don't do weather small talk"));
		System.out.println(cm.addNewContact("Professor X", "headmaster of Xavier's school for gifted youngsters"));
		System.out.println(cm.addNewContact("Rogue", "make sure she's wearing gloves"));
		
		Set<Contact> test = cm.getContacts("");
		
		Contact[] testArr = test.toArray(new Contact[test.size()]);
		for(int i = 0; i < testArr.length; i++){
			System.out.println(testArr[i].getName());
		}
		
		
		//System.out.println(cm.addFutureMeeting(contacts, futureDate));
		//System.out.println(cm.addFutureMeeting(contacts, new GregorianCalendar(2017, 4, 19)));
		
		//cm.addFutureMeeting(contacts, futureDate);
			
			
			
			
			
			
			

	}
}
