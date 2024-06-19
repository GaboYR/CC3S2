# Pruebas basadas en especificaciones

El problema consta de una clase que contiene un metodo qu retorna una lista de cadenas que empiezen con `start` y terminen en `end`.

La clase ya esta implementada,tenemos que realizar test y que pasen cada 1.

## Ejercicio 1 :Escribe el código de prueba y considera las entradas str = "axcaycazc", open = "a" y close

= "c" y explica lo que hace el código anterior

Hacer pasar el siguiente test:

![ej1](/Actividad-PruebasBasadasEnEspecificaciones/images/ej1.png)

Corremos la prueba y veremos:

![ej1test](/Actividad-PruebasBasadasEnEspecificaciones/images/ej1testpassed.png)

Esto significa que la prueba paso con exito.

## Ejercicio 2: Revisa los requisitos una vez más y escribe todos los casos de prueba que se te ocurran

### Paso 1: Comprensión de los requisitos, entradas y salidas

Se entiende lo que quiere realizar la funcion `substringBetween`, a grandes rasgos, sobre una cadena se retorna una lista con aquellas subcadenas que empiezen con el 2do parametro y terminen en el 3er parametro.

### Paso 2: Explora lo que hace el programa para varias entradas

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

## Ejercicio 3: Escribe un código de prueba llamado stringUtilsExploracionTest.java que albergue el código anterior

Todos los test realizados previamente han sido guardados en `StringUtilsTest.java`.

### Paso 3: Explora las posibles entradas y salidas, e identifica las particiones

Generar cada caso de prueba especificada en el documento.

```java
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
    //@Test
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
        assertThat(StringUtils.substringsBetween("abcd", null, "d")).isEqualTo(null);
    }
    // open como vacío
    //@Test
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
    //@Test
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
```

### Paso 4: Analiza los limites

Agregamos las sgtes pruebas:

```java
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
```

### Paso 5: Idear casos de prueba

## Ejercicio 4: ¿En nuestro ejemplo cuál es el número de pruebas?

* T1 es nulo o vacio

```java
@Test
    void nullString() {
        assertThat(StringUtils.substringsBetween(null, "a", "d")).isEqualTo(null);
    }
    // str como vacío
    @Test
    void emptyString() {
        assertThat(StringUtils.substringsBetween("", "a", "d")).isEqualTo(new String[] { });
    }
```

* open es nulo o vacio

```java
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
```
