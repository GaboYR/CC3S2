## Dia lunes
`SUT`:  System under test(granularidad)

`DOC` : Depend of component(cualquier cosa para que el `SUT` funcione)

Ambos deben tener la misma granularidad, si el SUT es una clase, se deben usar clases.

Importantes para pruebas.

Forma de trabajar excepciones :

```java
void testException()
{
  assertThatExceptionOfType(MyExceptionClass).
  isThrownBy(() ->
  {
  SUT.someMethod();
  });
}
```

Pruebas parametrizadas

`Accesorio de pruebas` : configura el sistema pra el proceso de prueba, le da todo el codigo necesario para iniciarlo, satisfaciendo asi las precondiciones, permite que sea repetibles, el estado debe ser conocido.

Importante : `@BeforeEach`

Pruebas unitarias --> creacion de nvos objetos(SUT,dobles de prueba), preparación de datos de entrada.
`@BeforeEach` sirve para tener los objetos requeridos, nuevos y listos para usar antes de cada metodo de prueba.
Es un metodo de marcado que se va a ejecutar antes de ejecutar cada metodo de prueba.

`TDD` : construyes la prueba de trabajo

`BDD` : 

## Dia miercoles
`AAA` :
Arrange (tear down)

Act(Ejecucion de los metodos del SUT)

Assert(Verificar)

Palabras clave : despliegue, rollback(planes), cutover , Blue-green deployment,canary release.

Despliegue : el software se pone a disposicion de los usuarios finales, puede encontrar problemas en el entorno.

Rollback : permite retrocedeer a una version anterior de software, reduciendo el impacto de da;o(recuperacion ante fallos)

Cutover : Similar a un backup, es un plan detallado en caso de encontrar un problema en el camino.

Equilibrio de carga.

Despliegue:

El despliegue es el proceso de liberar una nueva versión de tu aplicación de software o código base a un entorno de producción donde los usuarios puedan acceder a ella.
Este proceso involucra tomar tu código, junto con cualquier configuración y recursos necesarios, y hacerlo disponible en el entorno de producción.
Los despliegues pueden ser:
Manuales: Realizados manualmente por ingenieros, a menudo con pasos como revisión de código, pruebas y aprobación antes de enviar los cambios a producción.
Automatizados: Se logran mediante herramientas de automatización y scripts que agilizan el proceso de despliegue, reduciendo la intervención manual y el error humano.
Rollback:

Un rollback es el proceso de revertir un despliegue a una versión anterior si se encuentran problemas en la versión recién desplegada.
Esto podría implicar revertir cambios de código, configuraciones o cambios en la base de datos realizados durante el despliegue.
Los rollbacks son cruciales para minimizar el tiempo de inactividad y el impacto en los usuarios si un nuevo despliegue introduce problemas.
Existen diferentes estrategias de rollback, como:
Rollback a una confirmación de código anterior: Este es el enfoque más simple, pero es posible que no aborde los cambios de configuración o recursos.
Rollback a una imagen de despliegue anterior: Si los despliegues se empaquetan como imágenes, puedes revertir a una imagen previamente probada y funcional.
Rollback de actualización continua: En algunos despliegues, puedes revertir los cambios gradualmente mediante el rollback de instancias individuales de tu aplicación.

Cutover:

Cutover se refiere al acto de cambiar a los usuarios o el tráfico de un sistema o aplicación antiguo a uno nuevo.
Este puede ser un paso crítico en el proceso de despliegue, que requiere una planificación y ejecución cuidadosas para minimizar la interrupción de los usuarios.
Las estrategias de cutover pueden variar según la complejidad del sistema y el impacto en el usuario:
Despliegue Azul/Verde: Esta estrategia implica ejecutar simultáneamente la versión antigua y la nueva de la aplicación (entornos azul y verde). Una vez que se prueba y valida la nueva versión, el tráfico se cambia al entorno verde y el entorno azul se retira.
Cutover continuo: Esta estrategia implica migrar gradualmente a los usuarios o el tráfico al nuevo sistema en fases, minimizando el tiempo de inactividad y el riesgo.
En resumen:

Despliegue: Liberar una nueva versión de tu software a producción.
Rollback: Revertir a una versión anterior si se encuentran problemas después del despliegue.
Cutover: Cambiar a los usuarios o el tráfico de un sistema antiguo a uno nuevo.

***Leer diapo***

