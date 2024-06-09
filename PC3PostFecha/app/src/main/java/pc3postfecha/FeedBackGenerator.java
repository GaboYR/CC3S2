package pc3postfecha;

public class FeedBackGenerator {
    // Método para generar un feedback
    public String generateFeedback(boolean correct) {
        if (correct) {
            return "Correcto!";
        } else {
            return "Incorrecto!";
        }
    }
    // Método para mostrar el feedback
    public void viewFeedback(String feedback) {
        System.out.println(feedback);
    }
}
