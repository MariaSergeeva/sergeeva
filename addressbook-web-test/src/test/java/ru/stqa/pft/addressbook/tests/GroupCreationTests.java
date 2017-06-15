package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().GroupPage();
    List<GroupData> before = app.Group().list();
    GroupData group = new GroupData(RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10));
    app.Group().create(group);
    List<GroupData> after = app.Group().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.setGroupId(after.stream().max((o1, o2) -> Integer.compare(o1.id(), o2.id())).get().id());
    before.add(group);

    Comparator<? super GroupData> byId = ((o1, o2) -> Integer.compare(o1.id(), o2.id()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
