package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {

  UserData testUser;
  List<MailMessage> regMessages;
  List<MailMessage> resetMessages;
  MailMessage regMessage;

  @BeforeMethod
  public void en() throws IOException {
    app.mail().start();

    testUser = app.registration().testUser();
    app.registration().start(testUser.getUser(), testUser.getEmail());
    regMessages = app.mail().waitForMail(2, 10000);
    System.out.println("regMessages: " + regMessages.stream().map((m) -> m.text).iterator());
    regMessage = regMessages.stream().filter((m) -> m.to.equals(testUser.getEmail())).findAny().get();
    String confirmationLink = app.registration().findLinc(regMessage);
    app.registration().setPassword(confirmationLink, testUser.getPassword());
    assertTrue(app.newSession().login(testUser.getUser(), testUser.getPassword()));

    testUser.setUserId(app.db().users().stream().filter((u) -> (u.getUser().equals(testUser.getUser()))).iterator().next().getId());
    app.admin().login("administrator", "root");
    app.admin().userList();
    app.admin().resetPassword(testUser);
    resetMessages = app.mail().waitForMail(3, 10000);

  }

  @Test
  public void testResetPassword() throws IOException {
    assertFalse(app.newSession().login(testUser.getUser(), testUser.getPassword()));
    MailMessage resetMessage = resetMessages.stream().filter((m) -> m.to.equals(testUser.getEmail())).filter((m) -> (!m.text.equals(regMessage.text))).iterator().next();
    System.out.println("regMessage: " + regMessage.text);
    System.out.println("resetMessage: " + resetMessage.text);
    String confirmationLink = app.registration().findLinc(resetMessage);
    String newPassword = testUser.getPassword() + "_new";
    app.registration().setPassword(confirmationLink, newPassword);
    assertFalse(app.newSession().login(testUser.getUser(), testUser.getPassword()));
    assertTrue(app.newSession().login(testUser.getUser(), newPassword));
  }

  @AfterMethod(alwaysRun = true)
  public void stop() {
    app.mail().stop();
  }

}
