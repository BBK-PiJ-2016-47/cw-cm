package main.java.implementations;

import java.util.Calendar;
import java.util.Set;

import main.java.spec.Contact;
import main.java.spec.FutureMeeting;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
  
  private static final long serialVersionUID = 40807695573875798L;

  public FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) throws Exception {
    super(id, date,contacts);
  }
}
