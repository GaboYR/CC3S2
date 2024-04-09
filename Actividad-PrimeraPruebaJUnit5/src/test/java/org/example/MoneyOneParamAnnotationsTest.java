package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
class MoneyOneParamAnnotationsTest {
    @ParameterizedTest
    //Proveedor de datos de prueba
    @ValueSource(ints = {10,15,50,99})
    void constructorShouldSetAmountAndCurrency(int amount)
    {
        Money money = new Money(amount,"USD");
        assertThat(money.getAmount()).isEqualTo(amount);
    }
}
