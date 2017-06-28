package ru.stqa.pft.addressbook.tests;


import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AddingContactWithGroupInGroupTest extends TestBase {

  GroupData testGroup = new GroupData();
  ContactData contact = new ContactData();


  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    testGroup = getTestGroup(groups);
    groups = app.db().groups();
    GroupData secondGroup = getSecondGroup(groups);
    contact = getContact(secondGroup);
  }

  @Test
  public void testAddingContactWithGroupInGroup() {
    ContactData before = app.db().contacts().stream().filter((c) -> (c.id() == contact.id())).iterator().next();
    app.contact().addingContactInGroup(contact.id(), testGroup.id());
    ContactData after = app.db().contacts().stream().filter((c) -> (c.id() == contact.id())).iterator().next();
    System.out.println("before: " + before);
    System.out.println("after: " + after);
    assertThat(after, equalTo(before.inGroup(testGroup)));
  }

  private ContactData getContact(GroupData secondGroup) {
    ContactData newContact = new ContactData().withLastName(RandomStringUtils.randomAlphabetic(10)).withFirstName(RandomStringUtils.randomAlphabetic(10)).inGroup(secondGroup);
    app.contact().create(newContact);
    return app.db().contacts().stream().filter((c) -> (c.firstName().equals(newContact.firstName()) && c.lastName().equals(newContact.lastName()) && c.getGroups().equals(newContact.getGroups()))).iterator().next();
  }

  private GroupData getSecondGroup(Groups groups) {
    return groups.stream().filter((g) -> (g.id() != testGroup.id())).iterator().next();
  }

  private GroupData getTestGroup(Groups groups) {
    if (groups.size() < 2) {
      for (int i = 0; i < (2 - groups.size()); i++) {
        app.contact().createEmptyGroup();
        groups = app.db().groups();
      }
      testGroup = groups.stream().filter((g) -> (g.getContacts().size() == 0)).iterator().next();
    } else {
      testGroup = groups.iterator().next();
    }
    return testGroup;
  }
}


