package ru.stqa.geometry.figures;

import org.junit.jupiter.api.*;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        var V = new Triangle(3, 4, 5);
        if (!V.triangleVerification()) {
            Assertions.fail("The existence of such a triangle is impossible.");
        }
        double result = V.triangleSquare();
        Assertions.assertEquals(6, result);

    }

    @Test
    void canCalculatePerimeter() {
        var V = new Triangle(3, 4, 5);
        if (!V.triangleVerification()) {
            Assertions.fail("The existence of such a triangle is impossible.");
        }
        double result = V.perimeter();
        Assertions.assertEquals(12, result);
    }
}
