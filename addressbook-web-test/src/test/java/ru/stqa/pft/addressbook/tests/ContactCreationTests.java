package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("firstName", "middleName", "lastName", "address", "home", "mobile", "email", "name"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().goToHomePage();
    }


}
