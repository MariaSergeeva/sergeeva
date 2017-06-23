package ru.stqa.pft.addressbook.tests;


import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().ContactsList();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName(RandomStringUtils.randomAlphabetic(10)).withLastName(RandomStringUtils.randomAlphabetic(10)).withGroup(app.contact().groupName()));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteById(deletedContact);
    app.goTo().ContactsList();
    assertEquals(app.contact().count(), (before.size() - 1));
    Contacts after = app.db().contacts();
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
  }
}
