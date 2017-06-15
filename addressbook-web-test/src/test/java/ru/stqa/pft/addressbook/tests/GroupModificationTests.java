package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.Group().list().size() == 0) {
      app.Group().create(new GroupData().withName(RandomStringUtils.randomAlphabetic(10)));
    }
  }

  @Test
  public void testGroupModification() {

    List<GroupData> before = app.Group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData().withId(before.get(index).id()).withName(RandomStringUtils.randomAlphabetic(10)).withFooter(RandomStringUtils.randomAlphabetic(10)).withHeader(RandomStringUtils.randomAlphabetic(10));
    app.Group().modify(index, group);
    List<GroupData> after = app.Group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);

    Comparator<? super GroupData> byId = ((o1, o2) -> Integer.compare(o1.id(), o2.id()));
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
