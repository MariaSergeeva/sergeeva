package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.Group().list().size() == 0) {
      app.Group().create(new GroupData(RandomStringUtils.randomAlphabetic(10), null, null));
    }
  }

  @Test
  public void testGroupDeletion() {
    List<GroupData> before = app.Group().list();
    int index = before.size() - 1;
    app.Group().delete(index);
    List<GroupData> after = app.Group().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(after, before);
  }
}
