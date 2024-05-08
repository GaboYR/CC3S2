package sprint2;

import java.util.ArrayList;
import java.util.Scanner;
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
