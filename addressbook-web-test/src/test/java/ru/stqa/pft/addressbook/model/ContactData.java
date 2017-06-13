package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String contactFirstName;
    private final String contactMiddleName;
    private final String contactLastName;
    private final String contactAddress;
    private final String contactHomeTelephone;
    private final String contactMobileTelephone;
    private final String contactEmail;
    private final String contactGroup;

    public ContactData(String contactFirstName, String contactMiddleName, String contactLastName, String contactAddress, String contactHomeTelephone, String contactMobileTelephone, String contactEmail, String contactGroup) {
        this.contactFirstName = contactFirstName;
        this.contactMiddleName = contactMiddleName;
        this.contactLastName = contactLastName;
        this.contactAddress = contactAddress;
        this.contactHomeTelephone = contactHomeTelephone;
        this.contactMobileTelephone = contactMobileTelephone;
        this.contactEmail = contactEmail;
        this.contactGroup = contactGroup;
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

    @Override
    public String toString() {
        return "ContactData{" +
                "contactFirstName='" + contactFirstName + '\'' +
                ", contactLastName='" + contactLastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (contactFirstName != null ? !contactFirstName.equals(that.contactFirstName) : that.contactFirstName != null)
            return false;
        return contactLastName != null ? contactLastName.equals(that.contactLastName) : that.contactLastName == null;
    }

    @Override
    public int hashCode() {
        int result = contactFirstName != null ? contactFirstName.hashCode() : 0;
        result = 31 * result + (contactLastName != null ? contactLastName.hashCode() : 0);
        return result;
    }
}
