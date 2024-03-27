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

***Leer diapo***

