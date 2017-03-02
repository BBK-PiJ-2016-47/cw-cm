package implementations;

import interfaces.Contact;
import interfaces.PastMeeting;

import java.util.*;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting{
	//private int id;
	//private Calendar date;
	//private Set<Contact> contacts;
	private String notes;
	
	public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String notes) throws Exception{
		super(id,date,contacts);
		//this.id = id;
		//this.date = date;
		//this.contacts = contacts;
		this.notes = notes;
	}
	
	@Override
	public String getNotes() {
		return this.notes;
	}
	
	public void addNotes(String text){
		this.notes += "; " + text;
	}

}
