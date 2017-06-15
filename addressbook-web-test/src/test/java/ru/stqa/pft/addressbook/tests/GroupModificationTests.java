package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.Group().all().size() == 0) {
      app.Group().create(new GroupData().withName(RandomStringUtils.randomAlphabetic(10)));
    }
  }

  @Test
  public void testGroupModification() {

    Set<GroupData> before = app.Group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.id()).withName(RandomStringUtils.randomAlphabetic(10)).withFooter(RandomStringUtils.randomAlphabetic(10)).withHeader(RandomStringUtils.randomAlphabetic(10));
    app.Group().modify(group);
    Set<GroupData> after = app.Group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }


}
