# Actividad Hello-Cucumber

Primero ejecutamos la instruccion `./gradlew build`.
![build, scale = 0.8](/Actividad-HelloCucumber/images/build.png)

Para solucionar este problema escribimos en la terminal `chmod +x ./gradlew`

![chmod](/Actividad-HelloCucumber/images/chmod.png)

Tenemos la clase `Belly.java`.

```java

public class Belly {
    int eatenCucumbers = 0;
    int timeWaited = 0;
    
    public void reset() { 
        eatenCucumbers = 0; 
        timeWaited = 0;
    }
    public void eat(int cukes) {
        System.out.println("I ate " + cukes + " cucumbers.");
        eatenCucumbers += cukes;
    }
    public void wait(int timeInHours) {
        if (timeInHours > 0) { 
            timeWaited += timeInHours;
        }
    }
    
    public boolean isGrowling() { 
        return timeWaited >= 2 && eatenCucumbers > 10; 
    }
}
```

Dentro de esta clase tenemos la funcion booleana `isGrowling` que retornara `true` si el tiempo esperado supera o es igual a 2 y el numero de cucumber comidos sea mayor a 10.

Dentro del archivo `belly.feature` encontraremos diversos escenarios, uno de ejemplo puede ser el siguiente :

```feature
    Scenario: eaten many cukes and growl
        Given I have eaten 20 cukes
        When I wait 2 hour
        Then My belly should growl
```

Y de acuerdo a esta tabla :

![table](/Actividad-HelloCucumber/images/feature.png)

Agregamos escenarios al archivo `belly.feature` y en la terminal compilamos mediante `./gradlew test`.
Esto generara una salida en la terminal, para ver esta salida y agregarla al readme podemos usar esta instruccion `./gradlew.test >> README.md`.
Tendriamos:

```html

> Task :compileJava UP-TO-DATE
> Task :processResources NO-SOURCE
> Task :classes UP-TO-DATE
> Task :compileTestJava UP-TO-DATE
> Task :processTestResources
> Task :testClasses

> Task :test

RunCukesTest > BellyFeature > eaten many cukes and growl STANDARD_OUT
    I'm eating 11 cukes!
    I ate 11 cucumbers.
    I waited 2 hours.

RunCukesTest > BellyFeature > eaten many cukes and growl PASSED

RunCukesTest > BellyFeature > eaten many cukes and not growl STANDARD_OUT
    I'm eating 11 cukes!
    I ate 11 cucumbers.
    I waited 1 hours.

RunCukesTest > BellyFeature > eaten many cukes and not growl PASSED

RunCukesTest > BellyFeature > eaten few cukes and not growl #1 STANDARD_OUT
    I'm eating 10 cukes!
    I ate 10 cucumbers.
    I waited 2 hours.

RunCukesTest > BellyFeature > eaten few cukes and not growl #1 PASSED

RunCukesTest > BellyFeature > eaten few cukes and not growl #2 STANDARD_OUT
    I'm eating 10 cukes!
    I ate 10 cucumbers.
    I waited 1 hours.

RunCukesTest > BellyFeature > eaten few cukes and not growl #2 PASSED

Deprecated Gradle features were used in this build, making it incompatible with Gradle 9.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

For more on this, please refer to https://docs.gradle.org/8.6/userguide/command_line_interface.html#sec:command_line_warnings in the Gradle documentation.

BUILD SUCCESSFUL in 1s
4 actionable tasks: 2 executed, 2 up-to-date
```

Podemos ver que todos los test pasan de acuerdo a las condiciones establecidas.
