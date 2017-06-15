package ru.stqa.pft.addressbook.tests;

import com.google.common.base.Objects;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation() {
    Contacts before = app.Contact().all();

    ContactData contact = new ContactData().withFirstName(RandomStringUtils.randomAlphabetic(10)).withMiddleName(null).withLastName(RandomStringUtils.randomAlphabetic(10)).withAddress(null).withHomeTelephone(null).withMobileTelephone(null).withEmail(RandomStringUtils.randomAlphabetic(10)).withGroup(app.Contact().groupName());
    app.Contact().create(contact);
    Contacts after = app.Contact().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.id()).max().getAsInt()))));
  }


}
