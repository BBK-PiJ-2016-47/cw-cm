import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import interfaces.*;
import implementations.*;

public class ContactManagerImpl implements ContactManager, Serializable {
	
	private Set<Contact> contacts;
	private Calendar date;
	private List<Meeting> meetings = new ArrayList<Meeting>();
	private static int contactCount;
	private static int meetingCount;
	
	@Override
	public int addFutureMeeting(Set<Contact>contacts, Calendar date) {
		FutureMeeting newMeeting = null;
		try {
			int newId = (meetingCount + 1001);
			
			newMeeting = new FutureMeetingImpl(newId, date, contacts);
			meetingCount++;
			meetings.add(newMeeting);
			
		} catch (IllegalArgumentException e) {
			System.out.println("Date is in the past or contact isn't known.");
		} catch (NullPointerException e) {
			System.out.println("You've included a null parameter!");
		} catch (Exception e) {
			System.out.println("Something else went wrong, see stack trace");
			e.printStackTrace();
		}
		return newMeeting.getId();

	}
	 

	@Override
	public PastMeeting getPastMeeting(int id){
		Meeting past = getMeeting(id);
		if (past.getDate().after(Calendar.getInstance())) {
			throw new IllegalArgumentException("This date is in the future!");
		}
		return (PastMeeting) past;
	}
	
	@Override
	public FutureMeeting getFutureMeeting(int id){
		Meeting future = getMeeting(id);
		if (future.getDate().before(Calendar.getInstance())) {
			throw new IllegalArgumentException("This date is in the past!");
		}
		return (FutureMeeting) future;
	}
	
	@Override
	public Meeting getMeeting(int id){
		Meeting meetingGot = null;
		try {
			meetingGot = meetings.get(id);
		} catch (IllegalArgumentException e) {
			System.out.println("Your id must be more than 1 and your contacts must exist");
		} catch (NullPointerException e) {
			System.out.println("Your contacts or the date must not be null");
		} catch (Exception e) {
			System.out.println("There is another issue (see stack trace)");
			e.printStackTrace();
		}
		return meetingGot;
	}
	
	public List<Meeting> getFutureMeetingList(Contact contact) {
    	List<Meeting> filteredList = new ArrayList<Meeting>();
    	if(!contacts.contains(contact)){
    		throw new IllegalArgumentException("The contact doesn't exist!");
    	}

    	if(contact == null) {
    		throw new NullPointerException("The contact you've entered is null!");
    	}
    	for (int i = 0; i < meetings.size(); i++){
			if (meetings.get(i).getDate().after(Calendar.getInstance())) {
				Meeting future;
				future = meetings.get(i);
				if (future.getContacts().equals(contact)){
					filteredList.add(future);
				}
			}
			
    	}
    	return filteredList;
    }
	
	//List<Meeting> getMeetingListOn(Calendar date);
	
	public List<PastMeeting> getPastMeetingListFor(Contact contact){
	List<PastMeeting> pastFilteredList = new ArrayList<PastMeeting>();
	if(!contacts.contains(contact)){
		throw new IllegalArgumentException("The contact doesn't exist!");
	}

	if(contact == null) {
		throw new NullPointerException("The contact you've entered is null!");
	}
	for (int i = 0; i < meetings.size(); i++){
		if (meetings.get(i).getDate().before(Calendar.getInstance())) {
			Meeting past;
			past = meetings.get(i);
			if (past.getContacts().equals(contact)){
				pastFilteredList.add((PastMeeting) past);
			}
		}
		
	}
	return pastFilteredList;
}
	
	//int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text);
	
	//PastMeeting addMeetingNotes(int id, String text);
	
	
	@Override
	public int addNewContact(String name, String notes){
		contactCount++;
		int newId = contactCount;
		Contact contact = new ContactImpl(newId,name, notes);
		contacts.add(contact);
		return contact.getId();
	}
	
	// Set<Contact> getContacts(String name);
	
	//Set<Contact> getContacts(int... ids);
	
	//void flush();
}