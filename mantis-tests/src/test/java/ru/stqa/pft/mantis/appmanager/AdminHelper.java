package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.mantis.model.UserData;

public class AdminHelper extends HelperBase{
  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String login, String password){
    wd.get(app.getProperty("web.baseURL") + "/login_page.php");
    type(By.name("username"), login);
    click(By.xpath(".//input[@value = 'Войти']"));
    type(By.name("password"), password);
    click(By.xpath(".//input[@value = 'Войти']"));
  }

  public void userList() {
    click(By.xpath(".//i[@class = 'menu-icon fa fa-gears']"));
    click(By.xpath(".//a[@href = '/mantisbt-2.5.1/manage_user_page.php']"));
    click(By.linkText("E-mail"));
    click(By.linkText("E-mail"));
  }

  public void resetPassword(UserData testUser) {
    click(By.xpath(String.format(".//a[@href = 'manage_user_edit_page.php?user_id=%s']", testUser.getId())));
    click(By.xpath(".//input[@value = 'Сбросить пароль']"));
  }
}
