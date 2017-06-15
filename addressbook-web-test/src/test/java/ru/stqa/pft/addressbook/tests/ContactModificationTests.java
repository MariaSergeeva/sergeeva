package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().ContactsList();
    if (app.Contact().all().size() == 0) {
      app.Contact().create(new ContactData().withFirstName(RandomStringUtils.randomAlphabetic(10)).withLastName(RandomStringUtils.randomAlphabetic(10)).withGroup(app.Contact().groupName()));
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.Contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.id()).withFirstName(RandomStringUtils.randomAlphabetic(10)).withMiddleName(null).withLastName(RandomStringUtils.randomAlphabetic(10)).withAddress(null).withMobileTelephone(null).withHomeTelephone(null).withEmail(RandomStringUtils.randomAlphabetic(10));
    app.Contact().modify(contact);
    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
