package sprint1;
public class WordSelector {
    // Array de palabras
    private final String[] words = {"hola", "adios", "casa", "coche", "perro", "gato", "raton", "elefante", "caballo", "pajaro"};
    // Método para seleccionar una palabra aleatoria
    public String selectWord() {
        int index = (int) (Math.random() * words.length);
        return words[index];
    }
}
