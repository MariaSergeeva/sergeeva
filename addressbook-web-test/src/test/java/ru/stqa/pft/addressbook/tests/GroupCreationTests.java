package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupsHelper().getGroupList();
        GroupData group = new GroupData("name", "header", "footer");
        app.getGroupsHelper().createGroup(group);
        List<GroupData> after = app.getGroupsHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.setGroupId(after.stream().max((o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId())).get().getGroupId());
        before.add(group);

        Comparator<? super GroupData> byId = ((o1, o2) -> Integer.compare(o1.getGroupId(), o2.getGroupId()));
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
