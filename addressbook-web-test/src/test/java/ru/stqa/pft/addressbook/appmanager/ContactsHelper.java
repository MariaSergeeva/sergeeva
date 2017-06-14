package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getContactFirstName());
        type(By.name("middlename"), contactData.getContactMiddleName());
        type(By.name("lastname"), contactData.getContactLastName());
        type(By.name("address"), contactData.getContactAddress());
        type(By.name("home"), contactData.getContactHomeTelephone());
        type(By.name("mobile"), contactData.getContactMobileTelephone());
        type(By.name("email"), contactData.getContactEmail());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getContactGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void switchAlert() {
        wd.switchTo().alert().accept();
    }

    public void goToHomePage() {
        click(By.linkText("home page"));
    }

    public void initContactModification(String locator) {
        click(By.xpath(locator));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        new NavigationHelper(wd).gotoGroupPage();
        if (!new GroupsHelper(wd).isThereAGroup()) {
            new GroupsHelper(wd).createGroup(new GroupData("name", null, null));
        }
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        goToHomePage();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("selected[]"));
        for (WebElement element : elements) {
            String email = element.getAttribute("accept");
            int id = Integer.parseInt(element.getAttribute("id"));
            ContactData contact = new ContactData(id,null, null, null, null, null, null, email, null);
            contacts.add(contact);
        }
        return contacts;

    }
}
