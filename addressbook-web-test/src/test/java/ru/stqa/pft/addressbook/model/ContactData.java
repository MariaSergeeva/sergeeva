package ru.stqa.pft.addressbook.model;

public class ContactData {
  private int contactId;
  private final String contactFirstName;
  private final String contactMiddleName;
  private final String contactLastName;
  private final String contactAddress;
  private final String contactHomeTelephone;
  private final String contactMobileTelephone;
  private final String contactEmail;
  private final String contactGroup;

  public ContactData(String contactFirstName, String contactMiddleName, String contactLastName, String contactAddress, String contactHomeTelephone, String contactMobileTelephone, String contactEmail, String contactGroup) {
    this.contactId = 0;
    this.contactFirstName = contactFirstName;
    this.contactMiddleName = contactMiddleName;
    this.contactLastName = contactLastName;
    this.contactAddress = contactAddress;
    this.contactHomeTelephone = contactHomeTelephone;
    this.contactMobileTelephone = contactMobileTelephone;
    this.contactEmail = contactEmail;
    this.contactGroup = contactGroup;
  }

  public ContactData(int contactId, String contactFirstName, String contactMiddleName, String contactLastName, String contactAddress, String contactHomeTelephone, String contactMobileTelephone, String contactEmail, String contactGroup) {
    this.contactId = contactId;
    this.contactFirstName = contactFirstName;
    this.contactMiddleName = contactMiddleName;
    this.contactLastName = contactLastName;
    this.contactAddress = contactAddress;
    this.contactHomeTelephone = contactHomeTelephone;
    this.contactMobileTelephone = contactMobileTelephone;
    this.contactEmail = contactEmail;
    this.contactGroup = contactGroup;
  }

  public int getContactId() {
    return contactId;
  }

  public String getContactMiddleName() {
    return contactMiddleName;
  }

  public String getContactLastName() {
    return contactLastName;
  }

  public String getContactAddress() {
    return contactAddress;
  }

  public String getContactHomeTelephone() {
    return contactHomeTelephone;
  }

  public String getContactMobileTelephone() {
    return contactMobileTelephone;
  }

  public String getContactEmail() {
    return contactEmail;
  }

  public String getContactFirstName() {
    return contactFirstName;
  }

  public String getContactGroup() {
    return contactGroup;
  }

  public void setContactId(int contactId) {
    this.contactId = contactId;
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
