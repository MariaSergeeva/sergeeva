package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
@Entity
@javax.persistence.Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int contactId = 0;

  @Expose
  @Column(name = "firstname")
  private String contactFirstName;

  @Column(name = "middlename", nullable = true)
  private String contactMiddleName;

  @Expose
  @Column(name = "lastname")
  private String contactLastName;

  @Expose
  @Column(name = "address", nullable = true)
  @Type(type = "text")
  private String contactAddress;

  @Expose
  @Column(name = "home", nullable = true)
  @Type(type = "text")
  private String contactHomeTelephone;

  @Column(name = "mobile", nullable = true)
  @Type(type = "text")
  private String contactMobileTelephone;

  @Column(name = "work", nullable = true)
  @Type(type = "text")
  private String contactWorkTelephone;

  @Transient
  private String contactAllPhones;

  @Expose
  @Column(name = "email", nullable = true)
  @Type(type = "text")
  private String contactEmail1;

  @Column(name = "email2", nullable = true)
  @Type(type = "text")
  private String contactEmail2;

  @Column(name = "email3", nullable = true)
  @Type(type = "text")
  private String contactEmail3;

  @Transient
  private String contactAllEmails;

  @Transient
  private String contactGroup;

  @Column(name = "photo", nullable = true)
  @Type(type = "text")
  private String contactPhoto;

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

  public String homePhone() {
    return contactHomeTelephone;
  }

  public String mobilePhone() {
    return contactMobileTelephone;
  }

  public String workPhone() {

    return contactWorkTelephone;
  }

  public String allPhones() {
    return contactAllPhones;
  }

  public String email1() {
    return contactEmail1;
  }

  public String email2() {
    return contactEmail2;
  }

  public String email3() {
    return contactEmail3;
  }

  public String allEmails() {
    return contactAllEmails;
  }

  public String firstName() {
    return contactFirstName;
  }

  public String group() {
    return contactGroup;
  }

  public File photo() {
    try {
      return new File(contactPhoto);
    } catch (NullPointerException ex) {
      return null;
    }
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

  public ContactData withAllPhones(String contactAllPhones) {
    this.contactAllPhones = contactAllPhones;
    return this;
  }

  public ContactData withEmail1(String contactEmail) {
    this.contactEmail1 = contactEmail;
    return this;
  }

  public ContactData withEmail2(String contactEmail) {
    this.contactEmail2 = contactEmail;
    return this;
  }

  public ContactData withEmail3(String contactEmail) {
    this.contactEmail3 = contactEmail;
    return this;
  }

  public ContactData withAllEmails(String contactAllEmails) {
    this.contactAllEmails = contactAllEmails;
    return this;
  }

  public ContactData withGroup(String contactGroup) {
    this.contactGroup = contactGroup;
    return this;
  }

  public ContactData withPhoto(File contactPhoto) {
    this.contactPhoto = contactPhoto.getPath();
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "contactId=" + contactId +
            ", contactFirstName='" + contactFirstName + '\'' +
            ", contactLastName='" + contactLastName + '\'' +
            ", contactEmail1='" + contactEmail1 + '\'' +
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
    return contactEmail1 != null ? contactEmail1.equals(that.contactEmail1) : that.contactEmail1 == null;
  }

  @Override
  public int hashCode() {
    int result = contactId;
    result = 31 * result + (contactFirstName != null ? contactFirstName.hashCode() : 0);
    result = 31 * result + (contactLastName != null ? contactLastName.hashCode() : 0);
    result = 31 * result + (contactEmail1 != null ? contactEmail1.hashCode() : 0);
    return result;
  }

}
