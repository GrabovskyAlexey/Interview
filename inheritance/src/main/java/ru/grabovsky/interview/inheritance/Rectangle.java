package ru.grabovsky.interview.inheritance;

public class Rectangle extends Figure {
    private final double sideA;
    private final double sideB;

    public Rectangle(final double sideA, final double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public double calculatePerimeter() {
        return sideA * 2 + sideB * 2;
    }

    @Override
    public double calculateArea() {
        return sideA * sideB;
    }
}
