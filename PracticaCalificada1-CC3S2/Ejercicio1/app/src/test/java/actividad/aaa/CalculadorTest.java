package actividad.aaa;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class CalculadorTest {

    @Test
    public void testSum_PositiveNumbers_ShouldReturnCorrectSum() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = 5;

        // Act
        int resultado = calculator.sumar(numeroA, numeroB);

        // Assert
        assertEquals(15, resultado, "10 + 5 deberia ser 15");
    }
    @Test
    public void testSum_NegativeNumbers_ShouldReturnCorrectSum() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = -10;
        int numeroB = -5;

        // Act
        int resultado = calculator.sumar(numeroA, numeroB);

        // Assert
        assertEquals(-15, resultado, "-10 + -5 deberia ser -15");
    }
    @Test
    public void testSum_PositiveAndNegativeNumbers_ShouldReturnCorrectSum() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = -5;

        // Act
        int resultado = calculator.sumar(numeroA, numeroB);

        // Assert
        assertEquals(5, resultado, "10 + -5 deberia ser 5");
    }
    @Test
    public void testRestar_PositiveNumbers_ShouldReturnCorrectSubtraction() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = 5;

        // Act
        int resultado = calculator.restar(numeroA, numeroB);

        // Assert
        assertEquals(5, resultado, "10 - 5 deberia ser 5");
    }
    @Test
    public void testRestar_NegativeNumbers_ShouldReturnCorrectSubtraction() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = -10;
        int numeroB = -5;

        // Act
        int resultado = calculator.restar(numeroA, numeroB);

        // Assert
        assertEquals(-5, resultado, "-10 - -5 deberia ser -5");
    }
    @Test
    public void testRestar_PositiveAndNegativeNumbers_ShouldReturnCorrectSubtraction() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = -5;

        // Act
        int resultado = calculator.restar(numeroA, numeroB);

        // Assert
        assertEquals(15, resultado, "10 - -5 deberia ser 15");
    }
    @Test
    public void testMultiplicacion_PositiveNumbers_ShouldReturnCorrectMultiplication() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = 5;

        // Act
        int resultado = calculator.multiplicacion(numeroA, numeroB);

        // Assert
        assertEquals(50, resultado, "10 * 5 deberia ser 50");
    }
    @Test
    public void testMultiplicacion_NegativeNumbers_ShouldReturnCorrectMultiplication() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = -10;
        int numeroB = -5;

        // Act
        int resultado = calculator.multiplicacion(numeroA, numeroB);

        // Assert
        assertEquals(50, resultado, "-10 * -5 deberia ser 50");
    }
    @Test
    public void testMultiplicacion_PositiveAndNegativeNumbers_ShouldReturnCorrectMultiplication() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = -5;

        // Act
        int resultado = calculator.multiplicacion(numeroA, numeroB);

        // Assert
        assertEquals(-50, resultado, "10 * -5 deberia ser -50");
    }
    @Test
    public void testDivision_PositiveNumbers_ShouldReturnCorrectDivision() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = 5;

        // Act
        double resultado = calculator.division(numeroA, numeroB);

        // Assert
        assertEquals(2, resultado, "10 / 5 deberia ser 2");
    }
    @Test
    public void testDivision_NegativeNumbers_ShouldReturnCorrectDivision() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = -10;
        int numeroB = -5;

        // Act
        double resultado = calculator.division(numeroA, numeroB);

        // Assert
        assertEquals(2, resultado, "-10 / -5 deberia ser 2");
    }
    @Test
    public void testDivision_PositiveAndNegativeNumbers_ShouldReturnCorrectDivision() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = -5;

        // Act
        double resultado = calculator.division(numeroA, numeroB);

        // Assert
        assertEquals(-2, resultado, "10 / -5 deberia ser -2");
    }
    @Test
    public void testDivision_DivisionByZero_ShouldThrowException() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 10;
        int numeroB = 0;

        // Act & Assert
        ArithmeticException exception = org.junit.jupiter.api.Assertions.assertThrows(ArithmeticException.class, () -> {
            calculator.division(numeroA, numeroB);
        });
        assertEquals("Division por cero", exception.getMessage(), "La excepcion deberia ser Division por cero");
    }
    @Test
    public void testDivision_DivisionByZero_ShouldThrowException2() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = -10;
        int numeroB = 0;

        // Act 
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            calculator.division(numeroA, numeroB);
        });
        // Assert
        assertEquals("Division por cero", exception.getMessage(), "La excepcion deberia ser Division por cero");
    }
    @Test
    public void testDivision_DivisionByZero_ShouldThrowException3() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 0;
        int numeroB = 0;

        // Act
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            calculator.division(numeroA, numeroB);
        });
        // Assert
        assertEquals("Division por cero", exception.getMessage(), "La excepcion deberia ser Division por cero");
    }
    @Test
    public void testDivision_DivisionByZero_ShouldThrowException4() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 0;
        int numeroB = 10;

        // Act
        double resultado = calculator.division(numeroA, numeroB);
        // Assert
        assertEquals(0, resultado, "0 / 10 deberia ser 0");
    }
    @Test
    public void testDivision_DivisionByZero_ShouldThrowException5() {
        // Arrange
        Calculator calculator = new Calculator();
        int numeroA = 0;
        int numeroB = -10;

        // Act 
        double resultado = calculator.division(numeroA, numeroB);
        // Assert
        assertEquals(0, resultado, "0 / -10 deberia ser 0");
    }
}
