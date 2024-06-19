# Actividad-Mutaciones

Definicion de conceptos basicos:

## Configuracion del build.gradle

Para VSC uso el sgte `build`:

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
    pitest 'org.pitest:pitest-junit5-plugin:1.1.0'
}

test {
    useJUnitPlatform()
}

pitest {
    targetClasses = ['actividad.mutaciones*'] // Paquete de clases a mutar
    mutators = ['DEFAULTS'] // Conjunto de mutadores [OLD_DEFAULTS, DEFAULTS, STRONGER, ALL]
    outputFormats = ['HTML'] // Formato de salida del informe
    timestampedReports = false // Deshabilitar informes con marca de tiempo para facilitar la navegación
    verbose = true
}

// ./gradlew pitest
```

Importante cambiar el paquete de clases a mutar, podria habe errores al momento de ejecutar el `PIT`

## Pasos

* `./gradlew build`
* `./gradlew pitest`

Despues nos vamos a la ruta `app/build/reports/pitest` y abrimos `index.html`.

Veremos lo sgte:

![pitest](/Actividad-Mutaciones/images/pitest.png)

Y si revisamos mas veremos:

![mutators](/Actividad-Mutaciones/images/mutators.png)
