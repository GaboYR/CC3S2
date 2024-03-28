package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class MoneyManyValueTest {
    public static final String USD = "USD";
    @Test
    void constructorShouldSetAmountAndCurrency()
    {
        Money money = new Money(10,USD);
        assertThat(money.getAmount()).isEqualTo(10);
        assertThat(money.getCurrency()).isEqualTo(USD);
        money = new Money(20,USD);
        assertThat(money.getAmount()).isEqualTo(20);
        assertThat(money.getCurrency()).isEqualTo(USD);

    }
}
