# Practica Calificada 3

Alumno : Yarleque Ramos Gabriel

Este markdown contiene la explicacion de los 3 sprints

## Sprint 1

Definimos las clases iniciales

![treesp1](/PracticaCalificada3-CC3S2/images/treesp1.png)

Configuramos los archivos para que funcionen.
**Las clases no estan implementadas completamente pero hay un funcionamiento**.

- Clase HintGenerator:

```java
package sprint1;
public class HintGenerator {
    // Mostrar el tama;o de la palabra
    public String generateHint(String word) {
        return "_ ".repeat(word.length());
    }
    // Mostrar la palabra con las letras acertadas
    public String generateHint(String word, String guess) {
        // Mostrar _ si no es la letra correcta
        // Mosrtar la letra si es correcta
        
        return null;

    }
}

```

- Clase WordSelector

```java
package sprint1;
public class WordSelector {
    // Array de palabras
    private final String[] words = {"hola", "adios", "casa", "coche", "perro", "gato", "raton", "elefante", "caballo", "pajaro"};
    // Método para seleccionar una palabra aleatoria
    public String selectWord() {
        int index = (int) (Math.random() * words.length);
        return words[index];
    }
}
```

- Clase Game:

```java
  package sprint1;
public class Game {
    private final WordSelector wordSelector;
    private final HintGenerator hintGenerator;
    private final String word;
    private final String hint;
    private int attempts;
    public Game(WordSelector wordSelector, HintGenerator hintGenerator) {
        this.wordSelector = wordSelector;
        this.hintGenerator = hintGenerator;
        this.word = wordSelector.selectWord();
        this.hint = hintGenerator.generateHint(word);
        this.attempts = 5;
        System.out.println("La palabra es: " + hint);
    }
    public void start() {
        while (attempts > 0) {
            System.out.println("Intenta adivinar la palabra");
            String guess = System.console().readLine();
            if (guess.equals(word)) {
                System.out.println("¡Has adivinado la palabra!");
                return;
            } else {
                attempts--;
                System.out.println("¡Palabra incorrecta! Te quedan " + attempts + " intentos");
            }
        }
        System.out.println("¡Has perdido! La palabra era: " + word);
    }
    public static void main(String[] args) {
        System.out.println("Adivina la palabra secreta");
        System.out.println("Tienes 5 intentos");
        WordSelector wordSelector = new WordSelector();
        HintGenerator hintGenerator = new HintGenerator();
        Game game = new Game(wordSelector, hintGenerator);
        game.start();
    }
}   
```

### Codigo en ejecucion

![func1](/PracticaCalificada3-CC3S2/images/func1.png)

### Zona de pruebas

De momento se ha creado una clase de pruebas para verificar la seleccion, comprobacion y aleatorizacion de la palabra para el juego.

```java
package sprint1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordSelectorTest {
    // Test para comprobar que se selecciona una palabra
    @Test
    void testSelectWord() {
        WordSelector wordSelector = new WordSelector();
        String word = wordSelector.selectWord();
        assertNotNull(word);
        assertTrue(word.length() > 0);
    }
    // Test para comprobar que se selecciona una palabra aleatoria
    @Test
    void testSelectRandomWord() {
        WordSelector wordSelector = new WordSelector();
        String word1 = wordSelector.selectWord();
        String word2 = wordSelector.selectWord();
        assertNotEquals(word1, word2);
    }
    // Test para comprobar que se selecciona una palabra de la lista
    @Test
    void testSelectWordFromList() {
        WordSelector wordSelector = new WordSelector();
        String word = wordSelector.selectWord();
        String[] words = {"hola", "adios", "casa", "coche", "perro", "gato", "raton", "elefante", "caballo", "pajaro"};
        boolean found = false;
        for (String w : words) {
            if (w.equals(word)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
}
```

Salida de las pruebas:

![test1](/PracticaCalificada3-CC3S2/images/test1.png)

### Metricas de cohesion

**LCOM:**
> Clase Game:
Tiene 3 metodos, es decir, su |P| es combinatoria de 3 en 2 = 3
|Q| = 1 + 1  = 2
LCOM = 1
> HintGenerator:
Tiene 2 metodos, |P| = 1
|Q| = 0
LCOM = 1
> WordSelector:
|P| = 0, solo hay un metodo
|Q| = 0
LCOM = 0

