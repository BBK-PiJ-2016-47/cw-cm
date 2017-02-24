package tests;
import java.util.Calendar;
import static org.junit.Assert.*;
import org.junit.Test;
public class MeetingTest {
	
	Meeting test = new MeetingImpl();

	@Test
	public void testGetId() {
    	assertTrue(test.getId() != 0);
    	assertTrue(test.getId() > 0);
	}
	
	@Test
	public void testGetDate() {
		Calendar testCal;
		testCal = Calendar.getInstance();
		Calendar date = test.getDate();
		date.add(Calendar.YEAR, 1);
		assertFalse(date.get(Calendar.YEAR))
	}
	
	@Test
	public void testGetContacts(){
		//check getContacts
	}

}
