package tests;
import java.util.Calendar;
import org.junit.*; 
import static org.junit.Assert.*;
import org.junit.Before;

public class PastMeetingTest {
	PastMeetingImpl test;
	Set<Contact> contacts;
	Calendar date;
	
	
	@Before
	public void setUp() {
		test = new PastMeetingImpl();
	}
	@Test
	public void checkGetNotes_WithNotes() {
		test.notes = "Example";
		String output = test.getNotes();
		assertTrue(output.equals("Example"));
	}
	
	@Test
	public void checkGetNotes_NoNotes() {
		String output = test.getNotes();
		assertTrue(output.equals(""));
	}


}
