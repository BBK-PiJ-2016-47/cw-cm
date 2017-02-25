package implementations;

import interfaces.Contact;
import interfaces.Meeting;
import java.util.*;

public class MeetingImpl implements Meeting {
	private int id;
	private Calendar date;
	private Set<Contact> contacts;
	private static int counter;
	
	public MeetingImpl(Calendar date, Set<Contact> contacts){
		this.date = date;
		this.contacts = contacts;
		counter++;
		this.id = counter;
	}

	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public Calendar getDate(){
		return this.date;
	}
	
	@Override
	public Set<Contact> getContacts(){
		return this.contacts;
	}
}
