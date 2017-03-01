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
	
/*
 * 	@Override
 *	public int addFutureMeeting(Set<Contact>contacts, Calendar date) {
 *	}
 */

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
}