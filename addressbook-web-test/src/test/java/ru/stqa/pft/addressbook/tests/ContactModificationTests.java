package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToContactsList();
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("firstName", "middleName", "lastName", "address", "home", "mobile", "email", "name"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before - 1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("firstName", "middleName", "lastName", "address1", "home1", "mobile1", "email1", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().goToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);

    }
}
