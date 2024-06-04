package org.example;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {
    // Ejercicio 1
    @Test
    void substringsBetweenMustReturnExpectedValue() {
        assertThat(StringUtils.substringsBetween("axcaycazc","a","c")).isEqualTo(new String[]{"x","y","z"});
    }
    // Ejercicio 2 
    @Test
    void simpleCase() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }
    @Test
    void manyStrings() {
        assertThat(StringUtils.substringsBetween("abcdabcdab", "a", "d")).isEqualTo(new String[] { "bc",
    "bc" });
    }
    @Test
    void openAndCloseTagsThatAreLongerThan1Char() {
        assertThat(StringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new String[]{ "bc", "bf" });
    }
    // Ejercicio 3
    // Paramatro str
    // str como null
    @Test
    void nullString() {
        assertThat(StringUtils.substringsBetween(null, "a", "d")).isEqualTo(new String[] { });
    }
    // str como vacío
    @Test
    void emptyString() {
        assertThat(StringUtils.substringsBetween("", "a", "d")).isEqualTo(new String[] { });
    }
    // str con longitud 1
    @Test
    void oneCharString() {
        assertThat(StringUtils.substringsBetween("a", "a", "d")).isEqualTo(new String[] { });
    }
    // str con longitud > 1
    @Test
    void noOpenTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] {"bc"});
    }
    // Parametro open
    // open como null
    @Test
    void nullOpenTag() {
        assertThat(StringUtils.substringsBetween("abcd", null, "d")).isEqualTo(new String[] { });
    }
    // open como vacío
    @Test
    void emptyOpenTag() {
        assertThat(StringUtils.substringsBetween("abcd", "", "d")).isEqualTo(new String[] { });
    }
    // open con longitud 1
    @Test
    void oneCharOpenTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }
    // open con longitud > 1
    @Test
    void openTagLongerThan1Char() {
        assertThat(StringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new String[] { "bc", "bf" });
    }
    // Parametro close
    // close como null
    @Test
    void nullCloseTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", null)).isEqualTo(new String[] { });
    }
    // close como vacío
    @Test
    void emptyCloseTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "")).isEqualTo(new String[] { });
    }
    // close con longitud 1
    @Test
    void oneCharCloseTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }
    // close con longitud > 1
    @Test
    void closeTagLongerThan1Char() {
        assertThat(StringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new String[] { "bc", "bf" });
    }
}
