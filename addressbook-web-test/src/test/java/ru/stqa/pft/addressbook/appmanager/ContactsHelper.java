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

  public void selectContact() {
    click(By.name("selected[]"));
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

  public void initContactModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }
}
