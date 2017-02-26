package tests;
import interfaces.Contact;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import implementations.ContactImpl;

public class ContactImplTest {
	private Contact test;

	@Before
	public void buildUp() {
		test = new ContactImpl(2, "Wolverine", "");
	}
	
    @Test
    public void checkGetters() {
    	assertTrue(test.getId() != 0);
    	assertTrue(test.getId() > 0);
    	assertTrue(test.getName().equals("Wolverine"));
    }
  
    @Test
    public void checkAddAndGetNotes() {
    	String output = test.getNotes();
    	assertTrue(output.equals(""));
    	test.addNotes("Can be challenging");
    	output = test.getNotes();
    	assertTrue(output.equals(", Can be challenging"));
    	test.addNotes("has good hair");
    	output = test.getNotes();
    	assertTrue(output.equals(", Can be challenging, has good hair"));
    	
    }

}
