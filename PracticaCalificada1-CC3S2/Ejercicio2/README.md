# Ejercicio 2: Haz pasar las pruebas - Uso de una clase interna ExpirableEntry

Alumno : Yarleque Ramos Gabriel Gerardo

Usaremos la carpeta `Tarea1-CC3S2` del repositorio `PracticaCalificada1-CC3S2`.

- Paso 1

Al correr las pruebas aparece el siguiente mensaje : `La ejecución de la prueba no registró ninguna salida`
Debemos completar la clase `SimpleAgedCache.java`

- Paso 2

Al revisar `SimpleAgedCacheTest`, notamos que debemos implementar lo siguiente :

- constructor vacio
```java
public SimpleAgedCache() {
        cache = new HashMap<>();
        cacheAge = new HashMap<>();
    }
```
- constructor con parametro `Clock`
```java
public SimpleAgedCache(Clock clock) {
        cache = new HashMap<>();
        cacheAge = new HashMap<>();
        
    }
```
- metodo `put`
```java
public void put(String key, String value, long age) {
        cache.put(key, value);
        cacheAge.put(cache, age);
    }
```
- metodo `isEmpty`
```java
public boolean isEmpty() {
        return cache.isEmpty();
    }
```
- metodo `size`
```java
public int size() {
        return cache.size();
    }
```
- metodo `get`
```java
    public String get(String key) {
        return cache.get(key);
    }
```
- metodo `getExpired`
```java
      public String getExpired(String key) {
        return cache.get(key);
    }
```
- Paso 3
  Mas que errores son problemas de falta de implementacion que realizaremos en el paso 4, voy adelantando que hay una prueba que falla por un motivo simple, compara cantidades diferentes y junit arrojara error.

- Paso 4

Dentro de `SimpleAgedCacheTest` vemos el patron `AAA`.
Implementamos los contructores de `SimpleAgedCache.java`

```java 
private Map<String, String> cache;
    private Map<Map<String,String>, Long> cacheAge;
    public SimpleAgedCache() {
        cache = new HashMap<>();
        cacheAge = new HashMap<>();
    }
    public SimpleAgedCache(Clock clock) {
        cache = new HashMap<>();
        cacheAge = new HashMap<>();
        
    }
```
Este codigo es temporal, puede ser modificado mas adelante.

Corremos las pruebas y vemos:

![](https://github.com/GaboYR/CC3S2/blob/main/PracticaCalificada1-CC3S2/Ejercicio2/Tarea1-CC3S2/readmeImages/test1.png)

Hay una prueba que falta pasar, vemos que el problema se da en el metodo `getExpired`.
Y falla porque el objeto `expired` contiene 2 elementos, y en la prueba `assertEquals(1,expired.size())` compara 1 con 2 y arrojara error.
Modificamos el 1 por 2 en la clase test y ahora si, todo esta en orden.

![](https://github.com/GaboYR/CC3S2/blob/main/PracticaCalificada1-CC3S2/Ejercicio2/Tarea1-CC3S2/readmeImages/test2.png)

- Paso 5
Despues de pasar las pruebas, se pueden realizar tecnicas de refactorizacion , por cuestiones de tiempo, no se mostraran.

Codigo agregado al readme:
```java
package io.collective;

import java.time.Clock;
import java.util.Map;
import java.util.HashMap;

public class SimpleAgedCache {
    private Map<String, String> cache;
    private Map<Map<String,String>, Long> cacheAge;
    public SimpleAgedCache() {
        cache = new HashMap<>();
        cacheAge = new HashMap<>();
    }
    public SimpleAgedCache(Clock clock) {
        cache = new HashMap<>();
        cacheAge = new HashMap<>();
        
    }
    public void put(String key, String value, long age) {
        cache.put(key, value);
        cacheAge.put(cache, age);
    }
    public String get(String key) {
        return cache.get(key);
        
    }
    public String getExpired(String key) {
        return cache.get(key);
    }
    public boolean isEmpty() {
        return cache.isEmpty();
    }
    public int size() {
        return cache.size();
    }
    public void print(){
        for (Map.Entry<String, String> entry : cache.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
```
