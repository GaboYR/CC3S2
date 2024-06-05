package sprint2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordSelectorTest {
    // Test para comprobar que se selecciona una palabra
    @Test
    void testSelectWord() {
        WordSelector wordSelector = new WordSelector();
        String word = wordSelector.selectWord();
        assertNotNull(word);
        assertTrue(word.length() > 0);
    }
    // Test para comprobar que se selecciona una palabra aleatoria
    @Test
    void testSelectRandomWord() {
        WordSelector wordSelector = new WordSelector();
        String word1 = wordSelector.selectWord();
        String word2 = wordSelector.selectWord();
        assertNotEquals(word1, word2);
    }
    // Test para comprobar que se selecciona una palabra de la lista
    @Test
    void testSelectWordFromList() {
        WordSelector wordSelector = new WordSelector();
        String word = wordSelector.selectWord();
        String[] words = {"hola", "adios", "casa", "coche", "perro", "gato", "raton", "elefante", "caballo", "pajaro"};
        boolean found = false;
        for (String w : words) {
            if (w.equals(word)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
}
