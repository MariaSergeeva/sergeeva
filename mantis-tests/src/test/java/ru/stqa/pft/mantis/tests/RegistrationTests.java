package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.testng.Assert.*;

public class RegistrationTests extends TestBase {
 @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    UserData testUser = app.registration().testUser();
    //app.james().createUser(user, password);
    app.registration().start(testUser.getUser(), testUser.getEmail());
    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
    //List<MailMessage> mailMessages = app.james().waitForMail(user, password, 180000);
    String confirmationLink = app.registration().findLinc(mailMessages, testUser.getEmail());
    app.registration().finish(confirmationLink, testUser.getPassword());
    assertTrue(app.newSession().login(testUser.getUser(), testUser.getPassword()));
  }



  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
