package ru.stqa.pft.addressbook.tests;


import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AddingContactWithGroupInGroupTest extends TestBase{
  Groups allGroups;
  int groupsSize;
  GroupData testGroup;
  ContactData testContact;
  int contactId;

  @BeforeMethod
  public void ensurePreconditions(){
    Contacts contacts = app.db().contacts();
    allGroups = app.db().groups();
    groupsSize = getGroupsSize(allGroups);
    allGroups = app.db().groups();
    testContact = getTestContact(contacts, groupsSize);
    contactId = testContact.id();
    testGroup = getTestGroup(testContact.getGroups());
  }

  @Test
  public void testAddingContactWithGroupInGroup() {
    ContactData before = app.db().contacts().stream().filter((c) -> (c.id() == contactId)).iterator().next();
    app.contact().addingContactInGroup(contactId, testGroup.id());
    ContactData after = app.db().contacts().stream().filter((c) -> (c.id() == contactId)).iterator().next();
    System.out.println("before: " + before);
    System.out.println("after: " + after);
    assertThat(after, equalTo(before.inGroup(testGroup)));
  }

  private int getGroupsSize(Groups groups) {
    if (groups.size() == 0) {
      app.contact().createEmptyGroup();
      groupsSize = app.db().groups().size();
    } else {
      groupsSize = groups.size();
    }
    return groupsSize;
  }

  private ContactData getTestContact(Contacts contacts, int groupSize) {
    if (contacts.size() != 0) {
      try {
        testContact = contacts.stream().filter((c) -> (c.getGroups().size() < groupSize)).iterator().next();
      } catch (Exception ex) {
        testContact = null;
      }
      if (testContact == null) {
        ContactData newContact = contacts.stream().filter((c) -> (c.getGroups().size() == groupSize)).iterator().next();
        int newContactId = newContact.id();
        GroupData group = allGroups.iterator().next();
        app.contact().removingContactFromGroup(newContact, group);
        contacts = app.db().contacts();
        testContact = contacts.stream().filter((c) -> (c.id() == newContactId)).iterator().next();
      }
    }else {
      ContactData newContact = new ContactData().withLastName(RandomStringUtils.randomAlphabetic(10)).withFirstName(RandomStringUtils.randomAlphabetic(10));
      app.contact().create(newContact);
      contacts = app.db().contacts();
      testContact = contacts.stream().filter((c) -> (c.getGroups().size() < groupSize)).iterator().next();
    }
    return testContact;
  }

  private GroupData getTestGroup (Groups groupsWithContact){
    if (groupsWithContact != null) {
      Groups groupsWithoutContact = allGroups;
      groupsWithoutContact.removeAll(groupsWithContact);
      testGroup = groupsWithoutContact.stream().iterator().next();
    }else {
      testGroup = allGroups.stream().iterator().next();
    }
    return testGroup;
  }

  }


