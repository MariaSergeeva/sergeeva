package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int contactId = 0;
  private String contactFirstName;
  private String contactMiddleName;
  private String contactLastName;
  private String contactAddress;
  private String contactHomeTelephone;
  private String contactMobileTelephone;
  private String contactWorkTelephone;
  private String contactEmail;

  private String contactGroup;

  public int id() {
    return contactId;
  }

  public String middleName() {
    return contactMiddleName;
  }

  public String lastName() {
    return contactLastName;
  }

  public String address() {
    return contactAddress;
  }

  public String homeTelephone() {
    return contactHomeTelephone;
  }

  public String mobileTelephone() {
    return contactMobileTelephone;
  }

  public String workTelephone() {

    return contactWorkTelephone;
  }

  public String email() {
    return contactEmail;
  }

  public String firstName() {
    return contactFirstName;
  }

  public String group() {
    return contactGroup;
  }

  public ContactData withId(int contactId) {
    this.contactId = contactId;
    return this;
  }

  public ContactData withFirstName(String contactFirstName) {
    this.contactFirstName = contactFirstName;
    return this;
  }

  public ContactData withMiddleName(String contactMiddleName) {
    this.contactMiddleName = contactMiddleName;
    return this;
  }

  public ContactData withLastName(String contactLastName) {
    this.contactLastName = contactLastName;
    return this;
  }

  public ContactData withAddress(String contactAddress) {
    this.contactAddress = contactAddress;
    return this;
  }

  public ContactData withHomePhone(String home) {
    this.contactHomeTelephone = home;
    return this;
  }

  public ContactData withMobilePhone(String mobile) {
    this.contactMobileTelephone = mobile;
    return this;
  }

  public ContactData withWorkPhone(String work) {
    this.contactWorkTelephone = work;
    return this;
  }

  public ContactData withEmail(String contactEmail) {
    this.contactEmail = contactEmail;
    return this;
  }

  public ContactData withGroup(String contactGroup) {
    this.contactGroup = contactGroup;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "contactId=" + contactId +
            ", contactFirstName='" + contactFirstName + '\'' +
            ", contactLastName='" + contactLastName + '\'' +
            ", contactEmail='" + contactEmail + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (contactId != that.contactId) return false;
    if (contactFirstName != null ? !contactFirstName.equals(that.contactFirstName) : that.contactFirstName != null)
      return false;
    if (contactLastName != null ? !contactLastName.equals(that.contactLastName) : that.contactLastName != null)
      return false;
    return contactEmail != null ? contactEmail.equals(that.contactEmail) : that.contactEmail == null;
  }

  @Override
  public int hashCode() {
    int result = contactId;
    result = 31 * result + (contactFirstName != null ? contactFirstName.hashCode() : 0);
    result = 31 * result + (contactLastName != null ? contactLastName.hashCode() : 0);
    result = 31 * result + (contactEmail != null ? contactEmail.hashCode() : 0);
    return result;
  }
}
