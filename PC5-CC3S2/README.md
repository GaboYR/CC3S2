# Practica Calificada 5

Esta parte corresponde a lo que es la parte 2 de la actividad de `Docker-Kubernetes-MicroServicios`

## Ejercicio 1

* Describe los principios fundamentales de los contenedores Docker y su arquitectura interna.Explica cómo Docker maneja la seguridad y el aislamiento de contenedores.

Docker es un software que permite desplegar aplicaciones en contenedores

* Compara y contrasta Docker con soluciones de virtualización tradicionales, como VMware y VirtualBox. Discute las ventajas y desventajas de cada enfoque.

La principal diferencia entre Docker para contenerizacion y maq virtuales como VMWare es que contener es mas ligero que virtualizar porque el proceso de simular una maquina involucra la carga del SO y eso demanda recursos. Por otro lado, virtualizacion es mas seguro porque se trabaja en un entorno aislado.

Empezamos creando el proyecto gradle y agregando las clases necesarias.

Archivo Dockerfile

```docker
# Version de la imagen de Java y Alpine
FROM openjdk:17-jdk-alpine3.13
# Directorio de trabajo
WORKDIR /app
# Copiamos el contenido de la carpeta actual a la carpeta de trabajo
COPY . /app
# Compilamos el proyecto
RUN javac app/src/main/java/pc5/cc3s2/*.java
# Ejecutamos el proyecto
#CMD ["java", "-cp", "src/main/java","pc5.cc3s2.TowerDefenseGame"]
CMD ["sh"]
```

Para construir la imagen con el comando

```sh
docker build -t tower-defense-game .
```

Veremos en consola:

```txt
[+] Building 3.1s (9/9) FINISHED                                                                                                    docker:default
 => [internal] load build definition from Dockerfile                                                                                          0.0s
 => => transferring dockerfile: 388B                                                                                                          0.0s
 => [internal] load metadata for docker.io/library/openjdk:17-jdk-alpine3.13                                                                  1.2s
 => [internal] load .dockerignore                                                                                                             0.0s
 => => transferring context: 2B                                                                                                               0.0s
 => [1/4] FROM docker.io/library/openjdk:17-jdk-alpine3.13@sha256:7ee07585addded886cfea377f2ff6d03c707509ef2426b70bd6e1d4e8a3fc7e0            0.0s
 => [internal] load build context                                                                                                             0.0s
 => => transferring context: 179.07kB                                                                                                         0.0s
 => CACHED [2/4] WORKDIR /app                                                                                                                 0.0s
 => [3/4] COPY . /app                                                                                                                         0.1s
 => [4/4] RUN javac app/src/main/java/pc5/cc3s2/*.java                                                                                        1.5s
 => exporting to image                                                                                                                        0.1s
 => => exporting layers                                                                                                                       0.1s
 => => writing image sha256:d5607a9e2aba5ccb406d799095bf0824a388cc072c2864f31d273184386b8902                                                  0.0s
 => => naming to docker.io/library/tower-defense-game
```

Ahora para ver el contenedor

```sh
docker run --rm -it --name tower-defense-container tower-defense-game
```

Veremos en consola

```sh
docker run --rm -it --name tower-defense-container tower-defense-game
/app # 
```

Ahora, en otra terminal,realizamos la ejecucion del comando `exec`

```sh
docker exec -it tower-defense-container /bin/bash
#Resultado
OCI runtime exec failed: exec failed: unable to start container 
process: exec: "bin/bash": stat bin/bash: no such file or directory: unknown
```

Si cambiamos `/bin/bash` por `sh` ya no habra errores.

Ademas si listamos los contenedores con

```sh
docker container ls
```

Veremos

```txt
CONTAINER ID   IMAGE                COMMAND   CREATED         STATUS         PORTS     NAMES
3ca47a3dcfd4   tower-defense-game   "sh"      3 minutes ago   Up 3 minutes             tower-defense-container
```

## Ejercicio 2 (redes y volumenes)

## Redes en Docker

Ejecucion del comando

```sh
docker network create game-network
# Resultado
c23c83b51f2980510e3a842ef332f140d9b41eb764d2394a6d4568adfe4e8b3c
```

## Volumenes en Docker

Para crear un volumen en Docker se usa la instruccion

```sh
docker volume create game-data
```

Para listar los volumenes y verificar su creacion con

```sh
docker volume ls
```

Ahora ejcutamos el comando

```sh
docker run -it --name tower-defense-container --network game-network -v game-data:/app/data tower-defense-game
```

