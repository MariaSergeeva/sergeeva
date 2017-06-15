package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupsHelper().isThereAGroup()) {
      app.getGroupsHelper().createGroup(new GroupData("name", null, null));
    }
  }

  @Test
  public void testGroupModification() {

    List<GroupData> before = app.getGroupsHelper().getGroupList();
    int index = before.size() - 1;
    GroupData group = new GroupData(before.get(index).getGroupId(), "name1", "header1", "footer1");
    app.getGroupsHelper().modifyGroup(index, group);
    List<GroupData> after = app.getGroupsHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);

    Comparator<? super GroupData> byId = ((o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
