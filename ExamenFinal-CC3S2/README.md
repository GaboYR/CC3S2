# Examen final

Alumno: Yarleque Ramos Gabriel

Debemos implementar un sistema gestor de clima que prediga las condiciones del ambiente.
Implementaremos un sistema de uestra de alertas, la de acciones automaticas y notificaciones funcionan de forma similar.

Si el tiempo me alcanza, implementare el sistema de notificaciones(interfaz).

## Actividades para el final

Play with docker: <https://github.com/GaboYR/CC3S2/tree/main/PlayWithDocker>

Play with Kubernetes: <https://github.com/GaboYR/CC3S2/tree/main/PlayWithKubernetes>

Microservicios V3: <https://github.com/GaboYR/CC3S2/tree/main/Docker-Kubernetes-Microservicios>

## Sprint1

### Dise;o e implementacion inicial

Para el dise;o de clases aplicando el principio de Responsabilidad Unica, separaremos el trabajo en 6 clases.

![im1](/ExamenFinal-CC3S2/images/im1.png)

Contenido de cada clase:

* Temperatura

```java
package examenfinal.sprint1;

public class Temperatura {
    private double temperatura;
    // constructores
    public Temperatura() {
    }
    public double getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
    // Muestra si hay alerta o no
    public String showAlerta(){
        if (temperatura > 30){
            return "Alerta de temperatura alta";
        } else {
            return "Temperatura normal";
        }
    }
}
```

* Lluvia

```java
package examenfinal.sprint1;

public class Lluvia {
    private double cantidadLluvia;
    //Contructores
    public Lluvia() {
    }
    //Getters y Setters
    public double getCantidadLluvia() {
        return cantidadLluvia;
    }
    public void setCantidadLluvia(double cantidadLluvia) {
        this.cantidadLluvia = cantidadLluvia;
    }
    // alerta de lluvia
    public String showAlerta(){
        if (cantidadLluvia > 50){
            return "Alerta de lluvia fuerte";
        } else {
            return "Lluvia normal";
        }
    }
}
```

* Viento

```java
package examenfinal.sprint1;

public class Viento {
    private double velocidadViento;
    // constructores
    public Viento(){
        this.velocidadViento = 0;
    }
    public double getVelocidad(){
        return velocidadViento;
    }
    public void setVelocidadViento(double velocidadViento){
        this.velocidadViento = velocidadViento;
    }
    // Muestra si hay alerta o no
    public String showAlerta(){
        if (velocidadViento > 20){
            return "Alerta de viento fuerte";
        } else {
            return "Viento normal";
        }
    }
}
```

* Humedad

```java
package examenfinal.sprint1;

public class Humedad {
    private double porcentajeHumedad;
    // constructores
    public Humedad() {
    }
    public double getHumedad() {
        return porcentajeHumedad;
    }
    public void setHumedad(double porcentajeHumedad) {
        this.porcentajeHumedad = porcentajeHumedad;
    }
    // Muestra si hay alerta o no
    public String showAlerta(){
        if (porcentajeHumedad > 80){
            return "Alerta de humedad alta";
        } else {
            return "Humedad normal";
        }
    }
}
```

* Presion

```java
package examenfinal.sprint1;

public class Presion {
    private double presion;
    // constructores
    public Presion() {
    }
    public double getPresion() {
        return presion;
    }
    public void setPresion(double presion) {
        this.presion = presion;
    }
    // Muestra si hay alerta o no
    public String showAlerta(){
        if (presion > 500){
            return "Alerta de presion alta";
        } else {
            return "Presion normal";
        }
    }
}
```

Cada una de ellas realizara una unica funcion.

Tambien se puede implementar interfaces.

Salida en consola de la clase `SistemaGestor`

![im2](/ExamenFinal-CC3S2/images/im2.png)

Salida con implementacion de `Alerta`.

![im3](/ExamenFinal-CC3S2/images/im3.png)

### Desarrollo con TDD

#### RED

Haremos que las pruebas fallen y despues implementaremos el codigo necesario para que sean aceptadas.

* TempTest fallida

![im4](/ExamenFinal-CC3S2/images/TempR.png)

* LluviaTest Fallida

![im5](/ExamenFinal-CC3S2/images/LluviaR.png)

* VientoTest Fallido

![im6](/ExamenFinal-CC3S2/images/VientoR.png)

* HumedadTest fallido

![im7](/ExamenFinal-CC3S2/images/HumedadR.png)

* PresionTest fallido

![im8](/ExamenFinal-CC3S2/images/PresionR.png)

#### GREEN

Esta seccion es de implementacion de codigo para que las pruebas sean aceptadas.

* TemperaturaTest y pruebas unitarias aceptadas

![im9](/ExamenFinal-CC3S2/images/TempTest.png)

* LluviaTest y pruebas unitarias aceptadas

![im10](/ExamenFinal-CC3S2/images/Lluviatest.png)

* VientoTest y pruebas unitarias aceptadas

![im11](/ExamenFinal-CC3S2/images/VientoTest.png)

* HumedadTest y pruebas unitarias aceptadas

![im12](/ExamenFinal-CC3S2/images/HumeTest.png)

* PresionTest y pruebas unitarias aceptadas

