package pc3postfecha;

public interface IHintGenerator {
    // Método para generar una pista
    String generateHint(String word);
    // Metodo para mostrar los caracteres en la posicion correcta
    void viewCharactersInCorrectPosition(String word, String userWord);
    // Si la letra esta en la palabra pero no en la posicion correcta
    void viewCharacterInIncorrectPosition(String word, String userWord);    
}
