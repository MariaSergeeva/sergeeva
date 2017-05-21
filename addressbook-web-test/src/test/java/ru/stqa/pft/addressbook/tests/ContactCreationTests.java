package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{
  FirefoxDriver wd;


  @Test
  public void testContactCreation() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("firstName","middleName", "lastName", "address", "home", "mobile", "email"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().goToContactsList();
  }




}
