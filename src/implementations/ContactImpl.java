package implementations;
import java.util.HashSet;
import java.util.Set;

import interfaces.Contact;

public class ContactImpl implements Contact {
	private int id;
	private String name;
	private String notes;
	private Set<Contact> contacts = new HashSet<Contact>();
	  
	public ContactImpl(int id, String name, String notes){
		if (id < 1) {
			throw new IllegalArgumentException("Must be a positive non-zero integer");
		}
		
		if (name == null){
			throw new NullPointerException("Name cannot be null!");
		}
		
		this.id = id;
		this.name = name;
		this.notes = notes;
		contacts.add(this);
	}
	
	public ContactImpl(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getNotes() {
		return notes;
	}
	
	@Override
	public void addNotes(String note) {
		this.notes += ", " + note;
	}

}
