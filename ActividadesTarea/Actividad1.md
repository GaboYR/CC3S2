# Metodologias y Procesos de Desarrollo de Software

## Escenario

XKAPU es una empresa nueva en el mercado y lanzó su producto hace dos años. El producto es amado por los clientes y está ganando popularidad. No se anticipó el nivel de demanda del producto y la arquitectura actual del sistema no puede soportar la creciente demanda.

Para satisfacer la demanda prevista, la empresa necesita rediseñar el sistema y proporcionar exactamente la misma funcionalidad. Por tanto, los requisitos desde la perspectiva del cliente son muy conocidos y no es necesario cambiarlos. También se comprende bien lo que es necesario cambiar en el sistema para satisfacer la creciente demanda.

El producto tiene 4 componentes bastante independientes. Es necesario rediseñar los 4 componentes. De los cuatro, uno de ellos ha causado el mayor dolor y la organización podría beneficiarse enormemente si ese componente pudiera reemplazarse primero con una arquitectura nueva y altamente escalable.
El trabajo de migrar a una nueva plataforma es un trabajo tedioso y el despliegue de un nuevo sistema implicará mucha comunicación externa, gestión de las expectativas del cliente, etc. 

El arquitecto técnico y un director de proyecto trabajarán desde la sede corporativa en Marruecos, pero la mayor parte del equipo que realizará la codificación para la migración estará en Bélgica. El equipo de pruebas también estará en Bélgica.

**¿Qué metodología de desarrollo de software sugeriría para esta situación y por qué?**

`Paso 1`: Comience a analizar el escenario identificando las características de esta situación y especifique
la lógica detrás de la selección de características. Por ejemplo, puede identificar "Necesidades del
usuario desconocidas" como una característica basada en las afirmaciones X, Y y Z del escenario.

`Paso 2:` Seleccione el modelo que mejor se ajuste a las características que identificó en el paso 1.
Justifique su elección basándose en la lógica detrás de su selección. Por ejemplo, se puede decir que, dado que el escenario tiene las características X e Y, los modelos A y B son candidatos potenciales.
Además, como el escenario tiene la característica Z, el modelo A es la mejor opción.

## Solucion

### 1. Analisis del escenario

- `Requisitos del cliente bien definidas`: caracteristica basada en la afirmación de que **los requisitos desde la perspectiva del cliente son muy conocidos y no es necesario cambiarlos**.

- `Rediseño del sistema para alta escalabilidad`: caracteristica basada en que el sistema **no puede soportar la crenciente demanda**

- `Existencia de un componente problemático`: característica basa en la afirmación de que hay 4 componentes independientes y uno de ellos está causando **mucho dolor**

- `Equipo distribuido`: característica basada en la afirmacion de que **el arquitecto técnico esta en Marruecos y gran parte del equipo de codificación está en Bélgica**

- `Comunicación externa`: gestión de las expectativas del cliente

### 2. Seleccion de modelos

Entre los modelos candidatos tenemos los siguientes:

- Modelo Waterfall
- Scrum

**Por que *Waterfall* ?**

- Se adapta bien porque este modelo es rigido en cuanto a cambios,por lo tanto requiere unos requerimientos claros y fijos
- Buena eleccion porque con este modelo tienen una planificaion detallada de cada fase,permitiendo que el rediseño sea claro

- No es buena elección ya que el modelo en cascada no es tan flexible en cuanto a la adaptacion rapida a algun cambio de un componente en específico debido a su naturaleza secuencial, es decir, si aparece posteriormente otro componente o modulo problemático,
no podremos retroceder

- Este modelo no ofrece mecanismos para comunicacion efectiva

- Este modelo no nos brinda herramientas para una comunicación externa, esto generaría incertidumbre al cliente

**Por que *Scrum*?**

- Este modelo es agil por lo tanto da igual si los requisitos están bien definidos o no, despues de todo este modelo se adapta a los cambios

- *Scrum* tiene naturaleza iterativa `(sprint)`, es decir, con *Scrum* se puede rediseñar y mejorar en incrementos sucesivos el sistema

- *Scrum* es un buen canditado pues si hay un componente problemático,entonces se puede priorizar en ese problema en su mejora en un siguiente sprint

- En *scrum* hay coordinaciones diarias y/o semanal para el chequeo de sprints, estas reuniones se pueden hacer de manera virutal por lo tanto se adecua a esta caracteristica

- En *scrum* hay un product Owner que es el intermediario entre el equipo tecnico y el cliente, con esto se permite una comunicacion externa y una garantía de que se cumplen con los requisitos del cliente con cada sprint hecho

Por lo tanto la metodoliga ideal sería *Scrum*.
