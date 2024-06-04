# Pruebas basadas en especificaciones

El problema consta de una clase que contiene un metodo qu retorna una lista de cadenas que empiezen con `start` y terminen en `end`.

La clase ya esta implementada,tenemos que realizar test y que pasen cada 1.

## Ejercicio 1

Hacer pasar el siguiente test:

![ej1](/Actividad-PruebasBasadasEnEspecificaciones/images/ej1.png)

Corremos la prueba y veremos:

![ej1test](/Actividad-PruebasBasadasEnEspecificaciones/images/ej1testpassed.png)

Esto significa que la prueba paso con exito.

## Ejercicio 2

### Paso 1

Se entiende lo que quiere realizar la funcion `substringBetween`, a grandes rasgos, sobre una cadena se retorna una lista con aquellas subcadenas que empiezen con el 2do parametro y terminen en el 3er parametro.

### Paso 2

Esta parte consta de diversos test:

```java
@Test
void simpleCase() {
 assertThat(stringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
}
@Test
void manyStrings() {
 assertThat(stringUtils.substringsBetween("abcdabcdab", "a", "d")).isEqualTo(new String[] { "bc",
"bc" });
}
@Test
void openAndCloseTagsThatAreLongerThan1Char() {
 assertThat(stringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new String[]
{ "bc", "bf" });
}
```

Los cuales pasan con exito.

## Ejercicio 3

Todos los test realizados previamente han sido guardados en `StringUtilsTest.java`.

### Paso 3

Generar cada caso de prueba especificada en el documento.

```java
// Ejercicio 3
    // Paramatro str
    // str como null
    void nullString() {
        assertThat(StringUtils.substringsBetween(null, "a", "d")).isEqualTo(new String[] { });
    }
    // str como vacío
    void emptyString() {
        assertThat(StringUtils.substringsBetween("", "a", "d")).isEqualTo(new String[] { });
    }
    // str con longitud 1
    void oneCharString() {
        assertThat(StringUtils.substringsBetween("a", "a", "d")).isEqualTo(new String[] { });
    }
    // str con longitud > 1
    void noOpenTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] {"bc"});
    }
    // Parametro open
    // open como null
    void nullOpenTag() {
        assertThat(StringUtils.substringsBetween("abcd", null, "d")).isEqualTo(new String[] { });
    }
    // open como vacío
    void emptyOpenTag() {
        assertThat(StringUtils.substringsBetween("abcd", "", "d")).isEqualTo(new String[] { });
    }
    // open con longitud 1
    void oneCharOpenTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }
    // open con longitud > 1
    void openTagLongerThan1Char() {
        assertThat(StringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new String[] { "bc", "bf" });
    }
    // Parametro close
    // close como null
    void nullCloseTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", null)).isEqualTo(new String[] { });
    }
    // close como vacío
    void emptyCloseTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "")).isEqualTo(new String[] { });
    }
    // close con longitud 1
    void oneCharCloseTag() {
        assertThat(StringUtils.substringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }
    // close con longitud > 1
    void closeTagLongerThan1Char() {
        assertThat(StringUtils.substringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new String[] { "bc", "bf" });
    }
```
