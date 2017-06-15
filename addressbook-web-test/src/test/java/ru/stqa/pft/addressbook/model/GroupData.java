package ru.stqa.pft.addressbook.model;

public class GroupData {


  private int groupId;
  private final String groupName;
  private final String groupHeader;
  private final String groupFooter;

  public GroupData(String groupName, String groupHeader, String groupFooter) {
    this.groupId = 0;
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupFooter = groupFooter;
  }

  public GroupData(int groupId1, String groupName, String groupHeader, String groupFooter) {
    this.groupId = groupId1;
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupFooter = groupFooter;
  }

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

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }
}
