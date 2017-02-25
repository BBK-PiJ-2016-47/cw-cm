package implementations;
import interfaces.Contact;

public class ContactImpl implements Contact {
	private int id;
	private String name;
	private String notes;
	private static int count;
	  
	public ContactImpl(String name, String notes){
		this.name = name;
		this.notes = notes;
		count++;
		this.id = count;
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
		this.notes = note;
	}

}
