package ru.stqa.pft.sandbox;

public class MyFirstProject {
  public static void main(String[] args) {
    hello("Any");
    hello("Maria");

    Square s = new Square(7);
    //s.l = 8;
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(5, 6);
    //r.a = 8;
    //r.b = 3;
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");

  }

}