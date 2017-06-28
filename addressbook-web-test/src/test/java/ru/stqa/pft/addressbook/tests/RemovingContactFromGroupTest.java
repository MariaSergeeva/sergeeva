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
  GroupData group = new GroupData();
  ContactData contact = new ContactData();

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    group = getGroup(groups);
    contact = getContact(group);
  }

  @Test
  public void testRemovingContactFromGroup() {
    ContactData before = app.db().contacts().stream().filter((c) -> (c.id() == contact.id())).iterator().next();
    app.contact().removingContactInGroup(contact, group);
    ContactData after = app.db().contacts().stream().filter((c) -> (c.id() == contact.id())).iterator().next();
    System.out.println("before: " + before);
    System.out.println("after: " + after);
    org.junit.Assert.assertThat(before, CoreMatchers.equalTo(after.inGroup(group)));

  }

  private GroupData getGroup(Groups groups) {
    try {
      group = app.db().groups().iterator().next();
    } catch (NoSuchElementException ex) {
      GroupData newGroup = new GroupData().withName(RandomStringUtils.randomAlphabetic(10));
      app.goTo().GroupPage();
      app.group().create(newGroup);
      group = app.db().groups().stream().filter((g) -> (g.name().equals(newGroup.name()))).iterator().next();
    }
    return group;
  }

  private ContactData getContact(GroupData group) {
    ContactData newContact = new ContactData().withLastName(RandomStringUtils.randomAlphabetic(10)).withFirstName(RandomStringUtils.randomAlphabetic(10)).inGroup(group);
    app.goTo().ContactsList();
    app.contact().create(newContact);
    contact = app.db().contacts().stream().filter((c) -> (c.getGroups().size() != 0)).iterator().next();
    return contact;
  }
}