package ru.stqa.geometry.figures;

import org.junit.jupiter.api.*;

public class SquareTests {

    @Test
    void canCalculateArea(){
        double actual = Square.area(5.0);
        Assertions.assertEquals(25.0, actual);
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(20.0, Square.perimeter(5.0));
    }
}
