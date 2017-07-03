package ru.stqa.pft.addressbook.tests;


import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AddingContactWithGroupInGroupTest extends TestBase {

  int contactId;
  int groupId;
  GroupData testGroup = new GroupData();

  @BeforeMethod
  public void ensurePreconditions() {
    Contacts contacts = app.db().contacts();
    int groupSize = getGroupsSize(app.db().groups());
    Groups allGroups = app.db().groups();
    ContactData testContact;
    if (contacts.size() != 0) {
      try {
        testContact = contacts.stream().filter((c) -> (c.getGroups().size() != 0 && c.getGroups().size() < groupSize)).iterator().next();
      } catch (Exception ex) {
        testContact = null;
      }
      if (testContact == null) {
        int contactId;
        try {
          contactId = contacts.stream().filter((c) -> (c.getGroups().size() == 0)).iterator().next().id();
        } catch (Exception ex) {
          contactId = 0;
        }
        if (contactId != 0) {
          int groupId = allGroups.iterator().next().id();
          app.contact().addingContactInGroup(contactId, groupId);
          contacts = app.db().contacts();
          testContact = contacts.stream().filter((c) -> (c.getGroups().size() != 0 && c.getGroups().size() < groupSize)).iterator().next();
        }
      }
      if (testContact == null) {
        ContactData newContact;
        try {
          newContact = contacts.stream().filter((c) -> (c.getGroups().size() == groupSize)).iterator().next();
        } catch (Exception ex) {
          newContact = null;
        }
        if (newContact != null) {
          int newContactId = newContact.id();
          GroupData group = allGroups.iterator().next();
          app.contact().removingContactFromGroup(newContact, group);
          contacts = app.db().contacts();
          testContact = contacts.stream().filter((c) -> (c.id() == newContactId)).iterator().next();
        } else testContact = null;
      }
    } else {
      testContact = getContact(allGroups.iterator().next());
    }

    contactId = testContact.id();
    Groups groupsWithContact;
    try {
      groupsWithContact = contacts.stream().filter((c) -> (c.id() == contactId)).iterator().next().getGroups();
    } catch (java.util.NoSuchElementException ex) {
      groupsWithContact = null;
    }

    Groups groupsWithoutContact = allGroups;
    if (groupsWithContact != null) {
      groupsWithoutContact.removeAll(groupsWithContact);
    }
    groupId = groupsWithoutContact.iterator().next().id();
    testGroup = allGroups.stream().filter((g) -> (g.id() == groupId)).iterator().next();

  }

  @Test
  public void testAddingContactWithGroupInGroup() {
    ContactData before = app.db().contacts().stream().filter((c) -> (c.id() == contactId)).iterator().next();
    app.contact().addingContactInGroup(contactId, groupId);
    ContactData after = app.db().contacts().stream().filter((c) -> (c.id() == contactId)).iterator().next();
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

  private int getGroupsSize(Groups groups) {
    int groupSize;
    if (groups.size() < 2) {
      for (int i = 0; i < (2 - groups.size()); i++) {
        app.contact().createEmptyGroup();
      }
      groupSize = app.db().groups().size();
    } else {
      groupSize = groups.size();
    }
    return groupSize;
  }
}


