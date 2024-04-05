package ru.stqa.geometry.figures;

public record Triangle(
        double sideA,
        double sideB,
        double sideC
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(this.sideA, triangle.sideA) == 0 &&
                Double.compare(this.sideB, triangle.sideB) == 0 &&
                Double.compare(this.sideC, triangle.sideC) == 0 ||
                Double.compare(sideA, triangle.sideA) == 0 &&
                        Double.compare(this.sideC, triangle.sideB) == 0 &&
                        Double.compare(this.sideB, triangle.sideC) == 0 ||
                Double.compare(this.sideB, triangle.sideA) == 0 &&
                        Double.compare(this.sideC, triangle.sideB) == 0 &&
                        Double.compare(this.sideA, triangle.sideC) == 0 ||
                Double.compare(this.sideB, triangle.sideA) == 0 &&
                        Double.compare(this.sideA, triangle.sideB) == 0 &&
                        Double.compare(this.sideC, triangle.sideC) == 0 ||
                Double.compare(this.sideC, triangle.sideA) == 0 &&
                        Double.compare(this.sideB, triangle.sideB) == 0 &&
                        Double.compare(this.sideA, triangle.sideC) == 0 ||
                Double.compare(this.sideC, triangle.sideA) == 0 &&
                        Double.compare(this.sideA, triangle.sideB) == 0 &&
                        Double.compare(this.sideB, triangle.sideC) == 0;
    }

    @Override
    public int hashCode() {
        return 1;
    }

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

    public double perimeter() {
        return (sideA + sideB + sideC);
    }

    public double triangleSquare() {
        double p = perimeter() / 2;
        return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }
    
}