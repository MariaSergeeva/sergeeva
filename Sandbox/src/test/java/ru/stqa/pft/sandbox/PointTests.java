package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Vadim on 15.05.2017.
 */
public class PointTests {
  @Test
  public void testDistance() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Point p3 = new Point(0, 6);
    Point p4 = new Point(4, 0);
    Point p5 = new Point(1, 1);
    Point p6 = new Point(5, 4);

    Assert.assertEquals(p2.distance(p1), 0.0);
    Assert.assertEquals(p2.distance(p3), 6.0);
    Assert.assertEquals(p2.distance(p4), 4.0);
    Assert.assertEquals(p2.distance(p5), Math.sqrt(2.0));
    Assert.assertEquals(p2.distance(p6), Math.sqrt(41.0));
    Assert.assertEquals(p6.distance(p5), 5.0);
  }
}
