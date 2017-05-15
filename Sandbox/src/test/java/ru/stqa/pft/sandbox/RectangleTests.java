package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Vadim on 15.05.2017.
 */
public class RectangleTests {
  @Test
  public void testArea() {
    Rectangle r = new Rectangle(3, 8);
    Assert.assertEquals(r.area(), 24.0);
  }
}
