package test.java.tests;

import main.java.implementations.ContactImpl;
import main.java.spec.Contact;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


public class ContactImplTest {
  private Contact test;
  private Contact test2;

  @Before
  public void buildUp() {
    test = new ContactImpl(2, "Wolverine", "notes");
    test2 = new ContactImpl(2, "Wolverine");
  }

  @Test
  public void checkGetters1() {
    assertTrue(test.getId() != 0);
    assertTrue(test.getId() > 0);
    assertTrue(test.getName().equals("Wolverine"));
  }
  
  @Test
  public void checkGetters2() {
    assertTrue(test2.getId() != 0);
    assertTrue(test2.getId() > 0);
    assertTrue(test2.getName().equals("Wolverine"));
  }
  
  @Test
  public void checkAddAndGetNotes() {
    String output = test.getNotes();
    assertTrue(output.equals("notes"));
    test.addNotes("Can be challenging");
    output = test.getNotes();
    assertTrue(output.equals("notes; Can be challenging"));
    test.addNotes("has good hair");
    output = test.getNotes();
    assertTrue(output.equals("notes; Can be challenging; has good hair"));
  }
  
  @Test
  public void checkAddAndGetNotes2() {
    String output = test2.getNotes();
    assertTrue(output.equals(""));
    test2.addNotes("Can be challenging");
    output = test2.getNotes();
    assertTrue(output.equals("; Can be challenging"));
    test2.addNotes("has good hair");
    output = test2.getNotes();
    assertTrue(output.equals("; Can be challenging; has good hair"));
  }
}
