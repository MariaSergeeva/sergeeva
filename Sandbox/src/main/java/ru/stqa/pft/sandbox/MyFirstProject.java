package ru.stqa.pft.sandbox;

public class MyFirstProject {
	public static void main(String[] args) {
		hello ("Any");
		hello ("Maria");

		double length = 8;
		double hight = 3;
		System.out.println("Площадь квадрата со стороной " + length + " = " + area(length));
		System.out.println("Площадь прямоугольника со сторонами " + length + " и " + hight + " = " + area(length, hight));
	}
	public static void hello (String somebody){
		System.out.println("Hello " + somebody + "!");

	}
	public static double area (double l){
		return l * l;
	}
	public static double area(double l, double h){
		return l * h;
	}
}