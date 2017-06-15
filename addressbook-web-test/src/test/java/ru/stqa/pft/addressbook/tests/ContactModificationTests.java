package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.testng.Assert.assertEquals;

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
    Contacts before = app.Contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.id()).withFirstName(RandomStringUtils.randomAlphabetic(10)).withMiddleName(null).withLastName(RandomStringUtils.randomAlphabetic(10)).withAddress(null).withMobileTelephone(null).withHomeTelephone(null).withEmail(RandomStringUtils.randomAlphabetic(10));
    app.Contact().modify(contact);
    Contacts after = app.Contact().all();
    assertEquals(after.size(), before.size());
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
