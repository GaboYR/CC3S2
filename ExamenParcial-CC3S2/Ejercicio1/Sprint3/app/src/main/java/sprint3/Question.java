package sprint3;

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
    void showQuestion() {
        System.out.println(pregunta);
        System.out.println(opciones);
    }
    public String getQuestion() {
        return pregunta;
    }
    public String getOptions() {
        return opciones;
    }
    public int getAnswer() {
        return respuesta;
    }

}
