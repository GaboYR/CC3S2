# Practica Calificada 4

Alumno : Yarleque Ramos Gabriel

Problema : Tower Defense

Identificacion de clases respectivas:

* Mapa
* Enemigos (Velocidad, vida, recompensa)
  * Enemigo basico
  * Enemigo rapido
  * Jefe
* Torres (Da;o, alcance, velocidad de disparo)
  * Ca;on
  * Laser
  * Flecha
* Wave(Sistema de oleadas)
* Puntuacion

## Flujo de juego

### Inicializacion

Configuramos el `build.gradle`

```gradle
plugins {
    id 'java'
    id 'info.solidsoft.pitest' version '1.15.0'
}

group = 'org.example'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'org.assertj:assertj-core:3.25.3'
    testImplementation 'org.mockito:mockito-core:3.6.28'
    pitest 'org.pitest:pitest-junit5-plugin:1.1.0'
}

test {
    useJUnitPlatform()
}

pitest {
    targetClasses = ['practicacalificada4.cc3s2*'] // Paquete de clases a mutar
    mutators = ['DEFAULTS'] // Conjunto de mutadores [OLD_DEFAULTS, DEFAULTS, STRONGER, ALL]
    outputFormats = ['HTML'] // Formato de salida del informe
    timestampedReports = false // Deshabilitar informes con marca de tiempo para facilitar la navegación
    verbose = true
}

// ./gradlew pitest
```

De momento tenemos estas clases :

* Mapa:

```java
public class Mapa {
    public char[][] mapa = {{' ',' ','C',' ',' '},
                            {' ','C',' ',' ',' '},
                            {'C',' ',' ','C','B'},
                            {' ',' ','C',' ',' '},
                            {' ',' ',' ',' ',' '}};
    
}
```

* Enemigo:

```java
public class Enemy {
    private int speed; // número de celdas por turno
    private int health;
    private int reward;
    // Constructores, getters y setters
    public Enemy(int speed, int health, int reward) {
        this.speed = speed;
        this.health = health;
        this.reward = reward;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getReward() {
        return reward;
    }
    public void setReward(int reward) {
        this.reward = reward;
    }
    
}

class BasicEnemy extends Enemy {
    public BasicEnemy() {
        super(1, 100, 10);
    }
}
class FastEnemy extends Enemy {
    public FastEnemy() {
        super(2, 50, 20); 
    }
}
class BossEnemy extends Enemy {
    public BossEnemy() {
        super(1, 200, 50);
    }
}
```

* Tower

```java
public class Tower {
    private int damage;
    private int range;
    private int fireRate; // turnos entre disparos
    // Constructores, getters y setters
    public Tower(int damage, int range, int fireRate) {
        this.damage = damage;
        this.range = range;
        this.fireRate = fireRate;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }
    public int getFireRate() {
        return fireRate;
    }
    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }
}

class CannonTower extends Tower {
    public CannonTower() {
        super(50, 2, 3); // daño, alcance, velocidad de disparo
    }
}
class LaserTower extends Tower {
    public LaserTower() {
        super(100, 3, 5); // daño, alcance, velocidad de disparo
    }
}
class ArrowTower extends Tower {
    public ArrowTower() {
        super(25, 4, 1); // daño, alcance, velocidad de disparo
    }
}
```

* Wave

```java
import java.util.ArrayList;
import java.util.List;
public class Wave {
    private List<Enemy> enemies;
    private int waveNumber;

    public Wave(int waveNumber) {
        this.waveNumber = waveNumber;
        this.enemies = generateEnemies(waveNumber);
    }

    private List<Enemy> generateEnemies(int waveNumber) {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < waveNumber * 5; i++) { // más enemigos cada oleada
            enemies.add(new BasicEnemy());
        }
        if (waveNumber % 5 == 0) { // jefe cada 5 oleadas
            enemies.add(new BossEnemy());
        }
        return enemies;
    }
    // Métodos para manejar el progreso de la oleada
}
```

* Player

```java
public class Player {
    private int score;
    private int baseHealth;

    public Player() {
        this.score = 0;
        this.baseHealth = 100;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void deductBaseHealth(int damage) {
        this.baseHealth -= damage;
    }   

    public int getScore() {
        return score;
    }

    public int getBaseHealth() {
        return baseHealth;
    }
    // Métodos adicionales según las necesidades del juego
}
```

Para no confundir la 'C' de camino de enemigo con 'C' de posicion de Torre Cannon, camino de enemigo sera '1'.

Debemos agregar un metodo que coloque una torre en el mapa

```java
public void addTower(int x,int y,char tower) {
        mapa[x][y] = tower;
    }
```

Como ese metodo es muy general, aplicamos el principio Open Closed de SOLID, asi creamos clases hijas para cada tipo de torre.

