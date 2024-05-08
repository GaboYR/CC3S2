package sprint1;

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
