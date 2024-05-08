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

Se muestra una pregunta hasta que el usuario ingrese un numero.

```java
public class Quiz {
    public ArrayList<Question> Questions;
    int correctas;
    int incorrectas;
    public Quiz() {
        Questions = new ArrayList<Question>();
    }
    public void init() {
        correctas = 0;
        incorrectas = 0;
        Question q1 = new Question("¿Cual es la capital de Francia?",1,"1. Paris\n2. Madrid\n3. Roma\n4. Berlin");
        Questions.add(q1);
        Question q2 = new Question("¿Cual es el rio mas largo del mundo",1,"1. Amazonas\n2. Nilo\n3. Pacifico\n4. Ninguna");
        Questions.add(q2);
        
    }
    public void showQuestions(){
        int cont = 0;
        Scanner scanner = new Scanner(System.in);
        while (cont < Questions.size()) {
            System.out.println(Questions.get(cont).getQuestion());
            System.out.println(Questions.get(cont).getOptions());
            int respuesta = scanner.nextInt();
            if (Questions.get(cont).getAnswer() == respuesta) {
                correctas++;
            } else {
                incorrectas++;
            }
            cont++;
        }
    }
}
```

Salida en consola:
![Salida](//Ejercicio1/images/sprint1p1-1.png)

Para validar la entrada de usuario, agregamos estas lineas 

```java
while (respuesta < 0 && respuesta > 4) {
                System.out.println("Opcion fuera de rango, por favor seleccione una opcion valida");
                respuesta = scanner.nextInt();
            }
```

Para el testeo de numero ingresado por el usuario: 

```java
public class QuestionTest {
    
    public boolean answerInRange1to4(int answer) {
        return answer >= 1 && answer <= 4;
    }
    @Test
    public void testAnswerInRange1to4() {
        assertEquals(answerInRange1to4(1), true);
        assertEquals(answerInRange1to4(2), true);
        assertEquals(answerInRange1to4(3), true);
        assertEquals(answerInRange1to4(4), true);
        assertEquals(answerInRange1to4(0), false);
        assertEquals(answerInRange1to4(5), false);
    }
}
```

Es claro que las dos ultimas fallaran porque deben estar en el rango de 1 a 4.
Salida de pruebas:

![Pruebas](/Ejercicio1/images/sprint1p1-2.png)

### Sprint 2

Logica del juego e implementacion:

Necesitamos dos variables que muestren nuestras respuestas correctas e incorrectas, se agregaran a la clase `Quiz`.
```java
public class Quiz {
    public ArrayList<Question> Questions;
    int correctas;
    int incorrectas;
    public Quiz() {
        Questions = new ArrayList<Question>();
    }
    ...
```

Vista de clases: 

- `Quiz`

```java
public class Quiz {
    public ArrayList<Question> Questions;
    int correctas;
    int incorrectas;
    public Quiz() {
        Questions = new ArrayList<Question>();
    }
    public void init() {
        correctas = 0;
        incorrectas = 0;
        Question q1 = new Question("¿Cual es la capital de Francia?",1,"1. Paris\n2. Madrid\n3. Roma\n4. Berlin");
        Questions.add(q1);
        Question q2 = new Question("¿Cual es el rio mas largo del mundo",1,"1. Amazonas\n2. Nilo\n3. Pacifico\n4. Ninguna");
        Questions.add(q2);
        Question q3 = new Question("Cuanto es 1 + 2",3,"1. 1\n2. 2\n3. 3\n4. 4");
        Questions.add(q3);
    }
    public void showQuestions(){
        int cont = 0;
        Scanner scanner = new Scanner(System.in);
        while (cont < Questions.size()) {
            System.out.println(Questions.get(cont).getQuestion());
            System.out.println(Questions.get(cont).getOptions());
            int respuesta = scanner.nextInt();
            while (respuesta < 0 && respuesta > 4) {
                System.out.println("Opcion fuera de rango, por favor seleccione una opcion valida");
                respuesta = scanner.nextInt();
            }
            if (Questions.get(cont).getAnswer() == respuesta) {
                correctas++;
            } else {
                incorrectas++;
            }
            cont++;
        }
        System.out.println("Respuestas correctas: " + correctas);   
        System.out.println("Respuestas incorrectas: " + incorrectas);
        if (correctas > incorrectas) {
            System.out.println("Felicidades, has ganado!");
        } else {
            System.out.println("Sigue intentando");
        }
    }
}

```

Hemos agregado una pregunta para que se puedan dar los casos de ganar, perder.
Salida en consola :

```html
    Bienvenido al Juego de Trivia!
Responde las siguientes preguntas seleccionando el numero de la opcion correcta
¿Cual es la capital de Francia?
1. Paris
2. Madrid
3. Roma
4. Berlin
1
¿Cual es el rio mas largo del mundo
1. Amazonas
2. Nilo
3. Pacifico
4. Ninguna
2
Cuanto es 1 + 2
1. 1
2. 2
3. 3
4. 4
3
Respuestas correctas: 2
Respuestas incorrectas: 1
Felicidades, has ganado!
```

Testeo en la pregunta 2:

Se agrego una clase Test llamada PuntuacionTest:

```java
public class PuntuacionTest {
    public boolean checkCorrectas(int correctas,int usuario){
        assertEquals(correctas, usuario);
    }
    public boolean checkIncorrectas(int incorrectas, int usuario){
        assertEquals(incorrectas, usuario);
    }
    @Test
    public void checkShowQuestions(){
        assertEquals(checkCorrectas(1,1),true);
        assertEquals(checkIncorrectas(1,1),true);
        assertEquals(checkCorrectas(2,1),false);
        assertEquals(checkIncorrectas(2,1),false);
    }
}
```

Salida :

```hmtl
    
```


### Sprint 3

Mejoras en la interfaz y refinamiento

Lo que haremos sera mostrar al usuario las respuestas correctas y en que se equivoco, de esta forma sabra su respuesta.

Entonces agregamos estas linea a la clase `Quiz`

```java
System.out.println("----------------------------");
        for(Question q : Questions){
            q.showQuestion();
            System.out.println("Respuesta correcta: " + q.getAnswer());
        }
```

Salida en consola :

```html
Bienvenido al Juego de Trivia!
Responde las siguientes preguntas seleccionando el numero de la opcion correcta
¿Cual es la capital de Francia?
1. Paris
2. Madrid
3. Roma
4. Berlin
Ingrese su respuesta: 
1
¿Cual es el rio mas largo del mundo
1. Amazonas
2. Nilo
3. Pacifico
4. Ninguna
Ingrese su respuesta: 
1
Cuanto es 1 + 2
1. 1
2. 2
3. 3
4. 4
Ingrese su respuesta: 
2
Respuestas correctas: 2
Respuestas incorrectas: 1
Felicidades, has ganado!
----------------------------
Respuestas correctas:
¿Cual es la capital de Francia?
1. Paris
2. Madrid
3. Roma
4. Berlin
Respuesta correcta: 1
¿Cual es el rio mas largo del mundo
1. Amazonas
2. Nilo
3. Pacifico
4. Ninguna
Respuesta correcta: 1
Cuanto es 1 + 2
1. 1
2. 2
3. 3
4. 4
Respuesta correcta: 3
```

### Preguntas relacionadas al juego Trivia (4 puntos) 