![im13](/ExamenFinal-CC3S2/images/PresTest.png)

#### REFACTOR

Una vez que las pruebas hayan sido aceptadas, refactorizamos el codigo.
He notado que cada clase que no sea la principal, cuenta con el metodo `showAlerta`.
Podemos implementar una interfaz `Alerta` que contenga este metodo.
Asi cada clase implementara esta interfaz y cumplira con el principio de Responsabilidad Unica y el principio de Segregacion de Interfaces.

```java
public interface Alerta {
    public String showAlerta();
}
```

Y cada clase implementara esta interfaz.

```java
public class Temperatura implements Alerta{
    private double temperatura;
    // constructores
    public Temperatura() {
    }
    public double getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
    // Muestra si hay alerta o no
    @Override
    public String showAlerta(){
        if (temperatura > 30){
            return "Alerta de temperatura alta";
        } else {
            return "Temperatura normal";
        }
    }
}
```

```java
public class Viento implements Alerta{
    private double velocidadViento;
    // constructores
    public Viento(){
        this.velocidadViento = 0;
    }
    public double getVelocidad(){
        return velocidadViento;
    }
    public void setVelocidadViento(double velocidadViento){
        this.velocidadViento = velocidadViento;
    }
    // Muestra si hay alerta o no
    @Override
    public String showAlerta(){
        if (velocidadViento > 20){
            return "Alerta de viento fuerte";
        } else {
            return "Viento normal";
        }
    }
}
```

Y asi con las demas clases.

### Validacion de pruebas con stubs y fakes

Agregamos a nuestro archivo `build.gradle` las dependencias necesarias para poder realizar las pruebas con stubs y fakes.

```java
    testImplementation 'org.mockito:mockito-inline:3.12.4'
    testImplementation 'org.mockito:mockito-junit-jupiter:3.12.4'
```

### Refactorizacion y codigo limpio

Esta seccion y se implemento en la seccion de TDD, se aplican los principios SOLID.

### Metricas de calidad

Uso de `Jacoco` para medir la calidad del codigo.

Modificamos el `biuld.gradle` para que se ejecute el plugin de `Jacoco`.

```gradle
/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/8.0.2/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    id 'application'
    id 'jacoco'
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation 'org.junit.jupiter:junit-jupiter:5.9.1'

    // This dependency is used by the application.
    implementation 'com.google.guava:guava:31.1-jre'
    testImplementation 'org.mockito:mockito-inline:3.12.4'
    testImplementation 'org.mockito:mockito-junit-jupiter:3.12.4'
    //Jacoco
    testImplementation 'org.jacoco:org.jacoco.core:0.8.7'
}

application {
    // Define the main class for the application.
    mainClass = 'examenfinal.cc3s2.App'
}

tasks.named('test') {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
jacoco {
    toolVersion = "0.8.12" // Versión de JaCoCo (compatible con java 21)
}

jacocoTestReport {
    dependsOn test // Ejecuta las pruebas antes de generar el informe

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
check.dependsOn jacocoTestCoverageVerification
```

Construimos el gradle verificando que no haya errores

![im14](/ExamenFinal-CC3S2/images/build.png)

Ejecutamos `./gradlew jacocoTestReport` para generar el reporte de `Jacoco`.

![im16](/ExamenFinal-CC3S2/images/jacocoreport.png)

Una vez generado, podemos ver el reporte en la carpeta `build/reports/jacoco/test/html/index.html`.

![im15](/ExamenFinal-CC3S2/images/jacocoTest.png)

![im17](/ExamenFinal-CC3S2/images/jacocoresults.png)

Tenemos un promedio de 84% de cobertura de codigo sin contar la clase `SistemaGestor`.

Veamos la complejidad ciclomatica de cada clase.

Formula de la complejidad ciclomatica:

```md
M = E - N + 2P
```

Donde `E` es el numero de aristas, `N` es el numero de nodos y `P` es el numero de componentes conexos.

* Temperatura
* Lluvia
* Viento
* Humedad
* Presion

## Sprint2

### Contenerizacion del sistema

En VSC cuando construimos un GRADLE, se genera todos los archivos dentro de una carpeta `app`,la borre porque causaba problemas al construirla imagen de Docker.

Entonces contruimos la imagen con el comando

```bash
docker build -t sistema-gestor .
```

![im18](/ExamenFinal-CC3S2/images/dockerfile.png)

Creacion del `docker-compose.yml`

```yml
version: '1'
services:
  sistema-gestor-service:
    build: .
    container_name: sistema-container
```

Es un archivo simple que no incluye volumenes ni puertos.

**Nota: El Dockerfile debe estar en la misma carpeta que el archivo**`docker-compose.yml`.

Ejecucion del comando `docker-compose up -d`

![yaml](/ExamenFinal-CC3S2/images/yaml.png)

No hemos tenido problemas al construir la imagen y al ejecutar el contenedor.(En realidad si pero se soluciono).

Verificamos si podemos correr el contenedor que hemos creado con el comando

```sh
docker run --rm -it --name sistema-gestor sistema-container
```

![im19](/ExamenFinal-CC3S2/images/dockerun.png)

Todo funciona correctamente.
