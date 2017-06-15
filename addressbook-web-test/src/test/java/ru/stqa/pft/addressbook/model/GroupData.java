package ru.stqa.pft.addressbook.model;

public class GroupData {


  private int groupId = 0;
  private String groupName;
  private String groupHeader;
  private String groupFooter;

  public String name() {
    return groupName;
  }

  public String header() {
    return groupHeader;
  }

  public String footer() {
    return groupFooter;
  }

  public int id() {
    return groupId;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "groupId='" + groupId + '\'' +
            ", groupName='" + groupName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (groupId != groupData.groupId) return false;
    return groupName != null ? groupName.equals(groupData.groupName) : groupData.groupName == null;
  }

  @Override
  public int hashCode() {
    int result = groupId;
    result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
    return result;
  }

  public GroupData withId(int groupId) {
    this.groupId = groupId;
    return this;
  }

  public GroupData withName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public GroupData withHeader(String groupHeader) {
    this.groupHeader = groupHeader;
    return this;
  }

  public GroupData withFooter(String groupFooter) {
    this.groupFooter = groupFooter;
    return this;
  }
}