Y se abrira una shell interactiva

## Docker compose

Docker Compose es una herramienta para definir y ejecutar aplicaciones Docker multi-contenedor. Con un archivo YAML, se pueden configurar los servicios, redes y volúmenes necesarios para la aplicación.

Se requiere haber instalado docker-compose, verificamos si tenemos docker-compose con

```sh
docker-compose --version
```

Aparece Docker Compose version v2.28.1

Ahora corremos el comando

```sh
docker-compose up -d
```

en la ruta de nuestro trabajo.

Deberiamos ver una salida similar a esta:

```txt
WARN[0000] /home/gabriel/Documents/UNI/24_1/DesarrolloSoftware/CC3S2/PC5-CC3S2/docker-compose.yml: `version` is obsolete 
[+] Running 3/3
 ✔ Network pc5-cc3s2_game-network  Created               0.1s 
 ✔ Volume "pc5-cc3s2_game-data"    Created               0.0s 
 ✔ Container pc5-cc3s2-game-1      Started               0.9s
```

## Docker Swarm

Docker Swarm es una herramienta nativa de Docker para la orquestación de contenedores, permitiendo
la gestión de un clúster de Docker.

## Orquestacion con Kubernetes

### Uso de Minikube

Lo usaremos para crear un cluster de Kubernetes en un contenedor.

Comando

```sh
minikube start
```

En caso de error(me paso) puedes poner lo sgte

```sh
minikube stop
minikube delete
minikube start
```

Veamos si podemos acceder al cluster con

```sh
kubectl config get-contexts
```

Salida en consola

```sh
kubectl config get-contexts
CURRENT   NAME       CLUSTER    AUTHINFO   NAMESPACE
*         minikube   minikube   minikube   default
```

```sh
kubectl get nodes
NAME       STATUS   ROLES           AGE     VERSION
minikube   Ready    control-plane   3m30s   v1.30.0
```

Antes de la ejecucion de los files `.yaml`, su configuracion:

* deployment.yaml

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: tower-defense-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      app: tower-defense-game
  template:
    metadata:
      labels:
        app: tower-defense-game
    spec:
      containers:
        - name: tower-defense-game
          image: tower-defense-game
          ports:
            - containerPort: 8080
```

* services.yaml

```yaml
apiVersion: v1
kind: Service
metadata:
  name: tower-defense-service
spec:
  selector:
    app: tower-defense-game
  ports:
  - protocol: TCP
    port: 80
    targetPort: 8080
  type: LoadBalancer
```

Ahora la ejecucion en el cluster con los files de extension `.yaml`

```sh
kubectl apply -f service.yaml
kubectl apply -f deployment.yaml
```

Deberia aparecer algo como esto:

```sh
gabriel@192:PC5-CC3S2$ kubectl apply -f service.yaml 
service/tower-defense-service created
gabriel@192:PC5-CC3S2$ kubectl apply -f deployment.yaml 
deployment.apps/tower-defense-deployment created
```

## Pods

```sh
gabriel@192:PC5-CC3S2$ kubectl get pods
NAME                                        READY   STATUS             RESTARTS   AGE
tower-defense-deployment-749d7bbdcd-8tmb5   0/1     ImagePullBackOff   0          4m5s
gabriel@192:PC5-CC3S2$ kubectl get services
NAME                    TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)        AGE
kubernetes              ClusterIP      10.96.0.1       <none>        443/TCP        15m
tower-defense-service   LoadBalancer   10.105.150.15   <pending>     80:30602/TCP   7m12s
gabriel@192:PC5-CC3S2$ kubectl get deployments
NAME                       READY   UP-TO-DATE   AVAILABLE   AGE
tower-defense-deployment   0/1     1            0           7m20s
```

## Deployment

Usamos el comando `kubectl get pods` y extraemos el nombre del pod, en este caso es `tower-defense-deployment-749d7bbdcd-8tmb5`

```sh
kubectl expose pod tower-defense-deployment-749d7bbdcd-8tmb5 --type=NodePort --port=80
```

Nos debe salir en consola algo similar a

```sh
service/tower-defense-deployment-749d7bbdcd-8tmb5 exposed
```

Ahora corremos el comando

```sh
kubectl get services
```

Debe salir algo como

```sh
NAME                                        TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)        AGE
kubernetes                                  ClusterIP      10.96.0.1       <none>        443/TCP        29m
tower-defense-deployment-749d7bbdcd-8tmb5   NodePort       10.101.75.207   <none>        80:31569/TCP   2m18s
tower-defense-service                       LoadBalancer   10.105.150.15   <pending>     80:30602/TCP   21m
```

Luego colocamos el comando

```sh
minikube service tower-defense-deployment-749d7bbdcd-8tmb5
```

## Pruebas unitarias y de integracion con Mockito

### Teoria

* Explica los conceptos de mocks, stubs y fakes. Discute cuándo y cómo se deben utilizar estos patrones en las pruebas unitarias.
* * Mocks sirven para verificar llamadas a ciertos metodos.
* * Stubs brindan respuestas a las llamadas por prueba.
* * Fakes funcionan de forma independiente pero no necesariamente simula toda la funcionalidad del objeto.
* Escribe pruebas unitarias para la clase TowerDefenseGame utilizando Mockito para simular las dependencias de Map, Player y Wave

```java
public class TowerDefenseGameTest {
    
