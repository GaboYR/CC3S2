package actividad.mutaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void testSubtract() {
        Calculator calc = new Calculator();
        assertEquals(1, calc.subtract(3, 2));
    }

    @Test
    void testMultiply() {
        Calculator calc = new Calculator();
        assertEquals(6, calc.multiply(2, 3));
    }

    @Test
    void testDivide() {
        Calculator calc = new Calculator();
        //assertEquals(2, calc.divide(6, 3));
        assertThat(calc.divide(6, 3)).isEqualTo(2);
    }

    @Test
    void testDivideByZero() {
        Calculator calc = new Calculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calc.divide(1, 0));
        assertEquals("Divisor cannot be zero", exception.getMessage());
    }

    @Test
    void testAddWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-1, calc.add(-2, 1));
    }

    @Test
    void testSubtractWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-1, calc.subtract(-2, -1));
    }

    @Test
    void testMultiplyWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-6, calc.multiply(-2, 3));
    }

    @Test
    void testDivideWithNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-2, calc.divide(-6, 3));
    }

    @Test
    void testDivideWithZeroNumerator() {
        Calculator calc = new Calculator();
        assertEquals(0, calc.divide(0, 3));
    }

    @Test
    void testCalculateAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.calculate("add", 2, 3));
    }

    @Test
    void testCalculateSubtract() {
        Calculator calc = new Calculator();
        assertEquals(1, calc.calculate("subtract", 3, 2));
    }

    @Test
    void testCalculateMultiply() {
        Calculator calc = new Calculator();
        assertEquals(6, calc.calculate("multiply", 2, 3));
    }
    @Test
    void testCalculateDivide() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.calculate("divide", 6, 3));
    }
    @Test
    void testCalculateInvalidOperation() {
        Calculator calc = new Calculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calc.calculate("mod",
                1, 1));
        assertEquals("Invalid operation", exception.getMessage());
    }
}