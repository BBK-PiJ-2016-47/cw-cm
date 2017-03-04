package implementations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import interfaces.*;
import implementations.*;

public class ContactManagerImpl implements ContactManager{
	
	protected static Set<Contact> contacts = new HashSet<Contact>();
	//private Calendar date;
	protected static List<Meeting> meetings = new ArrayList<Meeting>();
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
		if (past == null){
			return null;
		}
		if (past.getDate().after(Calendar.getInstance())) {
			System.out.println("We are at future exception");
			throw new IllegalStateException("This date is in the future!");
		}
		
		return (PastMeeting) past;
	}
	
	@Override
	public FutureMeeting getFutureMeeting(int id){
		Meeting future = getMeeting(id);
		if (future == null){
			return null;
		}
		if (future.getDate().before(Calendar.getInstance())) {
			throw new IllegalStateException("This date is in the past!");
		}
		return (FutureMeeting) future;
	}
	
	@Override
	public Meeting getMeeting(int id){
		Meeting meetingGot = null;
		
		try {
			int placeInArray = (id-1);
			if (placeInArray > meetings.size()){
				return null;
			}
			meetingGot = meetings.get(placeInArray);
		} catch (Exception e) {
			System.out.println("There is another issue (see stack trace)");
			e.printStackTrace();
		}
		return meetingGot;
	}
	
	@Override
	public List<Meeting> getFutureMeetingList(Contact contact) {
    	List<Meeting> filteredList = new ArrayList<Meeting>();
    	if(contact == null) {
    		throw new NullPointerException("The contact you've entered is null!");
    	}
    	if(!contacts.contains(contact)){
    		throw new IllegalArgumentException("The contact doesn't exist!");
    	}

    	//getting list of future meetings
    	List <Meeting> futureMeetings = new ArrayList<Meeting>();
    	for(int i = 0; i < meetings.size(); i++){
    		if (meetings.get(i).getDate().after(Calendar.getInstance())) {
        			futureMeetings.add(meetings.get(i));
    		}
    	}
    	
    	for(int i = 0; i < futureMeetings.size();i++){
    		Set <Contact> tempSet = futureMeetings.get(i).getContacts();
    		Iterator<Contact> it = tempSet.iterator();
    		if (it.next().equals(contact)){
    			filteredList.add(futureMeetings.get(i));
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
		if(contact == null) {
			throw new NullPointerException("The contact you've entered is null!");
		}
		if(!contacts.contains(contact)){
			throw new IllegalArgumentException("The contact doesn't exist!");
		}
		
    	//getting list of past meetings
    	List <Meeting> pastMeetings = new ArrayList<Meeting>();
    	for(int i = 0; i < meetings.size(); i++){
    		if (meetings.get(i).getDate().before(Calendar.getInstance())) {
    			pastMeetings.add(meetings.get(i));
    		}
    	}
    	
    	for(int i = 0; i < pastMeetings.size();i++){
    		Set <Contact> tempSet = pastMeetings.get(i).getContacts();
    		Iterator<Contact> it = tempSet.iterator();
    		if (it.next().equals(contact)){
    			pastFilteredList.add((PastMeeting) pastMeetings.get(i));
    		}

    	}
    	return pastFilteredList;
	}
	
	@Override
	public int addNewPastMeeting(Set<Contact> contacts, Calendar date, String text){
		PastMeeting newPastMeeting = null;
		if (contacts.isEmpty()){
			throw new IllegalArgumentException("Your contacts are empty!");
		}
		if (date.after(Calendar.getInstance())){
			throw new IllegalArgumentException("This date is in the future!");
		}
		try {
			meetingCount++;
			newPastMeeting = new PastMeetingImpl(meetingCount, date, contacts, text);
			meetings.add(newPastMeeting);
			
		} catch (Exception e) {
			System.out.println("Check those parameters you entered!");
			e.printStackTrace();
		}
		return newPastMeeting.getId();
	}
	
	
	@Override
	public PastMeeting addMeetingNotes(int id, String text){
		if (text == null){
			throw new NullPointerException("The notes are null!");
		}
		if (id > meetingCount){
			throw new IllegalArgumentException("The meeting does not exist");
		}
		Meeting got = getPastMeeting(id);
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
		if (name == "" || notes == ""){
			throw new IllegalArgumentException("Name/notes is empty!");
		}
		contactCount++;
		int newId = contactCount;
		Contact contact = new ContactImpl(newId,name, notes);
		contacts.add(contact);
		return contact.getId();
	}
	

	@Override
	public Set<Contact> getContacts(String name){
		Set<Contact> filteredContacts = new HashSet<Contact>();
		filteredContacts.addAll(contacts);
		Iterator<Contact> iterator = filteredContacts.iterator();
		while (iterator.hasNext()) {
			Contact thing = iterator.next();
			if (!thing.getName().contains(name)) {
				iterator.remove();
			}
		}
		return filteredContacts;
	}
	
	@Override
	public Set<Contact> getContacts(int... ids){
		int idsSize = ids.length;
		int contactSize = contacts.size();
		Set<Contact> filteredContacts = new HashSet<Contact>();
		Contact[] search = contacts.toArray(new Contact[contactSize]);
		for(int i = 0; i < idsSize; i++){
			int toCompare = ids[i];
			for(int j = 0; j < contactSize; j++){
				if(search[j].getId() == toCompare){
					filteredContacts.add(search[j]);
				}
			}
		}
		if (filteredContacts.isEmpty()){
			throw new IllegalArgumentException("ID not found!");
		}
		return filteredContacts;
	}
	
	//TO-DO
	@Override
	public void flush() {
		
	}
}