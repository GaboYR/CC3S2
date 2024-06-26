# Actividad Docker-Kubernetes-Microservicios

## V1

Usamos los files de la Practica 4(de prueba) y creamos el Dockerfile

Creamos el Dockerfile en la ubicacion raiz del proyecto, podemos ver la estructura con el comando

```sh
tree 
```

Deberias ver algo parecido a esto

```sh
.
├── app
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       └── main
│   │   │           └── actividad
│   │   │               └── dkm
│   │   │                   ├── Mapa.class
│   │   │                   ├── Player.class
│   │   │                   ├── Point.class
│   │   │                   ├── Tower.class
│   │   │                   ├── TowerDefenseGame.class
│   │   │                   └── Wave.class
│   │   ├── generated
│   │   │   └── sources
│   │   │       ├── annotationProcessor
│   │   │       │   └── java
│   │   │       │       └── main
│   │   │       └── headers
│   │   │           └── java
│   │   │               └── main
│   │   ├── libs
│   │   │   └── app-1.0-SNAPSHOT.jar
│   │   ├── resources
│   │   └── tmp
│   │       ├── compileJava
│   │       │   ├── compileTransaction
│   │       │   │   ├── annotation-output
│   │       │   │   ├── compile-output
│   │       │   │   │   └── actividad
│   │       │   │   │       └── dkm
│   │       │   │   ├── header-output
│   │       │   │   └── stash-dir
│   │       │   │       ├── ArrowTower.class.uniqueId5
│   │       │   │       ├── CannonTower.class.uniqueId0
│   │       │   │       ├── LaserTower.class.uniqueId2
│   │       │   │       ├── Mapa.class.uniqueId1
│   │       │   │       ├── SniperTower.class.uniqueId3
│   │       │   │       ├── Tower.class.uniqueId4
│   │       │   │       └── TowerDefenseGame.class.uniqueId6
│   │       │   └── previous-compilation-data.bin
│   │       └── jar
│   │           └── MANIFEST.MF
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── actividad
│       │   │       └── dkm
│       │   │           ├── Mapa.java
│       │   │           ├── Player.java
│       │   │           ├── Point.java
│       │   │           ├── TowerDefenseGame.java
│       │   │           ├── Tower.java
│       │   │           └── Wave.java
│       │   └── resources
│       └── test
│           ├── java
│           │   └── actividad
│           │       └── dkm
│           └── resources
├── Dockerfile
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle
```

Contenido del `Dockerfile`

```dockerfile
# Version de la imagen de Java y Alpine
FROM openjdk:17-jdk-alpine3.13
# Directorio de trabajo
WORKDIR /app
# Copiamos el contenido de la carpeta actual a la carpeta de trabajo
COPY . /app
# Compilamos el proyecto
RUN javac app/src/main/java/actividad/dkm/*.java
# Ejecutamos el proyecto
CMD ["java", "-cp", "src/main/java","actividad.dkm.TowerDefenseGame"]
```

Ahora secuencialmente ejecutamos los siguientes comandos

```sh
#Para construir la imagen a partir del Dockerfile
docker build -t tower-defense-game .
```

Deberia salirte en consola algo similar a esto

```txt
[+] Building 2.2s (9/9) FINISHED                                                                                                     docker:default
 => [internal] load build definition from Dockerfile                                                                                           0.0s
 => => transferring dockerfile: 213B                                                                                                           0.0s
 => [internal] load metadata for docker.io/library/openjdk:17-jdk-alpine3.13                                                                   0.8s
 => [internal] load .dockerignore                                                                                                              0.0s
 => => transferring context: 2B                                                                                                                0.0s
 => [1/4] FROM docker.io/library/openjdk:17-jdk-alpine3.13@sha256:7ee07585addded886cfea377f2ff6d03c707509ef2426b70bd6e1d4e8a3fc7e0             0.0s
 => [internal] load build context                                                                                                              0.0s
 => => transferring context: 5.77kB                                                                                                            0.0s
 => CACHED [2/4] WORKDIR /app                                                                                                                  0.0s
 => [3/4] COPY . /app                                                                                                                          0.1s
 => [4/4] RUN javac app/src/main/java/actividad/dkm/*.java                                                                                     1.0s
 => exporting to image                                                                                                                         0.1s
 => => exporting layers                                                                                                                        0.1s
 => => writing image sha256:b58933ac0d0456ff4eb1327b1d8a4b29c3116b10e531a532fed9511b0092ce87                                                   0.0s
 => => naming to docker.io/library/tower-defense-game                                                                                          0.0s
 ```

Una vez la imagen este creada ponemos el comando

### Parte de errores

Esta seccion muestra algunos problemas que tuve al momento de crear y ejecutar el contenedor

Al correr

```sh
docker run -it --name tower-defense-container tower-defense-game
```

Aparece esto

```sh
Error: Could not find or load main class actividad.dkm.TowerDefenseGame
Caused by: java.lang.ClassNotFoundException: actividad.dkm.TowerDefenseGame
```

