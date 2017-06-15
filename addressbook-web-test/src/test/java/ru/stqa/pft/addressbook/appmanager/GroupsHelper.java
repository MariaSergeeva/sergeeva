package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupsHelper extends HelperBase {

  private GroupData group;

  public GroupsHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void fillForm(GroupData groupData) {
    type(By.name("group_name"), groupData.name());
    type(By.name("group_header"), groupData.header());
    type(By.name("group_footer"), groupData.footer());
  }

  public void initCreation() {
    click(By.name("new"));
  }

  public void create(GroupData group) {
    initCreation();
    fillForm(group);
    submitCreation();
    returnToGroupPage();
  }

  public void submitCreation() {
    click(By.name("submit"));
  }

  public void select(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void delete(int index) {
    select(index);
    click(By.name("delete"));
    returnToGroupPage();
  }

  public void initModification() {
    click(By.name("edit"));
  }

  public void modify(int index, GroupData group) {
    select(index);
    initModification();
    fillForm(group);
    submitModification();
    returnToGroupPage();
  }

  public void submitModification() {
    click(By.name("update"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }
}
