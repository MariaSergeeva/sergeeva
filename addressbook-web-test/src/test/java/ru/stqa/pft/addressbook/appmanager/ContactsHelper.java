package ru.stqa.pft.addressbook.appmanager;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.firstName());
    type(By.name("middlename"), contactData.middleName());
    type(By.name("lastname"), contactData.lastName());
    type(By.name("address"), contactData.address());
    type(By.name("home"), contactData.homeTelephone());
    type(By.name("mobile"), contactData.mobileTelephone());
    type(By.name("email"), contactData.email());
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
    goToHomePage();
  }

  public String groupName() {
    new NavigationHelper(wd).GroupPage();
    String groupName;
    if (new GroupsHelper(wd).list().size() == 0) {
      GroupData group = new GroupData().withName(RandomStringUtils.randomAlphabetic(10));
      new GroupsHelper(wd).create(group);
      groupName = group.name();
    } else {
      groupName = new GroupsHelper(wd).list().get(0).name();
    }

    return groupName;
  }

  public void submitCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initModification(int index) {
    String locator = "//tr[./td[./input[@name='selected[]']]][" + index + "]//img[@title='Edit']";
    click(By.xpath(locator));
  }

  public void modify(int index, ContactData contact) {
    initModification(index);
    fillForm(contact, false);
    submitModification();
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

  public void delete(int index) {
    selectContact(index);
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    switchAlert();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[./td[./input[@name='selected[]']]]"));
    int count = 2;
    for (WebElement element : elements) {
      String locator1 = "//tr[" + count + "]/td/input[@name='selected[]']";
      String locator2 = "//tr[" + count + "]/td[2]";
      String locator3 = "//tr[" + count + "]/td[3]";
      int id = Integer.parseInt(element.findElement(By.xpath(locator1)).getAttribute("id"));
      String email = element.findElement(By.xpath(locator1)).getAttribute("accept");
      String lastName = element.findElement(By.xpath(locator2)).getText();
      String firstName = element.findElement(By.xpath(locator3)).getText();
      ContactData contact = new ContactData(id, firstName, null, lastName, null, null, null, email, null);
      contacts.add(contact);
      count++;
    }
    return contacts;

  }
}
