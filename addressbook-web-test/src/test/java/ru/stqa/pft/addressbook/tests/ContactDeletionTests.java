package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().ContactsList();
    if (app.Contact().list().size() == 0) {
      app.Contact().create(new ContactData("firstName", "middleName", "lastName", "address", "home", "mobile", "email", app.Contact().groupName()));
    }
  }

  @Test
  public void testContactDeletion() {
    List<ContactData> before = app.Contact().list();
    int index = before.size() - 1;
    app.Contact().delete(index);
    app.goTo().ContactsList();
    List<ContactData> after = app.Contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(after, before);
  }
}
