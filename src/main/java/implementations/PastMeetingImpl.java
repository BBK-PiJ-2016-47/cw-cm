package main.java.implementations;

import main.java.spec.Contact;
import main.java.spec.PastMeeting;

import java.util.*;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting{

	private static final long serialVersionUID = -92033211010420020L;
	private String notes;
	
	public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String notes) throws Exception{
		super(id,date,contacts);
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
