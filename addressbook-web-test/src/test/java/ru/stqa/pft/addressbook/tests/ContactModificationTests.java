package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToContactsList();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("firstName1","middleName1", "lastName1", "address1", "home1", "mobile1", "email1"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goToHomePage();
  }
}
