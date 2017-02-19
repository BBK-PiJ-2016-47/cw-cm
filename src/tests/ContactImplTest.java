package tests;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class ContactImplTest {
	private ContactImpl test;

	@Before
	public void buildUp() {
		test = new ContactImpl();
	}
	
 /*   @Test
    public void checkGetID() {
    	test.id = 5; - how to assign ID??
    	int output = test.getId();
    	assertTrue(output == 5);
    }
*/    
    @Test
    public void checkAddAndGetNotes() {
    	test.addNotes("This is the note!");
    	String output = test.getNotes();
    	assertTrue(output.equals("This is the note!"));
    	
    }

}
