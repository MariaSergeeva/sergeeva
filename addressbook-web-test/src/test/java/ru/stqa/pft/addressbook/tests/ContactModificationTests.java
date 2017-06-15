package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().ContactsList();
    if (app.Contact().list().size() == 0) {
      app.Contact().create(new ContactData("firstName", "middleName", "lastName", "address", "home", "mobile", "email", app.Contact().groupName()));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.Contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).id(), RandomStringUtils.randomAlphabetic(10), null, RandomStringUtils.randomAlphabetic(10), null, null, null, RandomStringUtils.randomAlphabetic(10), null);
    app.Contact().modify(before.size(), contact);
    List<ContactData> after = app.Contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);

    Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.id(), o2.id()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
