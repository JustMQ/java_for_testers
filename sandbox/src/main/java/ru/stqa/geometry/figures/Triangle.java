package ru.stqa.geometry.figures;

public class Triangle {

    static double sideA = 10.0;
    static double sideB = 8.0;
    static double sideC = 12.8;
    static double triangleP = 30.8;
    static double triangleA = 39.99;

    public boolean triangleVerification() {
        return  (Triangle.sideA + Triangle.sideB > Triangle.sideC &&
                Triangle.sideB + Triangle.sideC > Triangle.sideA &&
                Triangle.sideA + Triangle.sideC > Triangle.sideB);
    }

    public double perimeter() {
        return (sideA + sideB + sideC);
    }

    public double triangleSquare() {
        double p = perimeter()/2;
        return Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC));
    }


}