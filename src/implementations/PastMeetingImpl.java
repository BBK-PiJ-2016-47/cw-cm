package implementations;

import interfaces.Contact;
import interfaces.PastMeeting;

import java.util.*;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting{
	private String notes;
	
	public PastMeetingImpl(Calendar date, Set<Contact> contacts, String notes){
		super(date,contacts);
		this.notes = notes;
	}
	
	@Override
	public String getNotes() {
		return this.notes;
	}

}
