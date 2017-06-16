package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstName(RandomStringUtils.randomAlphabetic(10)).withMiddleName(null).withLastName(RandomStringUtils.randomAlphabetic(10)).withAddress(null).withHomePhone(null).withMobilePhone(null).withEmail1(RandomStringUtils.randomAlphabetic(10)).withGroup(app.contact().groupName());
    app.contact().create(contact);
    assertEquals(app.contact().count(), (before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.id()).max().getAsInt()))));
  }


}
