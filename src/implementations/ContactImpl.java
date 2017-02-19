package implementations;
import interfaces.Contact;

public class ContactImpl implements Contact {
	  private int id;
	  private String name;
	  private String notes;
	  
	  public int getId() {
	    return id;
	  }
	  
	  public String getName() {
	    return name;
	  }
	  
	  public String getNotes() {
	    return notes;
	  }
	  
	  public void addNotes(String note) {
	    this.notes = note;
	  }

}
