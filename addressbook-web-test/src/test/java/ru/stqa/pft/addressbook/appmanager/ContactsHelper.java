package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsHelper extends HelperBase {

  public ContactsHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getContactFirstName());
    type(By.name("middlename"), contactData.getContactMiddleName());
    type(By.name("lastname"), contactData.getContactLastName());
    type(By.name("address"), contactData.getContactAddress());
    type(By.name("home"), contactData.getContactHomeTelephone());
    type(By.name("mobile"), contactData.getContactMobileTelephone());
    type(By.name("email"), contactData.getContactEmail());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

}
