package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().ContactsList();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName(RandomStringUtils.randomAlphabetic(10)).withLastName(RandomStringUtils.randomAlphabetic(10))
              .withEmail1("qqq@www.ee").withEmail2("aaa@sss.dd").withEmail3("zzz@xxx.cc"));
    }
  }
  @Test
  public void testContactEmails() {
    app.goTo().ContactsList();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().emailsFromEditForm(contact);
    assertThat(contact.allEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.email1(), contact.email2(), contact.email3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
