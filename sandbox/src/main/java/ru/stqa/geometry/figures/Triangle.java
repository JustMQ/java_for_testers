package ru.stqa.geometry.figures;

public record Triangle(
        double sideA,
        double sideB,
        double sideC
) {

    public Triangle {
        if (sideA < 0 || sideB < 0 || sideC < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }

       if (sideA + sideB < sideC ||
                sideB + sideC < sideA ||
                sideA + sideC < sideB) {
            throw new IllegalArgumentException("The existence of such a triangle is impossible");
        }
    }

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