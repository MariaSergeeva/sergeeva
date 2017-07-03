package ru.stqa.pft.addressbook.tests;


import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import java.util.NoSuchElementException;

public class AddingContactWithoutGroupInGroupTest extends TestBase {
  GroupData group = new GroupData();
  ContactData testContact = new ContactData();

  @BeforeMethod
  public void ensurePreconditions() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    group = getGroup(groups);

    if (contacts.size() != 0) {
      try {
        testContact = contacts.stream().filter((c) -> (c.getGroups().size() == 0)).iterator().next();
      } catch (Exception ex) {
        testContact = null;
      }
      if (testContact == null) {
        ContactData contact = contacts.stream().filter((c) -> (c.getGroups().size() != 0)).iterator().next();
        Groups groupsWithContact = contact.getGroups();
        for (GroupData group : groupsWithContact) {
          app.contact().removingContactFromGroup(contact, group);
        }
        testContact = contact;
      }
    }

  }

  @Test
  public void testAddingContactWithoutGroupInGroup() {
    ContactData before = app.db().contacts().stream().filter((c) -> (c.id() == testContact.id())).iterator().next();
    app.contact().addingContactInGroup(testContact.id(), group.id());
    ContactData after = app.db().contacts().stream().filter((c) -> (c.id() == testContact.id())).iterator().next();
    System.out.println("before: " + before);
    System.out.println("after: " + after);
    org.junit.Assert.assertThat(after, CoreMatchers.equalTo(before.inGroup(group)));
  }

  private ContactData getContact(Contacts contacts) {
    try {
      testContact = contacts.stream().filter((c) -> (c.getGroups().size() == 0)).iterator().next();
    } catch (NoSuchElementException ex) {
      ContactData newContact = new ContactData().withLastName(RandomStringUtils.randomAlphabetic(10)).withFirstName(RandomStringUtils.randomAlphabetic(10));
      app.contact().create(newContact);
      contacts = app.db().contacts();
      testContact = contacts.stream().filter((c) -> (c.getGroups().size() == 0)).iterator().next();
    }
    return testContact;
  }

  private GroupData getGroup(Groups groups) {
    if (groups.size() != 0) {
      group = groups.iterator().next();
    } else {
      GroupData newGroup = new GroupData().withName(RandomStringUtils.randomAlphabetic(10));
      app.group().create(newGroup);
      group = app.db().groups().iterator().next();
    }
    return group;
  }
}


