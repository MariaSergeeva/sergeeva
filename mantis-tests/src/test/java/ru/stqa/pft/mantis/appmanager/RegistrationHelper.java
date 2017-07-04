package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.appmanager.HelperBase;

import java.io.UnsupportedEncodingException;

public class RegistrationHelper extends HelperBase {


  public RegistrationHelper(WebDriver wd) {
    super((ApplicationManager) wd);
  }

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) throws UnsupportedEncodingException {
    wd.get(app.getProperty("web.baseURL") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.xpath(".//input[@value = 'Зарегистрироваться']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.xpath(".//button[@type='submit']"));
  }
}