    @Mock
    private Map mockMap;

    @Mock
    private Player mockPlayer;

    @Mock
    private Wave mockWave;

    @InjectMocks
    private TowerDefenseGame towerDefenseGame;

    @BeforeEach
    public void setup() {
        mockMap = mock(Map.class);
        mockPlayer = mock(Player.class);
        mockWave = mock(Wave.class);
        towerDefenseGame = new TowerDefenseGame(mockMap, mockPlayer);
    }
    // Test para verificar torre en (0, 0)
    // Usando mocks para simular el mapa y el jugador
    @Test
    public void testPlaceTower() {
        Tower tower = new Tower('T');
        towerDefenseGame.placeTower(tower, 0, 0);
        verify(mockMap).placeTower(eq(tower), eq(0), eq(0));
        when(mockMap.getTile(0, 0)).thenReturn('T');
        assertEquals('T', mockMap.getTile(0, 0));
    }
}
```

* Implementa pruebas de integración que verifiquen la interacción entre las clases principales (TowerDefenseGame, Map, Player, Wave). Utiliza Mockito para controlar y verificar el comportamiento de las dependencias en estas pruebas.
Resultados de las pruebas :

```java
public class TDGIntegrationTest {

    @Mock
    private Map mockMap;

    @Mock
    private Player mockPlayer;

    @InjectMocks
    private TowerDefenseGame towerDefenseGame;

    @BeforeEach
    public void setup() {
        mockMap = mock(Map.class);
        mockPlayer = mock(Player.class);

        towerDefenseGame = new TowerDefenseGame(mockMap, mockPlayer);
    }

    @Test
    public void testPlaceTower() {
        Tower tower = new Tower('T');
        towerDefenseGame.placeTower(tower, 0, 0);

        // Verificar que el método placeTower fue llamado en el mock
        verify(mockMap).placeTower(eq(tower), eq(0), eq(0));

        // Configurar el comportamiento del mock para devolver el símbolo correcto
        when(mockMap.getTile(0, 0)).thenReturn('T');

        // Verificar que el símbolo es el correcto
        assertEquals('T', mockMap.getTile(0, 0));
    }

    @Test
    public void testStartWave() {
        towerDefenseGame.startWave();

        // Verificar que una nueva ola fue añadida
        List<Wave> waves = towerDefenseGame.getWaveList();
        assertEquals(1, waves.size());

        // Verificar que la ola está activa
        assertTrue(waves.get(0).isFinished());
    }

    @Test
    public void testGameState() {
        when(mockPlayer.getScore()).thenReturn(100);
        when(mockPlayer.getBaseHealth()).thenReturn(50);

        towerDefenseGame.gameState();

        // Verificar que los métodos getScore y getBaseHealth fueron llamados
        verify(mockPlayer).getScore();
        verify(mockPlayer).getBaseHealth();
    }
}
```

Resultados y explicacion de las pruebas:

![ockTestAll](/PC5-CC3S2/images/MockTestAll.png)

Explicare este test

```java
@Test
    public void testPlaceTower() {
        Tower tower = new Tower('T');
        towerDefenseGame.placeTower(tower, 0, 0);
        verify(mockMap).placeTower(eq(tower), eq(0), eq(0));
        when(mockMap.getTile(0, 0)).thenReturn('T');
        assertEquals('T', mockMap.getTile(0, 0));
    }
