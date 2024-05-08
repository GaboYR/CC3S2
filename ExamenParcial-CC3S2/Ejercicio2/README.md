# Ejercicio 2

## Alumno : Yarleque Ramos Gabriel Gerardo

Este README contiene la informacion de los Sprints 1,2 y 3 del ejercicio2.

### Sprint 1

Configuracion del entorno.

Ejecutamos `./gradlew build` , si sale permiso denegado, entonces escribir:
`chmod +x ./gradlew`, despues de ello no habria problemas.

Configuracion de gradle :

```gradle
plugins {
    id 'java'
}

group 'org.example'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.1'
    testImplementation 'org.junit.jupiter:junit-jupiter-params:5.4.0'
    testImplementation 'org.assertj:assertj-core:3.21.0'
}

test {
    useJUnitPlatform()
}
```

`Clase word`

Metodo guess

```java
 public Score guess(String attempt) {
        var score = new Score(word);
        score.assess(attempt);
        return score;
    }
```