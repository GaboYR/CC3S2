package sprint2;
public class HintGenerator {
    // Mostrar el tama;o de la palabra
    public String generateHint(String word) {
        return "_ ".repeat(word.length());
    }
    // Mostrar la palabra con las letras acertadas y en su posicion
    public String generateHint(String word, String guess) {
        String hint = "";
        // si la palabra contiene la letra
        for (int i = 0; i < word.length(); i++) {
            if (guess.contains(String.valueOf(word.charAt(i)))) {
                hint += word.charAt(i) + " ";
            } else {
                hint += "_ ";
            }
        }
        return hint;
    }
    // Funcion que muestra las letras que se han acertado
    // pero no estan en su posicion 
    public String showChars (String word, String guess) {
        String chars = "";
        for (int i = 0; i < word.length(); i++) {
            if (guess.contains(String.valueOf(word.charAt(i))) && !guess.equals(String.valueOf(word.charAt(i)))) {
                chars += word.charAt(i);
                chars += " ";
            }
        }
        return chars;
    }
}
