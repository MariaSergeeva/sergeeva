package ru.stqa.pft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
@javax.persistence.Table(name = "mantis_user_table")
public class UserData {
  @Id
  @Column(name = "id")
  private int userId;

  @Column(name = "username")
  @Type(type = "string")
  private String user;

  @Column(name = "email")
  @Type(type = "string")
  private String email;

  @Transient
  private String userPassword;

  public int getUserId() {
    return userId;
  }

  public String getUser() {
    return user;
  }

  public String getEmail() {
    return email;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public UserData setUserId(int userId) {
    this.userId = userId;
    return this;
  }

  public UserData setUser(String user) {
    this.user = user;
    return this;
  }

  public UserData setEmail(String email) {
    this.email = email;
    return this;
  }

  public UserData setUserPassword(String userPassword) {
    this.userPassword = userPassword;
    return this;
  }
}
