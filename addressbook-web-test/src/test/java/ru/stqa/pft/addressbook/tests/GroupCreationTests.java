package ru.stqa.pft.addressbook.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().GroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName(RandomStringUtils.randomAlphabetic(10)).withFooter(RandomStringUtils.randomAlphabetic(10)).withHeader(RandomStringUtils.randomAlphabetic(10));
    app.group().create(group);
    Groups after = app.group().all();
    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.id()).max().getAsInt()))));
  }

}
