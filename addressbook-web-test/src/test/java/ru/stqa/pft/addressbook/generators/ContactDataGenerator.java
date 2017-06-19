package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%S\n", contact.firstName(), contact.lastName(), contact.address(), contact.email1(), contact.homePhone()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>(count);
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("firstName %s", i)).withLastName(String.format("lastName %s", i))
              .withAddress(String.format("address %s", i)).withEmail1(String.format("email1 %s", i)).withHomePhone(String.format("homePhone %s", i)));
    }
    return contacts;
  }
}
