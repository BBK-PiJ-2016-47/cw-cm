package main.java.implementations;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import main.java.spec.*;

public class ContactManagerImpl implements ContactManager{

	protected static Set<Contact> contacts = new HashSet<Contact>();
	protected static List<Meeting> meetings = new ArrayList<Meeting>();
	private static int contactCount;
	private static int meetingCount;
	
	/*
	 * adding constructor so can load contacts.txt on opening
	 */
	public ContactManagerImpl(){
		load();
	}
	
	/*
	 * method to load contacts.txt into Contact Manager
	 */
	@SuppressWarnings("unchecked")
	private void load(){
		try(FileInputStream fi = new FileInputStream("contacts.txt")){
			ObjectInputStream os = new ObjectInputStream(fi);
			contacts = (HashSet<Contact>) os.readObject();
			meetings = (List<Meeting>) os.readObject();
			os.close();
		} catch (FileNotFoundException e) {
			System.out.println("No saved file: New contacts file being made");
		} catch (IOException e) {
			System.out.println("Couldn't read from this file!");
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't find the right class!");
		}
		
		contactCount = (contacts.size());
		meetingCount = (meetings.size());
	}
	/*
	 * @see interfaces.ContactManager#addFutureMeeting(java.util.Set, java.util.Calendar)
	 */
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
		int futureId = newMeeting.getId();
		return futureId;

	}
	 
	/*
	 * @see interfaces.ContactManager#getPastMeeting(int)
	 */

	@Override
	public PastMeeting getPastMeeting(int id){
		Meeting past = getMeeting(id);
		if (past == null){
			return null;
		}
		if (past.getDate().after(Calendar.getInstance())) {
			throw new IllegalStateException("This date is in the future!");
		}
		
		return (PastMeeting) past;
	}
	
	/*
	 * @see interfaces.ContactManager#getFutureMeeting(int)
	 */
	
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
	
	/*
	 * (non-Javadoc)
	 * @see interfaces.ContactManager#getMeeting(int)
	 */
	
	@Override
	public Meeting getMeeting(int id){
		Meeting meetingGot = null;
		
		try {
			//id number is one more than array index so ids are > 1
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
	
	/*
	 * (non-Javadoc)
	 * @see interfaces.ContactManager#getFutureMeetingList(interfaces.Contact)
	 */
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
    	//iterating through list of future meetings to get meetings with relevant contacts
    	for(int i = 0; i < futureMeetings.size();i++){
    		Set <Contact> tempSet = futureMeetings.get(i).getContacts();
    		Iterator<Contact> it = tempSet.iterator();
    		if (it.next().equals(contact)){
    			filteredList.add(futureMeetings.get(i));
    		}

    	}
    	//Will this sort the list?
    	Collections.sort(filteredList, new Comparator<Meeting>() {
    	    public int compare(Meeting m1, Meeting m2) {
    	        return m1.getDate().compareTo(m2.getDate());
    	    }
    	});
    	return filteredList;
    }
	

	
/*
 * (non-Javadoc)
 * @see interfaces.ContactManager#getMeetingListOn(java.util.Calendar)
 */
	
	@Override
	public List<Meeting> getMeetingListOn(Calendar date){
    	if (date == null){
    		throw new NullPointerException("The date you entered was null!");
    	}
    	List<Meeting> filteredList = new ArrayList<Meeting>();
    	for (int i = 0; i < meetings.size(); i++){
			if (meetings.get(i).getDate().equals(date)) {
				Meeting meeting = meetings.get(i);
				filteredList.add(meeting);
				}
			}
    	return filteredList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see interfaces.ContactManager#getPastMeetingListFor(interfaces.Contact)
	 */
	
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
    	//iterating through to find past meetings with relevant contact
    	for(int i = 0; i < pastMeetings.size();i++){
    		Set <Contact> tempSet = pastMeetings.get(i).getContacts();
    		Iterator<Contact> it = tempSet.iterator();
    		if (it.next().equals(contact)){
    			pastFilteredList.add((PastMeeting) pastMeetings.get(i));
    		}

    	}
    	return pastFilteredList;
	}
	
	/*
	 * @see interfaces.ContactManager#addNewPastMeeting(java.util.Set, java.util.Calendar, java.lang.String)
	 */
	
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
	
	/*
	 * @see interfaces.ContactManager#addMeetingNotes(int, java.lang.String)
	 */
	
	@Override
	public PastMeeting addMeetingNotes(int id, String text){
		if (text == null){
			throw new NullPointerException("The notes are null!");
		}
		if (id > meetingCount){
			throw new IllegalArgumentException("The meeting does not exist");
		}
		Meeting got = getPastMeeting(id);
		if(got.getDate().after(Calendar.getInstance())) {
			throw new IllegalArgumentException("This is not a past meeting!");
		}
		 
		PastMeetingImpl pastGot = (PastMeetingImpl) got;
		pastGot.addNotes(text);
		got = pastGot;
		return (PastMeeting) got;
	}
	
	/*
	 * @see interfaces.ContactManager#addNewContact(java.lang.String, java.lang.String)
	 */
	@Override
	public int addNewContact(String name, String notes){
		if (name.equals("")|| notes.equals("")){
			throw new IllegalArgumentException("Name/notes is empty!");
		}
		contactCount++;
		int newId = contactCount;
		Contact contact = new ContactImpl(newId,name, notes);
		contacts.add(contact);
		return contact.getId();
	}
	
	/*
	 * @see interfaces.ContactManager#getContacts(java.lang.String)
	 */

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
	
	/*
	 * (non-Javadoc)
	 * @see interfaces.ContactManager#getContacts(int[])
	 */
	@Override
	public Set<Contact> getContacts(int... ids){
		int idsSize = ids.length;
		int contactSize = contacts.size();
		Set<Contact> filteredContacts = new HashSet<Contact>();
		//turning into array to search more easily
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
	
	@Override
	public void flush() {
		try(FileOutputStream fs = new FileOutputStream("contacts.txt")) {
			
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(contacts);
		    
			os.writeObject(meetings);
		    os.close();
		    

		} catch (FileNotFoundException e) {
			System.out.println("File wasn't found!");
		} catch (IOException e) {
			System.out.println("Couldn't write to file!");
		}
	 
		
	}
}