```java
// Addtower por cada hijo
    public void addCannonTower(int x,int y) {
        mapa[x][y] = 'C';
    }
    public void addLaserTower(int x,int y) {
        mapa[x][y] = 'L';
    }
    public void addArrowTower(int x,int y) {
        mapa[x][y] = 'A';
    }
    public void removeTower(int x,int y) {
        mapa[x][y] = ' ';
    }
```

Ahora necesitamos implementar una clase que se encargue de procesar cada comando que el jugador ingrese.

Para esto aplicamos el Patron Factory.

* Interfaz Command

```java
public interface Command {
    void execute(String[] command);
}

```

* Clase CommandFactory que implementa la interfaz Command

```java
public class CommandFactory implements Command {
    @Override
    public void execute(String[] command) {
        Mapa mapa = new Mapa();
        if (command[1].equals("Cannon")) {
            mapa.addCannonTower(Integer.parseInt(command[2]), Integer.parseInt(command[3]));
            System.out.println("Torre Cannon colocada en ("+ command[2]+" , "+command[3] + ")");
        }
        else if (command[1].equals("Laser")) {
            mapa.addLaserTower(Integer.parseInt(command[2]), Integer.parseInt(command[3]));
            System.out.println("Torre Laser colocada en ("+ command[2]+" , "+command[3] + ")");
        }
        else if (command[1].equals("Arrow")) {
            mapa.addArrowTower(Integer.parseInt(command[2]), Integer.parseInt(command[3]));
            System.out.println("Torre Arrow colocada en ("+ command[2]+" , "+command[3] + ")");
        }
    }
}

```

### Colocacion de torres

El jugador es capaz de colocar torres en el mapa, para ello se agregaron los metodos:

```java
// Addtower por cada hijo
    public void addCannonTower(int x,int y) {
        mapa[x][y] = 'C';
    }
    public void addLaserTower(int x,int y) {
        mapa[x][y] = 'L';
    }
    public void addArrowTower(int x,int y) {
        mapa[x][y] = 'A';
    }
    public void removeTower(int x,int y) {
        mapa[x][y] = ' ';
    }
```

### Inicio de oleadas

Para el inicio de oleadas, el usuario debe escribir el comando `START_WAVE`.

### Ataque de torres

Una vez que los enemigos se hayan movido, ubicaremos sus posiciones finales, y si la torre esta en rango de atacarla, lo hara.

Para esta parte se agrego la clase `Point` que contendra un metodo `distancia` que es la distancia Manhattan.

```java
public class Point {
    int x,y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int distance(Point p) {
        return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
    }
}
```

### Actualización del estado

Despues de que las torres hayan atacado a los enemigos o despues del desplazamiento de enemigos, debemos actualizar el mapa.
Esto se hace despues del comando `START_WAVE`.

### Finalización de oleada

Ya descrito anteriormente.

### Fin del juego

Si la salud de la base llega a 0 o se ejecuta el comando `exit`, el juego terminara.

## Interfaces y clases de Mocking

* EnemyFactory
* TowerFactory

## Implementacion de pruebas

### Mocks

```java
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MutacionTest {
    // Implementa pruebas de mutacion para verificar la calidad de las pruebas unitarias
    // Test para el método isValidPosition de la clase Mapa
    @Test
    public void testIsValidPosition() {
        // Configurar mock para posición válida
        Mapa mockMap = Mockito.mock(Mapa.class);
        when(mockMap.isValidPosition(3, 4)).thenReturn(true);
        // Verificar que la posición sea válida
        verify(mockMap,never()).isValidPosition(3, 4);
    }

    // Test para el método placeTower de la clase Game
    @Test
    public void testPlaceTower_ValidPosition() {
        // Configurar mock para posición válida
        Mapa mockMap = Mockito.mock(Mapa.class);
        Game game = Mockito.mock(Game.class);
        when(mockMap.isValidPosition(3, 4)).thenReturn(true);
        game.placeTower(new CannonTower(), 3, 4);
        // Verificar que la torre se haya colocado
        verify(mockMap,never()).placeTower(any(Tower.class), eq(3), eq(4));
        //verify(mockMap).placeTower(any(Tower.class), eq(3), eq(4));
    }

    @Test
    public void testPlaceTower_InvalidPosition() {
        // Configurar mock para posición inválida
        Mapa mockMap = Mockito.mock(Mapa.class);
        Game game = Mockito.mock(Game.class);
        when(mockMap.isValidPosition(3, 4)).thenReturn(false);
        game.placeTower(new CannonTower(), 3, 4);
        // Verificar que la torre no se haya colocado
        verify(mockMap, never()).placeTower(any(Tower.class), eq(3), eq(4));
    }
}
```

