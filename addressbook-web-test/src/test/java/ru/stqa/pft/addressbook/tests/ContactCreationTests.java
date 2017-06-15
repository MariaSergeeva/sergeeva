package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.Contact().all();

    ContactData contact = new ContactData().withFirstName(RandomStringUtils.randomAlphabetic(10)).withMiddleName(null).withLastName(RandomStringUtils.randomAlphabetic(10)).withAddress(null).withHomeTelephone(null).withMobileTelephone(null).withEmail(RandomStringUtils.randomAlphabetic(10)).withGroup(app.Contact().groupName());
    app.Contact().create(contact);
    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((g) -> g.id()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
