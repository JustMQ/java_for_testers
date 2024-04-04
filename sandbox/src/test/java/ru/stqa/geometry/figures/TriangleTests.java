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

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-3.0, 4.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateImpossibleTriangle() {
        try {
            new Triangle(30.0, 4.0, 5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void testTriangleEquality() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testTriangleEquality2() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(5.0, 3.0, 4.0);
        Assertions.assertEquals(t1, t2);
    }
}

