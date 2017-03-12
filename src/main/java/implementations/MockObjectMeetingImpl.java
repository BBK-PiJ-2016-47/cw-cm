package main.java.implementations;

import java.util.Calendar;
import java.util.Set;

import main.java.spec.Contact;
import main.java.spec.Meeting;

public class MockObjectMeetingImpl extends MeetingImpl implements Meeting{
  public MockObjectMeetingImpl(int id, Calendar date, Set<Contact> contacts) throws Exception {
    super(id, date, contacts);
  }
  
 
}
