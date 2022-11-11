package ru.grabovsky.interview.inheritance;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(2.0);
        Triangle triangle = new Triangle(2.0, 3.0, 4.0);
        Square square = new Square(4.0);
        Figure rectangle = new Rectangle(2., 4.);

        printFigureInfo(circle);
        printFigureInfo(triangle);
        printFigureInfo(rectangle);
        printFigureInfo(square);
    }

    static void printFigureInfo(Figure figure){
        System.out.println("Площадь фигуры - " + figure.calculateArea() );
        System.out.println("Периметр фигуры - " + figure.calculatePerimeter());
    }
}
