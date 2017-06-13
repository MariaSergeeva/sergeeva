package ru.stqa.pft.addressbook.model;

public class GroupData {
  private final String groupId;
  private final String groupName;
  private final String groupHeader;
  private final String groupFooter;

  public GroupData(String groupName, String groupHeader, String groupFooter) {
    this.groupId = null;
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupFooter = groupFooter;
  }
  public GroupData(String groupId1, String groupName, String groupHeader, String groupFooter) {
    this.groupId = groupId1;
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupFooter = groupFooter;
  }

  public String getGroupName() {
    return groupName;
  }

  public String getGroupHeader() {
    return groupHeader;
  }

  public String getGroupFooter() {
    return groupFooter;
  }
  public String getGroupId() {
    return groupId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (groupId != null ? !groupId.equals(groupData.groupId) : groupData.groupId != null) return false;
    return groupName != null ? groupName.equals(groupData.groupName) : groupData.groupName == null;
  }

  @Override
  public int hashCode() {
    int result = groupId != null ? groupId.hashCode() : 0;
    result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "groupId='" + groupId + '\'' +
            ", groupName='" + groupName + '\'' +
            '}';
  }

}
