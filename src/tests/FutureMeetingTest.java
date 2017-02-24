package tests;

import interfaces.FutureMeeting;
import interfaces.Meeting;
import static org.junit.Assert.*;

import org.junit.Test;

public class FutureMeetingTest {

	FutureMeeting test;
	
	@Before
	public void setUP() {
		test = new FutureMeetingImpl();
	}

	@Test
	public void testGetId() {
    	assertTrue(test.getId() != 0);
    	assertTrue(test.getId() > 0);
	}
}
