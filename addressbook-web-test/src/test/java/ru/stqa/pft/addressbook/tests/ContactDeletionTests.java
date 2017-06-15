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
    if (app.Contact().all().size() == 0) {
      app.Contact().create(new ContactData().withFirstName(RandomStringUtils.randomAlphabetic(10)).withLastName(RandomStringUtils.randomAlphabetic(10)).withGroup(app.Contact().groupName()));
    }
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.Contact().all();
    ContactData deletedContact = before.iterator().next();
    app.Contact().deleteById(deletedContact);
    app.goTo().ContactsList();
    Contacts after = app.Contact().all();
    assertEquals(after.size(), before.size() - 1);
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
  }
}
