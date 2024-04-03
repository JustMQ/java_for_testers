package ru.stqa.geometry.figures;

public record Triangle(
        double sideA,
        double sideB,
        double sideC
) {

    public boolean triangleVerification() {
        return (sideA + sideB > sideC &&
                sideB + sideC > sideA &&
                sideA + sideC > sideB);
    }

    public double perimeter() {
        return (sideA + sideB + sideC);
    }

    public double triangleSquare() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }


}