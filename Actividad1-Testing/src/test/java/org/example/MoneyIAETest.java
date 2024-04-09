package org.example;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
class MoneyIAETest {
    private final static String VALID_CURRENCY = "USD";
    @ParameterizedTest
    @ValueSource(ints = {-1,-5,-122})
    void constructorShouldThrowIAEForInvalidAmount(int invalidAmount) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Money(invalidAmount, VALID_CURRENCY);
                });
    }
}
