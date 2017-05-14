package ru.stqa.pft.sandbox;

/**
 * Created by Vadim on 14.05.2017.
 */
public class Point {
  public double x;
  public double y;
  public Point (double x, double y){
    this.x = x;
    this.y = y;
  }
  public double distance(Point p1) {
    double result = Math.sqrt(((p1.x - this.x) * (p1.x - this.x)) + ((p1.y - this.y) * (p1.y - this.y)));
    return result;
  }
}
