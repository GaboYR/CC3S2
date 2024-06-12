# Actividad pruebas estructurales

La actividad consiste en revisar la cobertura de pruebas

## Ejercicio 1

Explicar el codigo contenido en `CountWords`, en las lineas comentadas del 1 al 3:

1. Se usa un bucle `for` para recorrer la cadena.
`for (int i = 0; i < str.length(); i++)` recorre caracter por caracter.

2. El condicional `if (!isLetter(str.charAt(i)) && (last == 's' || last == 'r'))` verifica que la posicion en la que nos encontremos no sea una letra y que el valor de la variable `last` sea `r o s`.

3. La intruccion `last = str.charAt(i);` guarda el caracter de la posicion en la que estemos.

## Ejercicio 2

Presenta un informe generado por [JaCoCo](www.jacoco.org/jacoco) u otra herramienta de cobertura de código de tu preferencia en el IDE del curso.

* Explicacion de los test
  * `void twoWordsEndingWithS()`: La cadena contiene dos palabras con terminacion en `s`, asi que el valor de retorno deberia ser 2
  * `void noWordsAtAll()` : En este caso, no habra palabras con terminacion en `r` o `s`, debe retornar 0.
* Presentacion generada por `jacoco`
  * Ejecutamos la intruccion `./gradlew jacocoTestReport` para generar el reporte.
  * Nos dirigimos a la ubicacion `/build/reports/jacoco/test/html`, ahi ubicamos el file `index.html`, lo abrimos con cualquier navegador y veremos lo siguiente.
  * ![jacoco](/Actividad-PruebasEstructurales/images/jacoco.png)
  
## Ejercicio 3

Agregar la sgte prueba :

```java
@Test
void wordsThatEndInR() { // 1
  int words = new CountWords().count("car bar");
  assertThat(words).isEqualTo(2);
}
```

y explicar la linea 1.

* Al igual que la prueba anterior, ahora se verifica que las palabras terminen en `r`.
* Ejecutamos `./gradlew jacocoTestReport` nuevamente para visualizar los resultados
* Resultados : ![jacoco2](/Actividad-PruebasEstructurales/images/jacoco2.png)
  
## Ejercicio 4(Analisis de codigo y cobertura)

### Parte A

Analisis de codigo :

```java
public class CountWords {
  public int count(String str) {
    int words = 0;
    char last = ' ';
    for (int i = 0; i < str.length(); i++) { // 1
      if (!isLetter(str.charAt(i)) && (last == 's' || last == 'r')) { // 2
        words++;
      }
      last = str.charAt(i); // 3 
    }
    if (last == 'r' || last == 's') {
      words++;
    }
  return words;
  }
  private boolean isLetter(char c) {
    return Character.isLetter(c);
  }
}
```

1. Explica qué hacen las líneas 1, 2 y 3 en el código
  Lineas explicadas anteriormente en **Ejercicio 1**

2. ¿Qué sucedería si se eliminara la línea 3 del código?
  Si se elimina no actualizariamos el valor de `last`, esto sigunifica que durante toda la iteracion el valor de `last` se mantiene en `last = ' '`

3. Escribe una descripción de alto nivel de lo que hace este método count
  El metodo count es una funcion que retorna el numero de palabras de una cadena que terminen con caracter `r` o `s`.
  Cuando encuentre un caracter que no lo sea, analizara el caracter anterior y verificara si es o no un caracter requerido.
  Dentro de la funcion se crea una variable `words` que manejara el conteo.
  Si la palabra es valida, aumenta su valor hasta que el bucle termine.
  Finalmente se retorna el contador.

### Parte B

Seccion realizada anteriormente

### Parte C

Seccion realizada anteriormente.

## Ejercicio 5(Nuevas pruebas)

**Parte A**
Agregamos 2 nuevas pruebas:

```java
  @Test
  void wordsThatEndInRAndS() {
      int words = new CountWords().count("car bar cars");
      assertThat(words).isEqualTo(3);
  }
  @Test
  void noWordsEmptyString() {
      int words = new CountWords().count("");
      assertThat(words).isEqualTo(0);
  }
```

**Parte B**
Agregamos las pruebas al Test y ejecutamos el jacoco.

Resultados :
![jacoco3](/Actividad-PruebasEstructurales/images/jacoco3.png)

## Ejercicio 6(Exploracion y mejora)

**Parte A**
Exploracion:

* Mejora 1
  Modificacion del `for`:

  ```java
  public int count(String str) {
        int words = 0;
        char last = ' ';
        for (var c : str.toCharArray()) {
            words += (!isLetter(c) && (last == 's' || last == 'r')) ? 1 : 0;
            last = c;
        }
        return words;
    }
    ```

* Mejora 2:
  Dentro de la clase:

  ```java
  private static final char[] list = {'s', 'r'};
    public int count(String str) {
        str += " ";
        int words = 0;
        char last = ' ';
        for (char c : str.toCharArray()) {
            if (!isLetter(c) && isInList(last)) {
                words++;
            }
            last = c;
        }
        return words;
    }

    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }
    private boolean isInList(Character c){
        for (int i = 0; i < list.length; i++) {
            if (c == list[i]) {
                return true;
            }
        }
        return false;
    }
    ```

Modificamos y creamos una lista para poder restringir mas letras a un futuro.
