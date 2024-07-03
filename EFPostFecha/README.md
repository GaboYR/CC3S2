# Examen Final Post-10am

Trabajo anterior: <https://github.com/GaboYR/CC3S2/tree/main/ExamenFinal-CC3S2>

Incluye actividades para el examen final.

## Sprint 1

### Pruebas con mocks,stubs y fakes

En el avance anterior falto agregar algunas pruebas con `fakes` y `stubs`.

Stubs aara las clases Temperatura y Lluvia.

![im1](/EFPostFecha/images/stubs.png)

Fakes para las clases Temperatura y Lluvia.

![im2](/EFPostFecha/images/fakes.png)

Vemos el check verde al lado izquierdo de las funciones implementadas,
esto indica que las pruebas pasaron exitosamente.

### Metricas de codigo

Para esta seccion usaremos `sonarqube` para analizar el codigo fuente y ver las metricas de calidad.

Debemos inicializar el servicio de sonarqube con el siguiente comando:

```bash
sonarqube.sh start
```

Modificamos el archivo `build.gradle` para agregar las dependencias necesarias para sonarqube.

```gradle
// Configuración de SonarQube
sonarqube {
    properties {
        property "sonar.projectKey", "sprint2" // Reemplaza con tu clave de proyecto
        property "sonar.host.url", "http://localhost:9000" // Reemplaza con la URL de tu servidor SonarQube
        property "sonar.login", "sqp_f2bcf3b312a769fdd44174a69d8e25adcab422be" // Reemplaza con tu token de autenticación
        property "sonar.java.coveragePlugin", "jacoco"
        property "sonar.coverage.jacoco.xmlReportPaths", "${buildDir}/reports/jacoco/test/jacocoTestReport.xml"
    }
}
```

Luego ejecutamos el comando para analizar el codigo fuente.

```bash
./gradlew sonar   -Dsonar.projectKey=sprint   -Dsonar.projectName='sprint2'   -Dsonar.host.url=http://localhost:9000   -Dsonar.token=sqp_f2bcf3b312a769fdd44174a69d8e25adcab422be
```

Ese comando se encuentra en la pagina de sonarqube.Al momento de crear un nuevo proyecto, se genera un token de autenticacion que se debe usar para analizar el codigo fuente y el comando anterior.

Revision del codigo fuente con sonarqube:

Complejidad ciclomatica total: 34

![im](/EFPostFecha/images/sonar.png)

Complejidad ciclomatica de clase Viento: 7
![im](/EFPostFecha/images/sonarViento.png)

Complejidad ciclomatica de interfaz Temperatura: 0
![im](/EFPostFecha/images/sonarinterfaz.png)

Los resultados alcanzados son buenos, ya que la complejidad ciclomatica total es de 34, lo cual es un valor aceptable.

Ademas la complejidad ciclomatica por clase no excede el valor de 10, lo cual es un valor aceptable.

## Sprint 2

### Contenerizacion del sistema

El sistema ya fue contenerizado,se muestra en la primera entrega.

### Refinamiento del TDD

Implementacion de pruebas con TDD para las clases existentes

* Clase viento

Haremos pruebas sobre los nuevos metodos de la clase viento.
Es decir, los metodos `showNotification` y `showAction`.

![im](/EFPostFecha/images/test2.png)

Las pruebas para las demas clases son similares ya que provienen de la misma interfaz.(Por cuestiones de tiempo no se muestran)

Vemos que las pruebas pasaron exitosamente por el check verde al lado izquierdo de las funciones implementadas.

### Mejora de la estrategia de pruebas

### Refactorizacion y codigo limpio

Ya se aplicaron los principios SOLID y se refactorizo el codigo para que sea mas limpio y entendible.
Pero note una seccion de codigo que podria ser refactorizada.

Por ejm, en la clase humedad se tiene

```java
@Override
public String showAlerta(){
    if (porcentajeHumedad > 80){
        return "Alerta de humedad alta";
    } else {
        return "Humedad normal";
    }
}
```

Haremos el sgte cambio, creamos una constante `HUMEDAD_MAX` y la usamos en el if, asi mejoramos la legibilidad.

Ese cambio se puede realizar en las demas clases.

```java
package examenfinal.sprint2;

public class Humedad implements Alerta, Notificaciones, Acciones {
    public static final double HUMEDAD_MAX = 80;
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
    @Override
    public String showAlerta(){
        if (porcentajeHumedad > HUMEDAD_MAX){
            return "Alerta de humedad alta";
        } else {
            return "Humedad normal";
        }
    }
    @Override
    public String showNotification(){
        return "Notificación de humedad";
    }
    @Override
    public String showAction(){
        return "Acción de humedad";
    }
}
```

Mostramos un cambio en la clase humedad, pero tambien se puede realizar en las demas clases.

### Metricas de calidad

Ya se muestra en la primera parte de esta entrega.

Falta agregar la metrica de cobertura de codigo con las pruebas realizadas.

### Salida esperada

Finalmente, salida del codigo:

![im](/EFPostFecha/images/salida.png)
