package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Users;

/**
 * Created by sergeevam on 10.07.2017.
 */
public class DbTest extends TestBase{
  @Test
  public void testDb(){
    Users users = app.db().users();
    System.out.println(users);
  }
}
