package implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import interfaces.*;
import implementations.*;

public class ContactManagerImpl implements ContactManager{
	
	private Set<Contact> contacts;
	private Calendar date;
	private List<Meeting> meetings = new ArrayList<Meeting>();
	private static int contactCount;
	private static int meetingCount;
	
	@Override
	public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
		if (date.before(Calendar.getInstance())){
			throw new IllegalArgumentException("Date is in the past");
		}
		if (contacts.isEmpty()){
			throw new IllegalArgumentException("Contact set is empty");
		}
		FutureMeeting newMeeting = null;
		try {
			meetingCount++;
			int newId = meetingCount;
			newMeeting = new FutureMeetingImpl(newId, date, contacts);
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
	
	@Override
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
	
	@Override
	public List<Meeting> getMeetingListOn(Calendar date){
    	List<Meeting> filteredList = new ArrayList<Meeting>();
    	for (int i = 0; i < meetings.size(); i++){
			if (meetings.get(i).getDate().equals(date)) {
				Meeting meeting = meetings.get(i);
				filteredList.add(meeting);
				}
			}
    	return filteredList;
	}
	
	@Override
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
	
	@Override
	public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text){
		PastMeeting newPastMeeting = null;
		try {
			meetingCount++;
			newPastMeeting = new PastMeetingImpl(meetingCount, date, contacts, text);
		} catch (Exception e) {
			System.out.println("Check those parameters you entered!");
			e.printStackTrace();
		}
		int newId = newPastMeeting.getId();
		return newId;
	}
	
	
	@Override
	public PastMeeting addMeetingNotes(int id, String text){
		if (text == null){
			throw new NullPointerException("The notes are null!");
		}
		Meeting got = meetings.get(id);
		if(got.getDate().after(Calendar.getInstance()) || got == null) {
			throw new IllegalArgumentException("This is not a past meeting!");
		}
		 
		PastMeetingImpl pastGot = (PastMeetingImpl) got;
		
		pastGot.addNotes(text);
		got = pastGot;
		return (PastMeeting) got;
	}
	
	
	@Override
	public int addNewContact(String name, String notes){
		contactCount++;
		int newId = contactCount;
		Contact contact = new ContactImpl(newId,name, notes);
		contacts.add(contact);
		return contact.getId();
	}
	
	//TO-DO
	@Override
	public Set<Contact> getContacts(String name){
		return contacts;
	}
	
	//TO-DO
	@Override
	public Set<Contact> getContacts(int... ids){
		return contacts;
	}
	
	//TO-DO
	@Override
	public void flush() {
		
	}
}