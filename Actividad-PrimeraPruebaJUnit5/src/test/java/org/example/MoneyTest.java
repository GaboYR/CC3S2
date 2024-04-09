package org.example;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    @Test
    void constructorShouldSetAmountAndCurrency() {
        // Completa
        //SUT
        Money money = new Money(10,"USD");
        //Se pone a prueba con el metodo assertThat
        assertThat(money.getAmount()).isEqualTo(10);
        assertThat(money.getCurrency()).isEqualTo("USD");
    }

}