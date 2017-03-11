package main.java.implementations;

import main.java.spec.Contact;
import main.java.spec.Meeting;

import java.io.Serializable;
import java.util.*;

public abstract class MeetingImpl implements Meeting, Serializable {


	private static final long serialVersionUID = 1L;
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
	
	if (date == null){
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
