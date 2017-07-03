package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.NoSuchElementException;

public class RemovingContactFromGroupTest extends TestBase {
  GroupData testGroup = new GroupData();
  ContactData testContact = new ContactData();

  @BeforeMethod
  public void ensurePreconditions() {
    Groups allGroups = app.db().groups();
    if (allGroups.size() == 0){
      GroupData newGroup = new GroupData().withName(RandomStringUtils.randomAlphabetic(10));
      app.goTo().GroupPage();
      app.group().create(newGroup);
      allGroups = app.db().groups();
    }
    Contacts contacts = app.db().contacts();
    if (contacts.size() != 0) {
      try {
        testContact = contacts.stream().filter((c) -> c.getGroups().size() != 0).iterator().next();
      } catch (Exception ex) {
        testContact = null;
      }
      if (testContact == null) {
        int contactId = contacts.stream().filter((c) -> (c.getGroups().size() == 0)).iterator().next().id();
        int groupId = allGroups.iterator().next().id();
        app.contact().addingContactInGroup(contactId, groupId);
        contacts = app.db().contacts();
        testContact = contacts.stream().filter((c) -> c.getGroups().size() != 0).iterator().next();
      }
    } else {
      ContactData newContact = new ContactData().withLastName(RandomStringUtils.randomAlphabetic(10)).withFirstName(RandomStringUtils.randomAlphabetic(10)).inGroup(allGroups.stream().iterator().next());
      app.goTo().ContactsList();
      app.contact().create(newContact);
      testContact = app.db().contacts().stream().filter((c) -> (c.getGroups().size() != 0)).iterator().next();
    }
    Groups groupsWithContact = testContact.getGroups();
    testGroup = groupsWithContact.stream().iterator().next();

  }

  @Test
  public void testRemovingContactFromGroup() {
    ContactData before = app.db().contacts().stream().filter((c) -> (c.id() == testContact.id())).iterator().next();
    app.contact().removingContactFromGroup(testContact, testGroup);
    ContactData after = app.db().contacts().stream().filter((c) -> (c.id() == testContact.id())).iterator().next();
    System.out.println("before: " + before);
    System.out.println("after: " + after);
    org.junit.Assert.assertThat(before, CoreMatchers.equalTo(after.inGroup(testGroup)));

  }
}