package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToContactsList();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("firstName", "middleName", "lastName", "address", "home", "mobile", "email", "name"));
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.getContactHelper().getContactList();
    String locator = "//a[@href='edit.php?id=" + before.get(before.size() - 1).getContactId() + "']/img";
    app.getContactHelper().initContactModification(locator);
    ContactData contact = new ContactData(before.get(before.size() - 1).getContactId(), RandomStringUtils.randomAlphabetic(10), null, RandomStringUtils.randomAlphabetic(10), null, null, null, RandomStringUtils.randomAlphabetic(10), null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getContactId(), o2.getContactId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
