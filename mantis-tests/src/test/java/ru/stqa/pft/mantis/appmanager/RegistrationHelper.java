package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.testng.Assert.assertTrue;

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

  public void createUser(UserData user) throws IOException {
    app.registration().start(user.getUser(), user.getEmail());
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    String confirmationLink = findLinc(mailMessages, user.getEmail());
    app.registration().finish(confirmationLink, user.getPassword());
    assertTrue(app.newSession().login(user.getUser(), user.getPassword()));
    System.out.println(mailMessages);
  }

  public String findLinc(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public UserData testUser() {
    long now = System.currentTimeMillis();
    String user = String.format("user-%s", now);
    String password = "password";
    String email = String.format("user-%s@localhost.localdomain", now);
    return new UserData().setUser(user).setEmail(email).setPassword(password);
  }
}
