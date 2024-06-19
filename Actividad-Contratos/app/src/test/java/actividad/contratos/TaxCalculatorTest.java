package actividad.contratos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaxCalculatorTest {

    @Test
    public void testCalculateTaxWithPositiveValue() {
        TaxCalculator taxCalculator = new TaxCalculator();
        double value = 100.0;
        double expectedTax = 10.0;
        double actualTax = taxCalculator.calculateTax(value);
        Assertions.assertEquals(expectedTax, actualTax);
    }

    @Test
    public void testCalculateTaxWithZeroValue() {
        TaxCalculator taxCalculator = new TaxCalculator();
        double value = 0.0;
        double expectedTax = 0.0;
        double actualTax = taxCalculator.calculateTax(value);
        Assertions.assertEquals(expectedTax, actualTax);
    }

    @Test
    public void testCalculateTaxWithNegativeValue() {
        TaxCalculator taxCalculator = new TaxCalculator();
        double value = -100.0;
        Assertions.assertThrows(RuntimeException.class, () -> {
            taxCalculator.calculateTax(value);
        });
    }

    @Test
    public void testCalculateTaxWithNegativeTaxValue() {
        TaxCalculator taxCalculator = new TaxCalculator();
        // Set up a scenario where the tax value is negative
        // For example, if the business rule is not implemented correctly
        double value = 100.0;
        //double negativeTaxValue = -10.0;
        // Assuming the business rule should return a negative tax value
        Assertions.assertThrows(RuntimeException.class, () -> {
            taxCalculator.calculateTax(value);
        });
    }
}