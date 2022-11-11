package ru.grabovsky.interview.inheritance;

public class Square extends Figure {
    private final double side;

    public Square(final double side) {
        this.side = side;
    }

    @Override
    public double calculatePerimeter() {
        return side * 4;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }
}
