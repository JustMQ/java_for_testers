package ru.stqa.geometry.figures;

import org.junit.jupiter.api.*;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var V = new Triangle();
        boolean verification = V.triangleVerification();
        if (V.triangleVerification() == false) {
            Assertions.fail("The existence of such a triangle is impossible.");
        }
        var S = new Triangle();
        double result = S.triangleSquare();
        String text = String.format("Area of a triangle with sides %f , %f and %f = %f",
                Triangle.sideA, Triangle.sideB, Triangle.sideC, S.triangleSquare());
        System.out.println(text);

    }

    @Test
    void canCalculatePerimeter() {
        var V = new Triangle();
        boolean verification = V.triangleVerification();
        if (V.triangleVerification() == false) {
            Assertions.fail("The existence of such a triangle is impossible.");
        }
        var P = new Triangle();
        double result = P.perimeter();
        String text = String.format("Perimeter of a triangle with sides %f , %f and %f = %f",
                Triangle.sideA, Triangle.sideB, Triangle.sideC, P.perimeter());
        System.out.println(text);
    }
}

