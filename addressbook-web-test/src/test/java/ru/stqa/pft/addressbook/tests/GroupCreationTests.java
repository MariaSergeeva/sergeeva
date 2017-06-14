package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before = app.getGroupsHelper().getGroupList();
        GroupData group = new GroupData("name1", "header1", "footer1");
        app.getGroupsHelper().createGroup(group);
        List<GroupData> after = app.getGroupsHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int max = 0;
        for (GroupData g : after) {
            if (g.getGroupId() > max) {
                max = g.getGroupId();
            }
        }
        group.setGroupId(max);
        before.add(group);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
    }

}
