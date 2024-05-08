# Ejercicio 1

## Alumno : Yarleque Ramos Gabriel Gerardo

Este README contiene la informacion de los Sprints 1,2 y 3 del ejercicio1.

### Sprint 1

Implementacion de la clase `Question` y `Quiz`.

Clase `Question` : esta contiene una cadena de pregunta, un entero que indica la respuesta a la pregunta y finalmente, las opciones.

```java
public class Question {
    public String pregunta;
    public int respuesta;
    String opciones;
    // Constructor
    public Question(String pregunta, int respuesta, String opciones) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.opciones = opciones;
    }
}
```

Clase `Quiz` : esta contiene un arreglo de `Question` que sera mostrado en consola cuando el usuario empiece a jugar.
**Por el momento se han incluido 2 preguntas para ver el funcionamiento de la aplicacion**

```java
public class Quiz {
    public ArrayList<Question> Questions;
    public void init() {
        Question q1 = new Question("¿Cual es la capital de Francia?",1,"1. Paris\n2. Madrid\n3. Roma\n4. Berlin");
        Questions.add(q1);
        Question q2 = new Question("¿Cual es el rio mas largo del mundo",1,"1. Amazonas\n2. Nilo\n3. Pacifico\n4. Ninguna");
        Questions.add(q2);
    }
    public void showQuestions(){
        int cont = 0;
    }
}
```

