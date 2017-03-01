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
	
	//List<Meeting> getFutureMeetingList(Contact contact);
	
	//List<Meeting> getMeetingListOn(Calendar date);
	
	//List<PastMeeting> getPastMeetingListFor(Contact contact);
	
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