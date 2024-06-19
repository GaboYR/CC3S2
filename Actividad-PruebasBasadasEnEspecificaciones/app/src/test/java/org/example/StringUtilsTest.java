package org.example;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
class StringUtilsTest {
    // Ejercicio 1
    @Test
    public void substringsBetweenMustReturnExpectedValue() {
        assertThat(StringUtils.substringsBetween("axcaycazc","a","c")).isEqualTo(new String[]{"x","y","z"});
        //assertThat(StringUtils.substringsBetween("axcaycazc","a","c")).isEqualTo(new String[]{"x","y","z"});
    }

    // Ejercicio 2 
    @Test
    void simpleCase() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }

    @Test
    void manyStrings() {
        assertThat(StringUtils.substringsBetween("abcdabcdab", "a", "d")).isEqualTo(new String[] { "bc", "bc" });
    }

    @Test
    void openAndCloseTagsThatAreLongerThan1Char() {
        assertThat(StringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new String[]{ "bc", "bf" });
    }

    // Ejercicio 3

    // Parametro str
    
    // str como null
    @Test
    void nullString() {
        assertThat(StringUtils.substringsBetween(null, "a", "d")).isEqualTo(null);
    }
    // str como vacío
    @Test
    void emptyString() {
        assertThat(StringUtils.substringsBetween("", "a", "d")).isEqualTo(new String[] { });
    }

    // str con longitud 1
    @Test
    void oneCharString() {
        assertThat(StringUtils.substringsBetween("a", "a", "d")).isEqualTo(null);
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
        assertThat(StringUtils.substringsBetween("abcd", null, "d")).isEqualTo(null);
    }
    // open como vacío
    @Test
    void emptyOpenTag() {
        assertThat(StringUtils.substringsBetween("abcd", "", "d")).isEqualTo(null);
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
        assertThat(StringUtils.substringsBetween("abcd", "a",null)).isEqualTo(null);
    }
    // close como vacío
    @Test
    void emptyCloseTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "")).isEqualTo(null);
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

    // Ahora combinaciones 

    //str no contiene ni la etiqueta de open ni la de close.
    @Test
    void noOpenNorCloseTags() {
        assertThat(StringUtils.substringsBetween("abcd", "x", "y")).isEqualTo(null);
    }   
    // str contiene la etiqueta open pero no la etiqueta close.
    @Test
    void openTagButNoCloseTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "x")).isEqualTo(null);
    }
    //str contiene la etiqueta de close pero no la etiqueta de open.
    @Test
    void closeTagButNoOpenTag() {
        assertThat(StringUtils.substringsBetween("abcd", "x", "d")).isEqualTo(null);
    }
    //str contiene las etiquetas de open y close.
    @Test
    void openAndCloseTags() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }
    //str contiene las etiquetas de open y close varias veces
    @Test
    void openAndCloseTagsMultipleTimes() {
        assertThat(StringUtils.substringsBetween("abcdabcdab", "a", "d")).isEqualTo(new String[] { "bc", "bc" });
    }
    
    // Paso 4
    //str contiene etiquetas tanto de open como de close, sin caracteres entre ellas.
    @Test
    void openAndCloseTagsWithoutCharactersBetween() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "b")).isEqualTo(new String[] {""});
    }
    //str contiene etiquetas tanto de open como de close, con caracteres entre ellas.
    @Test
    void openAndCloseTagsWithCharactersBetween() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "c")).isEqualTo(new String[] { "b" });
    }

    @Test
    void strIsNullOrEmpty() {
        assertThat(StringUtils.substringsBetween(null, "a", "b")).isEqualTo(null);
        assertThat(StringUtils.substringsBetween("", "a", "b")).isEqualTo(new String[]{});
    }
}
