package implementations;

import interfaces.Contact;
import interfaces.Meeting;
import java.util.*;

public abstract class MeetingImpl implements Meeting {
	private int id;
	private Calendar date;
	private Set<Contact> contacts;
	
	public MeetingImpl(int id, Calendar date, Set<Contact> contacts) throws Exception{
		
	if (id < 1) {
		throw new IllegalArgumentException("Your ID is less than 1!");
	}
	
	if(contacts.isEmpty()) {
		throw new IllegalArgumentException("No contacts!");
	}
	
	if (date == null || contacts == null){
		throw new NullPointerException("Your date or contacts is null");
	}
		this.id = id;
		this.date = date;
		this.contacts = contacts;
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
