package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.Group().all().size() == 0) {
      app.Group().create(new GroupData().withName(RandomStringUtils.randomAlphabetic(10)));
    }
  }

  @Test
  public void testGroupDeletion() {
    Set<GroupData> before = app.Group().all();
    GroupData deletedGroup = before.iterator().next();
    app.Group().delete(deletedGroup);
    Set<GroupData> after = app.Group().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);
    Assert.assertEquals(after, before);
  }
}
