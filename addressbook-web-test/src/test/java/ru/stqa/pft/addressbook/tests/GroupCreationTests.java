package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().GroupPage();
    Set<GroupData> before = app.Group().all();
    GroupData group = new GroupData().withName(RandomStringUtils.randomAlphabetic(10)).withFooter(RandomStringUtils.randomAlphabetic(10)).withHeader(RandomStringUtils.randomAlphabetic(10));
    app.Group().create(group);
    Set<GroupData> after = app.Group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt((g) -> g.id()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
