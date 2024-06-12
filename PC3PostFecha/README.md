# Practica 3

## Sprint3

Se muestra la parte faltante a lo del dia miercoles.

Para esta parte se usara la palabra aleatoria `example`.

Ya se habia implementado la logica del juego anteriormente, mostraremos la salida del codigo modificado aplicando inyeccion de dependencias.

![game](/PC3PostFecha/images/game.png)

Vemos que por cada palaba que el jugador ingrese , se muestra su respectivo feedback.

### 1 Implementa la inyección de dependencias para las clases WordSelector y HintGenerator. (2 puntos)

* Creacion de las interfaces :

IwordSelector:

```java
package pc3postfecha;

public interface IWordSelector {
    String selectWord();
}
```

IHintGenerator:

```java
package pc3postfecha;

public interface IHintGenerator {
    // Método para generar una pista
    String generateHint(String word);
    // Metodo para mostrar los caracteres en la posicion correcta
    void viewCharactersInCorrectPosition(String word, String userWord);
    // Si la letra esta en la palabra pero no en la posicion correcta
    void viewCharacterInIncorrectPosition(String word, String userWord);    
}
```

Modificacion de la clase Game para usar las interfaces:

```java
package pc3postfecha;

public class Game {
    private IWordSelector wordSelector;
    private IHintGenerator hintGenerator;
    private static final int ATTEMPS = 5; 
    // Constructor clasico
    public Game(IWordSelector wordSelector, IHintGenerator hintGenerator) {
        this.wordSelector = wordSelector;
        this.hintGenerator = hintGenerator;
    }
    // Contructor con inyeccion de dependencias
    public Game(DependencyInjector dependencyInjector) {
        dependencyInjector.injectDependencies(this);
    }
    // Metodos para inyectar dependencias
    public void setWordSelector(IWordSelector wordSelector) {
        this.wordSelector = wordSelector;
    }
    public void setHintGenerator(IHintGenerator hintGenerator) {
        this.hintGenerator = hintGenerator;
    }
    ...
    // Metodo para iniciar el juego
    public void start() {
        ...
    }
    public static void main(String[] args) {
        System.out.println("Adivina la palabra");
        // Inyeccion de dependencias
        Game game = new Game(new DependencyInjector());
        game.start();
    }
}
```

### 2. Implementa un contenedor de inyección de dependencias simple para gestionar las dependencias del juego (2 puntos)

Una posible implementacion de la nueva clase `DependencyInjector`.

```java
package pc3postfecha;
// Clase para manejar la inyeccion de dependencias
public class DependencyInjector {
    // Metodo para inyectar dependencias
    public void injectDependencies(Game game) {
        game.setWordSelector(new WordSelector());
        game.setHintGenerator(new HintGenerator());
    }
    // Metodo para obtener la instancia de DependencyInjector
    public static DependencyInjector getDependencyInjector() {
        return new DependencyInjector();
    }
}
```

La clase `Game` ya ha sido modificada para incorporar el contenedor:

```java
 public static void main(String[] args) {
        System.out.println("Adivina la palabra");
        // Inyeccion de dependencias
        Game game = new Game(new DependencyInjector());
        game.start();
    }
```

### 3. Aplica principios SOLID al código del juego (3 puntos)

#### Principio de responsabilidad unica

Parece que cada clase cumple con un solo proposito gracias a la implementacion de otras y el uso de interfaces

#### Principio Abierto/Cerrado

La implementacion de interfaces permite la extension y no modificacion de las otras clases.

#### Sustitucion  de Liskov

#### ISP

Las interfaces estan dise;adas correctamente, no deberia haber problema con ello

#### DIP

La clase `Game` depende tanto de las interfaces implementadas, y hay un manejo de dependencias mediante `DependencyInjector`

### 4. Aplicar TDD y Jacoco para desarrollar una nueva funcionalidad y refactorizar el código existente (4puntos)

Para esta parte necesitamos incluir la dependencia de `jacoco` en nuestro gradle.

```gradle
dependencies {
    testImplementation platform('org.junit:junit-bom:5.9.1')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'org.assertj:assertj-core:3.25.3'
    testImplementation 'org.jetbrains:annotations:24.0.0'
    // Agrega el plugin de PIT para JUnit 5
    pitest 'org.pitest:pitest-junit5-plugin:1.1.0'
}
```

Para comprobar ponemos `./gradlew build`, `./gradlew jacoco`.

Encontraremos una carpeta llamada `/build`.

Nos dirigimos a `/reports/tests/test` y encontraremos el file `index.html`.
Abrimos desde un navegador y veremos lo siguiente :

![jacoco](/PC3PostFecha/images/jacoco.png)

Veamos la cobertura:

![coverage](/PC3PostFecha/images/coverage.png)

Hay cosas que deben ser corregidas, vamos a refactorizar el codigo.