### Stubs

### Fakes

### Pruebas de mutacion

* Implementa pruebas de mutación para verificar la calidad de las pruebas unitarias.

* ¿Qué herramienta utilizarías para realizar pruebas de mutación en este proyecto, y cómo la
configurarías?
Usaremos PITest, que es una herrmaienta de mutacion para Java. Para configurarlo en Gradle agregamos `pitest 'org.pitest:pitest-junit5-plugin:1.1.0'` en nuestras dependencias.

* Configura la herramienta de pruebas de mutación para el proyecto y ejecuta un análisis de
mutación en la clase TowerDefenseGame.

### Evaluacion de cobertura de pruebas

Para ello ejecutamos `./gradlew build y ./gradlew pitest

```sh
./gradlew build .
./gradlew pitest
```

Salida de pruebas:

![testp1](/PracticaCalificada4-CC3S2/images/Testp1.png)

![pitest](/PracticaCalificada4-CC3S2/images/pitest.png)

Aun falta reaizar pruebas, por eso se ve muy bajo porcentaje de cobertura.

### Preguntas de dise;o e implementacion

#### Diseño de la clase Map

* ¿Cómo implementarías la clase Mapa para representar el mapa del juego, asegurando que se
puedan agregar y verificar posiciones de torres y caminos?

Implementacion de la clase mapa:

```java
public class Mapa {
    public char[][] mapa = {{' ',' ','1',' ',' '},
                            {' ','1',' ',' ',' '},
                            {'1',' ',' ','1','2'},
                            {' ',' ','1',' ',' '},
                            {' ',' ',' ',' ',' '}};
    // Metodos
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < mapa.length && y >= 0 && y < mapa[0].length;
    }
    public void placeTower(Tower tower, int x, int y) {
        if (isValidPosition(x, y)) {
            mapa[x][y] = 'T';
        }
    }
    public void printMapa() {
        System.out.println("Mapa:");
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                System.out.print("[" + mapa[i][j] + "]");
            }
            System.out.println();
        }
    }
    // Ubicar la base en el mapa
    public Point getBase() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (mapa[i][j] == '2') {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }
    // Addtower por cada hijo
    public void addCannonTower(int x,int y) {
        mapa[x][y] = 'C';
    }
    public void addLaserTower(int x,int y) {
        mapa[x][y] = 'L';
    }
    public void addArrowTower(int x,int y) {
        mapa[x][y] = 'A';
    }
    public void removeTower(int x,int y) {
        mapa[x][y] = ' ';
    }

}
```

Para agregar y verificar posiciones de torres y caminos
solo necesitamos ver si la posicion en el mapa esta representada por `' '` , es decir, una celda vacia.

* Implementa un método en la clase Map llamado isValidPosition(int x, int y) que verifique si
una posición es válida para colocar una torre

Metodo `isvaildPosition`

```java
public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < mapa.length && y >= 0 && y < mapa[0].length;
    }
```

#### Enemigos con diferentes características

* Diseña e implementa una clase SpeedyEnemy que herede de Enemy y tenga una velocidad
mayor pero menos vida.

```java
class SpeedyEnemy extends Enemy {
    public SpeedyEnemy() {
        super(3, 75, 30);
    }
}
```

* ¿Cómo gestionarías el movimiento de los enemigos en el mapa, asegurando que sigan el
camino predefinido?

Primero tenemos que saber en que coord se encuentra la base, luego con una funcion, ir acercandonos.

#### Torres con diferentes habilidades

* Implementa una clase SniperTower que tenga un daño alto y un alcance muy largo pero una
velocidad de disparo baja.

```java
// Clase SniperTower
class SniperTower extends Tower {
    public SniperTower() {
        super(150, 10, 1); // daño, alcance, velocidad de disparo
    }
}
```

* ¿Cómo implementarías el método attack(`List<Enemy> enemies`) en la clase Tower para
atacar a los enemigos dentro de su alcance?

Despues de que las clases enemigas se hayan movido, evaluo sus posiciones actuales, y si estan en rango de torre, aplicamos el metodo `attack`.

#### Sistema de oleadas

* ¿Cómo diseñarías la generación de oleadas para que cada oleada sea progresivamente más
difícil?
* Implementa un método en la clase Wave llamado spawnEnemies() que genere los enemigos
de la oleada y los coloque en el mapa.

#### Sistema de puntuación y salud de la base

• ¿Cómo actualizarías la puntuación del jugador y la salud de la base cuando un enemigo es
derrotado o alcanza la base?
• Implementa un método en la clase Player llamado updateScoreAndHealth(Enemy enemy,
boolean defeated)

### Pruebas estructurales

#### Cobertura de ramas

#### Cobertura de condiciones