```

Lo que hacemos es primero inicializar una clase `Tower`, lo importante aqui es el tipo de torre, y se insertara en el mock de `towerDefenseGame`, luego con `verify` evaluamos si en el mock de mapa se coloco la torre en la pos 0,0.
Con `when` se evalua la funcion `getTile`, debe retornar un caracter, en este caso, `T`.
Y un `assertEquals` para ver si `T` esta ubicado en la posicion (0,0) del mock de Map

Las otras pruebas funcionan de forma similar.

## Pruebas de mutacion

* Define qué son las pruebas de mutación y cómo contribuyen a la mejora de la calidad del software. Explica los tipos de operadores de mutación y su propósito.

Una prueba de mutacion es una forma de medir las pruebas `alterando` partes del codigo original de forma intencionada con el fin de encontrar fallas y susceptibilidades. Hay mutaciones del tipo aritmetico(por ejm cambiar un signo, por ejm de `+` a `-`), otro del tipo logico (`==` por `!=`), entre otros.

* Discute las métricas utilizadas para evaluar la efectividad de las pruebas de mutación, como la tasa de mutación (mutation score) y la cobertura de mutación.

Para ello, vamos a ver las mutaciones, entonces debemos ejecutar `./gradlew build pitest`

Pero antes debemos construir el projecto, eso lo hacemos con `./gradlew build`

![build](/PC5-CC3S2/images/build.png)

Resultados de la prueba:

![pitest](/PC5-CC3S2/images/pitest.png)

Vemos que arroja 6 clases, clases contenidas en el `package pc5.cc3s2`, algunas con lineas de cobertura del 0% a 100%, cobertura de mutacion del 0 a 50% y fuerza de prueba de 33 a 100%

## Design by contract

* Explica el concepto de diseño por contrato y cómo se aplica en el desarrollo de software. Discute las diferencias entre precondiciones, postcondiciones e invariantes.

Los dise;os por contrato sirven para acordar y definir responsabilidades de acuerdo a las pre y postcondiciones.

Precondiciones son condiciones que deben cumplirse antes de pasar por un metodo

Post condiciones, lo mismo pero debe cumplirse despues de ser pasada por un metodo

Invariantes aplica para antes y despues.

* Describe cómo el diseño por contrato puede mejorar la robustez y mantenibilidad del
código.

Bueno, mejora la robustez debido a la documentacion facilita la lectura de ciertos metodos, eso contribuye a la legibilidad y facilita su comprension.

Mantenible porque ya se acordaron pre post e invariantes, entonces ya hay cierta especificacion, eso de la mano con refactorizacion y otras tecnicas de codigo limpio permiten mantenibilidad y escalabilidad.

* Aplica el diseño por contrato a la clase Tower. Define las precondiciones, postcondiciones e invariantes de los métodos principales de la clase.

Codigo de `Tower`

```java
public class Tower {
    private char symbol;

