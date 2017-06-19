package ru.stqa.pft.addressbook.appmanager;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactsHelper extends HelperBase {

  public ContactsHelper(WebDriver wd) {
    super(wd);
  }

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.firstName());
    type(By.name("middlename"), contactData.middleName());
    type(By.name("lastname"), contactData.lastName());
    type(By.name("address"), contactData.address());
    type(By.name("home"), contactData.homePhone());
    type(By.name("mobile"), contactData.mobilePhone());
    type(By.name("work"), contactData.workPhone());
    type(By.name("email"), contactData.email1());
    type(By.name("email2"), contactData.email2());
    type(By.name("email3"), contactData.email3());
    attach(By.name("photo"), contactData.photo());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initCreation() {
    click(By.linkText("add new"));
  }

  public void create(ContactData contact) {
    initCreation();
    fillForm(contact, true);
    submitCreation();
    contactCache = null;
    goToHomePage();
  }

  public String groupName() {
    new NavigationHelper(wd).GroupPage();
    String groupName;
    if (new GroupsHelper(wd).all().size() == 0) {
      GroupData group = new GroupData().withName(RandomStringUtils.randomAlphabetic(10));
      new GroupsHelper(wd).create(group);
      groupName = group.name();
    } else {
      groupName = new GroupsHelper(wd).all().iterator().next().name();
    }

    return groupName;
  }

  public void submitCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectById(int id) {
    wd.findElement(By.cssSelector(String.format("input[id='%s']", id))).click();
  }

  public void initModificationById(int id) {
    click(By.xpath(String.format("//tr[.//input[@id='%s']]//img[@title='Edit']", id)));
  }

  public void modify(ContactData contact) {
    initModificationById(contact.id());
    fillForm(contact, false);
    submitModification();
    contactCache = null;
    goToHomePage();
  }

  public void submitModification() {
    click(By.name("update"));
  }

  public void switchAlert() {
    wd.switchTo().alert().accept();
  }

  public void goToHomePage() {
    click(By.linkText("home page"));
  }

  public void deleteById(ContactData contact) {
    selectById(contact.id());
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    contactCache = null;
    switchAlert();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[.//input[@name='selected[]']]"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.xpath(".//input[@name='selected[]']")).getAttribute("id"));
      String email = element.findElement(By.xpath(".//input[@name='selected[]']")).getAttribute("accept");
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withEmail1(email).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);

  }

  public ContactData phonesFromEditForm(ContactData contact) {
    initModificationById(contact.id());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withFirstName(firstName).withLastName(lastName).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }

  public ContactData emailsFromEditForm(ContactData contact) {
    initModificationById(contact.id());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withFirstName(firstName).withLastName(lastName).withEmail1(email1).withEmail2(email2).withEmail3(email3);
  }

  public ContactData addressFromEditForm(ContactData contact) {
    initModificationById(contact.id());
    String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    wd.navigate().back();
    return new ContactData().withFirstName(firstName).withLastName(lastName).withAddress(address);
  }
}
