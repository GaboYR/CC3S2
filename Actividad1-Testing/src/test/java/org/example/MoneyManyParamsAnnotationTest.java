package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MoneyManyParamsAnnotationTest
{
    @ParameterizedTest
    @CsvSource({
            "10,USD",
            "15,EUR",
            "50,CHF"
    })
    void constructorShouldSetAmountAndCurrency(int amount, String currency)
    {
        Money money = new Money(amount, currency);
        assertThat(money.getAmount()).isEqualTo(amount);
        assertThat(money.getCurrency()).isEqualTo(currency);
    }
}
