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
}