Otro error que podia entender era

```sh
docker run -it --name tower-defense-container tower-defense-game sh
```

```sh
docker: Error response from daemon: Conflict. The container name "/tower-defense-container" is already in use by container "fa52f275f77540bfc383241eedf8a50f74f2d85eb16d250a47ca550f23ea8ab8". You have to remove (or rename) that container to be able to reuse that name.
See 'docker run --help'.
```

### Parte de solucion

Primero debemos remover el contenedor con

```sh
docker rm tower-defense-container
```

Para no tener el problema de contenedores duplicados podemos usar el sgte comando

```sh
docker run --rm -it --name tower-defense-container tower-defense-game sh
```

El `sh` al final abrira una shell en el contenedor.
Es decir que ya estamos en el contenedor.

Salida al ejecutar

```sh
/app #
```

Para ver que los directorios de trabajo se hayan agregado al directorio de trabajo podemos ejecutar `tree`.
Sale error, lo agregamos con

```sh
apk add tree
```

```sh
/app # tree
sh: tree: not found
/app # apk install tree
ERROR: 'install' is not an apk command. See 'apk --help'.
/app # apk add tree
fetch https://dl-cdn.alpinelinux.org/alpine/v3.13/main/x86_64/APKINDEX.tar.gz
fetch https://dl-cdn.alpinelinux.org/alpine/v3.13/community/x86_64/APKINDEX.tar.gz
(1/1) Installing tree (1.8.0-r0)
Executing busybox-1.32.1-r6.trigger
OK: 8 MiB in 21 packages
/app # tree
.
├── Dockerfile
├── README.md
├── app
│   ├── build
│   │   ├── classes
│   │   │   └── java
│   │   │       ├── main
│   │   │       │   └── actividad
│   │   │       │       └── dkm
│   │   │       │           ├── Mapa.class
│   │   │       │           ├── Player.class
│   │   │       │           ├── Point.class
│   │   │       │           ├── Tower.class
│   │   │       │           ├── TowerDefenseGame.class
│   │   │       │           └── Wave.class
│   │   │       └── test
│   │   ├── generated
│   │   │   └── sources
│   │   │       ├── annotationProcessor
│   │   │       │   └── java
│   │   │       │       └── main
│   │   │       └── headers
│   │   │           └── java
│   │   │               └── main
│   │   ├── libs
│   │   │   └── app-1.0-SNAPSHOT.jar
│   │   ├── resources
│   │   │   ├── main
│   │   │   └── test
│   │   └── tmp
│   │       ├── compileJava
│   │       │   ├── compileTransaction
│   │       │   │   ├── annotation-output
│   │       │   │   ├── compile-output
│   │       │   │   │   └── actividad
│   │       │   │   │       └── dkm
│   │       │   │   ├── header-output
│   │       │   │   └── stash-dir
│   │       │   │       ├── ArrowTower.class.uniqueId5
│   │       │   │       ├── CannonTower.class.uniqueId0
│   │       │   │       ├── LaserTower.class.uniqueId2
│   │       │   │       ├── Mapa.class.uniqueId1
│   │       │   │       ├── SniperTower.class.uniqueId3
│   │       │   │       ├── Tower.class.uniqueId4
│   │       │   │       └── TowerDefenseGame.class.uniqueId6
│   │       │   └── previous-compilation-data.bin
│   │       └── jar
│   │           └── MANIFEST.MF
│   ├── build.gradle
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── actividad
│       │   │       └── dkm
│       │   │           ├── Mapa.class
│       │   │           ├── Mapa.java
│       │   │           ├── Player.class
│       │   │           ├── Player.java
│       │   │           ├── Point.class
│       │   │           ├── Point.java
│       │   │           ├── Tower.class
│       │   │           ├── Tower.java
│       │   │           ├── TowerDefenseGame.class
│       │   │           ├── TowerDefenseGame.java
│       │   │           ├── Wave.class
│       │   │           └── Wave.java
│       │   └── resources
│       └── test
│           ├── java
│           │   └── actividad
│           │       └── dkm
│           └── resources
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle
```

Con eso comprobamos que todos los archivos hayan sido copiados de forma correcta

Ahora el comando

```sh
#Primero compilamos los .java
javac src/main/java/actividad/dkm/*.java -d out
#Despues ubicamos el que contiene la funcion main
java -cp out actividad.dkm.TowerDefenseGame
```

Salida en consola interactiva

```sh
Bienvenido a Tower Defense Game
Oleada iniciada!
```

Para salir ponemos

```sh
/app/app # exit
gabriel@192:DockerKubMic$ 
```

Ahora debe haber forma de agregar estos comandos o instrucciones al Dockerfile, no lo se.

### Configuracion de redes y volumenes en Docker

Ponemos el comando

```sh
docker network create game-network
#Resultado
c9d0e2b16e884361a293bd82f7f13ea933f302d2f8244dd19ac3b521b48d2152
```

```sh
docker run -it --name tower-defense-container --network game-network tower-defense-game
```
