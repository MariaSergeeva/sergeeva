package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(RandomStringUtils.randomAlphabetic(10), "middleName", RandomStringUtils.randomAlphabetic(10), "address", "home", "mobile", RandomStringUtils.randomAlphabetic(10), "name");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setContactId(after.stream().max((o1, o2) -> Integer.compare(o1.getContactId(), o2.getContactId())).get().getContactId());
    before.add(contact);

    Comparator<? super ContactData> byId = ((o1, o2) -> Integer.compare(o1.getContactId(), o2.getContactId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
