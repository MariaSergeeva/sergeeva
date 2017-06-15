package ru.stqa.pft.addressbook.tests;


import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

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
    Set<ContactData> before = app.Contact().all();
    ContactData deletedContact = before.iterator().next();
    app.Contact().deleteById(deletedContact);
    app.goTo().ContactsList();
    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(after, before);
  }
}