### Test de cobertura

![coverage](/PracticaCalificada3-CC3S2/images/coverageS1.png)

## Sprint 2

Vamos a reutilizar lo implementado en el sprint1 y le agregaremos las nuevas funcionalidades.

### Logica del juego

Para que el juego se haga mas atractivo, vamos a realizar lo siguiente:
Dentro de la clase `HintGenerator` agregaremos esta funcion que mostrara si la palabra ingresada tiene caracteres que coicidadn con la palabra del juego
Si el caracter coincide con la posicion y esta en la palabra, se mostrara.
Si el caracter coincide en la palabra pero no en la posicion, se mostrara en un apartado para que puedas investigar donde esta la palabra.
```java
// Mostrar la palabra con las letras acertadas y en su posicion
    public String generateHint(String word, String guess) {
        String hint = "";
        // si la palabra contiene la letra
        for (int i = 0; i < word.length(); i++) {
            if (guess.contains(String.valueOf(word.charAt(i)))) {
                hint += word.charAt(i) + " ";
            } else {
                hint += "_ ";
            }
        }
        return hint;
    }
    // Funcion que muestra las letras que se han acertado
    // pero no estan en su posicion 
    public String showChars (String word, String guess) {
        String chars = "";
        for (int i = 0; i < word.length(); i++) {
            if (guess.contains(String.valueOf(word.charAt(i))) && !guess.equals(String.valueOf(word.charAt(i)))) {
                chars += word.charAt(i);
                chars += " ";
            }
        }
        //System.out.println("Letras que acertaste pero no estan en su posicion:");
        return chars;
    }
```

Lo agregamos al metodo `start()` de la clase `Game`

```java
public void start() {
        while (attempts > 0) {
            System.out.println("Intenta adivinar la palabra");
            String guess = System.console().readLine();
            if (guess.equals(word)) {
                System.out.println("¡Has adivinado la palabra!");
                return;
            } else {
                attempts--;
                hintGenerator.generateHint(word, guess);
                System.out.println("La palabra es: " + hintGenerator.generateHint(word, guess));
                System.out.println("¡Palabra incorrecta! Te quedan " + attempts + " intentos");
            }
        }
        System.out.println("¡Has perdido! La palabra era: " + word);
    }
```

Salida en consola :

![gato](/PracticaCalificada3-CC3S2/images/gato.png)

El feedback al jugador ha sido implementado.

### MEtricas de codigo

> **CAMC**
> Para clase HintGenerator

```java
public String generateHint(String word)
public String generateHint(String word, String guess) 
public String showChars (String word, String guess) 
```
- 3 metodos
- metodo generateHint(1 tipo unico)
- metodo generateHint(2 tipos) *funcion sobrecargada*
- metodo showchars(2 tipos) 
- Suma = 5
  metodos = 3
  maximo de paramtro por metodo = 2
  **CAMC** = 5 /(3 * 2) = 0.866666
CAMC cercano a 1, alta cohesion.

## Sprint 3

Implementacion de interfaces para `WordSelector` y `HintGenerator`.

En clase HintGenerator

```java
public class HintGenerator implements IHintGenerator  {
    // Mostrar el tama;o de la palabra
    public String generateHint(String word) {
        return "_ ".repeat(word.length());
    }
    // Mostrar la palabra con las letras acertadas y en su posicion
    public String generateHint(String word, String guess) {
        String hint = "";
        // si la palabra contiene la letra
        for (int i = 0; i < word.length(); i++) {
            if (guess.contains(String.valueOf(word.charAt(i)))) {
                hint += word.charAt(i) + " ";
            } else {
                hint += "_ ";
            }
        }
        return hint;
    }
    ...
```

En clase WordSelector

```java
public class WordSelector implements IWordSelector{
    // Array de palabras
    private final String[] words = {"avion","hola", "adios", "casa", "coche", "perro", "gato", "raton", "elefante", "caballo", "pajaro"};
    // Método para seleccionar una palabra aleatoria
    public String selectWord() {
        int index = (int) (Math.random() * words.length);
        return words[index];
    }
    ...
```

En clase game

```java
public class Game {
    private IWordGenerator wordSelector;
    private IHintGenerator hintGenerator;
    private final String word;
    private final String hint;
    ...
```