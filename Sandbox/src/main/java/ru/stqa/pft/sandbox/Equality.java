package ru.stqa.pft.sandbox;

import java.util.Objects;

/**
 * Created by Vadim on 28.05.2017.
 */
public class Equality {
  public static void main(String[] args) {
    String s1 = "firefox 2.0";
    String s2 = "firefox "+ Math.sqrt(4.0);
    System.out.println(s1 == s2);
    System.out.println(Objects.equals(s1, s2));
  }
}
