# Primera Prueba con JUnit 5
## Objetivo de la actividad

El objetivo de esta actividad es familiarizarse con el uso de JUnit 5 y AssertJ para crear pruebas unitarias
en el desarrollo de software. Se espera que los participantes comprendan cómo escribir pruebas unitarias efectivas para 
garantizar la calidad del código y mejorar la robustez de sus aplicaciones.

## Prerequisitos 🤔

Utiliza el código entregado en el repositorio de apoyo del curso:

https://github.com/kapumota/Actividades-CC3S2/tree/main

## Entregable

Presenta las respuestas y el codigo solicitado en tu repositorio personal de Github del curso.

## Conceptos importantes

🔷 `SUT(sistema bajo prueba)` : entendemos la parte del sistema que se está probando.
Dependiendo del tipo de prueba, el SUT puede tener granularidades muy diferentes, desde una
sola clase hasta toda una aplicación.

🔷 `DOC(Depended On Component)` : es cualquier entidad que sea necesaria para que un SUT
cumpla con sus funciones.
Por lo general, un DOC tiene la misma granularidad que el SUT, por ejemplo, si el SUT es una clase, 
entonces usa otras clases; si es un módulo, entonces colabora con otros módulos. 

DOC y colaboradores son lo mismo en esta clase.

🔷 `Prueba Unitaria (Unit Testing)`: Es un tipo de prueba que se enfoca en probar unidades
individuales de código, como métodos o funciones, de manera aislada del resto del sistema.
Utilizaremos este gráfico en la explicación de la actividad.

🔷 `Framework de Pruebas JUnit 5`: JUnit 5 es un framework de pruebas para Java que proporciona
anotaciones y APIs para escribir y ejecutar pruebas unitarias.

🔷 `AssertJ`: AssertJ es una biblioteca de aserciones en Java que proporciona métodos más
expresivos y legibles para realizar afirmaciones en las pruebas unitarias.
