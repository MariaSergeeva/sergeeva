package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/pikachu.png");
    list.add(new Object[]{new ContactData().withFirstName("firstName 1").withLastName("lastName 1").withAddress("address 1").withEmail1("email 1").withHomePhone("homePhone 1").withPhoto(photo).withGroup(app.contact().groupName())});
    list.add(new Object[]{new ContactData().withFirstName("firstName 2").withLastName("lastName 2").withAddress("address 2").withEmail1("email 2").withHomePhone("homePhone 2").withPhoto(photo).withGroup(app.contact().groupName())});
    list.add(new Object[]{new ContactData().withFirstName("firstName 3").withLastName("lastName 3").withAddress("address 3").withEmail1("email 3").withHomePhone("homePhone 3").withPhoto(photo).withGroup(app.contact().groupName())});
    return list.iterator();
  }


  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertEquals(app.contact().count(), (before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.id()).max().getAsInt()))));
  }
}