    public Tower(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
```

Aplicando DBC(design by contract):

```java
public class Tower {
    private char symbol;

    /**
     * Construye una clase torre con tipo de torre.
     * 
     * @param symbol es el simbolo, para este caso se cuenta con Cannon, Laser, Arrow y SniperTower
     */
    public Tower(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Retorna el simbolo que representa la torre.
     * 
     * @return el simbolo de la torre
     */
    public char getSymbol() {
        return symbol;
    }
}
```

De momento hay invariantes, pero podemos establecer de precondicion que el simbolo no sea nulo o de mas de 1 caracter y perteneza a una letra de las clases hijas

```java
  assert isCharacter(symbol): "symbol debe ser un caracter no nulo"
```

Para la postcondicion, podemos acordar que el simbolo a retornar sea de uno de los tipos de clase Derivada de la Clase Torre y coincida con la letra asignada.

```java
  assert symbol == 'T' || symbol == 'C' || symbol == 'L' || symbol == 'A' || symbol == 'S'
```

* Escribe pruebas unitarias que verifiquen el cumplimiento de los contratos definidos para la clase Tower. Utiliza herramientas como Java Assertions para implementar estas verificaciones.

Clase `TowerTest` con documentacion

```java
public class TowerTest {

    @Test
    public void testTowerConstructorWithValidSymbols() {
        // Precondición: El símbolo debe pertenecer a 'T', 'C', 'L', 'A', 'S'
        char[] validSymbols = {'T','C', 'L', 'A', 'S'};

        for (char symbol : validSymbols) {
            Tower tower = new Tower(symbol);
            // Postcondición: El símbolo de la torre debe ser el dado previamente
            assertEquals(symbol, tower.getSymbol(), "El símbolo no coincide");
        }
    }

    @Test
    public void testTowerConstructorWithInvalidSymbol() {
        // Precondición: El símbolo no pertenece a las clases válidas
        char invalidSymbol = 'X';

        Tower tower = new Tower(invalidSymbol);
        // Postcondición: Simbolo invalido no debe ser aceptado
        assertNotEquals('T', tower.getSymbol(), "El símbolo no coincide");
    }
}
```

Resultados de la prueba:

![towerTest](/PC5-CC3S2/images/TowerTest.png)

* Realiza una revisión de código para asegurarte de que todas las clases del proyecto Tower Defense siguen los principios del diseño por contrato. Documenta cualquier ajuste o mejora necesaria en el código.

Revisamos cada codigo del projecto:

* Clase `Enemy`

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
    public void takeDamage(int damage) {
        health -= damage;
    }
}
```

Podemos agregar como precondicion que `setHealth` reciba un parametro mayor a cero.
Tambien que el metodo `takeDamage` no reste da;o si es que la salud sea mayor o igual al da;o.

```java
public void setHealth(int health) {
  assert health > 0: "Salud no puede ser negativa";
  this.health = health;
}
public void takeDamage(int damage) {
  assert health >= damage : "Da;o no puede exceder a la salud actual"
  health -= damage;
}
```

Clase `Map`

```java
public class Map {
    private char[][] grid;

    public Map() {
        grid = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void placeTower(Tower tower, int x, int y) {
        grid[x][y] = tower.getSymbol();
    }
    public char getTile(int x, int y) {
        return grid[x][y];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            for (char cell : row) {
                sb.append("[").append(cell).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
```

Precondiciones: que la posicion de la torre este en el rango del mapa.

```java
public void placeTower(Tower tower, int x, int y) {
        assert  x >= 0 && x < grid.length && y >= 0 && y < grid[0].length : "Coordenadas fuera de los límites del mapa.";
        grid[x][y] = tower.getSymbol();
}
        

    public char getTile(int x, int y) {
        assert x > 0 && x < grid.length && y > 0 || y < grid[0].length : "Coordenadas fuera de los límites del mapa.";
        return grid[x][y];
    }
```

Clase `Player`

```java
  public class Player {
    private int score;
    private int baseHealth;

    public Player() {
        this.score = 0;
        this.baseHealth = 100;
    }

    public int getScore() {
        return score;
    }

    public int getBaseHealth() {
        return baseHealth;
    }
}
```

Aplicando DBC

```java
public void addScore(int score) {
        assert score >= 0 : "El puntaje a a;adir debe ser no negativo.";
        this.score += score;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void reduceBaseHealth(int amount) {
        assert amount >= 0 : "La cantidad a reducir debe ser no negativa.";
        assert baseHealth - amount >= 0 : "La salud base debe ser mayor al da;o";
        this.baseHealth -= amount;
    }
```

Clase `Tower` ya se le aplico DBC.

Clase `TowerDefenseGame`

```java
public class TowerDefenseGame {
    private Map map;
    private Player player;
    private List<Wave> waves;

    public TowerDefenseGame() {
        this.map = new Map();
        this.player = new Player();
        this.waves = new ArrayList<>();
    }
    public TowerDefenseGame(Map map, Player player) {
        this.map = map;
        this.player = player;
        this.waves = new ArrayList<>();
    }

    public void placeTower(Tower tower, int x, int y) {
        map.placeTower(tower, x, y);
    }

    public void startWave() {
        Wave wave = new Wave();
        waves.add(wave);
        wave.start();
    }

    public List<Wave> getWaveList() {
        return waves;
    }

    public void gameState() {
        System.out.println(map);
        System.out.println("Puntuación: " + player.getScore());
        System.out.println("Vida de la base: " + player.getBaseHealth());
    }
    public static void main(String[] args) {
        TowerDefenseGame game = new TowerDefenseGame();
        game.gameState();
    }
}
```

Con DBC

```java
//Aqui se apliacara una 1ra precondicion y si la pasa entonces ira a las precondiciones la clase map.
public void placeTower(Tower tower, int x, int y) {
        assert x >= 0 && y >= 0 : "Las posiciones deben ser positivas o cero"
        map.placeTower(tower, x, y);
    }
```

Clase `Wave`

```java
public class Wave {
    public void start() {
        System.out.println("Oleada iniciada!");
    }
    public boolean isFinished() {
        return true;
    }
}
```

con DBC

```java
public void start() {
        this.active = true;
        assert this.active : "La ola debe estar activa después de iniciar.";
    }
```
