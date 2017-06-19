package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jcommander = new JCommander(generator);
    try {
      jcommander.parse(args);
    } catch (ParameterException ex){jcommander.usage();
    return;}
    generator.run();

  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%S\n", contact.firstName(), contact.lastName(), contact.address(), contact.email1(), contact.homePhone()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>(count);
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstName(String.format("firstName %s", i)).withLastName(String.format("lastName %s", i))
              .withAddress(String.format("address %s", i)).withEmail1(String.format("email1 %s", i)).withHomePhone(String.format("homePhone %s", i)));
    }
    return contacts;
  }
}
