package pc3postfecha;

import java.util.Set;
import java.util.HashSet;
public class HintGenerator implements IHintGenerator {
    @Override
    public String generateHint(String word) {
        //System.out.println("La palabra tiene " + word.length() + " letras.");
        String hint = "";
        for (int i = 0; i < word.length(); i++) {
            hint += "_ ";
        }
        return hint;
    }
    @Override
    public void viewCharactersInCorrectPosition(String word, String userWord) {
        String hint = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == userWord.charAt(i)) {
                hint += word.charAt(i) + " ";
            }
            else {
                hint += "_ ";
            }
        }
        System.out.println(hint);
    }
    @Override
    public void viewCharacterInIncorrectPosition(String word, String userWord) {
        // Por ejm si la palabra es "gato" y el usuario pone "hola"
        // Debe salir en consola :
        // _ _ _ _
        // Letras correctas :  a - o 
        Set<Character> correctLetters = new HashSet<>();
        Set<Character> userCorrectLetters = new HashSet<>();
        for (Character c : word.toCharArray()) {
            correctLetters.add(c);
        }
        for (Character uc : userWord.toCharArray()) {
            if (correctLetters.contains(uc)) {
                userCorrectLetters.add(uc);
            }
        }

        System.out.print("Letras correctas : ");
        for (Character c : userCorrectLetters) {
            System.out.print(c + " ");
        }
        userCorrectLetters.clear();
        System.out.println();
    }
}
